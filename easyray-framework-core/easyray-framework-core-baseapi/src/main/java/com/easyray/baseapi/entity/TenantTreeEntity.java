package com.easyray.baseapi.entity;

import com.easyray.baseapi.constant.ColumnNameConstant;
import com.wyy.actable.annotation.Column;
import com.wyy.actable.constants.MySqlDataType;

/**
 * @Date: 2020/7/1
 * @Author: wyy
 */
public class TenantTreeEntity<ID> extends TreeEntity<ID> {
    @Column(name = ColumnNameConstant.tenant_id, type = MySqlDataType.BIGINT, length = 10, nullable = false, defaultValue = "0")
    private long tenantId = 0;

    public TenantTreeEntity(ID id) {
        super(id);
    }

    public TenantTreeEntity() {
    }

    @Override
    public String toString() {
        return "TenantTreeEntity{" +
                "tenantId=" + tenantId +
                "} " + super.toString();
    }

    public long getTenantId() {
        return tenantId;
    }

    public TenantTreeEntity<ID> setTenantId(long tenantId) {
        this.tenantId = tenantId;
        return this;
    }
}
