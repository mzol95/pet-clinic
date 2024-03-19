package pl.zoltowskimarcin.petclinic.web.model.appointment;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


@Setter
@Getter
@ToString
@EqualsAndHashCode
public class BasicAppointmentDto {

    private LocalDateTime appointmentDate;
    private boolean finished;

    BasicAppointmentDto() {
    }

    public BasicAppointmentDto(LocalDateTime appointmentDate, boolean finished) {
        this.appointmentDate = appointmentDate;
        this.finished = finished;
    }
}
