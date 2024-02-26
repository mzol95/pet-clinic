package pl.zoltowskimarcin.petclinic.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AppointmentService {

    //CREATE
    public void saveAppointment() {
        System.out.println("Saving appointment");
    }

    //READ
    public void getAppointment() {
        System.out.println("Getting appointment");
    }

    //UPDATE
    public void updateAppointment() {
        System.out.println("Updating appointment");
    }

    //DELETE
    public void deleteAppointment() {
        System.out.println("Deleting appointment");
    }
}
