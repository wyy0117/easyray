package com.easyray.baseapi.init;

import com.easyray.baseapi.constant.InitOrderConstant;
import com.wyy.actable.service.SysMysqlCreateTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

/**
 * @Date: 20-2-8
 * @Author: wyy
 */
@Component
public class InitCreateTable implements IEasyInit {

    @Autowired
    private SysMysqlCreateTableService sysMysqlCreateTableService;

    @Override
    public int getOrder() {
        return InitOrderConstant.INIT_CREATE_TABLE;
    }

    @Override
    public void init(ApplicationArguments args) throws Exception {
//        sysMysqlCreateTableService.createMysqlTable();
    }
}
