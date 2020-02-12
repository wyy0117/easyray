package com.easyray.systemapi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.easyray.baseapi.entity.BaseEntity;
import com.wyy.actable.annotation.Column;
import com.wyy.actable.annotation.Table;

import static com.wyy.actable.constants.MySqlDataType.VARCHAR;

/**
 * @author wyy
 * @since 2020-02_12
 */
@TableName("sys_group")
@Table(name = "sys_group")
public class Group extends BaseEntity<Long> {

    @Column(name = "name", type = VARCHAR, length = 20, nullable = false)
    private String name;

    public Group(Long id) {
        super(id);
    }

    public Group() {
    }

    public String getName() {
        return name;
    }

    public Group setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}
