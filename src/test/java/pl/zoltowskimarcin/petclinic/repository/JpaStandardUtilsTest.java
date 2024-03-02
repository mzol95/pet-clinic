package pl.zoltowskimarcin.petclinic.repository;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JpaStandardUtilsTest {

    @Test
    void getEntityManager() {
        //given

        //when
        EntityManager entityManager  = JpaStandardUtils.getEntityManager();
        //then
        Assertions.assertNotNull(entityManager, "Entity manager is null");

    }
}