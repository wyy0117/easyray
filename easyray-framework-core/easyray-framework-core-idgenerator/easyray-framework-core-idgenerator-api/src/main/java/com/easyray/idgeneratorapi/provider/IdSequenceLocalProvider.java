package com.easyray.idgeneratorapi.provider;

import com.baomidou.mybatisplus.extension.service.IService;
import com.easyray.idgeneratorapi.entity.IdSequence;

/**
 * @Date: 20-2-11
 * @Author: wyy
 */
public interface IdSequenceLocalProvider extends IService<IdSequence> {

    public IdSequence fetchByClassName(String className);

    public IdSequence fetchById(long id);
}
