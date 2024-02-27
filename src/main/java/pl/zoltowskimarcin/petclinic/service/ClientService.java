package pl.zoltowskimarcin.petclinic.service;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import pl.zoltowskimarcin.exception.Client.ClientException;
import pl.zoltowskimarcin.exception.Client.ClientSavingFailedException;
import pl.zoltowskimarcin.petclinic.mapper.ClientMapper;
import pl.zoltowskimarcin.petclinic.repository.HibernateUtils;
import pl.zoltowskimarcin.petclinic.repository.entity.Client;
import pl.zoltowskimarcin.petclinic.repository.jpa.ClientRepository;
import pl.zoltowskimarcin.petclinic.web.model.ClientDto;

@Service
@Slf4j
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    //CREATE
    public ClientDto saveClient(ClientDto clientDto) throws ClientException {
        log.info("save " + clientDto + ")");
        Client clientToPersist = ClientMapper.getMapper().map(clientDto, Client.class);
        log.info("save address after = " + clientToPersist.getAddress());

        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
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

    //READ
    public Client getClient(Long id) {
        log.info("get client(ID: " + id + ")");
        Client resultClient = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.find(Client.class, id);
        } catch (Exception e) {
            log.error("Error while getting client", e);
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
