package com.easyray.baseapi.entity;

/**
 * @Date: 20-1-26
 * @Author: wyy
 */

import com.wyy.actable.annotation.Column;
import com.wyy.actable.constants.MySqlDataType;

/**
 * @param <T> 主键类型
 */
public abstract class TreeEntity<T> {
    @Column(name = "id", type = MySqlDataType.BIGINT, length = 10, nullable = false)
    private T id;

    @Column(name = "parent_id", type = MySqlDataType.BIGINT, length = 10, nullable = false)
    private T parentId;

    @Column(name = "tree_path", type = MySqlDataType.VARCHAR, length = 75, nullable = false)
    private String treePath;

    public TreeEntity() {
    }

    public TreeEntity(T id) {
        this.id = id;
    }

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
