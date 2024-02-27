package pl.zoltowskimarcin.petclinic.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DoctorDto {

    private String name;
    private String surname;

    private List<AppointmentDto> appointmentDtos;
    private WorkScheduleDto workScheduleDto;

}
