package com.easyray.documentapi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.easyray.baseapi.entity.TenantTreeEntity;
import com.wyy.actable.annotation.Column;
import com.wyy.actable.annotation.Table;

import static com.wyy.actable.constants.MySqlDataType.VARCHAR;

/**
 * @author wyy
 * @since 2020-02_13
 */
@TableName("sys_dfolder")
@Table(name = "sys_dfolder")
public class DFolder extends TenantTreeEntity<Long> {

    @Column(name = "name", type = VARCHAR, length = 20, nullable = false)
    private String name;

    public DFolder(Long id) {
        super(id);
    }

    public DFolder() {
    }

    @Override
    public String toString() {
        return "DFolder{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }

    public String getName() {
        return name;
    }

    public DFolder setName(String name) {
        this.name = name;
        return this;
    }
}
