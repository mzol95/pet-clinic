package pl.zoltowskimarcin.petclinic.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.zoltowskimarcin.petclinic.exception.appointment.AppointmentDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.appointment.AppointmentReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.appointment.AppointmentSavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.appointment.AppointmentUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.mapper.AppointmentMapper;
import pl.zoltowskimarcin.petclinic.repository.dao.AppointmentDao;
import pl.zoltowskimarcin.petclinic.repository.entity.Appointment;
import pl.zoltowskimarcin.petclinic.web.model.appointment.AppointmentDto;

@Service
@Slf4j
public class AppointmentService {

    private final AppointmentDao appointmentDao;

    public AppointmentService(AppointmentDao appointmentDao) {
        this.appointmentDao = appointmentDao;
    }

    public AppointmentDto saveAppointment(Appointment appointment) throws AppointmentSavingFailedException {
        log.info("save " + appointment + ")");
        Appointment returnedAppointment = appointmentDao.saveAppointment(appointment);
        AppointmentDto resultAppointment = new AppointmentMapper().mapToDto(returnedAppointment, AppointmentDto.class);
        log.info("save(...) = " + resultAppointment);
        return resultAppointment;
    }

    public AppointmentDto getAppointmentById(Long id) throws AppointmentReadingFailedException {
        log.info("getAppointmentById with id: " + id);

        Appointment returnedAppointment = appointmentDao.getAppointmentById(id)
                .orElseThrow(() -> new AppointmentReadingFailedException("Error while reading appointment"));

        AppointmentDto resultAppointment = new AppointmentMapper().mapToDto(returnedAppointment, AppointmentDto.class);

        log.info("getAppointmentById(...) = " + resultAppointment);
        return resultAppointment;
    }

    public AppointmentDto updateAppointment(Long id, Appointment appointment) throws AppointmentUpdatingFailedException {
        log.info("updateAppointment with id: " + id + " and appointmentDto: " + appointment);
        Appointment updatedAppointment = appointmentDao.updateAppointment(id, appointment);
        AppointmentDto resultAppointment = new AppointmentMapper().mapToDto(updatedAppointment, AppointmentDto.class);
        log.info("updateAppointment(...) = " + resultAppointment);
        return resultAppointment;
    }

    public void deleteAppointment(Long id) throws AppointmentDeletingFailedException {
        log.info("deleteAppointment with id: " + id);
        appointmentDao.deleteAppointment(id);
        log.info("deleteAppointment(...) = void");
    }
}
