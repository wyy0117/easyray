package com.easyray.systemprovider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easyray.systemapi.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author easyray
 * @since 2020-01-26
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
