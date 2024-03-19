package pl.zoltowskimarcin.petclinic.dao;

import liquibase.exception.CommandExecutionException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.zoltowskimarcin.petclinic.exception.pet.PetDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.pet.PetException;
import pl.zoltowskimarcin.petclinic.exception.pet.PetReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.pet.PetUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.repository.dao.DefaultPetDao;
import pl.zoltowskimarcin.petclinic.repository.entity.Pet;
import pl.zoltowskimarcin.petclinic.utils.DatabaseInitializer;

import java.util.Optional;

import static pl.zoltowskimarcin.petclinic.utils.TestUtils.*;

@SpringBootTest
class DefaultPetDaoTest {

    @Autowired
    private DefaultPetDao petDao;

    private Pet petGarfield = new Pet(PET_NAME_GARFIELD, PET_DATE_OF_BIRTH_19_06_1978, PET_GENDER_MALE);
    private Pet updatedPetTom = new Pet(UPDATE_PET_NAME_TOM, UPDATE_PET_DATE_OF_BIRTH_10_02_1980, UPDATE_PET_GENDER_MALE);

    @BeforeEach
    void setUp() throws CommandExecutionException {
        DatabaseInitializer.initializeDatabase();
    }

    @AfterEach
    void tearDown() throws CommandExecutionException {
        DatabaseInitializer.dropDatabase();
    }


    @Test
    void creating_new_doctor_should_return_created_doctor() throws PetException {
        //given

        //when
        Pet returnedPet = petDao.savePet(petGarfield);

        //then
        Assertions.assertEquals(petGarfield, returnedPet, "Pet is not equal");
    }


    @Test
    void updating_not_existing_entity_should_throw_doctor_updating_failed_exception() {
        //given

        //when

        //then
        Assertions.assertThrows(PetUpdatingFailedException.class,
                () -> petDao.updatePet(ID_1, updatedPetTom), "Exception not thrown");
    }


    @Test
    void no_entity_found_while_reading_should_return_empty_optional() throws PetReadingFailedException {
        //given

        //when
        Optional<Pet> returnedPet = petDao.getPetById(ID_1);

        //then
        Assertions.assertEquals(Optional.empty(), returnedPet, "Pet is not empty");
    }

    @Test
    void deleting_not_existing_entity_should_throw_doctor_deleting_failed_exception() {
        //given

        //when

        //then
        Assertions.assertThrows(PetDeletingFailedException.class,
                () -> petDao.deletePet(ID_1), "Exception not thrown");
    }
}