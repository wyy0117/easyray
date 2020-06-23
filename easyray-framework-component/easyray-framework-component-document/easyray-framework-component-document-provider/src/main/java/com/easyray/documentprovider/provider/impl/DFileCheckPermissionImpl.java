package com.easyray.documentprovider.provider.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.easyray.baseapi.provider.EasyrayServiceImpl;
import com.easyray.documentapi.entity.DFile;
import com.easyray.documentapi.entity.DFileVersion;
import com.easyray.documentapi.provider.DFileCheckPermission;
import com.easyray.documentprovider.mapper.DFileMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Date: 2020-02_13
 * @Author: wyy
 */
@Service
public class DFileCheckPermissionImpl extends EasyrayServiceImpl<DFileMapper, DFile> implements DFileCheckPermission {

    @Override
    public IPage<DFile> findByName(IPage<DFile> page, String name, long tenantId) {
        return null;
    }

    @Override
    public IPage<DFile> findByFolderId(IPage<DFile> page, long folderId, long tenantId) {
        return null;
    }

    @Override
    public List<DFile> findByFolderId(long folderId, long tenantId) {
        return null;
    }

    @Override
    public String uploadFile(DFile dFile, MultipartFile multipartFile) throws IOException {
        return null;
    }

    @Override
    public String updateFile(DFile dFile, MultipartFile multipartFile) throws IOException {
        return null;
    }

    @Override
    public String updateFile(DFile dFile, DFileVersion dFileVersion, MultipartFile multipartFile) throws IOException {
        return null;
    }

    @Override
    public String updateFile(DFile dFile, DFileVersion dFileVersion, String version, MultipartFile multipartFile) throws IOException {
        return null;
    }

    @Override
    public String updateFile(DFile dFile, DFileVersion dFileVersion, String version, MultipartFile multipartFile, String changeLog) throws IOException {
        return null;
    }

}
