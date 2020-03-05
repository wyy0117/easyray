package com.easyray.documentprovider;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easyray.documentapi.entity.DFile;
import com.easyray.documentapi.entity.DFolder;
import com.easyray.documentapi.provider.DFileLocalProvider;
import com.easyray.documentapi.provider.DFolderLocalProvider;
import com.easyray.idgeneratorapi.provider.IdService;
import com.easyray.systemapi.entity.Group;
import com.easyray.systemapi.entity.User;
import com.easyray.systemapi.service.GroupLocalProvider;
import com.easyray.systemapi.service.UserLocalProvider;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
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
        user = doAddUser();
        group = doAddGroup(user);
    }


    @AfterAll
    void after() {
        groupLocalProvider.removeById(group.getId());
        userLocalProvider.removeById(user.getId());
    }

    @Test
    void addFolderAndFile() {
        DFolder dFolder = doAddFolder();
        for (int i = 0; i < 5; i++) {
            doAddFile(dFolder);
        }

        IPage<DFile> dFileIPage = dFileLocalProvider.findByFolderId(new Page<>(1, 1), dFolder.getId(), group.getId());
        assert dFileIPage.getRecords().size() == 1;

        dFileIPage = dFileLocalProvider.findByFolderId(new Page<>(1, 3), dFolder.getId(), group.getId());
        assert dFileIPage.getRecords().size() == 3;

        List<DFile> dFileList = dFileLocalProvider.findByFolderId(dFolder.getId(), group.getId());
        assert dFileList.size() > 0;

        dFolderLocalProvider.deleteFolder(dFolder.getId());
    }

    private DFolder doAddFolder() {
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

    private User doAddUser() {
        User user = new User(idService.nextId(User.class.getName()))
                .setUsername(System.currentTimeMillis() + "")
                .setPassword("test");
        user.setUserId(user.getId())
                .setFullName("test")
                .setCreateDate(new Date());
        userLocalProvider.save(user);
        return user;
    }

    private Group doAddGroup(User user) {
        Group group = new Group(idService.nextId(Group.class.getName()))
                .setName(System.currentTimeMillis() + "");
        group.setUserId(user.getId())
                .setFullName(user.getFullName())
                .setCreateDate(new Date());
        groupLocalProvider.save(group);
        return group;
    }


    private DFile doAddFile(DFolder folder) {
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
