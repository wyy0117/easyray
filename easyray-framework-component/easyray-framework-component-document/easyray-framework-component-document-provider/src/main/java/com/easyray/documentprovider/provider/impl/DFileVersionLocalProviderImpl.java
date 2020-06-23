package com.easyray.documentprovider.provider.impl;

import com.easyray.baseapi.provider.EasyrayServiceImpl;
import com.easyray.documentapi.entity.DFileVersion;
import com.easyray.documentapi.provider.DFileVersionLocalProvider;
import com.easyray.documentprovider.mapper.DFileVersionMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * @author wyy
 * @since 2020-06_18
 */
@DubboService
@Service("dFileVersionLocalProviderImpl")
public class DFileVersionLocalProviderImpl extends EasyrayServiceImpl<DFileVersionMapper, DFileVersion> implements DFileVersionLocalProvider {


}
