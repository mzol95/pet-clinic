package pl.zoltowskimarcin.petclinic.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.zoltowskimarcin.petclinic.exception.Doctor.DoctorDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.Doctor.DoctorReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.Doctor.DoctorSavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.Doctor.DoctorUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.repository.dao.DoctorDao;
import pl.zoltowskimarcin.petclinic.web.model.DoctorDto;

import java.util.Optional;

@Service
@Slf4j
public class DoctorService {

    private final DoctorDao doctorDao;

    public DoctorService(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

    //CREATE
    public DoctorDto saveDoctor(DoctorDto doctorDto) throws DoctorSavingFailedException {
        log.info("saveDoctor(" + doctorDto + ")");
        DoctorDto resultDoctor = doctorDao.saveDoctor(doctorDto);
        log.info("saveDoctor(...) = " + resultDoctor);
        return resultDoctor;
    }

    //READ
    public Optional<DoctorDto> getDoctorById(Long id) throws DoctorReadingFailedException {
        log.info("getDoctorById with id: " + id);
        Optional<DoctorDto> resultDoctor = doctorDao.getDoctorById(id);
        log.info("getDoctorById(...) = " + resultDoctor);
        return resultDoctor;
    }

    //UPDATE
    public DoctorDto updateDoctor(Long id, DoctorDto doctorDto) throws DoctorUpdatingFailedException {
        log.info("updateDoctor with id: " + id + " and doctorDto: " + doctorDto);
        DoctorDto resultDoctor = doctorDao.updateDoctor(id, doctorDto);
        log.info("updateDoctor(...) = " + resultDoctor);
        return resultDoctor;
    }

    //DELETE
    public void deleteDoctor(Long id) throws DoctorDeletingFailedException {
        log.info("deleteDoctor with id: " + id);
        doctorDao.deleteDoctor(id);
        log.info("deleteDoctor(...) = void");
    }
}
