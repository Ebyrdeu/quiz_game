package dao.difficulty;

import database.utils.Query;
import entity.Difficulty;

import java.util.List;

public class DifficultyDaoImpl implements DifficultyDao {


    @Override
    public void create(Difficulty entity) {
        Query.inTransactionVoid(em -> em.persist(entity));
    }

    @Override
    public Difficulty read(Difficulty entity) {
        return Query.inTransaction(em -> em.find(Difficulty.class, entity.id()));
    }

    @Override
    public List<Difficulty> readAll() {
        return Query.inTransaction(em -> em.createQuery("SELECT d FROM Difficulty d", Difficulty.class).getResultList());
    }

    @Override
    public void update(Difficulty difficulty) {
        Query.inTransactionVoid(em -> {
            Difficulty existingDifficulty = em.find(Difficulty.class, difficulty.id());
            if (existingDifficulty != null) {
                existingDifficulty.setName(difficulty.name());
            }
        });
    }

    @Override
    public void delete(Difficulty entity) {
        Query.inTransactionVoid(em -> {
            Difficulty difficulty = em.find(Difficulty.class, entity.id());
            if (difficulty != null) {
                em.remove(difficulty);
            }
        });
    }
}