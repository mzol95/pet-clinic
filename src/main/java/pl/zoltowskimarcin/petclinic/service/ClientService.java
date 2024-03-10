package pl.zoltowskimarcin.petclinic.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.zoltowskimarcin.petclinic.exception.client.ClientDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.client.ClientReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.client.ClientSavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.client.ClientUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.repository.dao.ClientDao;
import pl.zoltowskimarcin.petclinic.web.model.cilent.ClientDto;
import pl.zoltowskimarcin.petclinic.web.model.cilent.LiteClientDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ClientService {

    private final ClientDao clientDao;

    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public ClientDto saveClient(ClientDto clientDto) throws ClientSavingFailedException {
        log.info("save " + clientDto + ")");

        ClientDto resultClient = clientDao.saveClient(clientDto);
        log.info("save(...) = " + resultClient);
        return resultClient;
    }

    public Optional<ClientDto> getClientById(Long id) throws ClientReadingFailedException {
        log.info("getClientById with id: " + id);
        Optional<ClientDto> client = clientDao.getClientById(id);
        ClientDto resultClient = null;

        if (client.isPresent()) { //todo lepiej wyjatek
            resultClient = client.get();
            log.info("getClientById(...) = " + resultClient);
        }
        return Optional.ofNullable(resultClient);
    }

    public Optional<ClientDto> getClientByIdWithDetails(Long id) throws ClientReadingFailedException {
        log.info("getClientByIdWithDetails with id: " + id);
        Optional<ClientDto> clientWithDetails = clientDao.getClientByIdWithDetails(id);
        log.info("getClientByIdWithDetails(...) = " + clientWithDetails);
        return clientWithDetails;
    }

    public List<LiteClientDto> getAllClients() throws ClientReadingFailedException {
        log.info("getAllClients()");
        List<ClientDto> clients = clientDao.getAllClients();

        List<LiteClientDto> mappedClients = new ArrayList<>();

        //todo
//        clients.stream().forEach(client -> {
//            LiteClientDto mappedClient = ClientMapper.getMapper().map(client, LiteClientDto.class);
//            mappedClients.add(mappedClient);
//        });

        log.info("getAllClients(...) = " + mappedClients);
        return mappedClients;
    }

    public ClientDto updateClient(Long id, ClientDto clientDto) throws ClientUpdatingFailedException {
        log.info("updateClient with id: " + id + " and clientDto: " + clientDto);
        //todo
        //ClientDto resultClient = ClientMapper.getMapper().map(clientDao.updateClient(id, clientDto), ClientDto.class);
       // log.info("updateClient(...) = " + resultClient);
        return null;
    }

    public void deleteClient(Long id) throws ClientDeletingFailedException {
        log.info("deleteClient with id: " + id);
        clientDao.deleteClient(id);
        log.info("deleteClient(...) = void");
    }
}
