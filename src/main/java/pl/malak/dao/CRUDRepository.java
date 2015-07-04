package pl.malak.dao;

import pl.malak.model.Test;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class CRUDRepository<T> {

    private final Class<T> type;

    protected EntityManager entityManager;

    public CRUDRepository() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    public T load(Long entityId) {
        return entityManager.find(type, entityId);
    }

    public T update(T entity) {
        return entityManager.merge(entity);
    }

    public void create(T entity) {
        entityManager.persist(entity);
    }

    public void delete(T entity) {
        entityManager.remove(entity);
    }

    public void delete(Long entityId) {
        T reference = lazyLoad(entityId);
        if (reference != null) {
            delete(reference);
        }
    }

    public T lazyLoad(Long entityId) {
        return entityId != null ? entityManager.getReference(type, entityId) : null;
    }

    public void flush() {
        entityManager.flush();
    }

    public Long getTimeStamp() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Timestamp> query = builder.createQuery(Timestamp.class);
        query.from(Test.class);
        query.select(builder.currentTimestamp());
        TypedQuery<Timestamp> typedQuery = entityManager.createQuery(query);
        typedQuery.setMaxResults(1);
        List<Timestamp> result = typedQuery.getResultList();
        return result.isEmpty() ? System.currentTimeMillis() : result.get(0).getTime();
    }

    public Date getDateNow() {
        return new Date(getTimeStamp());
    }

    public Expression<Date> now(CriteriaBuilder builder) {
        return builder.function("now", Date.class);
    }

    public boolean exists(Long entityId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<T> entity = query.from(type);
        query.select(builder.count(entity));
        ParameterExpression<Long> entityIdParam = builder.parameter(Long.class);
        query.where(
                builder.equal(entity.get("id"), entityIdParam)
        );
        TypedQuery<Long> typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter(entityIdParam, entityId);
        return typedQuery.getSingleResult() > 0;
    }

    public boolean notExists(Long entityId) {
        return !exists(entityId);
    }
}