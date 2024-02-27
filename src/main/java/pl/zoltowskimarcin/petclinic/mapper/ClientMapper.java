package pl.zoltowskimarcin.petclinic.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.zoltowskimarcin.petclinic.repository.entity.Client;
import pl.zoltowskimarcin.petclinic.web.model.ClientDto;

@Component
public class ClientMapper {

    private static ModelMapper modelMapper;

    public static ModelMapper getMapper() {
        if (modelMapper == null) {
            modelMapper = new ModelMapper();
            modelMapper.typeMap(Client.class, ClientDto.class)
                    .addMapping(src -> src.getAddress().getStreet(), ClientDto::setStreet)
                    .addMapping(src -> src.getAddress().getCity(), ClientDto::setCity)
                    .addMapping(src -> src.getAddress().getPostalCode(), ClientDto::setPostalCode);

            modelMapper.typeMap(ClientDto.class, Client.class)
                    .addMapping(ClientDto::getStreet, (entity, value) -> entity.getAddress().setStreet((String) value))
                    .addMapping(ClientDto::getCity, (entity, value) -> entity.getAddress().setCity((String) value))
                    .addMapping(ClientDto::getPostalCode, (entity, value) -> entity.getAddress().setPostalCode((String) value));
        }
        return modelMapper;
    }

}
