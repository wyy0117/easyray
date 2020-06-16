package com.easyray.baseapi.sqlinject;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.easyray.baseapi.constant.ActionScopeConstant;
import com.easyray.baseapi.constant.ColumnNameConstant;
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

        String sql = "<script>" +
                "select " + tableInfo.getTableName() + ".*\n" +
                "from " + tableInfo.getTableName() + ",\n" +
                "     (\n" +
                "         select sys_resource_permission.*\n" +
                "         from sys_resource_permission\n" +
                "                  inner join sys_resource_action\n" +
                "                             on sys_resource_permission." + ColumnNameConstant.name + " = sys_resource_action." + ColumnNameConstant.name + "\n" +
                "         where sys_resource_action.action = '" + ResourceActionConstant.VIEW + "'\n" +
                "           and sys_resource_action." + ColumnNameConstant.name + " = '" + modelClass.getName() + "'\n" +
                "           and sys_resource_permission." + ColumnNameConstant.action_ids + " &amp; sys_resource_action." + ColumnNameConstant.bitwise_value + " =\n" +
                "               sys_resource_action.bitwise_value\n" +
                "           and ((" + ColumnNameConstant.role_id + " in (select sys_user_role." + ColumnNameConstant.role_id + " from sys_user_role where " + ColumnNameConstant.user_id + " = #{userId}) and " + ColumnNameConstant.scope + " = " + ActionScopeConstant.GLOBAL + ") or\n" +
                "                (" + ColumnNameConstant.role_id + " in\n" +
                "                 (select sys_user_tenant_role." + ColumnNameConstant.role_id + "\n" +
                "                  from sys_user_tenant_role\n" +
                "                  where sys_user_tenant_role." + ColumnNameConstant.user_id + " = #{userId}\n" +
                "                    and " + ColumnNameConstant.tenant_id + " = #{tenantId}) and\n" +
                "                 (" + ColumnNameConstant.scope + " = " + ActionScopeConstant.TENANT + " or " + ColumnNameConstant.scope + " = " + ActionScopeConstant.ENTITY + ")))) as resource_permission\n" +
                "where (sys_team." + ColumnNameConstant.user_id + " = #{userId}\n" +
                "   or (resource_permission." + ColumnNameConstant.scope + " = " + ActionScopeConstant.GLOBAL + " or (resource_permission." + ColumnNameConstant.scope + " = " + ActionScopeConstant.TENANT + " and " + ColumnNameConstant.tenant_id + " = #{tenantId}))\n" +
                "   or (resource_permission.prim_key = cast(" + tableInfo.getTableName() + ".id as char) and " + ColumnNameConstant.scope + " = " + ActionScopeConstant.ENTITY + "))\n" +
                "<if test='ew!=null'>" +
                "or ${ew.targetSql}" +
                "</if>" +
                "</script>";
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addSelectMappedStatementForTable(mapperClass, "filterFindByQuery", sqlSource, tableInfo);
    }
}
