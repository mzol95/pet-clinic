package pl.zoltowskimarcin.petclinic.repository.dao;

import pl.zoltowskimarcin.petclinic.exception.pet.PetDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.pet.PetReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.pet.PetSavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.pet.PetUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.repository.entity.Pet;

import java.util.Optional;


public interface PetDao {

    Pet savePet(Pet pet) throws PetSavingFailedException;

    Optional<Pet> getPetById(Long id) throws PetReadingFailedException;

    Pet updatePet(Long id, Pet pet) throws PetUpdatingFailedException;

    void deletePet(Long id) throws PetDeletingFailedException;
}
