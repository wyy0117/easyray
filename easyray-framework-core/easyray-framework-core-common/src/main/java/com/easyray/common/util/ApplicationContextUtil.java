package com.easyray.common.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @Date: 20-2-10
 * @Author: wyy
 */
@Component
public class ApplicationContextUtil implements BeanFactoryPostProcessor {
    private static BeanFactory beanFactory;

    //通过name获取 Bean.
    public static Object getBean(String name) {
        return beanFactory.getBean(name);
    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz) {
        return beanFactory.getBean(clazz);
    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name, Class<T> clazz) {
        return beanFactory.getBean(name, clazz);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        ApplicationContextUtil.beanFactory = beanFactory;
    }
}
