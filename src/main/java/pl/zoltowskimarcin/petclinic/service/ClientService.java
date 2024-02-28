package pl.zoltowskimarcin.petclinic.service;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import pl.zoltowskimarcin.petclinic.exception.Client.ClientException;
import pl.zoltowskimarcin.petclinic.exception.Client.ClientSavingFailedException;
import pl.zoltowskimarcin.petclinic.jdbc.DataSource;
import pl.zoltowskimarcin.petclinic.jdbc.JdbcQueries;
import pl.zoltowskimarcin.petclinic.mapper.ClientMapper;
import pl.zoltowskimarcin.petclinic.repository.HibernateUtils;
import pl.zoltowskimarcin.petclinic.repository.entity.Address;
import pl.zoltowskimarcin.petclinic.repository.entity.Client;
import pl.zoltowskimarcin.petclinic.web.model.ClientDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
@Slf4j
public class ClientService {

    //CREATE - Native Hibernate
    public ClientDto saveClient(ClientDto clientDto) throws ClientException {
        log.info("save " + clientDto + ")");
        Client clientToPersist = ClientMapper.getMapper().map(clientDto, Client.class);

        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            session.beginTransaction();
            session.persist(clientToPersist);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error while saving client", e);
            throw new ClientSavingFailedException("Error while saving client");
        }
        log.info("save address = " + clientToPersist.getAddress());
        log.info("save(...) = " + clientToPersist);
        return ClientMapper.getMapper().map(clientToPersist, ClientDto.class);
    }

    //READ - JDBC
    public ClientDto getClient(Long id) {
        log.info("Getting client with id: " + id);


        try (Connection connection = DataSource.getConnection();
             PreparedStatement readStatement = connection.prepareStatement(JdbcQueries.FIND_CLIENT_BY_ID)) {
            readStatement.setLong(1, id);

            try (ResultSet resultSet = readStatement.executeQuery()) {
                if (resultSet.next()) {
                    Client client = new Client();
                    client.setId(resultSet.getLong(1));
                    client.setName(resultSet.getString(2));
                    client.setSurname(resultSet.getString(3));
                    client.setPhone(resultSet.getString(4));

                    Address address = new Address();
                    address.setStreet(resultSet.getString(5));
                    address.setCity(resultSet.getString(6));
                    address.setPostalCode(resultSet.getString(7));

                    client.setAddress(address);

                    return ClientMapper.getMapper().map(client, ClientDto.class);
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e); //TODO: Create custom exception
        }


        return null;
    }

    //UPDATE
    public Client updateClient() {
        log.info("Updating client");
        return null;
    }

    //DELETE
    public void deleteClient() {
        log.info("Deleting client");
    }
}
