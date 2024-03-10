package pl.zoltowskimarcin.petclinic.dao;

import liquibase.exception.CommandExecutionException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.zoltowskimarcin.petclinic.exception.pet.PetException;
import pl.zoltowskimarcin.petclinic.exception.pet.PetReadingFailedException;
import pl.zoltowskimarcin.petclinic.repository.dao.DefaultPetDao;
import pl.zoltowskimarcin.petclinic.repository.entity.Pet;
import pl.zoltowskimarcin.petclinic.utils.DatabaseInitializer;
import pl.zoltowskimarcin.petclinic.web.enums.Gender;

import java.time.LocalDate;

import static pl.zoltowskimarcin.petclinic.utils.TestUtils.*;

@SpringBootTest
class DefaultPetDaoIntegrationTest {

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
    void read_after_creating_new_pet_should_return_newly_created_pet() throws PetException {
        //given

        //when
        petDao.savePet(petGarfield);
        Pet returnedPet = petDao.getPetById(ID_1)
                .orElseThrow(PetReadingFailedException::new);

        String returnedName = returnedPet.getName();
        LocalDate returnedLocalDate = returnedPet.getDateOfBirth();
        Gender returnedGender = returnedPet.getGender();

        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(PET_NAME_GARFIELD, returnedName, "Name is not equal"),
                () -> Assertions.assertEquals(PET_DATE_OF_BIRTH_19_06_1978, returnedLocalDate, "Date of birth is not equal"),
                () -> Assertions.assertEquals(PET_GENDER_MALE, returnedGender, "Gender is not equal")
        );
    }

    @Test
    void after_updating_should_return_updated_pet_entity() throws PetException {
        //given

        //when
        Pet savedPet = petDao.savePet(petGarfield);
        Pet updatedPet = petDao.updatePet(ID_1, updatedPetTom);
        updatedPet.setId(null);
        //then
        Assertions.assertEquals(updatedPetTom, updatedPet, "Pet is not equal");
    }


    @Test
    void after_deleting_pet_should_return_null_when_try_to_read_deleted_entity() throws PetException {
        //given

        //when
        Pet savedPet = petDao.savePet(petGarfield);
        petDao.deletePet(savedPet.getId());
        Pet returnedPet = petDao.getPetById(savedPet.getId()).orElse(null);

        //then
        Assertions.assertNull(returnedPet, "Pet is not null");
    }

}