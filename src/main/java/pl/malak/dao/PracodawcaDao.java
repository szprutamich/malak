package pl.malak.dao;

import org.springframework.stereotype.Repository;
import pl.malak.model.Pracodawca;
import pl.malak.model.Pracodawca_;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class PracodawcaDao extends CRUDRepository<Pracodawca> {

    public List<String> loadAllNames(boolean withRemoved) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> query = builder.createQuery(String.class);
        Root<Pracodawca> pracodawcaRoot = query.from(Pracodawca.class);
        query.select(pracodawcaRoot.get(Pracodawca_.nazwa));
        if (!withRemoved) {
            query.where(builder.isNull(pracodawcaRoot.get(Pracodawca_.dataUsuniecia)));
        }
        query.orderBy(builder.asc(pracodawcaRoot.get(Pracodawca_.nazwa)));
        TypedQuery<String> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }

    public Pracodawca loadByName(String nazwa) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pracodawca> query = builder.createQuery(Pracodawca.class);
        Root<Pracodawca> pracodawcaRoot = query.from(Pracodawca.class);
        query.select(pracodawcaRoot);
        ParameterExpression<String> nazwaParam = builder.parameter(String.class);
        query.where(builder.equal(pracodawcaRoot.get(Pracodawca_.nazwa), nazwaParam));
        TypedQuery<Pracodawca> typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter(nazwaParam, nazwa);
        List<Pracodawca> result = typedQuery.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
}
