package pl.zoltowskimarcin.petclinic.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.zoltowskimarcin.petclinic.repository.entity.Client;

@Service
@Slf4j
public class ClientService {

    //CREATE
    public Client saveClient() {
        log.info("Saving client");
        return null;
    }

    //READ
    public Client getClient() {
        log.info("Getting client");
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
