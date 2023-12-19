package dao;

public interface Dao<T> {

    void create(T entity);

    T read(T entity);

    T readAll();

    void update(T entity);

    void delete(T entity);
}
