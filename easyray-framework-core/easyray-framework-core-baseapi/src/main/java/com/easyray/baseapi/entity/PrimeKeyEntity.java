package com.easyray.baseapi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.easyray.baseapi.constant.ColumnNameConstant;
import com.wyy.actable.annotation.Column;

import java.io.Serializable;

import static com.wyy.actable.constants.MySqlDataType.BIGINT;

/**
 * @Date: 20-2-8
 * @Author: wyy
 */
public abstract class PrimeKeyEntity<ID> implements Serializable {

    @Column(name = ColumnNameConstant.id, type = BIGINT, length = 20, nullable = false, key = true)
    @TableId(type = IdType.INPUT)
    private ID id;

    public PrimeKeyEntity(ID id) {
        this.id = id;
    }

    public PrimeKeyEntity() {
    }

    public ID getId() {
        return id;
    }

    public PrimeKeyEntity<ID> setId(ID id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "PrimeKeyEntity{" +
                "id=" + id +
                '}';
    }
}
