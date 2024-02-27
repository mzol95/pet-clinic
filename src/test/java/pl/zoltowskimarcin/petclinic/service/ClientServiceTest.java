package pl.zoltowskimarcin.petclinic.service;

import liquibase.command.CommandScope;
import liquibase.exception.CommandExecutionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.zoltowskimarcin.exception.Client.ClientException;
import pl.zoltowskimarcin.petclinic.web.model.AddressDto;
import pl.zoltowskimarcin.petclinic.web.model.ClientDto;

@SpringBootTest
class ClientServiceTest {

    private static final String TEST_CLIENT_ADDRESS_STREET = "Test street";
    private static final String TEST_CLIENT_CITY = "Test city";
    private static final String TEST_CLIENT_POSTAL_CODE = "Test postalCode";
    private static final String TEST_CLIENT_NAME = "Test name";
    private static final String TEST_CLIENT_SURNAME = "Test surname";
    private static final String TEST_CLIENT_PHONE = "Test phone";
    private static final long EXPECTED_ID_1 = 1L;

    @Autowired
    private ClientService clientService;

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
    void creating_new_client() throws ClientException {
        //given
        AddressDto clientAddressDto =
                new AddressDto(TEST_CLIENT_ADDRESS_STREET, TEST_CLIENT_CITY, TEST_CLIENT_POSTAL_CODE);

        ClientDto clientDto =
                new ClientDto(
                        TEST_CLIENT_NAME
                        , TEST_CLIENT_SURNAME
                        , clientAddressDto.getStreet()
                        , clientAddressDto.getCity()
                        , clientAddressDto.getPostalCode()
                        , TEST_CLIENT_PHONE
                );


        //when
        ClientDto persistedClient = clientService.saveClient(clientDto);

        //then
        Assertions.assertAll(
                () -> Assertions.assertNotNull(persistedClient, "Client is null"),
                () -> Assertions.assertEquals(TEST_CLIENT_NAME, persistedClient.getName(), "Name is not equal"),
                () -> Assertions.assertEquals(TEST_CLIENT_SURNAME, persistedClient.getSurname(), "Surname is not equal"),
                () -> Assertions.assertEquals(TEST_CLIENT_ADDRESS_STREET, persistedClient.getStreet(), "Street is not equal"),
                () -> Assertions.assertEquals(TEST_CLIENT_CITY, persistedClient.getCity(), "City is not equal"),
                () -> Assertions.assertEquals(TEST_CLIENT_POSTAL_CODE, persistedClient.getPostalCode(), "Postal code is not equal"),
                () -> Assertions.assertEquals(TEST_CLIENT_PHONE, persistedClient.getPhone(), "Phone is not equal")
        );
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