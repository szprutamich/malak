package pl.malak.dao;

import pl.malak.model.Pracodawca;

import javax.persistence.EntityManager;

public class PracodawcaDao extends CRUDRepository<Pracodawca> {

    private static PracodawcaDao instance;

    public static synchronized PracodawcaDao getInstance(EntityManager entityManager) {
        if (instance == null) {
            instance = new PracodawcaDao();
        }
        instance.entityManager = entityManager;
        return instance;
    }
}
