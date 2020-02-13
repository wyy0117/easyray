package com.easyray.documentprovider.provider.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easyray.documentapi.entity.DFile;
import com.easyray.documentapi.provider.DFileCheckPermission;
import com.easyray.documentprovider.mapper.DFileMapper;
import org.springframework.stereotype.Component;

/**
 * @Date: 2020-02_13
 * @Author: wyy
 */
@Component
public class DFileCheckPermissionImpl extends ServiceImpl<DFileMapper, DFile> implements DFileCheckPermission {

}
