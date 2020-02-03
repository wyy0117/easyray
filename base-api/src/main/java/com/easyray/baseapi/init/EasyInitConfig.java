package com.easyray.baseapi.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Date: 20-2-2
 * @Author: wyy
 */
@Configuration
public class EasyInitConfig implements ApplicationRunner {
    private Logger log = LoggerFactory.getLogger(EasyInitConfig.class);

    @Autowired
    private ApplicationContext applicationContext;

    public List<IEasyIniter> getIniters() {
        Map<String, IEasyIniter> iEasyIniterMap = applicationContext.getBeansOfType(IEasyIniter.class);
        return new ArrayList<>(iEasyIniterMap.values());
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<IEasyIniter> initerList = getIniters();
        if (initerList.size() > 1) {
            Collections.sort(initerList);
        }

        for (IEasyIniter iEasyIniter : initerList) {
            log.debug("{} initing...", iEasyIniter);
            iEasyIniter.init(args);
        }

    }
}
