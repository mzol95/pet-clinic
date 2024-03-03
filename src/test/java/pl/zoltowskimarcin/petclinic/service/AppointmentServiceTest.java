package pl.zoltowskimarcin.petclinic.service;

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
import pl.zoltowskimarcin.petclinic.utils.DatabaseInitializer;
import pl.zoltowskimarcin.petclinic.web.model.AppointmentDto;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
class AppointmentServiceTest {

    private static final LocalDateTime APPOINTMENT_DATE_TIME_2000_01_01 = LocalDateTime.of(2000, 1, 1, 1, 1);
    private static final LocalDateTime APPOINTMENT_UPDATED_DATE_TIME_3000_02_02 = LocalDateTime.of(3000, 2, 2, 2, 2);
    private static final boolean APPOINTMENT_STATUS_FALSE = false;
    private static final boolean APPOINTMENT_STATUS_TRUE = true;
    private static final long ID_1 = 1L;

    @Autowired
    private AppointmentService appointmentService;
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
    void creating_new_appointment_should_return_created_appointment() throws EntityException {
        //given

        //when
        AppointmentDto returnedAppointment = appointmentService.saveAppointment(appointmentDto);

        //then
        Assertions.assertEquals(appointmentDto, returnedAppointment, "Appointment is not equal");
    }

    @Test
    void read_after_creating_new_appointment_should_return_newly_created_appointment() throws EntityException {
        //given

        //when
        AppointmentDto persistedAppointment = appointmentService.saveAppointment(appointmentDto);
        AppointmentDto returnedAppointment = appointmentService.getAppointmentById(ID_1)
                .orElseThrow(EntityReadingFailedException::new);

        //then
        Assertions.assertEquals(appointmentDto, returnedAppointment, "Appointment is not equal");
    }

    @Test
    void after_updating_should_return_updated_appointment_entity() throws EntityException {
        //given

        //when
        AppointmentDto persistedAppointment = appointmentService.saveAppointment(appointmentDto);
        AppointmentDto updatedAppointment = appointmentService.updateAppointment(ID_1, updatedAppointmentDto);

        //then
        Assertions.assertEquals(updatedAppointmentDto, updatedAppointment, "Appointment is not equal");
    }

    @Test
    void updating_not_existing_entity_should_throw_appointment_updating_failed_exception() {
        //given

        //when

        //then
        Assertions.assertThrows(EntityUpdatingFailedException.class,
                () -> appointmentService.updateAppointment(ID_1, updatedAppointmentDto), "Exception not thrown");
    }

    @Test
    void after_deleting_appointment_should_return_null_when_try_to_read_deleted_entity() throws EntityException {
        //given

        //when
        AppointmentDto persistedAppointment = appointmentService.saveAppointment(appointmentDto);
        appointmentService.deleteAppointment(ID_1);
        AppointmentDto returnedAppointment = appointmentService.getAppointmentById(ID_1).orElse(null);

        //then
        Assertions.assertNull(returnedAppointment, "Appointment is not null");
    }

    @Test
    void no_entity_found_while_reading_should_return_empty_optional() throws EntityReadingFailedException {
        //given

        //when
        Optional<AppointmentDto> returnedAppointment = appointmentService.getAppointmentById(ID_1);

        //then
        Assertions.assertEquals(Optional.empty(), returnedAppointment, "Appointment is not empty");
    }

    @Test
    void deleting_not_existing_entity_should_throw_appointment_deleting_failed_exception() {
        //given

        //when

        //then
        Assertions.assertThrows(EntityDeletingFailedException.class,
                () -> appointmentService.deleteAppointment(ID_1), "Exception not thrown");
    }

}