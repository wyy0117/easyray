package com.easyray.baseapi.entity;

import com.easyray.baseapi.constant.ColumnNameConstant;
import com.wyy.actable.annotation.Column;
import com.wyy.actable.constants.MySqlDataType;

/**
 * @Date: 20-1-26
 * @Author: wyy
 */
public abstract class TreeEntity<ID> extends BaseEntity<ID> {

    @Column(name = ColumnNameConstant.parent_id, type = MySqlDataType.BIGINT, length = 10, nullable = false)
    private ID parentId;

    @Column(name = ColumnNameConstant.tree_path, type = MySqlDataType.VARCHAR, length = 75, nullable = false)
    private String treePath;

    public TreeEntity() {
    }

    public TreeEntity(ID id) {
        super(id);
    }

    @Override
    public String toString() {
        return "TreeEntity{" +
                "parentId=" + parentId +
                ", treePath='" + treePath + '\'' +
                "} " + super.toString();
    }

    public ID getParentId() {
        return parentId;
    }

    public TreeEntity<ID> setParentId(ID parentId) {
        this.parentId = parentId;
        return this;
    }

    public String getTreePath() {
        return treePath;
    }

    public TreeEntity<ID> setTreePath(String treePath) {
        this.treePath = treePath;
        return this;
    }
}
