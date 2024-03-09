package pl.zoltowskimarcin.petclinic.repository.dao;

import pl.zoltowskimarcin.petclinic.exception.client.ClientDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.client.ClientReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.client.ClientSavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.client.ClientUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.web.model.cilent.ClientDto;

import java.util.List;
import java.util.Optional;

public interface ClientDao {

    ClientDto saveClient(ClientDto basicClient) throws ClientSavingFailedException;

    Optional<ClientDto> getClientById(Long id) throws ClientReadingFailedException;

    Optional<ClientDto> getClientByIdWithDetails(Long id) throws ClientReadingFailedException;

    List<ClientDto> getAllClients() throws ClientReadingFailedException;

    ClientDto updateClient(Long id, ClientDto clientDto) throws ClientUpdatingFailedException;

    void deleteClient(Long id) throws ClientDeletingFailedException;
}
