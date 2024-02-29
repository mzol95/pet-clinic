package pl.zoltowskimarcin.petclinic.service;

import liquibase.command.CommandScope;
import liquibase.exception.CommandExecutionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.zoltowskimarcin.petclinic.exception.client.ClientException;
import pl.zoltowskimarcin.petclinic.exception.client.ClientReadingFailedException;
import pl.zoltowskimarcin.petclinic.web.model.ClientDto;

@SpringBootTest
class ClientServiceTest {

    private static final String TEST_CLIENT_ADDRESS_STREET = "Test street";
    private static final String TEST_CLIENT_CITY = "Test city";
    private static final String TEST_CLIENT_POSTAL_CODE = "Test postalCode";
    private static final String TEST_CLIENT_NAME = "Test name";
    private static final String TEST_CLIENT_SURNAME = "Test surname";
    private static final String TEST_CLIENT_PHONE = "Test phone";
    private static final long ID_1 = 1L;

    @Autowired
    private ClientService clientService;
    private ClientDto clientDto;

    @BeforeEach
    void setUp() throws CommandExecutionException {

        new CommandScope("dropAll")
                .addArgumentValue("url", "jdbc:h2:mem:pet-clinic")
                .addArgumentValue("username", "sa")
                .addArgumentValue("password", "")
                .addArgumentValue("changeLogFile", "db/changelog/master_test.xml")
                .execute();

        new CommandScope("update")
                .addArgumentValue("url", "jdbc:h2:mem:pet-clinic")
                .addArgumentValue("username", "sa")
                .addArgumentValue("password", "")
                .addArgumentValue("changeLogFile", "db/changelog/master_test.xml")
                .execute();

        clientDto =
                new ClientDto(
                        TEST_CLIENT_NAME
                        , TEST_CLIENT_SURNAME
                        , TEST_CLIENT_ADDRESS_STREET
                        , TEST_CLIENT_CITY
                        , TEST_CLIENT_POSTAL_CODE
                        , TEST_CLIENT_PHONE
                );
    }


    @Test
    void creating_new_client_should_return_created_client() throws ClientException {
        //given

        //when
        ClientDto returnedClient = clientService.saveClient(clientDto);

        //then
        Assertions.assertEquals(clientDto,returnedClient,"Client is not equal");
    }

    @Test
    void read_after_creating_new_client_should_return_newly_created_client() throws ClientException {
        //given

        //when
        ClientDto persistedClient = clientService.saveClient(clientDto);
        ClientDto returnedClient = clientService.getClientById(ID_1)
                .orElseThrow(ClientReadingFailedException::new);

        //then
        Assertions.assertEquals(clientDto,returnedClient,"Client is not equal");
    }


}