package pl.zoltowskimarcin.petclinic.repository.dao;

import pl.zoltowskimarcin.petclinic.exception.appointment.AppointmentDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.appointment.AppointmentReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.appointment.AppointmentSavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.appointment.AppointmentUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.web.model.appointment.AppointmentDto;

import java.util.Optional;

public interface AppointmentDao {

    AppointmentDto saveAppointment(AppointmentDto appointmentDto) throws AppointmentSavingFailedException;

    Optional<AppointmentDto> getAppointmentById(Long id) throws AppointmentReadingFailedException;

    AppointmentDto updateAppointment(Long id, AppointmentDto appointmentDto) throws AppointmentUpdatingFailedException;

    void deleteAppointment(Long id) throws AppointmentDeletingFailedException;
}
