package pl.zoltowskimarcin.petclinic.web.model;

import lombok.Data;

import java.util.List;

@Data
public class ClientDto {

    private String name;
    private String surname;
    private String street;
    private String city;
    private String postalCode;
    private String phone;

    private List<PetDto> petDtos;
    private List<AppointmentDto> appointmentDtos;

    public ClientDto() {
    }

    public ClientDto(String name, String surname, String street, String city, String postalCode, String phone) {
        this.name = name;
        this.surname = surname;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.phone = phone;
    }
}
