package com.easyray.documentprovider.provider.impl;

import com.easyray.documentapi.provider.DFileProvider;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * @Date: 2020-02_13
 * @Author: wyy
 */
@DubboService
@Service
public class DFileProviderImpl extends DFileLocalProviderImpl implements DFileProvider {

}
