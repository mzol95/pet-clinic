package pl.zoltowskimarcin.petclinic.dao;

import liquibase.exception.CommandExecutionException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.zoltowskimarcin.petclinic.exception.doctor.DoctorException;
import pl.zoltowskimarcin.petclinic.exception.doctor.DoctorReadingFailedException;
import pl.zoltowskimarcin.petclinic.repository.dao.DefaultDoctorDao;
import pl.zoltowskimarcin.petclinic.repository.entity.Doctor;
import pl.zoltowskimarcin.petclinic.utils.DatabaseInitializer;

import static pl.zoltowskimarcin.petclinic.utils.TestUtils.*;

@SpringBootTest
class DefaultDoctorDaoIntegrationTest {

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
    void read_after_creating_new_doctor_should_return_newly_created_doctor() throws DoctorException {
        //given

        //when
        doctorDao.saveDoctor(doctorGregory);

        Doctor returnedDoctor = doctorDao.getDoctorById(ID_1)
                .orElseThrow(DoctorReadingFailedException::new);

        String returnedName = returnedDoctor.getName();
        String returnedSurname = returnedDoctor.getSurname();


        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(DOCTOR_NAME_GREGORY, returnedName, "Name is not equal"),
                () -> Assertions.assertEquals(DOCTOR_SURNAME_HOUSE, returnedSurname, "Surname is not equal")
        );
    }

    @Test
    void after_updating_should_return_updated_doctor_entity() throws DoctorException {
        //given

        //when
        Doctor savedDoctor = doctorDao.saveDoctor(doctorGregory);
        Doctor updatedDoctor = doctorDao.updateDoctor(ID_1, updatedDoctorAllison);
        updatedDoctor.setId(null);
        //then
        Assertions.assertEquals(updatedDoctorAllison, updatedDoctor, "Doctor is not equal");
    }


    @Test
    void after_deleting_doctor_should_return_null_when_try_to_read_deleted_entity() throws DoctorException {
        //given

        //when
        Doctor savedDoctor = doctorDao.saveDoctor(doctorGregory);
        doctorDao.deleteDoctor(savedDoctor.getId());
        Doctor returnedDoctor = doctorDao.getDoctorById(savedDoctor.getId()).orElse(null);

        //then
        Assertions.assertNull(returnedDoctor, "Doctor is not null");
    }


}