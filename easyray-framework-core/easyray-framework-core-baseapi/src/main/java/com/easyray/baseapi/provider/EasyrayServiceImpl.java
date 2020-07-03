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
 * @param <Mapper>
 * @param <Entity>
 */
@SuppressWarnings("unchecked")
public abstract class EasyrayServiceImpl<Mapper extends EasyrayBaseMapper<Entity>, Entity> implements IEasyrayService<Entity> {

    @Autowired
    protected Mapper baseMapper;

    public Mapper getBaseMapper() {
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
    public List<Entity> filterFindBy(AbstractWrapper queryWrapper, long tenantId, long userId) {
        return getBaseMapper().filterFindByQuery(queryWrapper, tenantId, userId);
    }

    public Entity findOneByQueryAndTenantId(AbstractWrapper queryWrapper, Long tenantId) throws EntityNotExistException {
        Entity entity = fetchOneByQueryAndTenantId(queryWrapper, tenantId);
        if (entity == null) {
            ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
            // 获取第一个类型参数的真实类型
            Class clazz = (Class) pt.getActualTypeArguments()[1];
            throw new EntityNotExistException(new CustomThrowable(clazz, queryWrapper.getCustomSqlSegment() + ",tenant_id: " + tenantId));
        }
        return entity;
    }

    public Entity fetchOneByQueryAndTenantId(AbstractWrapper queryWrapper, Long tenantId) {
        return getBaseMapper().fetchOneByQueryAndTenantId(queryWrapper, tenantId);
    }

    public Entity findOneByQuery(AbstractWrapper queryWrapper) throws EntityNotExistException {
        return findOneByQueryAndTenantId(queryWrapper, null);
    }

    public Entity fetchOneByQuery(AbstractWrapper queryWrapper) {
        return fetchOneByQueryAndTenantId(queryWrapper, null);
    }

}
