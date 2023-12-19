package dao;

import java.util.List;

public interface Dao<T> {

    void create(T entity);

    T read(T entity);

    List<T> readAll();

    void update(T entity);

    void delete(T entity);
}
