package pl.malak;

public class Main {

    public static void main(String[] args) throws Exception {
//        System.out.println("Hello World!");
//        File file = new File("WZÓR.xlsm");
//        Migration migration = new Migration();
//        String error = migration.migrate(file);
//
//        EntityManager em = PersistenceManager.INSTANCE.getCustomEntityManager("192.168.1.102");
//        em.getTransaction().begin();
//
//        PracodawcaDao pracodawcaDao = PracodawcaDao.getInstance(em);
//        ZlecenieDao zlecenieDao = ZlecenieDao.getInstance(em);
//        zlecenieDao.deleteAll();
//        pracodawcaDao.deleteAll();
//        for (Pracodawca employer : migration.getEmployers()) {
//            pracodawcaDao.create(employer);
//            for (Zlecenie zlecenie : employer.getZlecenia()) {
//                if (zlecenie.getPracodawca() != null) {
//                    zlecenieDao.create(zlecenie);
//                }
//            }
//        }
//        em.getTransaction().commit();
//        em.close();
//
//        if (error.length() > 0) {
//            System.out.println(error);
//        }
        new MainFrame();
    }
}
