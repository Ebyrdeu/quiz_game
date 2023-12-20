package dao.student;

import database.utils.Query;
import entity.Student;

import java.util.List;
import java.util.function.Consumer;

public class StudentDaoImpl implements StudentDao {
    @Override
    public void create(Student entity) {
        Query.inTransactionVoid(em -> em.persist(entity));
    }

    @Override
    public Student read(Student entity) {
        return Query.inTransaction(em -> em.find(Student.class, entity.id()));
    }

    @Override
    public List<Student> readAll() {
        var hpq = "SELECT s FROM  Student s";
        return Query.inTransaction(em -> {
            var query = em.createQuery(hpq, Student.class);
            return query.getResultList();
        });
    }

    @Override
    public void update(Student entity) {
        Query.inTransactionVoid(em -> {
            Student existingEntity = em.find(Student.class, entity.id());
            if (existingEntity != null) {
                updateOfEntityValueNotNull(existingEntity::setName, entity.name());
                updateOfEntityValueNotNull(existingEntity::setAge, entity.age());
                updateOfEntityValueNotNull(existingEntity::setEmail, entity.email());
                updateOfEntityValueNotNull(existingEntity::setStudentClass, entity.studentClass());
            }
        });
    }

    @Override
    public void delete(Student entity) {
        Query.inTransactionVoid(em -> {
            var mergedEntity = em.merge(entity);

            em.remove(mergedEntity);
        });
    }

    @Override
    public <T> void updateOfEntityValueNotNull(Consumer<T> consumer, T value) {
        if (value != null) consumer.accept(value);
    }
}
