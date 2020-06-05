package com.easyray.baseapi.sqlinject;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.springframework.stereotype.Component;

/**
 * @Date: 20-2-22
 * @Author: wyy
 */
@Component
public class FetchOneByQueryAndTenantId extends AbstractMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sql = "<script>" +
                "select * from " + tableInfo.getTableName() + " ${ew.customSqlSegment}" +
                "<if test='tenantId != null and tenantId != 0'>" +
                "and tenant_id = #{tenantId}" +
                "</if>" +
                "</script>";
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addSelectMappedStatementForTable(mapperClass, "fetchOneByQueryAndTenantId", sqlSource, tableInfo);
    }
}
