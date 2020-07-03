package com.easyray.baseapi.entity;

import com.easyray.baseapi.constant.ColumnNameConstant;
import com.wyy.actable.annotation.Column;
import com.wyy.actable.constants.MySqlDataType;

/**
 * @Date: 2020/7/1
 * @Author: wyy
 */
public abstract class TenantEntity<ID> extends BaseEntity<ID> {
    @Column(name = ColumnNameConstant.tenant_id, type = MySqlDataType.BIGINT, length = 10, nullable = false, defaultValue = "0")
    private long tenantId = 0;

    @Override
    public String toString() {
        return "TenantEntity{" +
                "tenantId=" + tenantId +
                "} " + super.toString();
    }

    public TenantEntity() {
    }

    public TenantEntity(ID id) {
        super(id);
    }

    public long getTenantId() {
        return tenantId;
    }

    public TenantEntity<ID> setTenantId(long tenantId) {
        this.tenantId = tenantId;
        return this;
    }
}
