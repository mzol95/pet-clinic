package pl.zoltowskimarcin.petclinic.repository.dao;

import pl.zoltowskimarcin.petclinic.exception.EntityDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntityReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntitySavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntityUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.web.model.ClientDto;

import java.util.Optional;

public interface ClientDao {

    ClientDto saveClient(ClientDto clientDto) throws EntitySavingFailedException;

    Optional<ClientDto> getClientById(Long id) throws EntityReadingFailedException;

    ClientDto updateClient(Long id, ClientDto clientDto) throws EntityUpdatingFailedException;

    void deleteClient(Long id) throws EntityDeletingFailedException;
}
