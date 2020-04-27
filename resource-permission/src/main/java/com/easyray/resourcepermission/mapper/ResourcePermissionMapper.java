package com.easyray.resourcepermission.mapper;

import com.easyray.baseapi.mapper.EasyrayBaseMapper;
import com.easyray.resourcepermission.entity.ResourcePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Date: 20-2-2
 * @Author: wyy
 */
@Mapper
public interface ResourcePermissionMapper extends EasyrayBaseMapper<ResourcePermission> {

    @Select("select resource_permission.* from sys_resource_action ,(\n" +
            "    select sys_resource_permission.*\n" +
            "    from sys_resource_permission,\n" +
            "         sys_user_role,\n" +
            "         sys_user_group_role\n" +
            "    where (sys_resource_permission.role_id = sys_user_role.role_id and sys_user_role.user_id = #{userId} and scope = ${@com.easyray.baseapi.constant.RoleTypeConstant@GLOBAL_ROLE})\n" +
            "       or (sys_resource_permission.role_id = sys_user_group_role.role_id and group_id = #{groupId} and\n" +
            "           sys_user_role.user_id = #{userId} and (scope = ${@com.easyray.baseapi.constant.RoleTypeConstant@GROUP_ROLE} || scope = ${@com.easyray.baseapi.constant.RoleTypeConstant@RANGE_ROLE}))\n" +
            ") as resource_permission\n" +
            "where sys_resource_action.action = #{action}\n" +
            "  and sys_resource_action.name = #{name}\n" +
            "  and sys_resource_action.bitwise_value | resource_permission.action_ids = sys_resource_action.bitwise_value")
    public List<ResourcePermission> havePermission(long userId, long groupId, String action, String name);
}
