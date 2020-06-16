package com.easyray.baseapi.init;

import com.easyray.common.exception.EasyCustomException;
import com.easyray.common.exception.EntityNotExistException;
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
public class EasyrayInitConfig implements ApplicationRunner {
    private Logger log = LoggerFactory.getLogger(EasyrayInitConfig.class);

    @Autowired
    private ApplicationContext applicationContext;

    public List<IEasyrayInit> getInits() {
        Map<String, IEasyrayInit> iEasyIniterMap = applicationContext.getBeansOfType(IEasyrayInit.class);
        return new ArrayList<>(iEasyIniterMap.values());
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<IEasyrayInit> initList = getInits();
        if (initList.size() > 1) {
            Collections.sort(initList);
        }

        for (IEasyrayInit iEasyrayInit : initList) {
            log.debug("{} initing...", iEasyrayInit);
            try {
                iEasyrayInit.init(args);
            } catch (EntityNotExistException | EasyCustomException e) {
                e.printStackTrace();
                throw new Exception(e);
            }
        }

    }
}
