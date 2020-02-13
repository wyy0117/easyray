package com.easyray.documentprovider.provider.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easyray.documentapi.entity.DFolder;
import com.easyray.documentapi.provider.DFolderCheckPermission;
import com.easyray.documentprovider.mapper.DFolderMapper;
import org.springframework.stereotype.Component;

/**
 * @Date: 2020-02_13
 * @Author: wyy
 */
@Component
public class DFolderCheckPermissionImpl extends ServiceImpl<DFolderMapper, DFolder> implements DFolderCheckPermission {

}
