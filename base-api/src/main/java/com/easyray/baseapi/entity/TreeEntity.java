package com.easyray.baseapi.entity;

/**
 * @Date: 20-1-26
 * @Author: wyy
 */

/**
 *
 * @param <T> 主键类型
 */
public abstract class TreeEntity<T> {
    private T id;
    private T parentId;
    private String treePath;

    @Override
    public String toString() {
        return "TreeEntity{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", treePath='" + treePath + '\'' +
                '}';
    }

    public T getId() {
        return id;
    }

    public TreeEntity<T> setId(T id) {
        this.id = id;
        return this;
    }

    public T getParentId() {
        return parentId;
    }

    public TreeEntity<T> setParentId(T parentId) {
        this.parentId = parentId;
        return this;
    }

    public String getTreePath() {
        return treePath;
    }

    public TreeEntity<T> setTreePath(String treePath) {
        this.treePath = treePath;
        return this;
    }
}
