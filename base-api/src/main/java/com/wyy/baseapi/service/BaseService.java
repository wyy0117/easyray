package com.wyy.baseapi.service;

import java.util.List;

/**
 * @Date: 20-1-26
 * @Author: wyy
 */

/**
 *
 * @param <T> entity类型
 * @param <U> entity主键类型
 */
public interface BaseService<T, U> {
    public T add(T entity);

    public void delete(T entity);

    public T update(T entity);

    public T find(U primeKey);

    public T fetch(U primeKey);

    public List<T> findAll(List<U> primeKeyList);

}
