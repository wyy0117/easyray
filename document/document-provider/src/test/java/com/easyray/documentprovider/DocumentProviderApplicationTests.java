package com.easyray.documentprovider;

import com.alibaba.dubbo.config.annotation.Reference;
import com.easyray.documentapi.entity.DFile;
import com.easyray.documentapi.entity.DFolder;
import com.easyray.documentapi.provider.DFileLocalProvider;
import com.easyray.documentapi.provider.DFolderLocalProvider;
import com.easyray.idgeneratorapi.provider.IdService;
import com.easyray.systemapi.entity.Group;
import com.easyray.systemapi.entity.User;
import com.easyray.systemapi.service.GroupLocalProvider;
import com.easyray.systemapi.service.UserLocalProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DocumentProviderApplicationTests {

    @Reference
    private IdService idService;
    @Reference
    private UserLocalProvider userLocalProvider;

    @Autowired
    @Qualifier("DFolderLocalProviderImpl")
    private DFolderLocalProvider dFolderLocalProvider;
    @Autowired
    @Qualifier("DFileLocalProviderImpl")
    private DFileLocalProvider dFileLocalProvider;
    @Reference
    private GroupLocalProvider groupLocalProvider;

    private User user;
    private Group group;

    @BeforeAll
    void before() {
        user = userLocalProvider.getById(2);
        group = groupLocalProvider.getById(2);
    }

    @Test
    void addFolderAndFile() {
        DFolder dFolder = doAddFolder();
        for (int i = 0; i < 5; i++) {
            doAddFile(dFolder);
        }
    }

    DFolder doAddFolder() {
        DFolder dFolder = new DFolder(idService.nextId(DFolder.class.getName()))
                .setName(System.currentTimeMillis() + "")
                .setGroupId(group.getId());
        dFolder.setUserId(user.getId())
                .setFullName(user.getFullName())
                .setCreateDate(new Date());
        dFolder.setParentId(0L)
                .setTreePath(dFolder.getId() + "");
        dFolderLocalProvider.save(dFolder);
        System.out.println("dFolder = " + dFolder);
        return dFolder;
    }

    DFile doAddFile(DFolder folder) {
        DFile dFile = new DFile(idService.nextId(DFile.class.getName()))
                .setGroupId(group.getId())
                .setName("123.txt")
                .setExtension("txt")
                .setMediaType("text")
                .setFolderId(folder.getId())
                .setFolderPath(folder.getTreePath());

        dFile.setUserId(user.getId())
                .setFullName(user.getFullName())
                .setCreateDate(new Date());
        dFileLocalProvider.save(dFile);
        return dFile;
    }

}
