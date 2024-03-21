package pl.zoltowskimarcin.petclinic.web.model.cilent;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.zoltowskimarcin.petclinic.repository.entity.Address;
import pl.zoltowskimarcin.petclinic.web.model.appointment.BasicAppointmentDto;
import pl.zoltowskimarcin.petclinic.web.model.pet.BasicPetDto;

import java.util.List;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class ClientDto {
    private Long id;
    private String name;
    private String surname;
    private String phone;

    private Address address;
    private List<BasicPetDto> petDtos;
    private List<BasicAppointmentDto> appointmentDtos;

    private ClientDto() {
    }

    public ClientDto(String name, String surname, String phone, Address address, List<BasicPetDto> petDtos, List<BasicAppointmentDto> appointmentDtos) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
        this.petDtos = petDtos;
        this.appointmentDtos = appointmentDtos;
    }
}
