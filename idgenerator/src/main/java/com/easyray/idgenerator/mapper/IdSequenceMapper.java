package com.easyray.idgenerator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easyray.idgenerator.entity.IdSequence;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Date: 20-2-4
 * @Author: wyy
 */
@Mapper
public interface IdSequenceMapper extends BaseMapper<IdSequence> {
}
