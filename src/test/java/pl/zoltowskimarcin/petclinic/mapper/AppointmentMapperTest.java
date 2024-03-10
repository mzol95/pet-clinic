package pl.zoltowskimarcin.petclinic.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.zoltowskimarcin.petclinic.repository.entity.Appointment;
import pl.zoltowskimarcin.petclinic.web.model.appointment.AppointmentDto;

import java.time.LocalDateTime;

import static pl.zoltowskimarcin.petclinic.utils.TestUtils.APPOINTMENT_DATE_TIME;
import static pl.zoltowskimarcin.petclinic.utils.TestUtils.APPOINTMENT_FINISHED;


class AppointmentMapperTest {

    @Test
    void map_from_model_to_entity() {
        //given
        AppointmentDto appointmentDto = new AppointmentDto(APPOINTMENT_DATE_TIME, APPOINTMENT_FINISHED);

        //when
        Appointment mappedAppointmentEntity = new AppointmentMapper().mapToEntity(appointmentDto);
        LocalDateTime mappedLocalDateTime = mappedAppointmentEntity.getAppointmentDate();
        boolean mappedFinished = mappedAppointmentEntity.isFinished();

        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(APPOINTMENT_DATE_TIME, mappedLocalDateTime, "Date is not mapped correctly"),
                () -> Assertions.assertEquals(APPOINTMENT_FINISHED, mappedFinished, "Finished is not mapped correctly")
        );
    }

    @Test
    void map_from_entity_to_model() {
        //given
        Appointment appointment = new Appointment(APPOINTMENT_DATE_TIME, APPOINTMENT_FINISHED);

        //when
        AppointmentDto mappedAppointment = new AppointmentMapper().mapToDto(appointment, AppointmentDto.class);
        LocalDateTime mappedLocalDateTime = mappedAppointment.getAppointmentDate();
        boolean mappedFinished = mappedAppointment.isFinished();


        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(APPOINTMENT_DATE_TIME, mappedLocalDateTime, "Date is not mapped correctly"),
                () -> Assertions.assertEquals(APPOINTMENT_FINISHED, mappedFinished, "Finished is not mapped correctly")
        );
    }
}