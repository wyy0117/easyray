package com.easyray.idgeneratorapi.service;

/**
 * @Date: 20-2-4
 * @Author: wyy
 */
public interface IdService {

    public long nextId(String entityName);
}