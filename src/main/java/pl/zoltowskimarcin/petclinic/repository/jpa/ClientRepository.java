package pl.zoltowskimarcin.petclinic.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zoltowskimarcin.petclinic.repository.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
