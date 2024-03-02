package pl.zoltowskimarcin.petclinic.repository.dao;

import pl.zoltowskimarcin.petclinic.exception.client.ClientDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.client.ClientReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.client.ClientSavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.client.ClientUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.web.model.ClientDto;

import java.util.Optional;

public interface ClientDao {

    ClientDto saveClient(ClientDto clientDto) throws ClientSavingFailedException;

    Optional<ClientDto> getClientById(Long id)  throws ClientReadingFailedException;

    ClientDto updateClient(Long id, ClientDto clientDto) throws ClientUpdatingFailedException;

    void deleteClient(Long id) throws ClientDeletingFailedException;
}
