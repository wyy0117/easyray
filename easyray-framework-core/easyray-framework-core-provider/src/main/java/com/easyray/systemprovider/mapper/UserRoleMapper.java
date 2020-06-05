package com.easyray.systemprovider.mapper;

import com.easyray.baseapi.mapper.EasyrayBaseMapper;
import com.easyray.coreapi.entity.Role;
import com.easyray.coreapi.entity.User;
import com.easyray.coreapi.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wyy
 * @since 2020-02_12
 */
@Mapper
public interface UserRoleMapper extends EasyrayBaseMapper<UserRole> {

    @Select("select sys_role.* from sys_role left join sys_user_role on sys_role.id = sys_user_role.role_id where sys_user_role.user_id = #{userId} ")
    public List<Role> findRoleByUserId(long userId);


    @Select("select sys_user.* from sys_user left join sys_user_role on sys_user.id = sys_user_role.user_id where role_id = #{roleId}")
    List<User> findUserByRoleId(long roleId);

}
