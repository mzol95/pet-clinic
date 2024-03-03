package pl.zoltowskimarcin.petclinic.repository.dao;

import pl.zoltowskimarcin.petclinic.exception.EntityDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntityReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntitySavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntityUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.web.model.cilent.BasicClientDto;
import pl.zoltowskimarcin.petclinic.web.model.cilent.LiteClientDto;

import java.util.List;
import java.util.Optional;

public interface ClientDao {

    BasicClientDto saveClient(BasicClientDto basicClient) throws EntitySavingFailedException;

    Optional<BasicClientDto> getClientById(Long id) throws EntityReadingFailedException;

    List<LiteClientDto> getAllClients() throws EntityReadingFailedException;

    BasicClientDto updateClient(Long id, BasicClientDto clientDto) throws EntityUpdatingFailedException;

    void deleteClient(Long id) throws EntityDeletingFailedException;
}
