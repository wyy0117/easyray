package com.easyray.baseapi.sqlinject;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.easyray.baseapi.constant.ActionScopeConstant;
import com.easyray.baseapi.constant.ResourceActionConstant;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.springframework.stereotype.Component;

/**
 * @Date: 20-2-13
 * @Author: wyy
 */
@Component
public class FilterFindByQuery extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sql = "select " + tableInfo.getTableName() + ".*\n" +
                "from " + tableInfo.getTableName() + "\n" +
                "         inner join (\n" +
                "    select resource_permission.*\n" +
                "    from (select sys_resource_permission.*\n" +
                "          from sys_resource_permission\n" +
                "                   inner join sys_resource_action on sys_resource_permission.name = sys_resource_action.name\n" +
                "          where sys_resource_action.action = '" + ResourceActionConstant.VIEW + "'\n" +
                "            and sys_resource_action.name = '" + modelClass.getName() + "'\n" +
                "            and sys_resource_permission.action_ids & sys_resource_action.bitwise_value =\n" +
                "                sys_resource_action.bitwise_value) as resource_permission\n" +
                "            ,\n" +
                "         (select sys_user_role.user_id, sys_user_role.role_id\n" +
                "          from sys_user\n" +
                "                   inner join sys_user_role on sys_user.id = sys_user_role.user_id\n" +
                "          where sys_user.id = #{userId}) as user_role,\n" +
                "         (\n" +
                "             select sys_user_tenant_role.user_id, sys_user_tenant_role.tenant_id, sys_user_tenant_role.role_id\n" +
                "             from sys_user_tenant_role\n" +
                "                      inner join sys_user\n" +
                "                                 on sys_user_tenant_role.user_id = sys_user.id and sys_user_tenant_role.tenant_id = #{tenantId}\n" +
                "             where sys_user.id = #{userId}\n" +
                "         ) as user_tenant_role\n" +
                "    where (resource_permission.role_id = user_role.role_id and resource_permission.scope = " + ActionScopeConstant.GLOBAL + ")\n" +
                "       or (resource_permission.role_id = user_tenant_role.role_id and\n" +
                "           (resource_permission.scope = " + ActionScopeConstant.TENANT + " or resource_permission.scope = " + ActionScopeConstant.ENTITY + "))\n" +
                "       or (resource_permission.scope = " + ActionScopeConstant.ENTITY + " and owner_id = #{userId})\n" +
                ") as resource_permission on tenant_id = #{tenantId} and ((resource_permission.scope = " + ActionScopeConstant.GLOBAL + ") or (resource_permission.scope = " + ActionScopeConstant.TENANT + ") or\n" +
                "                                              (resource_permission.scope = " + ActionScopeConstant.ENTITY + " and\n" +
                "                                               resource_permission.prim_key = cast(" + tableInfo.getTableName() + ".id as char))) and ${ew.targetSql}";
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addSelectMappedStatementForTable(mapperClass, "filterFindByQuery", sqlSource, tableInfo);
    }
}
