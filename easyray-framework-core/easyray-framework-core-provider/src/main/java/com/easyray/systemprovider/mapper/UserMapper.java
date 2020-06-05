package com.easyray.systemprovider.mapper;

import com.easyray.baseapi.mapper.EasyrayBaseMapper;
import com.easyray.coreapi.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author easyray
 * @since 2020-01-26
 */
@Mapper
public interface UserMapper extends EasyrayBaseMapper<User> {

}
