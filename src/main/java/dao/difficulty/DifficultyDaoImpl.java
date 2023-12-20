package dao.difficulty;

import entity.Difficulty;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.List;

public class DifficultyDaoImpl implements DifficultyDao {

    private EntityManager entityManager;

    public DifficultyDaoImpl() {
        this.entityManager = Persistence.createEntityManagerFactory("jpa-hibernate-mysql").createEntityManager();
    }

    @Override
    public void insertDifficulty(Difficulty difficulty) {
        entityManager.getTransaction().begin();
        entityManager.persist(difficulty);
        entityManager.getTransaction().commit();
    }

    @Override
    public Difficulty findDifficultyById(Long id) {
        return entityManager.find(Difficulty.class, id);
    }

    @Override
    public List<Difficulty> findAllDifficulties() {
        return entityManager.createQuery("SELECT d FROM Difficulty d", Difficulty.class).getResultList();
    }

    @Override
    public void updateDifficulty(Difficulty difficulty) {
        entityManager.getTransaction().begin();
        Difficulty existingDifficulty = entityManager.find(Difficulty.class, difficulty.getId());
        if (existingDifficulty != null) {
            existingDifficulty.setName(difficulty.getName());
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteDifficulty(Long id) {
        entityManager.getTransaction().begin();
        Difficulty difficulty = entityManager.find(Difficulty.class, id);
        if (difficulty != null) {
            entityManager.remove(difficulty);
        }
        entityManager.getTransaction().commit();
    }
}