package pl.zoltowskimarcin.petclinic.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.zoltowskimarcin.petclinic.exception.pet.PetDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.pet.PetReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.pet.PetSavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.pet.PetUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.mapper.PetMapper;
import pl.zoltowskimarcin.petclinic.repository.dao.PetDao;
import pl.zoltowskimarcin.petclinic.repository.entity.Pet;
import pl.zoltowskimarcin.petclinic.web.model.pet.PetDto;

@Service
@Slf4j
public class PetService {

    private final PetDao petDao;

    public PetService(PetDao petDao) {
        this.petDao = petDao;
    }

    //CREATE
    public PetDto savePet(Pet pet) throws PetSavingFailedException {
        log.info("savePet(" + pet + ")");
        Pet resultPet = petDao.savePet(pet);
        log.info("savePet(...) = " + resultPet);
        return new PetMapper().mapToDto(resultPet, PetDto.class);
    }

    //READ
    public PetDto getPetById(Long id) throws PetReadingFailedException {
        log.info("getPetById with id: " + id);

        Pet mappedPet = petDao.getPetById(id)
                .orElseThrow(() -> new PetReadingFailedException("Error while reading pet"));

        PetDto resultPet = new PetMapper().mapToDto(mappedPet, PetDto.class);

        log.info("getPetById(...) = " + resultPet);
        return resultPet;
    }

    //UPDATE
    public PetDto updatePet(Long id, Pet pet) throws PetUpdatingFailedException {
        log.info("updatePet with id: " + id + " and petDto: " + pet);
        PetDto resultPet = new PetMapper().mapToDto(petDao.updatePet(id, pet), PetDto.class);
        log.info("updatePet(...) = " + resultPet);
        return resultPet;
    }

    //DELETE
    public void deletePet(Long id) throws PetDeletingFailedException {
        log.info("deletePet with id: " + id);
        petDao.deletePet(id);
        log.info("deletePet(...) = void");
    }
}
