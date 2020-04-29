package com.easyray.documentprovider.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.easyray.baseapi.provider.EasyrayServiceImpl;
import com.easyray.dfsapi.DFSClient;
import com.easyray.documentapi.entity.DFile;
import com.easyray.documentapi.provider.DFileLocalProvider;
import com.easyray.documentprovider.mapper.DFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public IPage<DFile> findByName(IPage<DFile> page, String name, long groupId) {
        return getBaseMapper().fetchByQueryAndGroupId(page, new QueryWrapper<DFile>().lambda().eq(DFile::getName, name), groupId);
    }

    @Override
    public IPage<DFile> findByFolderId(IPage<DFile> page, long folderId, long groupId) {
        return getBaseMapper().fetchByQueryAndGroupId(page, new QueryWrapper<DFile>().lambda().eq(DFile::getFolderId, folderId), groupId);
    }

    @Override
    public List<DFile> findByFolderId(long folderId, long groupId) {
        return getBaseMapper().fetchByQueryAndGroupId(new QueryWrapper<DFile>().lambda().eq(DFile::getFolderId, folderId), groupId);
    }

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        return dfsClient.uploadFile(file);
    }

}
