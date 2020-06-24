package com.easyray.baseapi.provider;

import com.baomidou.mybatisplus.extension.service.IService;
import com.easyray.baseapi.mapper.EasyrayBaseMapper;

/**
 * @Date: 20-1-28
 * @Author: wyy
 */

/**
 * localService接口的基类
 *
 * @param <T> 实体的类型
 */
public interface BaseLocalProvider<T> extends IService<T> {
    @Override
    EasyrayBaseMapper<T> getBaseMapper();
}
