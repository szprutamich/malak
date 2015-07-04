package pl.malak.persistenceutil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public enum PersistenceManager {
    INSTANCE;

    private static final String PERSISTENCE_UNIT = "malak";

    private EntityManagerFactory emFactory;

    private PersistenceManager() {
        emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    }

    public EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }

    public void close() {
        emFactory.close();
    }

    public EntityManager getCustomEntityManager(String url) {
        Map<String, String> persistenceMap = new HashMap<>();
        url = "jdbc:mysql://" + url + ":3306/malak?useUnicode=true&amp;characterEncoding=UTF-8&amp;autoReconnect=true&amp;logSlowQueries=true&amp;explainSlowQueries=true";
        persistenceMap.put("javax.persistence.jdbc.url", url);
        emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT, persistenceMap);
        return emFactory.createEntityManager();
    }
}
