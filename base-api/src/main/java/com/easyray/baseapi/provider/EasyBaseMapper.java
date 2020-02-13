package com.easyray.baseapi.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Date: 20-2-12
 * @Author: wyy
 */
public interface EasyBaseMapper<T> extends BaseMapper<T> {

    List<T> filterFindBy(QueryWrapper<T> queryWrapper);
}
