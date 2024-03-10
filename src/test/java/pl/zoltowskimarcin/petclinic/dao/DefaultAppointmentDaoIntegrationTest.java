package pl.zoltowskimarcin.petclinic.dao;

import liquibase.exception.CommandExecutionException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.zoltowskimarcin.petclinic.exception.appointment.AppointmentException;
import pl.zoltowskimarcin.petclinic.exception.appointment.AppointmentReadingFailedException;
import pl.zoltowskimarcin.petclinic.repository.dao.DefaultAppointmentDao;
import pl.zoltowskimarcin.petclinic.repository.entity.Appointment;
import pl.zoltowskimarcin.petclinic.utils.DatabaseInitializer;

import java.time.LocalDateTime;

import static pl.zoltowskimarcin.petclinic.utils.TestUtils.*;

@SpringBootTest
class DefaultAppointmentDaoIntegrationTest {

    @Autowired
    private DefaultAppointmentDao appointmentDao;

    private Appointment appointment = new Appointment(APPOINTMENT_DATE_TIME, APPOINTMENT_FINISHED);
    private Appointment updatingAppointment = new Appointment(UPDATE_APPOINTMENT_DATE_TIME, UPDATE_APPOINTMENT_FINISHED);


    @BeforeEach
    void setUp() throws CommandExecutionException {
        DatabaseInitializer.initializeDatabase();
    }

    @AfterEach
    void tearDown() throws CommandExecutionException {
        DatabaseInitializer.dropDatabase();
    }


    @Test
    void read_after_creating_new_doctor_should_return_newly_created_doctor() throws AppointmentException {
        //given

        //when
        appointmentDao.saveAppointment(appointment);

        Appointment returnedAppointment = appointmentDao.getAppointmentById(ID_1)
                .orElseThrow(AppointmentReadingFailedException::new);

        LocalDateTime returnedLocalDateTime = returnedAppointment.getAppointmentDate();
        boolean returnedFinished = returnedAppointment.isFinished();

        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(APPOINTMENT_DATE_TIME, returnedLocalDateTime, "Date is not equal"),
                () -> Assertions.assertEquals(APPOINTMENT_FINISHED, returnedFinished, "Finished is not equal")
        );
    }

    @Test
    void after_updating_should_return_updated_doctor_entity() throws AppointmentException {
        //given

        //when
        Appointment savedAppointment = appointmentDao.saveAppointment(appointment);
        Appointment updatedAppointment = appointmentDao.updateAppointment(ID_1, updatingAppointment);
        updatedAppointment.setId(null);
        //then
        Assertions.assertEquals(updatedAppointment, updatedAppointment, "Appointment is not equal");
    }


    @Test
    void after_deleting_doctor_should_return_null_when_try_to_read_deleted_entity() throws AppointmentException {
        //given

        //when
        Appointment savedAppointment = appointmentDao.saveAppointment(appointment);
        appointmentDao.deleteAppointment(savedAppointment.getId());
        Appointment returnedAppointment = appointmentDao.getAppointmentById(savedAppointment.getId()).orElse(null);

        //then
        Assertions.assertNull(returnedAppointment, "Appointment is not null");
    }


}