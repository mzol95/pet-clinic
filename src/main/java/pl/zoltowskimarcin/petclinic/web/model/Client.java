package pl.zoltowskimarcin.petclinic.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Client {

    private String name;
    private String surname;
    private Address address;
    private String phone;

    private List<Pet> pets;
    private List<Appointment> appointments;

}
