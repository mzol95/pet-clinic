package pl.zoltowskimarcin.petclinic.repository.dao;

import pl.zoltowskimarcin.petclinic.exception.client.ClientDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.client.ClientReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.client.ClientSavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.client.ClientUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.repository.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientDao {

    Client saveClient(Client client) throws ClientSavingFailedException;

    Optional<Client> getClientById(Long id) throws ClientReadingFailedException;

    Optional<Client> getClientByIdWithDetails(Long id) throws ClientReadingFailedException;

    List<Client> getAllClients() throws ClientReadingFailedException;

    Client updateClient(Long id, Client client) throws ClientUpdatingFailedException;

    void deleteClient(Long id) throws ClientDeletingFailedException;
}
