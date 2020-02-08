package com.easyray.idgenerator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easyray.idgenerator.entity.IdSequence;
import com.easyray.idgenerator.mapper.IdSequenceMapper;
import com.easyray.idgenerator.service.IdSequenceLocalService;
import org.springframework.stereotype.Component;

/**
 * @Date: 20-2-4
 * @Author: wyy
 */
@Component
public class IdSequenceLocalServiceImpl extends ServiceImpl<IdSequenceMapper, IdSequence> implements IdSequenceLocalService {
    @Override
    public IdSequence fetchByEntityName(String entityName) {
        return getOne(new QueryWrapper<IdSequence>().eq("entity_name", entityName), false);
    }
}
