package pl.zoltowskimarcin.petclinic.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WorkScheduleService {

    //CREATE
    public void saveWorkSchedule() {
        log.info("Saving work schedule");
    }

    //READ
    public void getWorkSchedule() {
        log.info("Getting work schedule");
    }

    //UPDATE
    public void updateWorkSchedule() {
        log.info("Updating work schedule");
    }

    //DELETE
    public void deleteWorkSchedule() {
        log.info("Deleting work schedule");
    }
}
