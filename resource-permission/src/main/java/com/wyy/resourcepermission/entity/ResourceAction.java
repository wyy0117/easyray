package com.wyy.resourcepermission.entity;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @Date: 20-1-27
 * @Author: wyy
 */
@TableName("sys_resource_action")
public class ResourceAction {
    private Long id;
    private String name;
    private String action;
    private int bitwiseVale;

    @Override
    public String toString() {
        return "ResourceAction{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", action='" + action + '\'' +
                ", bitwiseVale=" + bitwiseVale +
                '}';
    }

    public Long getId() {
        return id;
    }

    public ResourceAction setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ResourceAction setName(String name) {
        this.name = name;
        return this;
    }

    public String getAction() {
        return action;
    }

    public ResourceAction setAction(String action) {
        this.action = action;
        return this;
    }

    public int getBitwiseVale() {
        return bitwiseVale;
    }

    public ResourceAction setBitwiseVale(int bitwiseVale) {
        this.bitwiseVale = bitwiseVale;
        return this;
    }
}
