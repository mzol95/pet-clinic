package pl.zoltowskimarcin.petclinic.dao;

import liquibase.exception.CommandExecutionException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.zoltowskimarcin.petclinic.exception.client.ClientException;
import pl.zoltowskimarcin.petclinic.exception.client.ClientReadingFailedException;
import pl.zoltowskimarcin.petclinic.repository.dao.DefaultClientDao;
import pl.zoltowskimarcin.petclinic.repository.entity.Client;
import pl.zoltowskimarcin.petclinic.utils.DatabaseInitializer;

import java.util.List;

import static pl.zoltowskimarcin.petclinic.utils.TestUtils.*;

@SpringBootTest
class DefaultClientDaoIntegrationTest {

    @Autowired
    private DefaultClientDao clientDao;

    private Client clientJon = new Client(CLIENT_NAME_JON, CLIENT_SURNAME_SNOW, CLIENT_JON_PHONE_123_456_789, null, null, null);
    private Client clientNed = new Client(CLIENT_NAME_NED, CLIENT_SURNAME_STARK, CLIENT_NED_PHONE_123_000_987, null, null, null);
    private Client clientKhal = new Client(CLIENT_NAME_KHAL, CLIENT_SURNAME_DROGO, CLIENT_KHAL_PHONE_111_222_333, null, null, null);
    private Client updatedClientTyrion = new Client(UPDATE_CLIENT_NAME_TYRION, UPDATE_CLIENT_SURNAME_LANNISTER, UPDATE_CLIENT_PHONE_987_654_321, null, null, null);

    @BeforeEach
    void setUp() throws CommandExecutionException {
        DatabaseInitializer.initializeDatabase();
    }

    @AfterEach
    void tearDown() throws CommandExecutionException {
        DatabaseInitializer.dropDatabase();
    }

    @Test
    void getting_all_clients_should_return_all_clients_list() throws ClientException {
        //given

        //when
        clientDao.saveClient(clientJon);
        clientDao.saveClient(clientNed);

        List<Client> clients = clientDao.getAllClients();
        int listSize = clients.size();
        //then
        Assertions.assertEquals(2, listSize, "List size isn't equal 2");
    }

    @Test
    void getting_clients_should_return_clients_list() throws ClientException {
        //given

        //when
        Client savedClientJon = clientDao.saveClient(clientJon);
        Client savedClientNed = clientDao.saveClient(clientNed);
        List<Client> clients = clientDao.getAllClients();

        boolean containsSavedClientJon = clients.contains(savedClientJon);
        boolean containsSavedClientNed = clients.contains(savedClientNed);

        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(LIST_SIZE_2, clients.size(), "List size isn't equal 2"),
                () -> Assertions.assertTrue(containsSavedClientJon, "List doesn't contain savedClientJon"),
                () -> Assertions.assertTrue(containsSavedClientNed, "List doesn't contain savedClientNed")
        );
    }


    @Test
    void read_after_creating_new_client_should_return_newly_created_client() throws ClientException {
        //given

        //when
        clientDao.saveClient(clientKhal);

        Client returnedClient = clientDao.getClientById(ID_1)
                .orElseThrow(ClientReadingFailedException::new);

        String returnedName = returnedClient.getName();
        String returnedSurname = returnedClient.getSurname();
        String returnedPhone = returnedClient.getPhone();

        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(CLIENT_NAME_KHAL, returnedName, "Name is not equal"),
                () -> Assertions.assertEquals(CLIENT_SURNAME_DROGO, returnedSurname, "Surname is not equal"),
                () -> Assertions.assertEquals(CLIENT_KHAL_PHONE_111_222_333, returnedPhone, "Phone is not equal")
        );
    }

    @Test
    void after_updating_should_return_updated_client_entity() throws ClientException {
        //given

        //when
        Client savedClient = clientDao.saveClient(clientJon);
        Client updatedClient = clientDao.updateClient(ID_1, updatedClientTyrion);
        updatedClient.setId(null);
        //then
        Assertions.assertEquals(updatedClientTyrion, updatedClient, "Client is not equal");
    }


    @Test
    void after_deleting_client_should_return_null_when_try_to_read_deleted_entity() throws ClientException {
        //given

        //when
        Client savedClient = clientDao.saveClient(clientJon);
        clientDao.deleteClient(savedClient.getId());
        Client returnedClient = clientDao.getClientById(savedClient.getId()).orElse(null);

        //then
        Assertions.assertNull(returnedClient, "Client is not null");
    }


}