package pl.zoltowskimarcin.petclinic.web.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.zoltowskimarcin.petclinic.web.model.appointment.AppointmentDto;

import java.util.List;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class DoctorDto {
    private String name;
    private String surname;
    private List<AppointmentDto> appointmentDtos;

    private DoctorDto() {
    }

    public DoctorDto(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }


}

