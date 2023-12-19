package dao.result;

import database.utils.Query;
import entity.Result;

import java.util.List;

public class ResultDaoImpl implements ResultDao {
    @Override
    public void create(Result entity) {
        Query.inTransactionVoid(em -> em.persist(entity));
    }

    @Override
    public Result read(Result entity) {
        return Query.inTransaction(em -> em.find(Result.class, entity.id()));
    }

    @Override
    public List<Result> readAll() {
        var hql = "SELECT r FROM  Result  r";

        return Query.inTransaction(em -> {
            var query = em.createQuery(hql, Result.class);
            return query.getResultList();
        });
    }

    @Override
    public List<Result> readAllLeaderBoard() {
        var hql = """
                SELECT  r FROM  Result  r WHERE (r.student.name, r.score) IN
                (SELECT  r1.student.name, MAX(r1.score) FROM Result r1 GROUP BY r1.student.name)
                ORDER BY r.score DESC
                """;

        return Query.inTransaction(em -> {
            var query = em.createQuery(hql, Result.class);
            return query.getResultList();
        });
    }

    @Override
    public void update(Result entity) {
        Query.inTransactionVoid(em -> {
            Result existingEntity = em.find(Result.class, entity.id());
            if (existingEntity != null) existingEntity.setScore(entity.score());
        });
    }

    @Override
    public void delete(Result entity) {
        Query.inTransactionVoid(em -> {
            Result mergedEntity = em.merge(entity);
            em.remove(mergedEntity);
        });
    }
}
