package pl.zoltowskimarcin.petclinic.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.zoltowskimarcin.petclinic.repository.entity.Appointment;

import java.util.Optional;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

    @Override
    <S extends Appointment> S save(S entity);

    @Override
    Optional<Appointment> findById(Long aLong);

    @Override
    Iterable<Appointment> findAll();

    @Override
    void deleteById(Long aLong);
}
