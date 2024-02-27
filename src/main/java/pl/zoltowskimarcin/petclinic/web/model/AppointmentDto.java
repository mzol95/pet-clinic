package pl.zoltowskimarcin.petclinic.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AppointmentDto {

    private LocalDateTime appointmentDate;
    private boolean finished;

    private PetDto petDto;
    private DoctorDto doctorDto;

}
