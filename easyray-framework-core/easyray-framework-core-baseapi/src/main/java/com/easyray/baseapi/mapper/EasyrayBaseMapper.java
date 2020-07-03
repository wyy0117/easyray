package com.easyray.baseapi.mapper;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.easyray.baseapi.constant.ColumnNameConstant;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Date: 20-2-13
 * @Author: wyy
 */
public interface EasyrayBaseMapper<Entity> extends BaseMapper<Entity> {

    /**
     * 使用sql注入，{@link com.easyray.baseapi.sqlinject.FilterFindByQuery}
     *
     * @param queryWrapper
     * @param tenantId
     * @param userId
     * @return
     */
    List<Entity> filterFindByQuery(@Param(Constants.WRAPPER) AbstractWrapper queryWrapper, long tenantId, long userId);

    /**
     * 使用sql注入，{@link com.easyray.baseapi.sqlinject.FilterFindByQuery}
     *
     * @param page
     * @param queryWrapper
     * @param tenantId
     * @param userId
     * @return
     */
    IPage<Entity> filterFindByQuery(IPage<Entity> page, @Param(Constants.WRAPPER) AbstractWrapper queryWrapper, long tenantId, long userId);


    /**
     * sql注入 {@link com.easyray.baseapi.sqlinject.FetchOneByQueryAndTenantId}
     *
     * @param queryWrapper
     * @param tenantId
     * @return
     */
    Entity fetchOneByQueryAndTenantId(@Param(Constants.WRAPPER) AbstractWrapper queryWrapper, Long tenantId);


    default IPage<Entity> fetchByQueryAndTenantId(IPage<Entity> page, @Param(Constants.WRAPPER) AbstractWrapper queryWrapper, Long tenantId) {

        assert page != null;

        if (tenantId != null) {
            queryWrapper.eq(ColumnNameConstant.tenant_id, tenantId);
        }
        return fetchByQuery(page, queryWrapper);
    }

    default List<Entity> fetchByQueryAndTenantId(@Param(Constants.WRAPPER) AbstractWrapper queryWrapper, Long tenantId) {
        if (tenantId != null) {
            queryWrapper.eq(ColumnNameConstant.tenant_id, tenantId);
        }
        return fetchByQuery(queryWrapper);
    }

    /**
     * sql注入 {@link com.easyray.baseapi.sqlinject.FetchByQuery}
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<Entity> fetchByQuery(IPage<Entity> page, @Param(Constants.WRAPPER) AbstractWrapper queryWrapper);

    /**
     * sql注入 {@link com.easyray.baseapi.sqlinject.FetchByQuery}
     *
     * @param queryWrapper
     * @return
     */
    List<Entity> fetchByQuery(@Param(Constants.WRAPPER) AbstractWrapper queryWrapper);

}
