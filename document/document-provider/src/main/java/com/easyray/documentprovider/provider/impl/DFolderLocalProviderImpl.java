package com.easyray.documentprovider.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easyray.documentapi.entity.DFolder;
import com.easyray.documentapi.provider.DFolderLocalProvider;
import com.easyray.documentprovider.mapper.DFolderMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author wyy
 * @since 2020-02_13
 */
@Service
@Component
public class DFolderLocalProviderImpl extends ServiceImpl<DFolderMapper, DFolder> implements DFolderLocalProvider {


}
