package pl.zoltowskimarcin.petclinic.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.zoltowskimarcin.petclinic.exception.EntityDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntityReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntitySavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntityUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.repository.dao.ClientDao;
import pl.zoltowskimarcin.petclinic.web.model.cilent.ClientDto;

import java.util.Optional;

@Service
@Slf4j
public class ClientService {

    private final ClientDao clientDao;

    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public ClientDto saveClient(ClientDto clientDto) throws EntitySavingFailedException {
        log.info("save " + clientDto + ")");
        ClientDto resultClient = clientDao.saveClient(clientDto);
        log.info("save(...) = " + resultClient);
        return resultClient;
    }

    public Optional<ClientDto> getClientById(Long id) throws EntityReadingFailedException {
        log.info("getClientById with id: " + id);
        Optional<ClientDto> resultClient = clientDao.getClientById(id);
        log.info("getClientById(...) = " + resultClient);
        return resultClient;
    }

    public ClientDto updateClient(Long id, ClientDto clientDto) throws EntityUpdatingFailedException {
        log.info("updateClient with id: " + id + " and clientDto: " + clientDto);
        ClientDto resultClient = clientDao.updateClient(id, clientDto);
        log.info("updateClient(...) = " + resultClient);
        return resultClient;
    }

    public void deleteClient(Long id) throws EntityDeletingFailedException {
        log.info("deleteClient with id: " + id);
        clientDao.deleteClient(id);
        log.info("deleteClient(...) = void");
    }
}
