package pl.zoltowskimarcin.petclinic.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.zoltowskimarcin.petclinic.exception.EntityDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntityReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntitySavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntityUpdatingFailedException;
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

    public AppointmentDto saveAppointment(AppointmentDto appointmentDto) throws EntitySavingFailedException {
        log.info("save " + appointmentDto + ")");
        AppointmentDto resultAppointment = appointmentDao.saveAppointment(appointmentDto);
        log.info("save(...) = " + resultAppointment);
        return resultAppointment;
    }

    public Optional<AppointmentDto> getAppointmentById(Long id) throws EntityReadingFailedException {
        log.info("getAppointmentById with id: " + id);
        Optional<AppointmentDto> resultAppointment = appointmentDao.getAppointmentById(id);
        log.info("getAppointmentById(...) = " + resultAppointment);
        return resultAppointment;
    }

    public AppointmentDto updateAppointment(Long id, AppointmentDto appointmentDto) throws EntityUpdatingFailedException {
        log.info("updateAppointment with id: " + id + " and appointmentDto: " + appointmentDto);
        AppointmentDto resultAppointment = appointmentDao.updateAppointment(id, appointmentDto);
        log.info("updateAppointment(...) = " + resultAppointment);
        return resultAppointment;
    }

    public void deleteAppointment(Long id) throws EntityDeletingFailedException {
        log.info("deleteAppointment with id: " + id);
        appointmentDao.deleteAppointment(id);
        log.info("deleteAppointment(...) = void");
    }
}
