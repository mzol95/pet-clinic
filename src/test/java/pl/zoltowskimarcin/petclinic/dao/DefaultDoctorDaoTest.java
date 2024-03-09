package pl.zoltowskimarcin.petclinic.dao;

import liquibase.exception.CommandExecutionException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.zoltowskimarcin.petclinic.exception.doctor.DoctorDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.doctor.DoctorException;
import pl.zoltowskimarcin.petclinic.exception.doctor.DoctorReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.doctor.DoctorUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.repository.dao.DefaultDoctorDao;
import pl.zoltowskimarcin.petclinic.utils.DatabaseInitializer;
import pl.zoltowskimarcin.petclinic.web.model.DoctorDto;

import java.util.Optional;

@SpringBootTest
class DefaultDoctorDaoTest {

    private static final String DOCTOR_TEST_NAME = "Test name";
    private static final String DOCTOR_TEST_SURNAME = "Test surname";
    private static final String DOCTOR_UPDATE_SURNAME = "Update surname";
    private static final String DOCTOR_UPDATE_NAME = "Update name";
    private static final Long ID_1 = 1L;

    @Autowired
    private DefaultDoctorDao doctorDao;

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
    void creating_new_doctor_should_return_created_doctor() throws DoctorException {
        //given

        //when
        DoctorDto returnedDoctor = doctorDao.saveDoctor(doctorDto);

        //then
        Assertions.assertEquals(doctorDto, returnedDoctor, "Doctor is not equal");
    }

    @Test
    void read_after_creating_new_doctor_should_return_newly_created_doctor() throws DoctorException {
        //given

        //when
        DoctorDto persistedDoctor = doctorDao.saveDoctor(doctorDto);
        DoctorDto returnedDoctor = doctorDao.getDoctorById(ID_1)
                .orElseThrow(DoctorReadingFailedException::new);

        //then
        Assertions.assertEquals(doctorDto, returnedDoctor, "Doctor is not equal");
    }

    @Test
    void after_updating_should_return_updated_doctor_entity() throws DoctorException {
        //given

        //when
        DoctorDto persistedDoctor = doctorDao.saveDoctor(doctorDto);
        DoctorDto updatedDoctor = doctorDao.updateDoctor(ID_1, updatedDoctorDto);

        //then
        Assertions.assertEquals(updatedDoctorDto, updatedDoctor, "Doctor is not equal");
    }

    @Test
    void updating_not_existing_entity_should_throw_doctor_updating_failed_exception() {
        //given

        //when

        //then
        Assertions.assertThrows(DoctorUpdatingFailedException.class,
                () -> doctorDao.updateDoctor(ID_1, updatedDoctorDto), "Exception not thrown");
    }

    @Test
    void after_deleting_doctor_should_return_null_when_try_to_read_deleted_entity() throws DoctorException {
        //given

        //when
        DoctorDto persistedDoctor = doctorDao.saveDoctor(doctorDto);
        doctorDao.deleteDoctor(ID_1);
        DoctorDto returnedDoctor = doctorDao.getDoctorById(ID_1).orElse(null);

        //then
        Assertions.assertNull(returnedDoctor, "Doctor is not null");
    }

    @Test
    void no_entity_found_while_reading_should_return_empty_optional() throws DoctorException {
        //given

        //when
        Optional<DoctorDto> returnedDoctor = doctorDao.getDoctorById(ID_1);

        //then
        Assertions.assertEquals(Optional.empty(), returnedDoctor, "Doctor is not empty");
    }

    @Test
    void deleting_not_existing_entity_should_throw_doctor_deleting_failed_exception() {
        //given

        //when

        //then
        Assertions.assertThrows(DoctorDeletingFailedException.class,
                () -> doctorDao.deleteDoctor(ID_1), "Exception not thrown");
    }
}