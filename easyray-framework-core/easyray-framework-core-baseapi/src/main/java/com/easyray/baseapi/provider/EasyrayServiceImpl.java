package com.easyray.baseapi.provider;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easyray.baseapi.mapper.EasyrayBaseMapper;
import com.easyray.baseapi.service.IEasyrayService;
import com.easyray.common.exception.EntityNotExistException;
import com.easyray.common.exception.filter.CustomThrowable;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @Date: 20-2-12
 * @Author: wyy
 */

/**
 * {@link ServiceImpl}
 * @param <M>
 * @param <T>
 */
@SuppressWarnings("unchecked")
public abstract class EasyrayServiceImpl<M extends EasyrayBaseMapper<T>, T> implements IEasyrayService<T> {

    @Autowired
    protected M baseMapper;

    public M getBaseMapper() {
        return baseMapper;
    }

    /**
     * 根据权限筛选过滤
     *
     * @param queryWrapper
     * @param tenantId
     * @param userId
     * @return
     */
    public List<T> filterFindBy(AbstractWrapper queryWrapper, long tenantId, long userId) {
        return getBaseMapper().filterFindByQuery(queryWrapper, tenantId, userId);
    }

    public T findOneByQueryAndTenantId(AbstractWrapper queryWrapper, Long tenantId) throws EntityNotExistException {
        T entity = fetchOneByQueryAndTenantId(queryWrapper, tenantId);
        if (entity == null) {
            ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
            // 获取第一个类型参数的真实类型
            Class clazz = (Class) pt.getActualTypeArguments()[1];
            throw new EntityNotExistException(new CustomThrowable(clazz, queryWrapper.getCustomSqlSegment() + ",tenant_id: " + tenantId));
        }
        return entity;
    }

    public T fetchOneByQueryAndTenantId(AbstractWrapper queryWrapper, Long tenantId) {
        return getBaseMapper().fetchOneByQueryAndTenantId(queryWrapper, tenantId);
    }

    public T findOneByQuery(AbstractWrapper queryWrapper) throws EntityNotExistException {
        return findOneByQueryAndTenantId(queryWrapper, null);
    }

    public T fetchOneByQuery(AbstractWrapper queryWrapper) {
        return fetchOneByQueryAndTenantId(queryWrapper, null);
    }

}
