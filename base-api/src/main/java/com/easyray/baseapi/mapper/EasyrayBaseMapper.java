package com.easyray.baseapi.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Date: 20-2-13
 * @Author: wyy
 */
public interface EasyrayBaseMapper<T> extends BaseMapper<T> {


    /**
     * 使用sql注入，{@link com.easyray.baseapi.sqlinject.FilterFindBy}
     *
     * @param queryWrapper
     * @param groupId
     * @param userId
     * @return
     */
    List<T> filterFindBy(@Param(Constants.WRAPPER) QueryWrapper<T> queryWrapper, long groupId, long userId);

    /**
     * sql注入 {@link com.easyray.baseapi.sqlinject.FetchOneBy}
     *
     * @param queryWrapper
     * @param groupId
     * @return
     */
    T fetchOneBy(@Param(Constants.WRAPPER) QueryWrapper<T> queryWrapper, Long groupId);

    /**
     * sql注入 {@link com.easyray.baseapi.sqlinject.FetchBy}
     *
     * @param page
     * @param queryWrapper
     * @param groupId
     * @return
     */
    IPage<T> fetchBy(IPage<T> page, @Param(Constants.WRAPPER) QueryWrapper<T> queryWrapper, Long groupId);

    IPage<T> doFetchBy();

}
