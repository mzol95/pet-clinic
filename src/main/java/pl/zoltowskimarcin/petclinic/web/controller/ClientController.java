package pl.zoltowskimarcin.petclinic.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.zoltowskimarcin.petclinic.exception.client.ClientDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.client.ClientReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.client.ClientSavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.client.ClientUpdatingFailedException;
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
    public ResponseEntity<BasicClientDto> create(@RequestBody ClientDto client) throws ClientSavingFailedException {
        log.info("create(" + client + ")");
        BasicClientDto createdClient = clientService.saveClient(client);
        log.info("create(...) = " + createdClient);
        return ResponseEntity.created(URI.create("/" + client.getId())).body(createdClient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BasicClientDto> readById(@PathVariable Long id) throws ClientReadingFailedException {
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
    public ResponseEntity<ClientDto> readByIdWithDetails(@PathVariable Long id) throws ClientReadingFailedException {
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
    public ResponseEntity<List<LiteClientDto>> readAllClients() throws ClientReadingFailedException {
        log.info("readAllClients()");
        List<LiteClientDto> clients = clientService.getAllClients();
        log.info("readAllClients(...) = " + clients);
        return ResponseEntity.ok(clients);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BasicClientDto> update(@PathVariable Long id, ClientDto client) throws ClientUpdatingFailedException {
        log.info("update(" + client + ")");
        BasicClientDto resultClient = clientService.updateClient(id, client);
        log.info("update(...) = " + resultClient);
        return ResponseEntity.ok(resultClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws ClientDeletingFailedException {
        log.info("delete(id: " + id + ")");
        clientService.deleteClient(id);
        log.info("delete(...) succeed");
        return ResponseEntity.ok().build();
    }

}
