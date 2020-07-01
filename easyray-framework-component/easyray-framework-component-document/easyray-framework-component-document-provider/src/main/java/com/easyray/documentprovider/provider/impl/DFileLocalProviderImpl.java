package com.easyray.documentprovider.provider.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.easyray.baseapi.provider.EasyrayServiceImpl;
import com.easyray.common.exception.EasyrayAbstractException;
import com.easyray.dfsapi.DFSClient;
import com.easyray.documentapi.entity.DFile;
import com.easyray.documentapi.entity.DFileVersion;
import com.easyray.documentapi.provider.DFileLocalProvider;
import com.easyray.documentapi.util.FileVersionUtil;
import com.easyray.documentprovider.mapper.DFileMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author wyy
 * @since 2020-02_13
 */
@DubboService
@Service
public class DFileLocalProviderImpl extends EasyrayServiceImpl<DFileMapper, DFile> implements DFileLocalProvider {

    @Autowired
    private DFSClient dfsClient;

    @Autowired
    @Qualifier("dFileVersionLocalProviderImpl")
    private DFileVersionLocalProviderImpl dFileVersionLocalProviderImpl;

    @Override
    public void add(DFile entity) throws EasyrayAbstractException {
        getBaseMapper().insert(entity);
        DFileVersion dFileVersion = new DFileVersion(entity);

        dFileVersionLocalProviderImpl.add(dFileVersion);
    }

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
    public String uploadFile(DFile dFile, MultipartFile multipartFile) throws IOException, EasyrayAbstractException {
        return updateFile(dFile, new DFileVersion(dFile), multipartFile);
    }

    @Override
    public String updateFile(DFile dFile, MultipartFile multipartFile) throws IOException, EasyrayAbstractException {
        return updateFile(dFile, new DFileVersion(dFile), multipartFile);
    }

    @Override
    public String updateFile(DFile dFile, DFileVersion dFileVersion, MultipartFile multipartFile) throws IOException, EasyrayAbstractException {

        return updateFile(dFile, dFileVersion, FileVersionUtil.nextVersion(dFile.getVersion()), multipartFile);
    }


    @Override
    public String updateFile(DFile dFile, DFileVersion dFileVersion, String version, MultipartFile multipartFile) throws IOException, EasyrayAbstractException {
        return updateFile(dFile, dFileVersion, version, multipartFile, "");
    }

    @Override
    public String updateFile(DFile dFile, DFileVersion dFileVersion, String version, MultipartFile multipartFile, String changeLog) throws IOException, EasyrayAbstractException {
        String url = dfsClient.uploadFile(multipartFile);
        dFile.setUrl(url);
        dFile.setVersion(version);
        update(dFile);

        dFileVersion.setUrl(url);
        dFileVersion.setVersion(version);
        dFileVersion.setChangeLog(changeLog);
        dFileVersionLocalProviderImpl.add(dFileVersion);
        return url;
    }

}
