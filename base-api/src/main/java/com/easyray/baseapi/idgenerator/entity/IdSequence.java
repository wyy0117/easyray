package com.easyray.baseapi.idgenerator.entity;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @Date: 20-2-4
 * @Author: wyy
 */
@TableName("sys_id_sequence")
public class IdSequence {
    private long id;
    private String entityName;
    private long value = 1L;

    @Override
    public String toString() {
        return "IdSequence{" +
                "id=" + id +
                ", entityName='" + entityName + '\'' +
                ", value=" + value +
                '}';
    }

    public long getId() {
        return id;
    }

    public IdSequence setId(long id) {
        this.id = id;
        return this;
    }

    public String getEntityName() {
        return entityName;
    }

    public IdSequence setEntityName(String entityName) {
        this.entityName = entityName;
        return this;
    }

    public long getValue() {
        return value;
    }

    public IdSequence setValue(long value) {
        this.value = value;
        return this;
    }
}
