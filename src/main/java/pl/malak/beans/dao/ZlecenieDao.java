package pl.malak.beans.dao;

import org.springframework.stereotype.Repository;
import pl.malak.model.Pracodawca_;
import pl.malak.model.Zlecenie;
import pl.malak.model.Zlecenie_;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ZlecenieDao extends CRUDRepository<Zlecenie> {

    public Long countByPracodawcaId(Long pracodawcaId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Zlecenie> zlecenie = query.from(Zlecenie.class);
        query.select(builder.countDistinct(zlecenie));
        ParameterExpression<Long> pracodawcaIdParam = builder.parameter(Long.class);
        query.where(
                builder.equal(zlecenie.get(Zlecenie_.pracodawca).get(Pracodawca_.id), pracodawcaIdParam),
                builder.isNull(zlecenie.get(Zlecenie_.dataUsuniecia))
        );
        TypedQuery<Long> typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter(pracodawcaIdParam, pracodawcaId);
        return typedQuery.getSingleResult();
    }

    public List<String> loadNamesByPracodawcaId(Long pracodawcaId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> query = builder.createQuery(String.class);
        Root<Zlecenie> zlecenie = query.from(Zlecenie.class);
        query.select(zlecenie.get(Zlecenie_.nazwa));
        ParameterExpression<Long> pracodawcaIdParam = builder.parameter(Long.class);
        query.where(
                builder.equal(zlecenie.get(Zlecenie_.pracodawca).get(Pracodawca_.id), pracodawcaIdParam),
                builder.isNull(zlecenie.get(Zlecenie_.dataUsuniecia))
        );
        TypedQuery<String> typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter(pracodawcaIdParam, pracodawcaId);
        return typedQuery.getResultList();
    }

    public Zlecenie loadByName(String nazwa) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Zlecenie> query = builder.createQuery(Zlecenie.class);
        Root<Zlecenie> pracodawcaRoot = query.from(Zlecenie.class);
        query.select(pracodawcaRoot);
        ParameterExpression<String> nazwaParam = builder.parameter(String.class);
        query.where(builder.equal(pracodawcaRoot.get(Zlecenie_.nazwa), nazwaParam));
        TypedQuery<Zlecenie> typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter(nazwaParam, nazwa);
        List<Zlecenie> result = typedQuery.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
}
