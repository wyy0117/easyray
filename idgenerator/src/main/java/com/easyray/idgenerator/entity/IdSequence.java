package com.easyray.idgenerator.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
public class IdSequence {
    @Column(name = "id", type = BIGINT, length = 20, nullable = false, key = true)
    private long id;

    @Unique
    @Column(name = "entity_name", type = VARCHAR, length = 75, nullable = false)
    private String entityName;

    @Column(name = "value", type = BIGINT, length = 20, nullable = false)
    private long value = 1L;

    public IdSequence(long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "IdSequence{" +
                "id=" + id +
                ", entityName='" + entityName + '\'' +
                ", value=" + value +
                '}';
    }
}
