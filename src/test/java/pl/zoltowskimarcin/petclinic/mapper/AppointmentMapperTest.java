package pl.zoltowskimarcin.petclinic.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.zoltowskimarcin.petclinic.repository.entity.Appointment;
import pl.zoltowskimarcin.petclinic.web.model.appointment.AppointmentDto;

import java.time.LocalDateTime;


class AppointmentMapperTest {


    private static final LocalDateTime APPOINTMENT_DATE_TIME_2000_01_01 = LocalDateTime.of(2000, 1, 1, 1, 1);
    private static final LocalDateTime APPOINTMENT_UPDATED_DATE_TIME_3000_02_02 = LocalDateTime.of(3000, 2, 2, 2, 2);
    private static final boolean APPOINTMENT_STATUS_FALSE = false;
    private static final boolean APPOINTMENT_STATUS_TRUE = true;

    @Test
    void map_from_model_to_entity() {
        //given
        AppointmentDto appointmentDto = new AppointmentDto
                .Builder()
                .appointmentDate(APPOINTMENT_DATE_TIME_2000_01_01)
                .finished(APPOINTMENT_STATUS_FALSE)
                .build();

        //when
        Appointment mappedAppointmentEntity = AppointmentMapper.getMapper().map(appointmentDto, Appointment.class);

        LocalDateTime mappedAppointmentDate = mappedAppointmentEntity.getAppointmentDate();
        boolean mappedFinished = mappedAppointmentEntity.isFinished();


        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(APPOINTMENT_DATE_TIME_2000_01_01, mappedAppointmentDate, "Appointment date is not mapped correctly"),
                () -> Assertions.assertEquals(APPOINTMENT_STATUS_FALSE, mappedFinished, "Appointment status is not mapped correctly")
        );
    }

    @Test
    void map_from_entity_to_model() {
        //given
        Appointment doctor = new Appointment.Builder()
                .appointmentDate(APPOINTMENT_DATE_TIME_2000_01_01)
                .finished(APPOINTMENT_STATUS_FALSE)
                .build();
        //when
        AppointmentDto mappedAppointment = AppointmentMapper.getMapper().map(doctor, AppointmentDto.class);
        LocalDateTime mappedAppointmentDate = mappedAppointment.getAppointmentDate();
        boolean mappedFinished = mappedAppointment.isFinished();

        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(APPOINTMENT_DATE_TIME_2000_01_01, mappedAppointmentDate, "Appointment date is not mapped correctly"),
                () -> Assertions.assertEquals(APPOINTMENT_STATUS_FALSE, mappedFinished, "Appointment status is not mapped correctly")
        );
    }
}