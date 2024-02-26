package pl.zoltowskimarcin.petclinic.service;

import liquibase.command.CommandScope;
import liquibase.exception.CommandExecutionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClientServiceTest {

    @BeforeEach
    void setUp() throws CommandExecutionException {

        new CommandScope("dropAll")
                .addArgumentValue("url", "jdbc:h2:mem:test")
                .addArgumentValue("username", "sa")
                .addArgumentValue("password", "")
                .addArgumentValue("changeLogFile", "db/changelog/master_test.xml")
                .execute();

        new CommandScope("update")
                .addArgumentValue("url", "jdbc:h2:mem:test")
                .addArgumentValue("username", "sa")
                .addArgumentValue("password", "")
                .addArgumentValue("changeLogFile", "db/changelog/master_test.xml")
                .execute();
    }


    @Test
    void saveClient() {
        Assertions.assertNotNull(null);
    }

    @Test
    void getClient() {
        Assertions.assertNotNull(null);
    }

    @Test
    void updateClient() {
        Assertions.assertNotNull(null);
    }

    @Test
    void deleteClient() {
        Assertions.assertNotNull(null);
    }
}