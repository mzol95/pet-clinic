package pl.zoltowskimarcin.petclinic.repository.dao;

import pl.zoltowskimarcin.petclinic.exception.EntityDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntityReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntitySavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntityUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.web.model.pet.PetDto;

import java.util.Optional;

public interface PetDao {

    PetDto savePet(PetDto petDto) throws EntitySavingFailedException;

    Optional<PetDto> getPetById(Long id) throws EntityReadingFailedException;

    PetDto updatePet(Long id, PetDto petDto) throws EntityUpdatingFailedException;

    void deletePet(Long id) throws EntityDeletingFailedException;
}
