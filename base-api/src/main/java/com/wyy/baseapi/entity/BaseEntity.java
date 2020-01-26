package com.wyy.baseapi.entity;

import java.util.Date;

/**
 * @Date: 20-1-26
 * @Author: wyy
 */

/**
 *
 * @param <T> 主键类型
 */
public abstract class BaseEntity<T> {
    private T id;
    private long userId;
    private String fullName;
    private Date createDate;
    private Date modifiedDate;

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
