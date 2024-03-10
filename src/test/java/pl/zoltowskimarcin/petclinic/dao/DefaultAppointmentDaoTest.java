package pl.zoltowskimarcin.petclinic.dao;

import liquibase.exception.CommandExecutionException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.zoltowskimarcin.petclinic.exception.appointment.AppointmentDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.appointment.AppointmentException;
import pl.zoltowskimarcin.petclinic.exception.appointment.AppointmentReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.appointment.AppointmentUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.repository.dao.DefaultAppointmentDao;
import pl.zoltowskimarcin.petclinic.repository.entity.Appointment;
import pl.zoltowskimarcin.petclinic.utils.DatabaseInitializer;

import java.util.Optional;

import static pl.zoltowskimarcin.petclinic.utils.TestUtils.*;

@SpringBootTest
class DefaultAppointmentDaoTest {

    @Autowired
    private DefaultAppointmentDao appointmentDao;

    private Appointment appointment = new Appointment(APPOINTMENT_DATE_TIME, APPOINTMENT_FINISHED);
    private Appointment updatedAppointment = new Appointment(UPDATE_APPOINTMENT_DATE_TIME, UPDATE_APPOINTMENT_FINISHED);

    @BeforeEach
    void setUp() throws CommandExecutionException {
        DatabaseInitializer.initializeDatabase();
    }

    @AfterEach
    void tearDown() throws CommandExecutionException {
        DatabaseInitializer.dropDatabase();
    }

    @Test
    void creating_new_appointment_should_return_created_appointment() throws AppointmentException {
        //given

        //when
        Appointment returnedAppointment = appointmentDao.saveAppointment(appointment);

        //then
        Assertions.assertEquals(appointment, returnedAppointment, "Appointment is not equal");
    }

    @Test
    void updating_not_existing_entity_should_throw_appointment_updating_failed_exception() {
        //given

        //when

        //then
        Assertions.assertThrows(AppointmentUpdatingFailedException.class,
                () -> appointmentDao.updateAppointment(ID_1, updatedAppointment), "Exception not thrown");
    }

    @Test
    void no_entity_found_while_reading_should_return_empty_optional() throws AppointmentReadingFailedException {
        //given

        //when
        Optional<Appointment> returnedAppointment = appointmentDao.getAppointmentById(ID_1);

        //then
        Assertions.assertEquals(Optional.empty(), returnedAppointment, "Appointment is not empty");
    }

    @Test
    void deleting_not_existing_entity_should_throw_appointment_deleting_failed_exception() {
        //given

        //when

        //then
        Assertions.assertThrows(AppointmentDeletingFailedException.class,
                () -> appointmentDao.deleteAppointment(ID_1), "Exception not thrown");
    }

}