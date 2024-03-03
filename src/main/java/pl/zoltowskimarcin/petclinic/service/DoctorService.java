package pl.zoltowskimarcin.petclinic.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.zoltowskimarcin.petclinic.exception.EntityDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntityReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntitySavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntityUpdatingFailedException;
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
    public DoctorDto saveDoctor(DoctorDto doctorDto) throws EntitySavingFailedException {
        log.info("saveDoctor(" + doctorDto + ")");
        DoctorDto resultDoctor = doctorDao.saveDoctor(doctorDto);
        log.info("saveDoctor(...) = " + resultDoctor);
        return resultDoctor;
    }

    //READ
    public Optional<DoctorDto> getDoctorById(Long id) throws EntityReadingFailedException {
        log.info("getDoctorById with id: " + id);
        Optional<DoctorDto> resultDoctor = doctorDao.getDoctorById(id);
        log.info("getDoctorById(...) = " + resultDoctor);
        return resultDoctor;
    }

    //UPDATE
    public DoctorDto updateDoctor(Long id, DoctorDto doctorDto) throws EntityUpdatingFailedException {
        log.info("updateDoctor with id: " + id + " and doctorDto: " + doctorDto);
        DoctorDto resultDoctor = doctorDao.updateDoctor(id, doctorDto);
        log.info("updateDoctor(...) = " + resultDoctor);
        return resultDoctor;
    }

    //DELETE
    public void deleteDoctor(Long id) throws EntityDeletingFailedException {
        log.info("deleteDoctor with id: " + id);
        doctorDao.deleteDoctor(id);
        log.info("deleteDoctor(...) = void");
    }
}
