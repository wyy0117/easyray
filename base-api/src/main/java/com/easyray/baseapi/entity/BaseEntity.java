package com.easyray.baseapi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.wyy.actable.annotation.Column;
import com.wyy.actable.constants.MySqlTypeConstant;

import java.util.Date;

/**
 * @Date: 20-1-26
 * @Author: wyy
 */

/**
 * @param <T> 主键类型
 */
public abstract class BaseEntity<T> {
    @TableId(type = IdType.INPUT)
    @Column(name = "id", type = MySqlTypeConstant.BIGINT, length = 20, isNull = false, isKey = true)
    private T id;
    @Column(name = "user_id", type = MySqlTypeConstant.BIGINT, length = 20, isNull = false)
    private long userId;
    @Column(name = "full_name", type = MySqlTypeConstant.VARCHAR, length = 75, isNull = false)
    private String fullName;
    @Column(name = "create_date", type = MySqlTypeConstant.DATETIME, isNull = false)
    private Date createDate;
    @Column(name = "modified_date", type = MySqlTypeConstant.DATETIME)
    private Date modifiedDate;

    public BaseEntity() {
    }

    public BaseEntity(T id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", fullName='" + fullName + '\'' +
                ", createDate=" + createDate +
                ", modifiedDate=" + modifiedDate +
                '}';
    }

    public T getId() {
        return id;
    }

    public BaseEntity<T> setId(T id) {
        this.id = id;
        return this;
    }

    public long getUserId() {
        return userId;
    }

    public BaseEntity setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public BaseEntity setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public BaseEntity setCreateDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public BaseEntity setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }
}
