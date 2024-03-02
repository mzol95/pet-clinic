package pl.zoltowskimarcin.petclinic.repository.dao;

import pl.zoltowskimarcin.petclinic.exception.client.EntityDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.client.EntityReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.client.EntitySavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.client.EntityUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.web.model.DoctorDto;

import java.util.Optional;

public interface DoctorDao {

    DoctorDto saveDoctor(DoctorDto doctorDto) throws EntitySavingFailedException;

    Optional<DoctorDto> getDoctorById(Long id) throws EntityReadingFailedException;

    DoctorDto updateDoctor(Long id, DoctorDto doctorDto) throws EntityUpdatingFailedException;

    void deleteDoctor(Long id) throws EntityDeletingFailedException;
}
