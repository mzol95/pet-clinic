package pl.zoltowskimarcin.petclinic.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;


@RestController
//@RequestMapping("/clients")
@Slf4j
public class ClientController {

//    private final ClientService clientService;
//
//    public ClientController(ClientService clientService) {
//        this.clientService = clientService;
//    }
//
//    @PostMapping()
//    public ResponseEntity<ClientDto> create(@RequestBody Client client) throws ClientSavingFailedException {
//        log.info("create(" + client + ")");
//        ClientDto createdClient = clientService.saveClient(client);
//        log.info("create(...) = " + createdClient);
//        return ResponseEntity.created(URI.create("/" + 1)).body(createdClient);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ClientDto> readById(@PathVariable Long id) throws ClientReadingFailedException {
//        log.info("read(id: " + id + ")");
//        ClientDto readClient = clientService.getClientById(id);
//        log.info("read(...) = " + readClient);
//        return ResponseEntity.ok(readClient);
//
//    }
//
//    @GetMapping("/{id}/details")
//    public ResponseEntity<ClientDto> readByIdWithDetails(@PathVariable Long id) throws ClientReadingFailedException {
//        log.info("read(id: " + id + ")");
//        ClientDto readClient = clientService.getClientByIdWithDetails(id);
//        log.info("read(...) = " + readClient);
//        return ResponseEntity.ok(readClient);
//
//    }
//
//    @GetMapping()
//    public ResponseEntity<List<LiteClientDto>> readAllClients() throws ClientReadingFailedException {
//        log.info("readAllClients()");
//        List<LiteClientDto> clients = clientService.getAllClients();
//        log.info("readAllClients(...) = " + clients);
//        return ResponseEntity.ok(clients);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<ClientDto> update(@PathVariable Long id, Client client) throws ClientUpdatingFailedException {
//        log.info("update(" + client + ")");
//        ClientDto resultClient = clientService.updateClient(id, client);
//        log.info("update(...) = " + resultClient);
//        return ResponseEntity.ok(resultClient);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity delete(@PathVariable Long id) throws ClientDeletingFailedException {
//        log.info("delete(id: " + id + ")");
//        clientService.deleteClient(id);
//        log.info("delete(...) succeed");
//        return ResponseEntity.ok().build();
//    }

}
