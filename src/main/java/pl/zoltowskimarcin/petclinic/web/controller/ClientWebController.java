package pl.zoltowskimarcin.petclinic.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.zoltowskimarcin.petclinic.web.model.cilent.ClientDto;

import java.util.List;

@Controller
@RequestMapping(value = "/clients")
public class ClientWebController {

    @GetMapping
    public String listClients(ModelMap modelMap) {
        List<ClientDto> clients = List.of(
                new ClientDto.Builder().name("imie").surname("nazwisko").build(),
                new ClientDto.Builder().name("Jan").surname("Kowalski").build()
        );

        modelMap.addAttribute("clients", clients);

        return "clients/clients.html";
    }
}