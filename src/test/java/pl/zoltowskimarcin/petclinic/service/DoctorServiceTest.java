package pl.zoltowskimarcin.petclinic.service;

import liquibase.exception.CommandExecutionException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.zoltowskimarcin.petclinic.exception.client.EntityDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.client.EntityException;
import pl.zoltowskimarcin.petclinic.exception.client.EntityReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.client.EntityUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.utils.DatabaseInitializer;
import pl.zoltowskimarcin.petclinic.web.model.DoctorDto;

import java.util.Optional;

@SpringBootTest
class DoctorServiceTest {

    private static final String DOCTOR_TEST_NAME = "Test name";
    private static final String DOCTOR_TEST_SURNAME = "Test surname";
    private static final String DOCTOR_UPDATE_SURNAME = "Update surname";
    private static final String DOCTOR_UPDATE_NAME = "Update name";
    private static final Long ID_1 = 1L;

    @Autowired
    private DoctorService doctorService;

    private DoctorDto doctorDto;
    private DoctorDto updatedDoctorDto;

    @BeforeEach
    void setUp() throws CommandExecutionException {
        DatabaseInitializer.initializeDatabase();

        doctorDto = new DoctorDto
                .Builder()
                .name(DOCTOR_TEST_NAME)
                .surname(DOCTOR_TEST_SURNAME)
                .build();

        updatedDoctorDto = new DoctorDto.Builder()
                .name(DOCTOR_UPDATE_NAME)
                .surname(DOCTOR_UPDATE_SURNAME)
                .build();
    }

    @AfterEach
    void tearDown() throws CommandExecutionException {
        DatabaseInitializer.dropDatabase();
    }

    @Test
    void creating_new_doctor_should_return_created_doctor() throws EntityException {
        //given

        //when
        DoctorDto returnedDoctor = doctorService.saveDoctor(doctorDto);

        //then
        Assertions.assertEquals(doctorDto, returnedDoctor, "Doctor is not equal");
    }

    @Test
    void read_after_creating_new_doctor_should_return_newly_created_doctor() throws EntityException {
        //given

        //when
        DoctorDto persistedDoctor = doctorService.saveDoctor(doctorDto);
        DoctorDto returnedDoctor = doctorService.getDoctorById(ID_1)
                .orElseThrow(EntityReadingFailedException::new);

        //then
        Assertions.assertEquals(doctorDto, returnedDoctor, "Doctor is not equal");
    }

    @Test
    void after_updating_should_return_updated_doctor_entity() throws EntityException {
        //given

        //when
        DoctorDto persistedDoctor = doctorService.saveDoctor(doctorDto);
        DoctorDto updatedDoctor = doctorService.updateDoctor(ID_1, updatedDoctorDto);

        //then
        Assertions.assertEquals(updatedDoctorDto, updatedDoctor, "Doctor is not equal");
    }

    @Test
    void updating_not_existing_entity_should_throw_doctor_updating_failed_exception() {
        //given

        //when

        //then
        Assertions.assertThrows(EntityUpdatingFailedException.class,
                () -> doctorService.updateDoctor(ID_1, updatedDoctorDto), "Exception not thrown");
    }

    @Test
    void after_deleting_doctor_should_return_null_when_try_to_read_deleted_entity() throws EntityException {
        //given

        //when
        DoctorDto persistedDoctor = doctorService.saveDoctor(doctorDto);
        doctorService.deleteDoctor(ID_1);
        DoctorDto returnedDoctor = doctorService.getDoctorById(ID_1).orElse(null);

        //then
        Assertions.assertNull(returnedDoctor, "Doctor is not null");
    }

    @Test
    void no_entity_found_while_reading_should_return_empty_optional() throws EntityReadingFailedException {
        //given

        //when
        Optional<DoctorDto> returnedDoctor = doctorService.getDoctorById(ID_1);

        //then
        Assertions.assertEquals(Optional.empty(), returnedDoctor, "Doctor is not empty");
    }

    @Test
    void deleting_not_existing_entity_should_throw_doctor_deleting_failed_exception() {
        //given

        //when

        //then
        Assertions.assertThrows(EntityDeletingFailedException.class,
                () -> doctorService.deleteDoctor(ID_1), "Exception not thrown");
    }
}