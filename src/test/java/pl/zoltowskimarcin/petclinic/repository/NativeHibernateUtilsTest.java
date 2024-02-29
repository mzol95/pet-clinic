package pl.zoltowskimarcin.petclinic.repository;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NativeHibernateUtilsTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getSessionFactory() {
        //given
        SessionFactory sessionFactory = NativeHibernateUtils.getSessionFactory();
        //when

        //then
        Assertions.assertNotNull(sessionFactory);
    }
}