package com.wyy.userservice.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyy.easyry.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wyy
 * @since 2020-01-26
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
