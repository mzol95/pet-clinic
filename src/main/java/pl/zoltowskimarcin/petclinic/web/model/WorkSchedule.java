package pl.zoltowskimarcin.petclinic.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class WorkSchedule {

    private List<WorkDay> workDay;

}
