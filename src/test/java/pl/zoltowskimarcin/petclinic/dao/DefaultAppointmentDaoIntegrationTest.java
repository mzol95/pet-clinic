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
import pl.zoltowskimarcin.petclinic.utils.DatabaseInitializer;
import pl.zoltowskimarcin.petclinic.web.model.appointment.AppointmentDto;

import java.time.LocalDateTime;

@SpringBootTest
class DefaultAppointmentDaoIntegrationTest {

    private static final LocalDateTime APPOINTMENT_DATE_TIME_2000_01_01 = LocalDateTime.of(2000, 1, 1, 1, 1);
    private static final LocalDateTime APPOINTMENT_UPDATED_DATE_TIME_3000_02_02 = LocalDateTime.of(3000, 2, 2, 2, 2);
    private static final boolean APPOINTMENT_STATUS_FALSE = false;
    private static final boolean APPOINTMENT_STATUS_TRUE = true;
    private static final long ID_1 = 1L;

    @Autowired
    private DefaultAppointmentDao appointmentDao;
    private AppointmentDto appointmentDto;
    private AppointmentDto updatedAppointmentDto;

    @BeforeEach
    void setUp() throws CommandExecutionException {
        DatabaseInitializer.initializeDatabase();

        appointmentDto = new AppointmentDto
                .Builder()
                .appointmentDate(APPOINTMENT_DATE_TIME_2000_01_01)
                .finished(APPOINTMENT_STATUS_FALSE)
                .build();

        updatedAppointmentDto = new AppointmentDto
                .Builder()
                .appointmentDate(APPOINTMENT_UPDATED_DATE_TIME_3000_02_02)
                .finished(APPOINTMENT_STATUS_TRUE)
                .build();
    }

    @AfterEach
    void tearDown() throws CommandExecutionException {
        DatabaseInitializer.dropDatabase();
    }

    @Test
    void read_after_creating_new_appointment_should_return_newly_created_appointment() throws AppointmentException {
        //given

        //when
        AppointmentDto persistedAppointment = appointmentDao.saveAppointment(appointmentDto);
        AppointmentDto returnedAppointment = appointmentDao.getAppointmentById(ID_1)
                .orElseThrow(AppointmentReadingFailedException::new);

        //then
        Assertions.assertEquals(appointmentDto, returnedAppointment, "Appointment is not equal");
    }

    @Test
    void after_updating_should_return_updated_appointment_entity() throws AppointmentException {
        //given

        //when
        AppointmentDto persistedAppointment = appointmentDao.saveAppointment(appointmentDto);
        AppointmentDto updatedAppointment = appointmentDao.updateAppointment(ID_1, updatedAppointmentDto);

        //then
        Assertions.assertEquals(updatedAppointmentDto, updatedAppointment, "Appointment is not equal");
    }


    @Test
    void after_deleting_appointment_should_return_null_when_try_to_read_deleted_entity() throws AppointmentException {
        //given

        //when
        AppointmentDto persistedAppointment = appointmentDao.saveAppointment(appointmentDto);
        appointmentDao.deleteAppointment(ID_1);
        AppointmentDto returnedAppointment = appointmentDao.getAppointmentById(ID_1).orElse(null);

        //then
        Assertions.assertNull(returnedAppointment, "Appointment is not null");
    }


}