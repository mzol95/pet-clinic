package pl.zoltowskimarcin.petclinic.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/menu")
public class MenuWebController {

    @GetMapping
    public String getMenu() {
        return "clients/menu.html";
    }


}
