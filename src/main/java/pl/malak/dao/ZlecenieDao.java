package pl.malak.dao;

import pl.malak.model.Zlecenie;

import javax.persistence.EntityManager;

public class ZlecenieDao extends CRUDRepository<Zlecenie> {

    private static ZlecenieDao instance;

    public static synchronized ZlecenieDao getInstance(EntityManager entityManager) {
        if (instance == null) {
            instance = new ZlecenieDao();
        }
        instance.entityManager = entityManager;
        return instance;
    }
}
