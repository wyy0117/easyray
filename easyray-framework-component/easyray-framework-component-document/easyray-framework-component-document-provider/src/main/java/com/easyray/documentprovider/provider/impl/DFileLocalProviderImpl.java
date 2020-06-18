package com.easyray.documentprovider.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.easyray.baseapi.provider.EasyrayServiceImpl;
import com.easyray.dfsapi.DFSClient;
import com.easyray.documentapi.entity.DFile;
import com.easyray.documentapi.entity.DFileVersion;
import com.easyray.documentapi.provider.DFileLocalProvider;
import com.easyray.documentprovider.mapper.DFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author wyy
 * @since 2020-02_13
 */
@Service
@Component
public class DFileLocalProviderImpl extends EasyrayServiceImpl<DFileMapper, DFile> implements DFileLocalProvider {

    @Autowired
    private DFSClient dfsClient;

    @Autowired
    @Qualifier("dFileVersionLocalProviderImpl")
    private DFileVersionLocalProviderImpl dFileVersionLocalProviderImpl;

    @Override
    public IPage<DFile> findByName(IPage<DFile> page, String name, long tenantId) {
        return getBaseMapper().fetchByQueryAndTenantId(page, new QueryWrapper<DFile>().lambda().eq(DFile::getName, name), tenantId);
    }

    @Override
    public IPage<DFile> findByFolderId(IPage<DFile> page, long folderId, long tenantId) {
        return getBaseMapper().fetchByQueryAndTenantId(page, new QueryWrapper<DFile>().lambda().eq(DFile::getFolderId, folderId), tenantId);
    }

    @Override
    public List<DFile> findByFolderId(long folderId, long tenantId) {
        return getBaseMapper().fetchByQueryAndTenantId(new QueryWrapper<DFile>().lambda().eq(DFile::getFolderId, folderId), tenantId);
    }

    @Override
    public String uploadFile(DFile dFile, MultipartFile multipartFile) throws IOException {
        String url = dfsClient.uploadFile(multipartFile);
        dFile.setUrl(url);
        save(dFile);
        return url;
    }

    @Override
    public String updateFile(DFile dFile, DFileVersion dFileVersion, MultipartFile multipartFile) throws IOException {
        String url = dfsClient.uploadFile(multipartFile);
        dFile.setUrl(url);
        dFileVersion.setUrl(url);
        saveOrUpdate(dFile);
        dFileVersionLocalProviderImpl.save(dFileVersion);
        return url;
    }

}
