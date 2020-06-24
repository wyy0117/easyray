package com.easyray.baseapi.provider;

/**
 * @Date: 20-1-28
 * @Author: wyy
 */

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;

import java.util.List;

/**
 * service接口的基类,需要检查权限
 */
public interface BaseProvider<T> extends BaseLocalProvider<T> {

    default List<T> filterFindBy(AbstractWrapper queryWrapper, long tenantId, long userId) {
        return getBaseMapper().filterFindByQuery(queryWrapper, tenantId, userId);

    }

}
