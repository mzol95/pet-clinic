package pl.zoltowskimarcin.petclinic.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import pl.zoltowskimarcin.petclinic.exception.client.ClientException;
import pl.zoltowskimarcin.petclinic.exception.client.ClientReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.client.ClientSavingFailedException;
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

@Service
@Slf4j
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    //CREATE - Native Hibernate
    public ClientDto saveClient(ClientDto clientDto) throws ClientException {
        log.info("save " + clientDto + ")");
        Client clientToPersist = ClientMapper.getMapper().map(clientDto, Client.class);

        try (Session session = NativeHibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(clientToPersist);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error while saving client", e);
            throw new ClientSavingFailedException("Error while saving client");
        }
        log.info("save(...) = " + clientToPersist);
        return ClientMapper.getMapper().map(clientToPersist, ClientDto.class);
    }

    //READ - JDBC
    public Optional<ClientDto> getClientById(Long id) throws ClientReadingFailedException {
        log.info("getClientById with id: " + id);

        try (Connection connection = DataSource.getConnection();
             PreparedStatement readStatement = connection.prepareStatement(JdbcQueries.FIND_CLIENT_BY_ID)) {
            readStatement.setLong(1, id);

            try (ResultSet resultSet = readStatement.executeQuery()) {
                if (resultSet.next()) {
                    ClientDto returnedClient = new ClientDto();
                    returnedClient.setName(resultSet.getString(2));
                    returnedClient.setSurname(resultSet.getString(3));
                    returnedClient.setPhone(resultSet.getString(4));
                    returnedClient.setStreet(resultSet.getString(5));
                    returnedClient.setCity(resultSet.getString(6));
                    returnedClient.setPostalCode(resultSet.getString(7));

                    log.info("get(...) = " + returnedClient);
                    return Optional.ofNullable(returnedClient);
                }
            }
        } catch (SQLException e) {
            log.error("Error while getting client", e);
            throw new ClientReadingFailedException("Client with id: " + id + " doesn't exists in database.");
        }
        return Optional.empty();
    }

    //UPDATE - Spring Data JPA
    @Transactional
    public ClientDto updateClient(Long id, ClientDto clientDto) throws ClientReadingFailedException {
        log.info("update " + clientDto + " with id: " + id);

        Client clientToUpdate = clientRepository.findById(id)
                .orElseThrow(() -> new ClientReadingFailedException("Client with id: " + id + " doesn't exists in database."));

        clientToUpdate.setName(clientDto.getName());
        clientToUpdate.setSurname(clientDto.getSurname());
        clientToUpdate.setPhone(clientDto.getPhone());
        clientToUpdate.getAddress().setStreet(clientDto.getStreet());
        clientToUpdate.getAddress().setCity(clientDto.getCity());
        clientToUpdate.getAddress().setPostalCode(clientDto.getPostalCode());
        clientRepository.save(clientToUpdate);

        log.info("update(...) = " + clientToUpdate);
        return ClientMapper.getMapper().map(clientToUpdate, ClientDto.class);
    }

    //DELETE - JpaStandard
    public void deleteClient(Long id) throws ClientReadingFailedException {
        EntityManager entityManager = JpaStandardUtils.getEntityManager();
        entityManager.getTransaction().begin();

        Client clientToRemove = entityManager.find(Client.class, id);

        if (clientToRemove == null) {
            entityManager.close();
            log.error("Client with id: " + id + " doesn't exists in database.");
            throw new ClientReadingFailedException("Client with id: " + id + " doesn't exists in database.");
        }
        entityManager.remove(clientToRemove);
        entityManager.getTransaction().commit();
        entityManager.close();
        log.info("delete(...) = " + clientToRemove);
    }
}
