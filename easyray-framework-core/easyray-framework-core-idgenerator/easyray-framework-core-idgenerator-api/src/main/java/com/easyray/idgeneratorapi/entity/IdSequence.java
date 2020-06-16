package com.easyray.idgeneratorapi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wyy.actable.annotation.Column;
import com.wyy.actable.annotation.Table;
import com.wyy.actable.annotation.Unique;

import java.io.Serializable;

import static com.wyy.actable.constants.MySqlDataType.BIGINT;
import static com.wyy.actable.constants.MySqlDataType.VARCHAR;

/**
 * @Date: 20-2-4
 * @Author: wyy
 */
@TableName("sys_id_sequence")
@Table(name = "sys_id_sequence")
public class IdSequence implements Serializable {

    @Column(name ="id", type = BIGINT, length = 20, nullable = false, key = true)
    @TableId(type = IdType.INPUT)
    private long id;

    @Unique
    @Column(name = "class_name", type = VARCHAR, length = 75, nullable = false)
    private String className;

    @Column(name = "value", type = BIGINT, length = 20, nullable = false)
    private long value = 1L;

    public IdSequence(long id) {
        this.id = id;
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


    public long getId() {
        return id;
    }

    public IdSequence setId(long id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "IdSequence{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", value=" + value +
                '}';
    }
}
