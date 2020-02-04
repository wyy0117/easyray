package com.easyray.baseapi.idgenerator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.easyray.baseapi.idgenerator.entity.IdSequence;

/**
 * @Date: 20-2-4
 * @Author: wyy
 */
public interface IdSequenceLocalService extends IService<IdSequence> {

    public IdSequence fetchByEntityName(String entityName);
}
