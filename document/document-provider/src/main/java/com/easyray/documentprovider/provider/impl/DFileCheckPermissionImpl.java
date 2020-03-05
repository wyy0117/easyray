package com.easyray.documentprovider.provider.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.easyray.baseapi.provider.EasyrayServiceImpl;
import com.easyray.documentapi.entity.DFile;
import com.easyray.documentapi.provider.DFileCheckPermission;
import com.easyray.documentprovider.mapper.DFileMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Date: 2020-02_13
 * @Author: wyy
 */
@Component
public class DFileCheckPermissionImpl extends EasyrayServiceImpl<DFileMapper, DFile> implements DFileCheckPermission {

    @Override
    public IPage<DFile> findByName(IPage<DFile> page, String name, long groupId) {
        return null;
    }

    @Override
    public IPage<DFile> findByFolderId(IPage<DFile> page, long folderId, long groupId) {
        return null;
    }

    @Override
    public List<DFile> findByFolderId(long folderId, long groupId) {
        return null;
    }
}
