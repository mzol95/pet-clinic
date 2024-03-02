package pl.zoltowskimarcin.petclinic.repository.dao;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import pl.zoltowskimarcin.petclinic.exception.client.EntityDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.client.EntityReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.client.EntitySavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.client.EntityUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.jdbc.DataSource;
import pl.zoltowskimarcin.petclinic.jdbc.JdbcQueries;
import pl.zoltowskimarcin.petclinic.mapper.ClientMapper;
import pl.zoltowskimarcin.petclinic.repository.JpaStandardUtils;
import pl.zoltowskimarcin.petclinic.repository.NativeHibernateUtils;
import pl.zoltowskimarcin.petclinic.repository.entity.Client;
import pl.zoltowskimarcin.petclinic.repository.jpa.ClientRepository;
import pl.zoltowskimarcin.petclinic.web.model.ClientDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
@Slf4j
public class ClientDaoImpl implements ClientDao {

    private final ClientRepository clientRepository;

    public ClientDaoImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    //CREATE - Native Hibernate
    @Override
    public ClientDto saveClient(ClientDto clientDto) throws EntitySavingFailedException {
        log.info("save " + clientDto + ")");
        Client clientToPersist = ClientMapper.getMapper().map(clientDto, Client.class);

        try (Session session = NativeHibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(clientToPersist);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error while saving client", e);
            throw new EntitySavingFailedException("Error while saving client");
        }
        log.info("save(...) = " + clientToPersist);
        return ClientMapper.getMapper().map(clientToPersist, ClientDto.class);
    }

    //READ - JDBC
    @Override
    public Optional<ClientDto> getClientById(Long id) throws EntityReadingFailedException {
        log.info("getClientById with id: " + id);

        try (Connection connection = DataSource.getConnection();
             PreparedStatement readStatement = connection.prepareStatement(JdbcQueries.FIND_CLIENTS_BY_ID)) {

            readStatement.setLong(1, id);

            try (ResultSet resultSet = readStatement.executeQuery()) {
                if (resultSet.next()) {
                    ClientDto returnedClient = new ClientDto.Builder()
                            .name(resultSet.getString("name"))
                            .surname(resultSet.getString("surname"))
                            .phone(resultSet.getString("phone"))
                            .street(resultSet.getString("street"))
                            .city(resultSet.getString("city"))
                            .postalCode(resultSet.getString("postal_code"))
                            .build();
                    log.info("get(...) = " + returnedClient);
                    return Optional.ofNullable(returnedClient);
                }
            }
        } catch (SQLException e) {
            log.error("Error while getting client", e);
            throw new EntityReadingFailedException("Error while getting client with id: " + id);
        }
        return Optional.empty();
    }

    //UPDATE - Spring Data JPA
    @Override
    @Transactional
    public ClientDto updateClient(Long id, ClientDto clientDto) throws EntityUpdatingFailedException {
        log.info("update " + clientDto + " with id: " + id);

        Client clientToUpdate = clientRepository.findById(id)
                .orElseThrow(() -> new EntityUpdatingFailedException("Client with id: " + id + " doesn't exists in database."));

        clientToUpdate.setName(clientDto.getName());
        clientToUpdate.setSurname(clientDto.getSurname());
        clientToUpdate.setPhone(clientDto.getPhone());
        clientToUpdate.getAddresses().setStreet(clientDto.getStreet());
        clientToUpdate.getAddresses().setCity(clientDto.getCity());
        clientToUpdate.getAddresses().setPostalCode(clientDto.getPostalCode());
        clientRepository.save(clientToUpdate);

        log.info("update(...) = " + clientToUpdate);
        return ClientMapper.getMapper().map(clientToUpdate, ClientDto.class);
    }

    //DELETE - JpaStandard
    @Override
    public void deleteClient(Long id) throws EntityDeletingFailedException {
        EntityManager entityManager = JpaStandardUtils.getEntityManager();
        entityManager.getTransaction().begin();
        Client clientToRemove = entityManager.find(Client.class, id);
        if (clientToRemove == null) {
            entityManager.close();
            log.error("Client with id: " + id + " doesn't exists in database.");
            throw new EntityDeletingFailedException("Client with id: " + id + " doesn't exists in database.");
        }
        entityManager.remove(clientToRemove);
        entityManager.getTransaction().commit();
        entityManager.close();
        log.info("delete(...) = " + clientToRemove);
    }
}
