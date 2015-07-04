package pl.malak;

import pl.malak.dao.TestDao;
import pl.malak.model.Test;
import pl.malak.persistenceutil.PersistenceManager;
import pl.malak.model.Pracodawca;

import javax.persistence.EntityManager;
import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        File file = new File("WZÓR.xlsm");
        Migration migration = new Migration();
        migration.migrate(file);
//        migration.printEmployersWithEmployees();


        EntityManager em = PersistenceManager.INSTANCE.getCustomEntityManager("192.168.1.102");
        em.getTransaction().begin();

        for (Pracodawca employer : migration.getEmployers()) {
            em.persist(employer);
        }

        TestDao testDao = TestDao.getInstance(em);

        Test test;
        for (int i = 0; i < 10; i++) {
            int index = i + 1;
            test = new Test();
            test.setText("test" + index);
            testDao.create(test);
            System.out.println("Newly inserted entity's id is: " + test.getId());
        }

        List<Test> tests = testDao.loadAll();

        for (Test test1: tests) {
            System.out.println(String.format("Test: %s, %s", test1.getId(), test1.getText()));

            test1.setText(test1.getText() + " updated");

            if (test1.getId() % 2 == 0) {
                testDao.delete(test1);
            }
        }

        em.getTransaction().commit();
        em.close();

    }
}
