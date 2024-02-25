package pl.zoltowskimarcin.petclinic.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class WorkDay {

    private LocalDateTime dateOfWork;
    private LocalTime startOfWork;
    private LocalTime endOfWork;

}
