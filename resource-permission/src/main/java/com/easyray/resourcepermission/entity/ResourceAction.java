package com.easyray.resourcepermission.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.easyray.baseapi.entity.PrimeKeyEntity;
import com.wyy.actable.annotation.Column;
import com.wyy.actable.annotation.Index;
import com.wyy.actable.annotation.Table;
import com.wyy.actable.annotation.Unique;

import static com.wyy.actable.constants.MySqlDataType.*;

/**
 * @Date: 20-1-27
 * @Author: wyy
 */
@TableName("sys_resource_action")
@Table(name = "sys_resource_action")
public class ResourceAction extends PrimeKeyEntity<Long> {

    @Index(name = "idx_name_action")
    @Unique(name = "uni_name_action")
    @Column(name = "name", type = VARCHAR, length = 75, nullable = false)
    private String name;

    @Index(name = "idx_name_action")
    @Unique(name = "uni_name_action")
    @Column(name = "action", type = VARCHAR, length = 75, nullable = false)
    private String action;

    @Column(name = "bitwise_value", type = INT, length = 10, nullable = false)
    private int bitwiseValue;

    public ResourceAction() {
    }

    public ResourceAction(Long id) {
        super(id);
    }

    @Override
    public String toString() {
        return "ResourceAction{" +
                ", name='" + name + '\'' +
                ", action='" + action + '\'' +
                ", bitwiseValue=" + bitwiseValue +
                '}';
    }

    public String getName() {
        return name;
    }

    public ResourceAction setName(String name) {
        this.name = name;
        return this;
    }

    public String getAction() {
        return action;
    }

    public ResourceAction setAction(String action) {
        this.action = action;
        return this;
    }

    public int getBitwiseValue() {
        return bitwiseValue;
    }

    public ResourceAction setBitwiseValue(int bitwiseValue) {
        this.bitwiseValue = bitwiseValue;
        return this;
    }
}
