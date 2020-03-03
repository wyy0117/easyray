package com.easyray.baseapi.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easyray.baseapi.mapper.EasyrayBaseMapper;
import com.easyray.common.exception.EntityNotExistException;
import com.easyray.common.exception.filter.CustomThrowable;

import java.util.List;

/**
 * @Date: 20-2-12
 * @Author: wyy
 */
public class EasyrayServiceImpl<M extends EasyrayBaseMapper<T>, T> extends ServiceImpl<M, T> {


    /**
     * 根据权限筛选过滤
     *
     * @param queryWrapper
     * @param groupId
     * @param userId
     * @return
     */
    public List<T> filterFindBy(QueryWrapper<T> queryWrapper, long groupId, long userId) {
        return getBaseMapper().filterFindBy(queryWrapper, groupId, userId);
    }

    public T findBy(QueryWrapper<T> queryWrapper, Long groupId) throws EntityNotExistException {
        T entity = fetchBy(queryWrapper, groupId);
        if (entity == null) {
            throw new EntityNotExistException(new CustomThrowable(entity.getClass(), queryWrapper.getCustomSqlSegment() + "groupId: " + groupId));
        }
        return entity;
    }

    public T fetchBy(QueryWrapper<T> queryWrapper, Long groupId) {
        return getBaseMapper().fetchOneBy(queryWrapper, groupId);
    }


}
