package com.easyray.teamapi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.easyray.baseapi.entity.BaseEntity;
import com.wyy.actable.annotation.Column;
import com.wyy.actable.annotation.Index;
import com.wyy.actable.annotation.Table;

import static com.wyy.actable.constants.MySqlDataType.BIGINT;
import static com.wyy.actable.constants.MySqlDataType.VARCHAR;

/**
 * @author wyy
 * @since 2020-02_11
 */
@TableName("sys_team")
@Table(name = "sys_team")
public class Team extends BaseEntity<Long> {

    @Index
    @Column(name = "name", type = VARCHAR, length = 20, nullable = false)
    private String name;
    @Column(name = "role_id", type = BIGINT, length = 20, nullable = false)
    private long roleId;

    public Team(Long id) {
        super(id);
    }

    public Team() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
