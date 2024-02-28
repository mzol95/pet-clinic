package pl.zoltowskimarcin.petclinic.repository;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HibernateUtilsTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getSessionFactory() {
        //given
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        //when

        //then
        Assertions.assertNotNull(sessionFactory);
    }
}