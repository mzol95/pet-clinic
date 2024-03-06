package pl.zoltowskimarcin.petclinic.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.zoltowskimarcin.petclinic.exception.EntityDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntityReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntitySavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntityUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.service.ClientService;
import pl.zoltowskimarcin.petclinic.web.model.cilent.BasicClientDto;
import pl.zoltowskimarcin.petclinic.web.model.cilent.ClientDto;
import pl.zoltowskimarcin.petclinic.web.model.cilent.LiteClientDto;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/clients")
@Slf4j
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping()
    public ResponseEntity<BasicClientDto> create(@RequestBody ClientDto client) throws EntitySavingFailedException {
        log.info("create(" + client + ")");
        BasicClientDto createdClient = clientService.saveClient(client);
        log.info("create(...) = " + createdClient);
        return ResponseEntity.created(URI.create("/" + client.getId())).body(createdClient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BasicClientDto> readById(@PathVariable Long id) throws EntityReadingFailedException {
        log.info("read(id: " + id + ")");
        Optional<BasicClientDto> readClient = clientService.getClientById(id);

        if (readClient.isPresent()) {
            log.info("read(...) = " + readClient);
            return ResponseEntity.ok(readClient.get());
        } else {
            log.info("read(...) = null");
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<ClientDto> readByIdWithDetails(@PathVariable Long id) throws EntityReadingFailedException {
        log.info("read(id: " + id + ")");
        Optional<ClientDto> readClient = clientService.getClientByIdWithDetails(id);

        if (readClient.isPresent()) {
            log.info("read(...) = " + readClient);
            return ResponseEntity.ok(readClient.get());
        } else {
            log.info("read(...) = null");
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<LiteClientDto>> readAllClients() throws EntityReadingFailedException {
        log.info("readAllClients()");
        List<LiteClientDto> clients = clientService.getAllClients();
        log.info("readAllClients(...) = " + clients);
        return ResponseEntity.ok(clients);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BasicClientDto> update(@PathVariable Long id, ClientDto client) throws EntityUpdatingFailedException {
        log.info("update(" + client + ")");
        BasicClientDto resultClient = clientService.updateClient(id, client);
        log.info("update(...) = " + resultClient);
        return ResponseEntity.ok(resultClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws EntityDeletingFailedException {
        log.info("delete(id: " + id + ")");
        clientService.deleteClient(id);
        log.info("delete(...) succeed");
        return ResponseEntity.ok().build();
    }

}
