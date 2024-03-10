package pl.zoltowskimarcin.petclinic.mapper;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.zoltowskimarcin.petclinic.repository.entity.Client;
import pl.zoltowskimarcin.petclinic.web.model.cilent.ClientDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ClientMapper {

    public <T> T mapToDto(Client client, Class<T> type) {
        ModelMapper modelMapper = new ModelMapper();
        log.info("Mapping Client: " + client + "to ClientDto");
        T mappedClient = modelMapper.map(client, type);
        log.info("Mapped Client: " + client + "to ClientDto: " + mappedClient);
        return mappedClient;
    }


    public Client mapToEntity(ClientDto clientDto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(ClientDto.class, Client.class).addMappings(mapper -> mapper.skip(Client::setId));
        log.info("Mapping ClientDto: " + clientDto + "to Client");
        Client mappedClient = modelMapper.map(clientDto, Client.class);
        log.info("Mapped ClientDto: " + clientDto + "to Client: " + mappedClient);
        return mappedClient;
    }

    public <T> List<T> mapToDtoList(List<Client> clients, Class<T> type) {
        log.info("Mapping List<Client> to List<ClientDto>");
        List<T> clientDtos = clients.stream()
                .map(src -> mapToDto(src, type))
                .collect(Collectors.toList());
        return clientDtos;
    }

    public List<Client> mapToEntityList(List<ClientDto> clientDtos) {
        log.info("Mapping List<ClientDto> to List<Client>");
        List<Client> clients = clientDtos.stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList());
        return clients;
    }

}
