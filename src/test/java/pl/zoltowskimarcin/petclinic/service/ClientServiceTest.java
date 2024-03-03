package pl.zoltowskimarcin.petclinic.service;

import liquibase.exception.CommandExecutionException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.zoltowskimarcin.petclinic.exception.EntityDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntityException;
import pl.zoltowskimarcin.petclinic.exception.EntityReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntityUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.utils.DatabaseInitializer;
import pl.zoltowskimarcin.petclinic.web.model.ClientDto;

import java.util.Optional;

@SpringBootTest
class ClientServiceTest {

    private static final String TEST_CLIENT_ADDRESS_STREET = "Test street";
    private static final String TEST_CLIENT_CITY = "Test city";
    private static final String TEST_CLIENT_POSTAL_CODE = "Test postalCode";
    private static final String TEST_CLIENT_NAME = "Test name";
    private static final String TEST_CLIENT_SURNAME = "Test surname";
    private static final String TEST_CLIENT_PHONE = "Test phone";

    private static final String UPDATE_TEST_CLIENT_ADDRESS_STREET = "Update Test street";
    private static final String UPDATE_TEST_CLIENT_CITY = "Update Test city";
    private static final String UPDATE_TEST_CLIENT_POSTAL_CODE = "Update Test postalCode";
    private static final String UPDATE_TEST_CLIENT_NAME = "Update Test name";
    private static final String UPDATE_TEST_CLIENT_SURNAME = "Update Test surname";
    private static final String UPDATE_TEST_CLIENT_PHONE = "Update Test phone";
    private static final long ID_1 = 1L;

    @Autowired
    private ClientService clientService;

    private ClientDto clientDto;
    private ClientDto updatedClientDto;

    @BeforeEach
    void setUp() throws CommandExecutionException {
        DatabaseInitializer.initializeDatabase();

        clientDto = new ClientDto.Builder()
                .name(TEST_CLIENT_NAME)
                .surname(TEST_CLIENT_SURNAME)
                .street(TEST_CLIENT_ADDRESS_STREET)
                .city(TEST_CLIENT_CITY)
                .postalCode(TEST_CLIENT_POSTAL_CODE)
                .phone(TEST_CLIENT_PHONE)
                .build();


        updatedClientDto = new ClientDto.Builder()
                .name(UPDATE_TEST_CLIENT_NAME)
                .surname(UPDATE_TEST_CLIENT_SURNAME)
                .street(UPDATE_TEST_CLIENT_ADDRESS_STREET)
                .city(UPDATE_TEST_CLIENT_CITY)
                .postalCode(UPDATE_TEST_CLIENT_POSTAL_CODE)
                .phone(UPDATE_TEST_CLIENT_PHONE)
                .build();

    }

    @AfterEach
    void tearDown() throws CommandExecutionException {
        DatabaseInitializer.dropDatabase();
    }

    @Test
    void creating_new_client_should_return_created_client() throws EntityException {
        //given

        //when
        ClientDto returnedClient = clientService.saveClient(clientDto);

        //then
        Assertions.assertEquals(clientDto, returnedClient, "Client is not equal");
    }

    @Test
    void read_after_creating_new_client_should_return_newly_created_client() throws EntityException {
        //given

        //when
        ClientDto persistedClient = clientService.saveClient(clientDto);
        ClientDto returnedClient = clientService.getClientById(ID_1)
                .orElseThrow(EntityReadingFailedException::new);

        //then
        Assertions.assertEquals(clientDto, returnedClient, "Client is not equal");
    }

    @Test
    void after_updating_should_return_updated_client_entity() throws EntityException {
        //given

        //when
        ClientDto persistedClient = clientService.saveClient(clientDto);
        ClientDto updatedClient = clientService.updateClient(ID_1, updatedClientDto);

        //then
        Assertions.assertEquals(updatedClientDto, updatedClient, "Client is not equal");
    }

    @Test
    void updating_not_existing_entity_should_throw_client_updating_failed_exception() {
        //given

        //when

        //then
        Assertions.assertThrows(EntityUpdatingFailedException.class,
                () -> clientService.updateClient(ID_1, updatedClientDto), "Exception not thrown");
    }

    @Test
    void after_deleting_client_should_return_null_when_try_to_read_deleted_entity() throws EntityException {
        //given

        //when
        ClientDto persistedClient = clientService.saveClient(clientDto);
        clientService.deleteClient(ID_1);
        ClientDto returnedClient = clientService.getClientById(ID_1).orElse(null);

        //then
        Assertions.assertNull(returnedClient, "Client is not null");
    }

    @Test
    void no_entity_found_while_reading_should_return_empty_optional() throws EntityReadingFailedException {
        //given

        //when
        Optional<ClientDto> returnedClient = clientService.getClientById(ID_1);

        //then
        Assertions.assertEquals(Optional.empty(), returnedClient, "Client is not empty");
    }

    @Test
    void deleting_not_existing_entity_should_throw_client_deleting_failed_exception() {
        //given

        //when

        //then
        Assertions.assertThrows(EntityDeletingFailedException.class,
                () -> clientService.deleteClient(ID_1), "Exception not thrown");
    }

}