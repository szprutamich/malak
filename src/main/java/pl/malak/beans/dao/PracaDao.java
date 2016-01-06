package pl.malak.beans.dao;

import org.springframework.stereotype.Repository;
import pl.malak.model.Praca;
import pl.malak.model.Praca_;
import pl.malak.model.Pracodawca_;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class PracaDao extends CRUDRepository<Praca> {

    public Long countByPracodawcaId(Long pracodawcaId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Praca> praca = query.from(Praca.class);
        query.select(builder.countDistinct(praca));
        ParameterExpression<Long> pracodawcaIdParam = builder.parameter(Long.class);
        query.where(
                builder.equal(praca.get(Praca_.pracodawca).get(Pracodawca_.id), pracodawcaIdParam),
                builder.isNull(praca.get(Praca_.dataUsuniecia))
        );
        TypedQuery<Long> typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter(pracodawcaIdParam, pracodawcaId);
        return typedQuery.getSingleResult();
    }

    public List<String> loadNamesByPracodawcaId(Long pracodawcaId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> query = builder.createQuery(String.class);
        Root<Praca> praca = query.from(Praca.class);
        query.select(praca.get(Praca_.nazwa));
        ParameterExpression<Long> pracodawcaIdParam = builder.parameter(Long.class);
        query.where(
                builder.equal(praca.get(Praca_.pracodawca).get(Pracodawca_.id), pracodawcaIdParam),
                builder.isNull(praca.get(Praca_.dataUsuniecia))
        );
        query.orderBy(builder.asc(praca.get(Praca_.nazwa)));
        TypedQuery<String> typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter(pracodawcaIdParam, pracodawcaId);
        return typedQuery.getResultList();
    }

    public Praca loadByName(String nazwa) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Praca> query = builder.createQuery(Praca.class);
        Root<Praca> pracodawcaRoot = query.from(Praca.class);
        query.select(pracodawcaRoot);
        ParameterExpression<String> nazwaParam = builder.parameter(String.class);
        query.where(builder.equal(pracodawcaRoot.get(Praca_.nazwa), nazwaParam));
        TypedQuery<Praca> typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter(nazwaParam, nazwa);
        List<Praca> result = typedQuery.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    public List<Praca> loadByPracodawcaId(Long pracodawcaId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Praca> query = builder.createQuery(Praca.class);
        Root<Praca> praca = query.from(Praca.class);
        query.select(praca);
        ParameterExpression<Long> pracodawcaIdParam = builder.parameter(Long.class);
        query.where(
                builder.equal(praca.get(Praca_.pracodawca).get(Pracodawca_.id), pracodawcaIdParam),
                builder.isNull(praca.get(Praca_.dataUsuniecia))
        );
        query.orderBy(builder.asc(praca.get(Praca_.nazwa)));
        TypedQuery<Praca> typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter(pracodawcaIdParam, pracodawcaId);
        return typedQuery.getResultList();
    }
}
