package com.easyray.baseapi.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.easyray.baseapi.mapper.EasyrayBaseMapper;
import com.easyray.common.exception.EasyrayAbstractException;
import com.easyray.common.exception.EntityAddFailedException;
import com.easyray.common.exception.EntityDeleteFailedException;
import com.easyray.common.exception.EntityUpdateFailedException;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Date: 2020/7/1
 * @Author: wyy
 */

/**
 * {@link com.baomidou.mybatisplus.extension.service.IService}
 * @param <Entity>
 */
@Transactional
public interface IEasyrayService<Entity> {

    EasyrayBaseMapper<Entity> getBaseMapper();

    default void add(Entity entity) throws EasyrayAbstractException {
        boolean result = SqlHelper.retBool(getBaseMapper().insert(entity));
        if (!result) {
            throw new EntityAddFailedException(entity);
        }
    }

    default void delete(Serializable id) throws EntityDeleteFailedException {
        boolean result = SqlHelper.retBool(getBaseMapper().deleteById(id));
        if (!result) {
            Class<?> superClassGenericType = ReflectionKit.getSuperClassGenericType(getClass(), 0);
            throw new EntityDeleteFailedException(superClassGenericType, id);
        }
    }

    default void delete(Wrapper<Entity> queryWrapper) throws EntityDeleteFailedException {
        boolean result = SqlHelper.retBool(getBaseMapper().delete(queryWrapper));
        if (!result) {
            Class<?> superClassGenericType = ReflectionKit.getSuperClassGenericType(getClass(), 0);
            throw new EntityDeleteFailedException(superClassGenericType, queryWrapper.getCustomSqlSegment());
        }
    }

    /**
     * 删除（根据ID 批量删除）
     *
     * @param idList 主键ID列表
     */
    default void deleteByIds(Collection<? extends Serializable> idList) throws EntityDeleteFailedException {
        if (!CollectionUtils.isEmpty(idList)) {
            boolean result = SqlHelper.retBool(getBaseMapper().deleteBatchIds(idList));
            if (!result) {
                Class<?> superClassGenericType = ReflectionKit.getSuperClassGenericType(getClass(), 0);
                throw new EntityDeleteFailedException(superClassGenericType, idList);
            }
        }

    }

    default void update(Entity entity) throws EntityUpdateFailedException {
        boolean result = SqlHelper.retBool(getBaseMapper().updateById(entity));
        if (!result) {
            throw new EntityUpdateFailedException(entity);
        }
    }

    default List<Entity> list(Wrapper<Entity> queryWrapper) {
        return getBaseMapper().selectList(queryWrapper);
    }

    /**
     * 查询总记录数
     *
     * @see Wrappers#emptyWrapper()
     */
    default int count() {
        return count(Wrappers.emptyWrapper());
    }

    /**
     * 根据 Wrapper 条件，查询总记录数
     *
     * @param queryWrapper 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     */
    default int count(Wrapper<Entity> queryWrapper) {
        return SqlHelper.retCount(getBaseMapper().selectCount(queryWrapper));
    }

    /**
     * 查询所有
     *
     * @see Wrappers#emptyWrapper()
     */
    default List<Entity> list() {
        return list(Wrappers.emptyWrapper());
    }

}
