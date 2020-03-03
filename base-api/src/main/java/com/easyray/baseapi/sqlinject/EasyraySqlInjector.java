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
    private FilterFindBy filterFindBy;
    @Autowired
    private FetchOneBy fetchOneBy;
    @Autowired
    private FetchBy fetchBy;

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        methodList.add(filterFindBy);
        methodList.add(fetchOneBy);
        methodList.add(fetchBy);
        return methodList;
    }
}
