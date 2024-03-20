package pl.zoltowskimarcin.petclinic.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.zoltowskimarcin.petclinic.exception.client.ClientSavingFailedException;
import pl.zoltowskimarcin.petclinic.repository.entity.Client;
import pl.zoltowskimarcin.petclinic.service.ClientService;

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
    public String showReadClientPage(Model model) {
        Client client = new Client();
        model.addAttribute("client", client);
        return "clients/read-client.html";
    }
}
