package pl.zoltowskimarcin.petclinic.repository.dao;

import pl.zoltowskimarcin.petclinic.exception.pet.PetDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.pet.PetReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.pet.PetSavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.pet.PetUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.web.model.pet.PetDto;

import java.util.Optional;

public interface PetDao {

    PetDto savePet(PetDto petDto) throws PetSavingFailedException;

    Optional<PetDto> getPetById(Long id) throws PetReadingFailedException;

    PetDto updatePet(Long id, PetDto petDto) throws PetUpdatingFailedException;

    void deletePet(Long id) throws PetDeletingFailedException;
}
