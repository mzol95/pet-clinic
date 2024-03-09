package pl.zoltowskimarcin.petclinic.repository.dao;

import pl.zoltowskimarcin.petclinic.exception.doctor.DoctorDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.doctor.DoctorReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.doctor.DoctorSavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.doctor.DoctorUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.web.model.DoctorDto;

import java.util.Optional;

public interface DoctorDao {

    DoctorDto saveDoctor(DoctorDto doctorDto) throws DoctorSavingFailedException;

    Optional<DoctorDto> getDoctorById(Long id) throws DoctorReadingFailedException;

    DoctorDto updateDoctor(Long id, DoctorDto doctorDto) throws DoctorUpdatingFailedException;

    void deleteDoctor(Long id) throws DoctorDeletingFailedException;
}
