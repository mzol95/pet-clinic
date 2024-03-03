package pl.zoltowskimarcin.petclinic.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.zoltowskimarcin.petclinic.exception.EntityDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntityReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntitySavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntityUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.repository.dao.ClientDao;
import pl.zoltowskimarcin.petclinic.web.model.cilent.BasicClientDto;

import java.util.Optional;

@Service
@Slf4j
public class ClientService {

    private final ClientDao clientDao;

    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public BasicClientDto saveClient(BasicClientDto basicClientDto) throws EntitySavingFailedException {
        log.info("save " + basicClientDto + ")");
        BasicClientDto resultClient = clientDao.saveClient(basicClientDto);
        log.info("save(...) = " + resultClient);
        return resultClient;
    }

    public Optional<BasicClientDto> getClientById(Long id) throws EntityReadingFailedException {
        log.info("getClientById with id: " + id);
        Optional<BasicClientDto> resultClient = clientDao.getClientById(id);
        log.info("getClientById(...) = " + resultClient);
        return resultClient;
    }

    public BasicClientDto updateClient(Long id, BasicClientDto basicClient) throws EntityUpdatingFailedException {
        log.info("updateClient with id: " + id + " and basicClient: " + basicClient);
        BasicClientDto resultClient = clientDao.updateClient(id, basicClient);
        log.info("updateClient(...) = " + resultClient);
        return resultClient;
    }

    public void deleteClient(Long id) throws EntityDeletingFailedException {
        log.info("deleteClient with id: " + id);
        clientDao.deleteClient(id);
        log.info("deleteClient(...) = void");
    }
}
