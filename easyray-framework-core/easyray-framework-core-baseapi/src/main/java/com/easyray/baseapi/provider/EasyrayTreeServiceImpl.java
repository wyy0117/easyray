package com.easyray.baseapi.provider;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.easyray.baseapi.entity.TreeEntity;
import com.easyray.baseapi.mapper.EasyrayTreeBaseMapper;

import java.io.Serializable;
import java.util.List;

/**
 * @Date: 2020/7/3
 * @Author: wyy
 */
public class EasyrayTreeServiceImpl<Mapper extends EasyrayTreeBaseMapper<Entity>, Entity extends TreeEntity<? extends Serializable>> extends EasyrayServiceImpl<Mapper, Entity> {

    public Entity parent(Entity entity) {
        return getBaseMapper().parent(entity);
    }

    public List<Entity> subNode(Entity entity) {
        return getBaseMapper().subNode(entity);
    }

    public IPage<Entity> subNode(IPage<Entity> page, Entity entity) {
        return getBaseMapper().subNode(page, entity);
    }

}
