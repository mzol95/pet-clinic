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
import pl.zoltowskimarcin.petclinic.exception.pet.PetUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.repository.dao.DefaultPetDao;
import pl.zoltowskimarcin.petclinic.utils.DatabaseInitializer;
import pl.zoltowskimarcin.petclinic.web.enums.Gender;
import pl.zoltowskimarcin.petclinic.web.model.pet.PetDto;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
class DefaultPetDaoTest {

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
                .name(PET_TEST_NAME)
                .dateOfBirth(PET_TEST_DATE)
                .gender(PET_TEST_GENDER_MALE)
                .build();

        updatedPetDto = new PetDto
                .Builder()
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
    void creating_new_pet_should_return_created_pet() throws PetException {
        //given

        //when
        PetDto returnedPet = petDao.savePet(petDto);

        //then
        Assertions.assertEquals(petDto, returnedPet, "Pet is not equal");
    }


    @Test
    void updating_not_existing_entity_should_throw_pet_updating_failed_exception() {
        //given

        //when

        //then
        Assertions.assertThrows(PetUpdatingFailedException.class,
                () -> petDao.updatePet(PET_ID_1, updatedPetDto), "Exception not thrown");
    }


    @Test
    void no_entity_found_while_reading_should_return_empty_optional() throws PetException {
        //given

        //when
        Optional<PetDto> returnedPet = petDao.getPetById(PET_ID_1);

        //then
        Assertions.assertEquals(Optional.empty(), returnedPet, "Pet is not empty");
    }

    @Test
    void deleting_not_existing_entity_should_throw_pet_deleting_failed_exception() {
        //given

        //when

        //then
        Assertions.assertThrows(PetDeletingFailedException.class,
                () -> petDao.deletePet(PET_ID_1), "Exception not thrown");
    }
}