package pl.zoltowskimarcin.petclinic.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.zoltowskimarcin.petclinic.repository.entity.Client;
import pl.zoltowskimarcin.petclinic.web.model.cilent.BasicClientDto;
import pl.zoltowskimarcin.petclinic.web.model.cilent.ClientDto;

@Component
public class ClientMapper {

    private static ModelMapper modelMapper;

    public static ModelMapper getMapper() {
        if (modelMapper == null) {
            modelMapper = new ModelMapper();
            modelMapper.typeMap(Client.class, ClientDto.class)
                    .addMapping(src -> src.getAddresses().getStreet(), ClientDto::setStreet)
                    .addMapping(src -> src.getAddresses().getCity(), ClientDto::setCity)
                    .addMapping(src -> src.getAddresses().getPostalCode(), ClientDto::setPostalCode);

            modelMapper.typeMap(ClientDto.class, Client.class)
                    .addMapping(ClientDto::getStreet, (entity, value) -> entity.getAddresses().setStreet((String) value))
                    .addMapping(ClientDto::getCity, (entity, value) -> entity.getAddresses().setCity((String) value))
                    .addMapping(ClientDto::getPostalCode, (entity, value) -> entity.getAddresses().setPostalCode((String) value));

            modelMapper.typeMap(Client.class, BasicClientDto.class)
                    .addMapping(src -> src.getAddresses().getStreet(), BasicClientDto::setStreet)
                    .addMapping(src -> src.getAddresses().getCity(), BasicClientDto::setCity)
                    .addMapping(src -> src.getAddresses().getPostalCode(), BasicClientDto::setPostalCode);

            modelMapper.typeMap(BasicClientDto.class, Client.class)
                    .addMapping(BasicClientDto::getStreet, (entity, value) -> entity.getAddresses().setStreet((String) value))
                    .addMapping(BasicClientDto::getCity, (entity, value) -> entity.getAddresses().setCity((String) value))
                    .addMapping(BasicClientDto::getPostalCode, (entity, value) -> entity.getAddresses().setPostalCode((String) value));
        }
        return modelMapper;
    }

}
