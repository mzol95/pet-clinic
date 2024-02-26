package pl.zoltowskimarcin.petclinic.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PetService {

    //CREATE
    public void savePet() {
        log.info("Saving pet");
    }

    //READ
    public void getPet() {
        log.info("Getting pet");
    }

    //UPDATE
    public void updatePet() {
        log.info("Updating pet");
    }

    //DELETE
    public void deletePet() {
        log.info("Deleting pet");
    }
}
