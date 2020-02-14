package com.easyray.baseapi.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easyray.baseapi.mapper.EasyrayBaseMapper;
import org.apache.ibatis.annotations.Param;

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
    public List<T> filterFindBy(@Param(Constants.WRAPPER) QueryWrapper<T> queryWrapper, long groupId, long userId) {
        return getBaseMapper().filterFindBy(queryWrapper, groupId, userId);
    }
}
