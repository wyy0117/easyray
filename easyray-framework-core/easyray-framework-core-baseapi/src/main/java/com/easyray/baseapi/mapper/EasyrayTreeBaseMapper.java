package com.easyray.baseapi.mapper;

/**
 * @Date: 2020/7/3
 * @Author: wyy
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.easyray.baseapi.entity.TreeEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 树结构的baseMapper层
 *
 * @param <Entity>
 */
public interface EasyrayTreeBaseMapper<Entity extends TreeEntity<? extends Serializable>> extends EasyrayBaseMapper<Entity> {

    default Entity parent(Entity entity) {
        return selectById(entity.getParentId());
    }

    default List<Entity> subNode(Entity entity) {
        return selectList(new QueryWrapper<Entity>().lambda().eq(e -> e.getParentId(), entity.getId()));
    }

    default IPage<Entity> subNode(IPage<Entity> page, Entity entity) {
        return selectPage(page, new QueryWrapper<Entity>().lambda().eq(e -> e.getParentId(), entity.getId()));
    }

}
