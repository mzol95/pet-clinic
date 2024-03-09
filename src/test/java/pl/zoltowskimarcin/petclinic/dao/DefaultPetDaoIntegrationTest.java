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
import pl.zoltowskimarcin.petclinic.utils.DatabaseInitializer;
import pl.zoltowskimarcin.petclinic.web.enums.Gender;
import pl.zoltowskimarcin.petclinic.web.model.pet.PetDto;

import java.time.LocalDate;

@SpringBootTest
class DefaultPetDaoIntegrationTest {

    private static final String PET_TEST_NAME = "Test name";
    private static final LocalDate PET_TEST_DATE = LocalDate.of(2000, 1, 1);
    private static final Gender PET_TEST_GENDER_MALE = Gender.MALE;


    private static final String PET_UPDATE_NAME = "Update name";
    private static final LocalDate PET_UPDATE_DATE = LocalDate.of(3000, 2, 2);
    private static final Gender PET_UPDATE_GENDER_FEMALE = Gender.FEMALE;
    private static final Long PET_ID_1 = 1L;

    @Autowired
    private DefaultPetDao petDao;

    private PetDto petDto;
    private PetDto updatedPetDto;

    @BeforeEach
    void setUp() throws CommandExecutionException {
        DatabaseInitializer.initializeDatabase();

        petDto = new PetDto
                .Builder()
                .id(PET_ID_1)
                .name(PET_TEST_NAME)
                .dateOfBirth(PET_TEST_DATE)
                .gender(PET_TEST_GENDER_MALE)
                .build();

        updatedPetDto = new PetDto
                .Builder()
                .id(PET_ID_1)
                .name(PET_UPDATE_NAME)
                .dateOfBirth(PET_UPDATE_DATE)
                .gender(PET_UPDATE_GENDER_FEMALE)
                .build();
    }

    @AfterEach
    void tearDown() throws CommandExecutionException {
        DatabaseInitializer.dropDatabase();
    }

    @Test
    void read_after_creating_new_pet_should_return_newly_created_pet() throws PetException {
        //given

        //when
        PetDto persistedPet = petDao.savePet(petDto);
        PetDto returnedPet = petDao.getPetById(PET_ID_1)
                .orElseThrow(PetReadingFailedException::new);

        //then
        Assertions.assertEquals(petDto, returnedPet, "Pet is not equal");
    }

    @Test
    void after_updating_should_return_updated_pet_entity() throws PetException {
        //given

        //when
        PetDto persistedPet = petDao.savePet(petDto);
        PetDto updatedPet = petDao.updatePet(PET_ID_1, updatedPetDto);

        //then
        Assertions.assertEquals(updatedPetDto, updatedPet, "Pet is not equal");
    }

    @Test
    void after_deleting_pet_should_return_null_when_try_to_read_deleted_entity() throws PetException {
        //given

        //when
        PetDto persistedPet = petDao.savePet(petDto);
        petDao.deletePet(PET_ID_1);
        PetDto returnedPet = petDao.getPetById(PET_ID_1).orElse(null);

        //then
        Assertions.assertNull(returnedPet, "Pet is not null");
    }

}