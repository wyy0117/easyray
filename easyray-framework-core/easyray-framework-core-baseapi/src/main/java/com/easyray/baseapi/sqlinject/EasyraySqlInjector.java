package com.easyray.baseapi.sqlinject;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Date: 20-2-13
 * @Author: wyy
 */
@Component
public class EasyraySqlInjector extends DefaultSqlInjector {

    @Autowired
    private FilterFindByQuery filterFindByQuery;
    @Autowired
    private FetchOneByQueryAndTenantId fetchOneByQueryAndTenantId;
    @Autowired
    private FetchByQuery fetchByQuery;

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        methodList.add(filterFindByQuery);
        methodList.add(fetchOneByQueryAndTenantId);
        methodList.add(fetchByQuery);
        return methodList;
    }
}
