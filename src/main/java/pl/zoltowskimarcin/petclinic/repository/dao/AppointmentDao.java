package pl.zoltowskimarcin.petclinic.repository.dao;

import pl.zoltowskimarcin.petclinic.exception.EntityDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntityReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntitySavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntityUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.web.model.AppointmentDto;

import java.util.Optional;

public interface AppointmentDao {

    AppointmentDto saveAppointment(AppointmentDto appointmentDto) throws EntitySavingFailedException;

    Optional<AppointmentDto> getAppointmentById(Long id) throws EntityReadingFailedException;

    AppointmentDto updateAppointment(Long id, AppointmentDto appointmentDto) throws EntityUpdatingFailedException;

    void deleteAppointment(Long id) throws EntityDeletingFailedException;
}
