package pl.zoltowskimarcin.petclinic.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.zoltowskimarcin.petclinic.exception.client.ClientDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.client.ClientReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.client.ClientSavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.client.ClientUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.mapper.ClientMapper;
import pl.zoltowskimarcin.petclinic.repository.dao.ClientDao;
import pl.zoltowskimarcin.petclinic.repository.entity.Client;
import pl.zoltowskimarcin.petclinic.web.model.cilent.ClientDto;
import pl.zoltowskimarcin.petclinic.web.model.cilent.LiteClientDto;

import java.util.List;

@Service
@Slf4j
public class ClientService {

    private final ClientDao clientDao;

    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public ClientDto saveClient(Client client) throws ClientSavingFailedException {
        log.info("save " + client + ")");
        Client resultClient = clientDao.saveClient(client);
        log.info("save(...) = " + resultClient);
        return new ClientMapper().mapToDto(resultClient, ClientDto.class);
    }

    public ClientDto getClientById(Long id) throws ClientReadingFailedException {
        log.info("getClientById with id: " + id);

        Client resultClient = clientDao.getClientById(id)
                .orElseThrow(() -> new ClientReadingFailedException("Client not found"));

        log.info("getClientById(...) = " + resultClient);

        return new ClientMapper().mapToDto(resultClient, ClientDto.class);
    }

    public ClientDto getClientByIdWithDetails(Long id) throws ClientReadingFailedException {
        log.info("getClientByIdWithDetails with id: " + id);

        Client resultClient = clientDao.getClientByIdWithDetails(id)
                .orElseThrow(() -> new ClientReadingFailedException("Client not found"));

        log.info("getClientByIdWithDetails(...) = " + resultClient);
        return new ClientMapper().mapToDto(resultClient, ClientDto.class);
    }

    public List<LiteClientDto> getAllClients() throws ClientReadingFailedException {
        log.info("getAllClients()");
        List<Client> clients = clientDao.getAllClients();
        List<LiteClientDto> mappedClients = new ClientMapper().mapToDtoList(clients, LiteClientDto.class);
        log.info("getAllClients(...) = " + mappedClients);
        return mappedClients;
    }

    public ClientDto updateClient(Long id, Client client) throws ClientUpdatingFailedException {
        log.info("updateClient with id: " + id + " and client: " + client);
        Client resultClient = clientDao.updateClient(id, client);
        log.info("updateClient(...) = " + resultClient);
        return new ClientMapper().mapToDto(resultClient, ClientDto.class);
    }

    public void deleteClient(Long id) throws ClientDeletingFailedException {
        log.info("deleteClient with id: " + id);
        clientDao.deleteClient(id);
        log.info("deleteClient(...) = void");
    }
}
