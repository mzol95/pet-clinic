package pl.zoltowskimarcin.petclinic.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Doctor {

    private String name;
    private String surname;

    private List<Appointment> appointments;
    private WorkSchedule workSchedule;

}
