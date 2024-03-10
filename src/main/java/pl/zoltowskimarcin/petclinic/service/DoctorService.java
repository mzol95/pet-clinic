package pl.zoltowskimarcin.petclinic.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.zoltowskimarcin.petclinic.exception.doctor.DoctorDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.doctor.DoctorReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.doctor.DoctorSavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.doctor.DoctorUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.mapper.DoctorMapper;
import pl.zoltowskimarcin.petclinic.repository.dao.DoctorDao;
import pl.zoltowskimarcin.petclinic.repository.entity.Doctor;
import pl.zoltowskimarcin.petclinic.web.model.DoctorDto;

@Service
@Slf4j
public class DoctorService {

    private final DoctorDao doctorDao;

    public DoctorService(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

    //CREATE
    public DoctorDto saveDoctor(Doctor doctor) throws DoctorSavingFailedException {
        log.info("saveDoctor(" + doctor + ")");
        Doctor resultDoctor = doctorDao.saveDoctor(doctor);
        log.info("saveDoctor(...) = " + resultDoctor);
        return new DoctorMapper().mapToDto(resultDoctor, DoctorDto.class);
    }

    //READ
    public DoctorDto getDoctorById(Long id) throws DoctorReadingFailedException {
        log.info("getDoctorById with id: " + id);
        Doctor resultDoctor = doctorDao.getDoctorById(id)
                .orElseThrow(() -> new DoctorReadingFailedException("Error while reading doctor"));
        log.info("getDoctorById(...) = " + resultDoctor);
        return new DoctorMapper().mapToDto(resultDoctor, DoctorDto.class);
    }

    //UPDATE
    public DoctorDto updateDoctor(Long id, Doctor doctor) throws DoctorUpdatingFailedException {
        log.info("updateDoctor with id: " + id + " and doctorDto: " + doctor);
        Doctor resultDoctor = doctorDao.updateDoctor(id, doctor);
        log.info("updateDoctor(...) = " + resultDoctor);
        return new DoctorMapper().mapToDto(resultDoctor, DoctorDto.class);
    }

    //DELETE
    public void deleteDoctor(Long id) throws DoctorDeletingFailedException {
        log.info("deleteDoctor with id: " + id);
        doctorDao.deleteDoctor(id);
        log.info("deleteDoctor(...) = void");
    }
}
