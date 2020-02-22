package com.easyray.documentprovider.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.easyray.baseapi.provider.EasyrayServiceImpl;
import com.easyray.documentapi.entity.DFolder;
import com.easyray.documentapi.provider.DFolderLocalProvider;
import com.easyray.documentprovider.mapper.DFolderMapper;
import org.springframework.stereotype.Component;

/**
 * @author wyy
 * @since 2020-02_13
 */
@Service
@Component
public class DFolderLocalProviderImpl extends EasyrayServiceImpl<DFolderMapper, DFolder> implements DFolderLocalProvider {


}
