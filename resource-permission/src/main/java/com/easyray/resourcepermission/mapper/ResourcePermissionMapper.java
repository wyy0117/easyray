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

    @Select("select distinct sys_resource_permission.*\n" +
            "from sys_resource_permission,\n" +
            "     sys_user_role,\n" +
            "     sys_user_group_role,\n" +
            "     sys_resource_action\n" +
            "where ((sys_resource_permission.role_id = sys_user_role.role_id and sys_user_role.user_id = #{userId} and scope = ${@com.easyray.baseapi.constant.ActionScopeConstant@GLOBAL})\n" +
            "    or (sys_resource_permission.role_id = sys_user_group_role.role_id and group_id = #{groupId} and\n" +
            "        sys_user_role.user_id = ${userId} and (scope = ${@com.easyray.baseapi.constant.ActionScopeConstant@GROUP} || scope = ${@com.easyray.baseapi.constant.ActionScopeConstant@ENTITY})))\n" +
            "  and sys_resource_action.action = #{action}\n" +
            "    and sys_resource_action.name = #{name}\n" +
            "    and sys_resource_permission.name = #{name}\n" +
            "    and sys_resource_action.bitwise_value & sys_resource_permission.action_ids = sys_resource_action.bitwise_value")
    public List<ResourcePermission> findResourcePermission(long userId, long groupId, String action, String name);
}
