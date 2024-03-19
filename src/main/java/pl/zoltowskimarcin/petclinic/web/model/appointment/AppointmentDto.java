package pl.zoltowskimarcin.petclinic.web.model.appointment;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.zoltowskimarcin.petclinic.web.model.DoctorDto;
import pl.zoltowskimarcin.petclinic.web.model.pet.PetDto;

import java.time.LocalDateTime;


@Setter
@Getter
@ToString
@EqualsAndHashCode
public class AppointmentDto {
    private LocalDateTime appointmentDate;
    private boolean finished;

    private PetDto petDto;
    private DoctorDto doctorDto;


    AppointmentDto() {
    }

    public AppointmentDto(LocalDateTime appointmentDate, boolean finished) {
        this.appointmentDate = appointmentDate;
        this.finished = finished;
    }
}
