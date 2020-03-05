package com.easyray.idgeneratorprovider.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.easyray.baseapi.provider.EasyrayServiceImpl;
import com.easyray.idgeneratorapi.entity.IdSequence;
import com.easyray.idgeneratorapi.provider.IdSequenceLocalProvider;
import com.easyray.idgeneratorprovider.mapper.IdSequenceMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Date: 20-2-11
 * @Author: wyy
 */
@Component
@Transactional
public class IdSequenceLocalProviderImpl extends EasyrayServiceImpl<IdSequenceMapper, IdSequence> implements IdSequenceLocalProvider {
    @Override
    public IdSequence fetchByClassName(String className) {
        return fetchOneByQueryAndGroupId(new QueryWrapper<IdSequence>().eq("class_name", className), null);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public IdSequence fetchById(long id) {
        return fetchOneByQueryAndGroupId(new QueryWrapper<IdSequence>().eq("id", id), null);
    }
}
