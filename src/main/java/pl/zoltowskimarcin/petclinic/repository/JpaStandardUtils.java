package pl.zoltowskimarcin.petclinic.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import pl.zoltowskimarcin.petclinic.utils.PropertyManager;


public class JpaStandardUtils {

    private static EntityManager entityManager;
    private static final EntityManagerFactory entityManagerFactory;

    static {
        entityManagerFactory =
                Persistence.createEntityManagerFactory(PropertyManager.getProperty("entityUnit.persistanceUnit"));
    }

    private JpaStandardUtils() {
    }

    public static EntityManager getEntityManager() {
        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }

    public static void closeEntityManager() {
        entityManager.close();
    }


}
