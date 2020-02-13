package com.easyray.documentprovider.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easyray.documentapi.entity.DFile;
import com.easyray.documentapi.provider.DFileLocalProvider;
import com.easyray.documentprovider.mapper.DFileMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author wyy
 * @since 2020-02_13
 */
@Service
@Component
public class DFileLocalProviderImpl extends ServiceImpl<DFileMapper, DFile> implements DFileLocalProvider {


}
