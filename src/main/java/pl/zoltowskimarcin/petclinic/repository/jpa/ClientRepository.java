package pl.zoltowskimarcin.petclinic.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.zoltowskimarcin.petclinic.repository.entity.Client;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

    @Override
    <S extends Client> S save(S entity);

    @Override
    Optional<Client> findById(Long aLong);

    @Override
    Iterable<Client> findAll();

    @Override
    void deleteById(Long aLong);
}
