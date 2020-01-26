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
public abstract class BaseTreeEntity<T> {
    private T id;
    private long userId;
    private String fullName;
    private Date createDate;
    private Date modifiedDate;
    private T parentId;
    private String treePath;

    @Override
    public String toString() {
        return "BaseTreeEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", fullName='" + fullName + '\'' +
                ", createDate=" + createDate +
                ", modifiedDate=" + modifiedDate +
                ", parentId=" + parentId +
                ", treePath='" + treePath + '\'' +
                '}';
    }

    public T getId() {
        return id;
    }

    public BaseTreeEntity<T> setId(T id) {
        this.id = id;
        return this;
    }

    public long getUserId() {
        return userId;
    }

    public BaseTreeEntity<T> setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public BaseTreeEntity<T> setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public BaseTreeEntity<T> setCreateDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public BaseTreeEntity<T> setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public T getParentId() {
        return parentId;
    }

    public BaseTreeEntity<T> setParentId(T parentId) {
        this.parentId = parentId;
        return this;
    }

    public String getTreePath() {
        return treePath;
    }

    public BaseTreeEntity<T> setTreePath(String treePath) {
        this.treePath = treePath;
        return this;
    }
}
