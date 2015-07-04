package pl.malak.dao;

import pl.malak.model.Test;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class TestDao extends CRUDRepository<Test> {

    private static TestDao instance;

    public static synchronized TestDao getInstance(EntityManager entityManager) {
        if (instance == null) {
            instance = new TestDao();
        }
        instance.entityManager = entityManager;
        return instance;
    }

    public List<Test> loadAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Test> query = builder.createQuery(Test.class);
        Root<Test> test = query.from(Test.class);
        query.select(test);
        TypedQuery<Test> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }
}
