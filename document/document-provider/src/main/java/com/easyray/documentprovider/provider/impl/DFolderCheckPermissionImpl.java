package com.easyray.documentprovider.provider.impl;

import com.easyray.baseapi.provider.EasyrayServiceImpl;
import com.easyray.documentapi.entity.DFolder;
import com.easyray.documentapi.provider.DFolderCheckPermission;
import com.easyray.documentprovider.mapper.DFolderMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Date: 2020-02_13
 * @Author: wyy
 */
@Component
public class DFolderCheckPermissionImpl extends EasyrayServiceImpl<DFolderMapper, DFolder> implements DFolderCheckPermission {

    @Override
    public void deleteFolder(long id) {

    }

    @Override
    public List<DFolder> getSubFolderList(long parentFolderId) {
        return null;
    }
}
