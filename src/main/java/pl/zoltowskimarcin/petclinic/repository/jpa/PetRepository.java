package pl.zoltowskimarcin.petclinic.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.zoltowskimarcin.petclinic.repository.entity.Pet;

import java.util.Optional;

@Repository
public interface PetRepository extends CrudRepository<Pet, Long> {

    @Override
    <S extends Pet> S save(S entity);

    @Override
    Optional<Pet> findById(Long aLong);

    @Override
    Iterable<Pet> findAll();

    @Override
    void deleteById(Long aLong);
}
