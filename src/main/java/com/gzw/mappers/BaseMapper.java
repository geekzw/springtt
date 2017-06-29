package com.gzw.mappers;

/**
 * Created by gujian on 2017/6/25.
 */
public interface BaseMapper<T> {

    T findById(Integer id);

    boolean insert(T t);

    boolean delete(T t);

    boolean delete(Integer id);

    boolean update(T t);
}
