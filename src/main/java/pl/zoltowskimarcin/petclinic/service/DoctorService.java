package pl.zoltowskimarcin.petclinic.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DoctorService {

    //CREATE
    public void saveDoctor() {
        log.info("Saving doctor");
    }

    //READ
    public void getDoctor() {
        log.info("Getting doctor");
    }

    //UPDATE
    public void updateDoctor() {
        log.info("Updating doctor");
    }

    //DELETE
    public void deleteDoctor() {
        log.info("Deleting doctor");
    }
}
