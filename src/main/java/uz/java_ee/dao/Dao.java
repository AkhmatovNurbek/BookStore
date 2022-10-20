package uz.java_ee.dao;

import java.util.List;


public interface Dao<T> {
    T create(T entity);

    void delete(Long id);

    List<T> findAll();

    T findOne(Long id);

}
