package com.easyray.documentprovider.provider.impl;

import com.easyray.baseapi.provider.EasyrayServiceImpl;
import com.easyray.documentapi.entity.DFileVersion;
import com.easyray.documentapi.provider.DFileVersionCheckPermission;
import com.easyray.documentprovider.mapper.DFileVersionMapper;
import org.springframework.stereotype.Service;

/**
 * @Date: 2020-06_18
 * @Author: wyy
 */
@Service
public class DFileVersionCheckPermissionImpl extends EasyrayServiceImpl<DFileVersionMapper, DFileVersion> implements DFileVersionCheckPermission {

}
