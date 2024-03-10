package pl.zoltowskimarcin.petclinic.dao;

import liquibase.exception.CommandExecutionException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.zoltowskimarcin.petclinic.exception.client.ClientDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.client.ClientException;
import pl.zoltowskimarcin.petclinic.exception.client.ClientReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.client.ClientUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.repository.dao.DefaultClientDao;
import pl.zoltowskimarcin.petclinic.repository.entity.Client;
import pl.zoltowskimarcin.petclinic.utils.DatabaseInitializer;

import java.util.List;
import java.util.Optional;

import static pl.zoltowskimarcin.petclinic.utils.TestUtils.*;

@SpringBootTest
class DefaultClientDaoTest {

    @Autowired
    private DefaultClientDao clientDao;

    private Client clientJon = new Client(CLIENT_NAME_JON, CLIENT_SURNAME_SNOW, CLIENT_PHONE_123_456_789);
    private Client clientNed = new Client(CLIENT_NAME_NED, CLIENT_SURNAME_STARK, CLIENT_PHONE_123_000_987);
    private Client clientKhal = new Client(CLIENT_NAME_KHAL, CLIENT_SURNAME_DROGO, CLIENT_PHONE_111_222_333);
    private Client updatedClientTyrion = new Client(UPDATE_CLIENT_NAME_TYRION, UPDATE_CLIENT_SURNAME_LANNISTER, UPDATE_CLIENT_PHONE_987_654_321);

    @BeforeEach
    void setUp() throws CommandExecutionException {
        DatabaseInitializer.initializeDatabase();
    }

    @AfterEach
    void tearDown() throws CommandExecutionException {
        DatabaseInitializer.dropDatabase();
    }

    @Test
    void getting_empty_table_should_return_empty_list() throws ClientReadingFailedException {
        //given

        //when
        List<Client> clients = clientDao.getAllClients();
        int listSize = clients.size();

        //then
        Assertions.assertEquals(0, listSize, "List size isn't equal 0");
    }


    @Test
    void creating_new_client_should_return_created_client() throws ClientException {
        //given

        //when
        Client returnedClient = clientDao.saveClient(clientJon);

        //then
        Assertions.assertEquals(clientJon, returnedClient, "Client is not equal");
    }


    @Test
    void updating_not_existing_entity_should_throw_client_updating_failed_exception() {
        //given

        //when

        //then
        Assertions.assertThrows(ClientUpdatingFailedException.class,
                () -> clientDao.updateClient(ID_1, updatedClientTyrion), "Exception not thrown");
    }


    @Test
    void no_entity_found_while_reading_should_return_empty_optional() throws ClientReadingFailedException {
        //given

        //when
        Optional<Client> returnedClient = clientDao.getClientById(ID_1);

        //then
        Assertions.assertEquals(Optional.empty(), returnedClient, "Client is not empty");
    }

    @Test
    void deleting_not_existing_entity_should_throw_client_deleting_failed_exception() {
        //given

        //when

        //then
        Assertions.assertThrows(ClientDeletingFailedException.class,
                () -> clientDao.deleteClient(ID_1), "Exception not thrown");
    }

}