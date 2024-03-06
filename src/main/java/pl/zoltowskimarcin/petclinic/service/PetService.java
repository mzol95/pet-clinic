package pl.zoltowskimarcin.petclinic.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.zoltowskimarcin.petclinic.exception.EntityDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntityReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntitySavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntityUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.repository.dao.PetDao;
import pl.zoltowskimarcin.petclinic.web.model.pet.PetDto;

import java.util.Optional;

@Service
@Slf4j
public class PetService {

    private final PetDao petDao;

    public PetService(PetDao petDao) {
        this.petDao = petDao;
    }

    //CREATE
    public PetDto savePet(PetDto petDto) throws EntitySavingFailedException {
        log.info("savePet(" + petDto + ")");
        PetDto resultPet = petDao.savePet(petDto);
        log.info("savePet(...) = " + resultPet);
        return resultPet;
    }

    //READ
    public Optional<PetDto> getPetById(Long id) throws EntityReadingFailedException {
        log.info("getPetById with id: " + id);
        Optional<PetDto> resultPet = petDao.getPetById(id);
        log.info("getPetById(...) = " + resultPet);
        return resultPet;
    }

    //UPDATE
    public PetDto updatePet(Long id, PetDto petDto) throws EntityUpdatingFailedException {
        log.info("updatePet with id: " + id + " and petDto: " + petDto);
        PetDto resultPet = petDao.updatePet(id, petDto);
        log.info("updatePet(...) = " + resultPet);
        return resultPet;
    }

    //DELETE
    public void deletePet(Long id) throws EntityDeletingFailedException {
        log.info("deletePet with id: " + id);
        petDao.deletePet(id);
        log.info("deletePet(...) = void");
    }
}
