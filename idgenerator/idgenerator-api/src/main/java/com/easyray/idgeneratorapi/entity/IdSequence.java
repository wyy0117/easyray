package com.easyray.idgeneratorapi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.easyray.baseapi.entity.PrimeKeyEntity;
import com.wyy.actable.annotation.Column;
import com.wyy.actable.annotation.Table;
import com.wyy.actable.annotation.Unique;

import static com.wyy.actable.constants.MySqlDataType.BIGINT;
import static com.wyy.actable.constants.MySqlDataType.VARCHAR;

/**
 * @Date: 20-2-4
 * @Author: wyy
 */
@TableName("sys_id_sequence")
@Table(name = "sys_id_sequence")
public class IdSequence extends PrimeKeyEntity<Long> {
    @Unique
    @Column(name = "class_name", type = VARCHAR, length = 75, nullable = false)
    private String className;

    @Column(name = "value", type = BIGINT, length = 20, nullable = false)
    private long value = 1L;

    public IdSequence() {
    }

    public IdSequence(long id) {
        super(id);
    }

    public String getClassName() {
        return className;
    }

    public IdSequence setClassName(String className) {
        this.className = className;
        return this;
    }

    public long getValue() {
        return value;
    }

    public IdSequence setValue(long value) {
        this.value = value;
        return this;
    }

    @Override
    public String toString() {
        return "IdSequence{" +
                "className='" + className + '\'' +
                ", value=" + value +
                "} " + super.toString();
    }
}
