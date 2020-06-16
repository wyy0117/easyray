package com.easyray.idgeneratorprovider.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class IdSequenceLocalProviderImpl extends ServiceImpl<IdSequenceMapper, IdSequence> implements IdSequenceLocalProvider {
    @Override
    public IdSequence fetchByClassName(String className) {
        return getOne(new QueryWrapper<IdSequence>().lambda().eq(IdSequence::getClassName, className));
    }

    /**
     * @param id
     * @return
     */
    @Override
    public IdSequence fetchById(long id) {
        return getOne(new QueryWrapper<IdSequence>().lambda().eq(IdSequence::getId, id));
    }
}
