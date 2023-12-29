package dao.category;

import database.utils.Query;
import entity.Category;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    @Override
    public void create(Category entity) {
        Query.inTransactionVoid(em -> em.persist(entity));
    }

    @Override
    public Category read(Category entity) {
        return Query.inTransaction(em -> em.find(Category.class, entity.categoryId()));
    }

    @Override
    public List<Category> readAll() {
        return Query.inTransaction(em -> em.createQuery("SELECT c FROM Category c", Category.class).getResultList());
    }

    @Override
    public void update(Category entity) {
        Query.inTransactionVoid(em -> {
            Category existingCategory = em.find(Category.class, entity.categoryId());
            if (existingCategory != null) {
                existingCategory.setName(entity.name());
            }
        });
    }

    @Override
    public void delete(Category entity) {
        Query.inTransactionVoid(em -> {
            Category existingCategory = em.find(Category.class, entity.categoryId());
            if (existingCategory != null) {
                em.remove(existingCategory);
            }
        });
    }
}