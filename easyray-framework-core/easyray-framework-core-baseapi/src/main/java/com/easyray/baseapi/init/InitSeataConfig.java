package com.easyray.baseapi.init;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.easyray.baseapi.constant.InitOrderConstant;
import com.easyray.common.init.IEasyrayInit;
import io.seata.spring.boot.autoconfigure.properties.SeataProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Configuration;

/**
 * @Date: 2020/7/1
 * @Author: wyy
 */
@Configuration
public class InitSeataConfig implements IEasyrayInit {

    private final Logger logger = LoggerFactory.getLogger(InitSeataConfig.class);

    @Value("${nacos.host}")
    private String nacosHost;
    @Value("${seata.tx-group-value}")
    private String txGroupValue;

    @Autowired
    private SeataProperties seataProperties;

    @Override
    public int getOrder() {
        return InitOrderConstant.INIT_SEATA;
    }

    @Override
    public void init(ApplicationArguments args) throws Exception {
        String dataId = "service.vgroupMapping." + seataProperties.getTxServiceGroup();
        ConfigService configService = NacosFactory.createConfigService(nacosHost);
        String txServiceGroup = seataProperties.getTxServiceGroup();
        logger.debug("init seata tx,dataId:{} , txServiceGroup:{} , txGroupValue:{} ", dataId, txServiceGroup, txGroupValue);
        configService.publishConfig(dataId, txServiceGroup, txGroupValue);
    }
}
