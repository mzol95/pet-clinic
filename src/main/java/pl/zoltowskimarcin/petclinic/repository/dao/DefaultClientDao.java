package pl.zoltowskimarcin.petclinic.repository.dao;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import pl.zoltowskimarcin.petclinic.exception.client.ClientDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.client.ClientReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.client.ClientSavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.client.ClientUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.jdbc.DataSource;
import pl.zoltowskimarcin.petclinic.jdbc.JdbcQueries;
import pl.zoltowskimarcin.petclinic.repository.JpaStandardUtils;
import pl.zoltowskimarcin.petclinic.repository.NativeHibernateUtils;
import pl.zoltowskimarcin.petclinic.repository.entity.Client;
import pl.zoltowskimarcin.petclinic.repository.jpa.ClientRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class DefaultClientDao implements ClientDao {

    private final ClientRepository clientRepository;

    public DefaultClientDao(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    //CREATE - Native Hibernate
    @Override
    public Client saveClient(Client client) throws ClientSavingFailedException {
        log.info("save " + client + ")");

        Client clientToPersist = client;

        try (Session session = NativeHibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(clientToPersist);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error while saving client", e);
            throw new ClientSavingFailedException("Error while saving client");
        }
        log.info("save(...) = " + clientToPersist);
        return clientToPersist;
    }

    @Override
    public Optional<Client> getClientById(Long id) throws ClientReadingFailedException {
        log.info("getClientById with id: " + id);

        Client returnedClient = null;

        try (Connection connection = DataSource.getConnection();
             PreparedStatement readStatement = connection.prepareStatement(JdbcQueries.FIND_CLIENT_BY_ID)) {

            readStatement.setLong(1, id);

            try (ResultSet resultSet = readStatement.executeQuery()) {
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    String phone = resultSet.getString("phone");
                    Long idFromDb = resultSet.getLong("id");

                    returnedClient = new Client(name, surname, phone, null, null, null);
                    returnedClient.setId(idFromDb);

                    log.info("get(...) = " + returnedClient);
                }
            }
        } catch (SQLException e) {
            log.error("Error while getting client", e);
            throw new ClientReadingFailedException("Error while getting client with id: " + id);
        }
        return Optional.ofNullable(returnedClient);
    }

    //todo group by distinct
    @Override
    public Optional<Client> getClientByIdWithDetails(Long id) throws ClientReadingFailedException {
//        log.info("getClientByIdWithDetails with id: " + id);
//
//        ClientDto returnedClient = null;
//        List<BasicPetDto> pets = new ArrayList<>();
//        List<BasicAppointmentDto> appointments = new ArrayList<>();
//
//        try (Connection connection = DataSource.getConnection();
//             PreparedStatement readClientStatement = connection.prepareStatement(JdbcQueries.FIND_CLIENT_BY_ID);
//             PreparedStatement readPetsStatement = connection.prepareStatement(JdbcQueries.FIND_PETS_BY_CLIENT_ID);
//             PreparedStatement readAppointmentsStatement = connection.prepareStatement(JdbcQueries.FIND_APPOINTMENTS_BY_CLIENT_ID);
//        ) {
//            readClientStatement.setLong(1, id);
//            readPetsStatement.setLong(1, id);
//            readAppointmentsStatement.setLong(1, id);
//
//            try (
//                    ResultSet resultPetsSet = readPetsStatement.executeQuery();
//                    ResultSet resultAppointmentsSet = readAppointmentsStatement.executeQuery();
//                    ResultSet resultClientsSet = readClientStatement.executeQuery();
//            ) {
//
//                while (!resultPetsSet.wasNull() && resultPetsSet.next()) {
//                    BasicPetDto pet = new BasicPetDto.Builder()
//                            .id(resultPetsSet.getLong("id"))
//                            .name(resultPetsSet.getString("name"))
//                            .dateOfBirth(resultPetsSet.getDate("date_of_birth").toLocalDate())
//                            .build();
//                    pets.add(pet);
//                }
//
//                while (!resultAppointmentsSet.wasNull() && resultAppointmentsSet.next()) {
//                    BasicAppointmentDto appointment = new BasicAppointmentDto.Builder()
//                            .id(resultAppointmentsSet.getLong("id"))
//                            .appointmentDate(resultAppointmentsSet.getTimestamp("appointment_date").toLocalDateTime())
//                            .finished(resultAppointmentsSet.getBoolean("finished"))
//                            .build();
//                    appointments.add(appointment);
//                }
//
//                while (resultClientsSet.next()) {
//                    returnedClient = new ClientDto.Builder()
//                            .id(resultClientsSet.getLong("id"))
//                            .name(resultClientsSet.getString("name"))
//                            .surname(resultClientsSet.getString("surname"))
//                            .phone(resultClientsSet.getString("phone"))
//                            .street(resultClientsSet.getString("street"))
//                            .city(resultClientsSet.getString("city"))
//                            .postalCode(resultClientsSet.getString("postal_code"))
//                            .petDtos(pets)
//                            .appointmentDtos(appointments)
//                            .build();
//                }
//            }
//        } catch (SQLException e) {
//            log.error("Error while getting client", e);
//            throw new ClientReadingFailedException("Error while getting client with id: " + id);
//        }
//
//        log.info("get(...) = " + returnedClient);
//        return Optional.ofNullable(returnedClient);
        return null;
    }

    //READ ALL - JDBC
    @Override
    public List<Client> getAllClients() throws ClientReadingFailedException {
        log.info("getAllClients()");

        List<Client> returnedClients = new ArrayList<>();

        try (Connection connection = DataSource.getConnection();
             PreparedStatement readStatement = connection.prepareStatement(JdbcQueries.FIND_ALL_CLIENTS)) {

            try (ResultSet resultSet = readStatement.executeQuery()) {
                while (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    String phone = resultSet.getString("phone");

                    Client returnedClient = new Client(name, surname, phone, null, null, null);
                    returnedClient.setId(id);

                    returnedClients.add(returnedClient);
                }
                log.info("getAllClients(...) = " + returnedClients);
            }
        } catch (SQLException e) {
            log.error("Error while getting clients", e);
            throw new ClientReadingFailedException("Error while getting clients");
        }
        return returnedClients;
    }


    //UPDATE - Spring Data JPA
    @Override
    @Transactional
    public Client updateClient(Long id, Client client) throws ClientUpdatingFailedException {
        log.info("update " + client + " with id: " + id);

        Client clientToUpdate = clientRepository.findById(id)
                .orElseThrow(() -> new ClientUpdatingFailedException("Client with id: " + id + " doesn't exists in database."));

        clientToUpdate.setName(client.getName());
        clientToUpdate.setSurname(client.getSurname());
        clientToUpdate.setPhone(client.getPhone());

        Client updatedClient = clientRepository.save(clientToUpdate);

        log.info("update(...) = " + clientToUpdate);
        return updatedClient;
    }

    //DELETE - JpaStandard
    @Override
    public void deleteClient(Long id) throws ClientDeletingFailedException {
        EntityManager entityManager = JpaStandardUtils.getEntityManager();
        entityManager.getTransaction().begin();
        Client clientToRemove = entityManager.find(Client.class, id);

        if (clientToRemove == null) {
            entityManager.close();
            log.error("Client with id: " + id + " doesn't exists in database.");
            throw new ClientDeletingFailedException("Client with id: " + id + " doesn't exists in database.");
        }

        entityManager.remove(clientToRemove);
        entityManager.getTransaction().commit();
        entityManager.close();
        log.info("delete(...) = " + clientToRemove);
    }
}