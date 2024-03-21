package pl.zoltowskimarcin.petclinic.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.zoltowskimarcin.petclinic.exception.client.ClientReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.client.ClientSavingFailedException;
import pl.zoltowskimarcin.petclinic.repository.entity.Client;
import pl.zoltowskimarcin.petclinic.service.ClientService;
import pl.zoltowskimarcin.petclinic.web.model.cilent.ClientDto;

@Controller
@RequestMapping(value = "/clients")
public class ClientWebController {

    private ClientService clientService;

    public ClientWebController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/create")
    public String showAddClientPage(Model model) {
        Client client = new Client();
        model.addAttribute("client", client);
        return "clients/add-client.html";
    }

    @PostMapping("/create")
    public String addClient(Client client) throws ClientSavingFailedException {
        clientService.saveClient(client);
        return "clients/add-client.html";
    }

    @GetMapping("/read")
    public String showReadClientPage(@RequestParam(name = "clientId", required = false) Long clientId, Model model) {
        if(clientId != null) {
            ClientDto client = null;
            try {
                client = clientService.getClientById(clientId);
            } catch (ClientReadingFailedException e) {
                e.printStackTrace(); //todo
            }
            model.addAttribute("client", client);
        }
        return "clients/read-client.html";
    }

//    @GetMapping("/read")
//    public String showReadClientPage2(@PathVariable Long clientId, Model model) throws ClientReadingFailedException {
//        ClientDto clientDto = clientService.getClientById(clientId);
//        model.addAttribute("client", clientDto);
//        return "test";
//    }


}
