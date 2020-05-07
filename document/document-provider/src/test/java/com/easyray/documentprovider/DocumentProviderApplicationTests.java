package com.easyray.documentprovider;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easyray.documentapi.entity.DFile;
import com.easyray.documentapi.entity.DFolder;
import com.easyray.documentapi.provider.DFileLocalProvider;
import com.easyray.documentapi.provider.DFolderLocalProvider;
import com.easyray.idgeneratorapi.provider.IdService;
import com.easyray.systemapi.entity.Tenant;
import com.easyray.systemapi.entity.User;
import com.easyray.systemapi.service.TenantLocalProvider;
import com.easyray.systemapi.service.UserLocalProvider;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    private TenantLocalProvider tenantLocalProvider;

    private User user;
    private Tenant tenant;

    @BeforeAll
    void before() {
        user = doAddUser();
        tenant = doAddTenant(user);
    }


    @AfterAll
    void after() {
        tenantLocalProvider.removeById(tenant.getId());
        userLocalProvider.removeById(user.getId());
    }

    @Test
    void addFolderAndFile() {
        DFolder dFolder = doAddFolder();
        for (int i = 0; i < 5; i++) {
            doAddFile(dFolder);
        }

        IPage<DFile> dFileIPage = dFileLocalProvider.findByFolderId(new Page<>(1, 1), dFolder.getId(), tenant.getId());
        assert dFileIPage.getRecords().size() == 1;

        dFileIPage = dFileLocalProvider.findByFolderId(new Page<>(1, 3), dFolder.getId(), tenant.getId());
        assert dFileIPage.getRecords().size() == 3;

        List<DFile> dFileList = dFileLocalProvider.findByFolderId(dFolder.getId(), tenant.getId());
        assert dFileList.size() > 0;

        dFolderLocalProvider.deleteFolder(dFolder.getId());
    }

    @Test
    void uploadFile() throws IOException {
        doUploadFile();
    }

    private void doUploadFile() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("2.jpg");
        String contentType = Files.probeContentType(Paths.get(classPathResource.getURI()));
        MockMultipartFile multipartFile = new MockMultipartFile(classPathResource.getFilename(), classPathResource.getFilename(), contentType, classPathResource.getInputStream());
        String url = dFileLocalProvider.uploadFile(multipartFile);
        System.out.println("url = " + url);
    }

    private DFolder doAddFolder() {
        DFolder dFolder = new DFolder(idService.nextId(DFolder.class.getName()))
                .setName(System.currentTimeMillis() + "")
                .setTenantId(tenant.getId());
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

    private Tenant doAddTenant(User user) {
        Tenant tenant = new Tenant(idService.nextId(Tenant.class.getName()))
                .setName(System.currentTimeMillis() + "");
        tenant.setUserId(user.getId())
                .setFullName(user.getFullName())
                .setCreateDate(new Date());
        tenantLocalProvider.save(tenant);
        return tenant;
    }


    private DFile doAddFile(DFolder folder) {
        DFile dFile = new DFile(idService.nextId(DFile.class.getName()))
                .setTenantId(tenant.getId())
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
