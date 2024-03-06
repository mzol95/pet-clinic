package pl.zoltowskimarcin.petclinic.repository.dao;

import pl.zoltowskimarcin.petclinic.exception.EntityDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntityReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntitySavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntityUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.web.model.cilent.ClientDto;

import java.util.List;
import java.util.Optional;

public interface ClientDao {

    ClientDto saveClient(ClientDto basicClient) throws EntitySavingFailedException;

    Optional<ClientDto> getClientById(Long id) throws EntityReadingFailedException;

    Optional<ClientDto> getClientByIdWithDetails(Long id) throws EntityReadingFailedException;

    List<ClientDto> getAllClients() throws EntityReadingFailedException;

    ClientDto updateClient(Long id, ClientDto clientDto) throws EntityUpdatingFailedException;

    void deleteClient(Long id) throws EntityDeletingFailedException;
}
