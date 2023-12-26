package dao.student;

import dao.Dao;
import entity.Student;

import java.util.function.Consumer;

public interface StudentDao extends Dao<Student> {
    <T> void updateOfEntityValueNotNull(Consumer<T> consumer, T value);
}