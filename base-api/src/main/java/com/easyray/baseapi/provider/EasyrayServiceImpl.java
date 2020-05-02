package com.easyray.baseapi.provider;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easyray.baseapi.mapper.EasyrayBaseMapper;
import com.easyray.common.exception.EntityNotExistException;
import com.easyray.common.exception.filter.CustomThrowable;

import java.util.List;

/**
 * @Date: 20-2-12
 * @Author: wyy
 */
public abstract class EasyrayServiceImpl<M extends EasyrayBaseMapper<T>, T> extends ServiceImpl<M, T> {


    /**
     * 根据权限筛选过滤
     *
     * @param queryWrapper
     * @param groupId
     * @param userId
     * @return
     */
    public List<T> filterFindBy(AbstractWrapper queryWrapper, long groupId, long userId) {
        return getBaseMapper().filterFindByQuery(queryWrapper, groupId, userId);
    }

    public T findOneByQueryAndGroupId(AbstractWrapper queryWrapper, Long groupId) throws EntityNotExistException {
        T entity = fetchOneByQueryAndGroupId(queryWrapper, groupId);
        if (entity == null) {
            throw new EntityNotExistException(new CustomThrowable(entity.getClass(), queryWrapper.getCustomSqlSegment() + "group_id: " + groupId));
        }
        return entity;
    }

    public T fetchOneByQueryAndGroupId(AbstractWrapper queryWrapper, Long groupId) {
        return getBaseMapper().fetchOneByQueryAndGroupId(queryWrapper, groupId);
    }


}
