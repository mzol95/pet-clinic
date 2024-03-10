package pl.zoltowskimarcin.petclinic.repository.dao;

import pl.zoltowskimarcin.petclinic.exception.appointment.AppointmentDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.appointment.AppointmentReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.appointment.AppointmentSavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.appointment.AppointmentUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.repository.entity.Appointment;

import java.util.Optional;

public interface AppointmentDao {

    Appointment saveAppointment(Appointment appointment) throws AppointmentSavingFailedException;

    Optional<Appointment> getAppointmentById(Long id) throws AppointmentReadingFailedException;

    Appointment updateAppointment(Long id, Appointment appointment) throws AppointmentUpdatingFailedException;

    void deleteAppointment(Long id) throws AppointmentDeletingFailedException;
}
