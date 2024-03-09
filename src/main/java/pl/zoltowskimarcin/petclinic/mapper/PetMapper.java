package pl.zoltowskimarcin.petclinic.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.zoltowskimarcin.petclinic.repository.entity.Pet;
import pl.zoltowskimarcin.petclinic.web.model.pet.PetDto;

import java.util.List;
import java.util.stream.Collectors;

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

    public PetDto from(Pet pet) {
        // ADD LOGGING
        ModelMapper mm = new ModelMapper();
        PetDto mappedPet = mm.map(pet, PetDto.class);
        return mappedPet;
    }

    public Pet from(PetDto petDto) {
        // TODO implement!
        return null;
    }

    public List<PetDto> fromPet(List<Pet> pets) {
        // ADD LOGGING
        List<PetDto> petDtos = pets.stream()
                .map(this::from)
                .collect(Collectors.toList());
        return petDtos;
    }

}
