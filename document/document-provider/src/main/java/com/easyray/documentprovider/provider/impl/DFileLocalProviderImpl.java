package com.easyray.documentprovider.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.easyray.baseapi.provider.EasyrayServiceImpl;
import com.easyray.documentapi.entity.DFile;
import com.easyray.documentapi.provider.DFileLocalProvider;
import com.easyray.documentprovider.mapper.DFileMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wyy
 * @since 2020-02_13
 */
@Service
@Component
public class DFileLocalProviderImpl extends EasyrayServiceImpl<DFileMapper, DFile> implements DFileLocalProvider {


    @Override
    public IPage<DFile> findByName(IPage<DFile> page, String name, long groupId) {
        return getBaseMapper().fetchByQueryAndGroupId(page, new QueryWrapper<DFile>().like("name", name), groupId);
    }

    @Override
    public IPage<DFile> findByFolderId(IPage<DFile> page, long folderId, long groupId) {
        return getBaseMapper().fetchByQueryAndGroupId(page, new QueryWrapper<DFile>().eq("folder_id", folderId), groupId);
    }

    @Override
    public List<DFile> findByFolderId(long folderId, long groupId) {
        return getBaseMapper().fetchByQueryAndGroupId(new QueryWrapper<DFile>().eq("folder_id", folderId), groupId);
    }
}
