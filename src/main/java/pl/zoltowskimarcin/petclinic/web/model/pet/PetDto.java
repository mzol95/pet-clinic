package pl.zoltowskimarcin.petclinic.web.model.pet;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.zoltowskimarcin.petclinic.web.enums.Gender;
import pl.zoltowskimarcin.petclinic.web.model.appointment.AppointmentDto;
import pl.zoltowskimarcin.petclinic.web.model.cilent.ClientDto;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class PetDto {
    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;

    private ClientDto clientDto;
    private List<AppointmentDto> appointmentDtos;

    PetDto() {
    }

    public PetDto(String name, LocalDate dateOfBirth, Gender gender) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }
}
