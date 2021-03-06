package pl.malak.beans.dao;

import org.springframework.stereotype.Repository;
import pl.malak.model.*;

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

    public Praca loadByName(String nazwa, Long pracodawcaId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Praca> query = builder.createQuery(Praca.class);
        Root<Praca> pracodawcaRoot = query.from(Praca.class);
        query.select(pracodawcaRoot);
        ParameterExpression<String> nazwaParam = builder.parameter(String.class);
        ParameterExpression<Long> pracodawcaIdParam = builder.parameter(Long.class);
        query.where(
                builder.equal(pracodawcaRoot.get(Praca_.nazwa), nazwaParam),
                builder.equal(pracodawcaRoot.get(Praca_.pracodawca).get(Pracodawca_.id), pracodawcaIdParam)
        );
        TypedQuery<Praca> typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter(nazwaParam, nazwa);
        typedQuery.setParameter(pracodawcaIdParam, pracodawcaId);
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

    public List<ReportRow> generateReport() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ReportRow> query = builder.createQuery(ReportRow.class);
        Root<Praca> zlecenie = query.from(Praca.class);
        query.multiselect(
                zlecenie.get(Praca_.pracodawca).get(Pracodawca_.nazwa),
                zlecenie.get(Praca_.nazwa),
                zlecenie.get(Praca_.okresoweBadaniaBhpData),
                zlecenie.get(Praca_.orzeczenieLekarskieData)
        );
        query.orderBy(
                builder.asc(zlecenie.get(Praca_.pracodawca).get(Pracodawca_.nazwa)),
                builder.asc(zlecenie.get(Praca_.nazwa))
        );
        TypedQuery<ReportRow> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }
}
