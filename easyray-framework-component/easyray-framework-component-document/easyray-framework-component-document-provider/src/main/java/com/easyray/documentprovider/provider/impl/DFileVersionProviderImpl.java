package com.easyray.documentprovider.provider.impl;

import com.easyray.documentapi.provider.DFileVersionProvider;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * @Date: 2020-06_18
 * @Author: wyy
 */
@DubboService
@Service
public class DFileVersionProviderImpl extends DFileVersionLocalProviderImpl implements DFileVersionProvider {

}
