package dao.quiz;

import database.utils.Query;
import entity.Quiz;

import java.util.List;

public class QuizDaoimpl implements dao.quiz.QuizDao {
    @Override
    public void create(Quiz entity) {
        Query.inTransactionVoid(em -> em.persist(entity));
    }

    @Override
    public Quiz read(Quiz entity) {
        return Query.inTransaction(em -> {
            if (em != null) {
                return em.find(Quiz.class, entity.id());
            } else {

                return null;
            }
        });
    }


    @Override
    public List<Quiz> readAll() {
        var hql = "SELECT q FROM Quiz q";

        return Query.inTransaction(em -> {
            var query = em.createQuery(hql, Quiz.class);
            return query.getResultList();
        });
    }

    @Override
    public void update(Quiz entity) {
        Query.inTransactionVoid(em -> {
            Quiz existingEntity = em.find(Quiz.class, entity.id());
            if (existingEntity != null) {
                existingEntity.setDifficulty(entity.difficulty());
                existingEntity.setCategory(entity.category());
                existingEntity.setQuizQuestion(entity.quizQuestion());
                existingEntity.setCorrectAnswer(entity.correctAnswer());
            }
        });
    }

    @Override
    public void delete(Quiz entity) {
        Query.inTransactionVoid(em -> {
            Quiz mergedEntity = em.merge(entity);
            em.remove(mergedEntity);
        });
    }


}




