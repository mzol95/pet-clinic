package pl.zoltowskimarcin.petclinic.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.zoltowskimarcin.petclinic.repository.entity.Pet;
import pl.zoltowskimarcin.petclinic.web.model.pet.PetDto;

@Component
public class PetMapper {

    private static ModelMapper modelMapper;

    public static ModelMapper getMapper() {
        if (modelMapper == null) {
            modelMapper = new ModelMapper();
            modelMapper.typeMap(PetDto.class, Pet.class)
                    .addMappings(mapper -> mapper.skip(Pet::setId));
        }
        return modelMapper;
    }

}
