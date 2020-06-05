package com.easyray.baseapi.entity;

import com.easyray.baseapi.constant.ColumnNameConstant;
import com.wyy.actable.annotation.Column;

import java.io.Serializable;

import static com.wyy.actable.constants.MySqlDataType.BIGINT;

/**
 * @Date: 20-2-8
 * @Author: wyy
 */
public class PrimeKeyEntity<T> implements Serializable {

    @Column(name = ColumnNameConstant.id, type = BIGINT, length = 20, nullable = false, key = true)
    private T id;

    public PrimeKeyEntity(T id) {
        this.id = id;
    }

    public PrimeKeyEntity() {
    }

    public T getId() {
        return id;
    }

    public PrimeKeyEntity<T> setId(T id) {
        this.id = id;
        return this;
    }
}
