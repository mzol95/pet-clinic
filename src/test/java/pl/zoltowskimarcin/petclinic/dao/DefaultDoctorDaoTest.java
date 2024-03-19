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
import pl.zoltowskimarcin.petclinic.repository.entity.Doctor;
import pl.zoltowskimarcin.petclinic.utils.DatabaseInitializer;

import java.util.Optional;

import static pl.zoltowskimarcin.petclinic.utils.TestUtils.*;

@SpringBootTest
class DefaultDoctorDaoTest {

    @Autowired
    private DefaultDoctorDao doctorDao;

    private Doctor doctorGregory = new Doctor(DOCTOR_NAME_GREGORY, DOCTOR_SURNAME_HOUSE);
    private Doctor updatedDoctorAllison = new Doctor(UPDATE_DOCTOR_NAME_ALLISON, UPDATE_DOCTOR_SURNAME_CAMERON);

    @BeforeEach
    void setUp() throws CommandExecutionException {
        DatabaseInitializer.initializeDatabase();
    }

    @AfterEach
    void tearDown() throws CommandExecutionException {
        DatabaseInitializer.dropDatabase();
    }

    @Test
    void creating_new_doctor_should_return_created_doctor() throws DoctorException {
        //given

        //when
        Doctor returnedDoctor = doctorDao.saveDoctor(doctorGregory);

        //then
        Assertions.assertEquals(doctorGregory, returnedDoctor, "Doctor is not equal");
    }

    @Test
    void updating_not_existing_entity_should_throw_doctor_updating_failed_exception() {
        //given

        //when

        //then
        Assertions.assertThrows(DoctorUpdatingFailedException.class,
                () -> doctorDao.updateDoctor(ID_1, updatedDoctorAllison), "Exception not thrown");
    }

    @Test
    void no_entity_found_while_reading_should_return_empty_optional() throws DoctorReadingFailedException {
        //given

        //when
        Optional<Doctor> returnedDoctor = doctorDao.getDoctorById(ID_1);

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