package com.easyray.idgeneratorapi.provider;

/**
 * @Date: 20-2-4
 * @Author: wyy
 */
public interface IdService {

    public void init();

    public long nextId(String className);
}
