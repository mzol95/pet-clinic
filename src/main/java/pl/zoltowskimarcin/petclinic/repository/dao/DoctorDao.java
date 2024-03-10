package pl.zoltowskimarcin.petclinic.repository.dao;

import pl.zoltowskimarcin.petclinic.exception.doctor.DoctorDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.doctor.DoctorReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.doctor.DoctorSavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.doctor.DoctorUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.repository.entity.Doctor;

import java.util.Optional;

public interface DoctorDao {

    Doctor saveDoctor(Doctor doctorDto) throws DoctorSavingFailedException;

    Optional<Doctor> getDoctorById(Long id) throws DoctorReadingFailedException;

    Doctor updateDoctor(Long id, Doctor doctor) throws DoctorUpdatingFailedException;

    void deleteDoctor(Long id) throws DoctorDeletingFailedException;
}
