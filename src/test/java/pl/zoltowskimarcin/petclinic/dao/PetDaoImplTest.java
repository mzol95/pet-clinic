package pl.zoltowskimarcin.petclinic.dao;

import liquibase.exception.CommandExecutionException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.zoltowskimarcin.petclinic.exception.EntityDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntityException;
import pl.zoltowskimarcin.petclinic.exception.EntityReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntityUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.repository.dao.PetDaoImpl;
import pl.zoltowskimarcin.petclinic.utils.DatabaseInitializer;
import pl.zoltowskimarcin.petclinic.web.enums.Gender;
import pl.zoltowskimarcin.petclinic.web.model.PetDto;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
class PetDaoImplTest {

    private static final String PET_TEST_NAME = "Test name";
    private static final LocalDate PET_TEST_DATE = LocalDate.of(2000, 1, 1);
    private static final Gender PET_TEST_GENDER_MALE = Gender.MALE;


    private static final String PET_UPDATE_NAME = "Update name";
    private static final LocalDate PET_UPDATE_DATE = LocalDate.of(3000, 2, 2);
    private static final Gender PET_UPDATE_GENDER_FEMALE = Gender.FEMALE;
    private static final Long PET_ID_1 = 1L;

    @Autowired
    private PetDaoImpl petDao;

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
    void creating_new_pet_should_return_created_pet() throws EntityException {
        //given

        //when
        PetDto returnedPet = petDao.savePet(petDto);

        //then
        Assertions.assertEquals(petDto, returnedPet, "Pet is not equal");
    }

    @Test
    void read_after_creating_new_pet_should_return_newly_created_pet() throws EntityException {
        //given

        //when
        PetDto persistedPet = petDao.savePet(petDto);
        PetDto returnedPet = petDao.getPetById(PET_ID_1)
                .orElseThrow(EntityReadingFailedException::new);

        //then
        Assertions.assertEquals(petDto, returnedPet, "Pet is not equal");
    }

    @Test
    void after_updating_should_return_updated_pet_entity() throws EntityException {
        //given

        //when
        PetDto persistedPet = petDao.savePet(petDto);
        PetDto updatedPet = petDao.updatePet(PET_ID_1, updatedPetDto);

        //then
        Assertions.assertEquals(updatedPetDto, updatedPet, "Pet is not equal");
    }

    @Test
    void updating_not_existing_entity_should_throw_pet_updating_failed_exception() {
        //given

        //when

        //then
        Assertions.assertThrows(EntityUpdatingFailedException.class,
                () -> petDao.updatePet(PET_ID_1, updatedPetDto), "Exception not thrown");
    }

    @Test
    void after_deleting_pet_should_return_null_when_try_to_read_deleted_entity() throws EntityException {
        //given

        //when
        PetDto persistedPet = petDao.savePet(petDto);
        petDao.deletePet(PET_ID_1);
        PetDto returnedPet = petDao.getPetById(PET_ID_1).orElse(null);

        //then
        Assertions.assertNull(returnedPet, "Pet is not null");
    }

    @Test
    void no_entity_found_while_reading_should_return_empty_optional() throws EntityReadingFailedException {
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
        Assertions.assertThrows(EntityDeletingFailedException.class,
                () -> petDao.deletePet(PET_ID_1), "Exception not thrown");
    }
}