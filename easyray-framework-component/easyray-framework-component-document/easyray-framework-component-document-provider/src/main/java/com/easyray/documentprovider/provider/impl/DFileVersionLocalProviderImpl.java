package com.easyray.documentprovider.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.easyray.baseapi.provider.EasyrayServiceImpl;
import com.easyray.documentapi.entity.DFileVersion;
import com.easyray.documentapi.provider.DFileVersionLocalProvider;
import com.easyray.documentprovider.mapper.DFileVersionMapper;
import org.springframework.stereotype.Component;

/**
 * @author wyy
 * @since 2020-06_18
 */
@Service
@Component("dFileVersionLocalProviderImpl")
public class DFileVersionLocalProviderImpl extends EasyrayServiceImpl<DFileVersionMapper, DFileVersion> implements DFileVersionLocalProvider {


}
