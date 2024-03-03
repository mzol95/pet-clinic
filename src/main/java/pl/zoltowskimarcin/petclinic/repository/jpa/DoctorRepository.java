package pl.zoltowskimarcin.petclinic.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.zoltowskimarcin.petclinic.repository.entity.Doctor;

import java.util.Optional;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long> {

    @Override
    <S extends Doctor> S save(S entity);

    @Override
    Optional<Doctor> findById(Long aLong);

    @Override
    Iterable<Doctor> findAll();

    @Override
    void deleteById(Long aLong);
}
