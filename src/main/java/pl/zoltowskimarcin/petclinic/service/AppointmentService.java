package pl.zoltowskimarcin.petclinic.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.zoltowskimarcin.petclinic.exception.appointment.AppointmentDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.appointment.AppointmentReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.appointment.AppointmentSavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.appointment.AppointmentUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.repository.dao.AppointmentDao;
import pl.zoltowskimarcin.petclinic.web.model.appointment.AppointmentDto;

import java.util.Optional;

@Service
@Slf4j
public class AppointmentService {

    private final AppointmentDao appointmentDao;

    public AppointmentService(AppointmentDao appointmentDao) {
        this.appointmentDao = appointmentDao;
    }

    public AppointmentDto saveAppointment(AppointmentDto appointmentDto) throws AppointmentSavingFailedException {
        log.info("save " + appointmentDto + ")");
        AppointmentDto resultAppointment = appointmentDao.saveAppointment(appointmentDto);
        log.info("save(...) = " + resultAppointment);
        return resultAppointment;
    }

    public Optional<AppointmentDto> getAppointmentById(Long id) throws AppointmentReadingFailedException {
        log.info("getAppointmentById with id: " + id);
        Optional<AppointmentDto> resultAppointment = appointmentDao.getAppointmentById(id);
        log.info("getAppointmentById(...) = " + resultAppointment);
        return resultAppointment;
    }

    public AppointmentDto updateAppointment(Long id, AppointmentDto appointmentDto) throws AppointmentUpdatingFailedException {
        log.info("updateAppointment with id: " + id + " and appointmentDto: " + appointmentDto);
        AppointmentDto resultAppointment = appointmentDao.updateAppointment(id, appointmentDto);
        log.info("updateAppointment(...) = " + resultAppointment);
        return resultAppointment;
    }

    public void deleteAppointment(Long id) throws AppointmentDeletingFailedException {
        log.info("deleteAppointment with id: " + id);
        appointmentDao.deleteAppointment(id);
        log.info("deleteAppointment(...) = void");
    }
}
