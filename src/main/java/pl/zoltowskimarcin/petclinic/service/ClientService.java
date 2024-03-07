package pl.zoltowskimarcin.petclinic.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.zoltowskimarcin.petclinic.exception.EntityDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntityReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntitySavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntityUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.mapper.ClientMapper;
import pl.zoltowskimarcin.petclinic.repository.dao.ClientDao;
import pl.zoltowskimarcin.petclinic.web.model.cilent.BasicClientDto;
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

    public BasicClientDto saveClient(ClientDto clientDto) throws EntitySavingFailedException {
        log.info("save " + clientDto + ")");
        BasicClientDto resultClient = ClientMapper.getMapper().map(clientDao.saveClient(clientDto), BasicClientDto.class);
        log.info("save(...) = " + resultClient);
        return resultClient;
    }

    public Optional<BasicClientDto> getClientById(Long id) throws EntityReadingFailedException {
        log.info("getClientById with id: " + id);
        Optional<ClientDto> client = clientDao.getClientById(id);
        BasicClientDto resultClient = null;

        if (client.isPresent()) { //todo lepiej wyjatek
            resultClient = ClientMapper.getMapper().map(client.get(), BasicClientDto.class);
            log.info("getClientById(...) = " + resultClient);
        }
        return Optional.ofNullable(resultClient);
    }

    public Optional<ClientDto> getClientByIdWithDetails(Long id) throws EntityReadingFailedException {
        log.info("getClientByIdWithDetails with id: " + id);
        Optional<ClientDto> clientWithDetails  = clientDao.getClientByIdWithDetails(id);
        log.info("getClientByIdWithDetails(...) = " + clientWithDetails);
        return clientWithDetails;
    }

    public List<LiteClientDto> getAllClients() throws EntityReadingFailedException {
        log.info("getAllClients()");
        List<ClientDto> clients = clientDao.getAllClients();

        List<LiteClientDto> mappedClients = new ArrayList<>();

        clients.stream().forEach(client -> {
            LiteClientDto mappedClient = ClientMapper.getMapper().map(client, LiteClientDto.class);
            mappedClients.add(mappedClient);
        });

        log.info("getAllClients(...) = " + mappedClients);
        return mappedClients;
    }

    public BasicClientDto updateClient(Long id, ClientDto clientDto) throws EntityUpdatingFailedException {
        log.info("updateClient with id: " + id + " and clientDto: " + clientDto);
        BasicClientDto resultClient = ClientMapper.getMapper().map(clientDao.updateClient(id, clientDto), BasicClientDto.class);
        log.info("updateClient(...) = " + resultClient);
        return resultClient;
    }

    public void deleteClient(Long id) throws EntityDeletingFailedException {
        log.info("deleteClient with id: " + id);
        clientDao.deleteClient(id);
        log.info("deleteClient(...) = void");
    }
}
