package pl.zoltowskimarcin.petclinic.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Appointment {

    private LocalDateTime appointmentDate;
    private boolean finished;

    private Pet pet;
    private Doctor doctor;

}
