package pl.zoltowskimarcin.petclinic.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.zoltowskimarcin.petclinic.exception.EntitySavingFailedException;
import pl.zoltowskimarcin.petclinic.service.ClientService;
import pl.zoltowskimarcin.petclinic.web.model.cilent.ClientDto;

import java.net.URI;


@RestController
@RequestMapping("/clients")
@Slf4j
public class ClientController {


    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping()
    public ResponseEntity<ClientDto> create(@RequestBody ClientDto client) throws EntitySavingFailedException {
        log.info("create(" + client + ")");
        ClientDto createdClient = clientService.saveClient(client);
        log.info("create(...) = " + createdClient);
        return ResponseEntity.created(URI.create("/" + client.getId())).body(client);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Client> read(@PathVariable Long id) throws EntityReadingFailedException {
//        log.info("read(id: " + id + ")");
//        Optional<ClientDto> readClient = clientService.getClientById(id);
//        log.info("read(...) = " + readClient);
//        return ResponseEntity.ok(readClient);
//    }
//
//    @PutMapping()
//    public ResponseEntity<Client> update(Client client) {
//        LOGGER.info("update(id: " + client.getId() + ")");
//        Client resultClient = clientService.update(client);
//        LOGGER.info("update(...) succeed");
//        return ResponseEntity.ok(resultClient);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity delete(@PathVariable Long id) throws ClientDeleteFaultException {
//        LOGGER.info("delete(id: " + id + ")");
//        boolean result = clientService.delete(id);
//        LOGGER.info("delete(...) " + (result ? "succeed" : "not succeed"));
//        return ResponseEntity.ok().build();
//    }

}
