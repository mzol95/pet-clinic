package pl.zoltowskimarcin.petclinic.mapper;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.zoltowskimarcin.petclinic.repository.entity.Pet;
import pl.zoltowskimarcin.petclinic.web.model.pet.PetDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class PetMapper {

    public <T> T mapToDto(Pet pet, Class<T> type) {
        ModelMapper modelMapper = new ModelMapper();
        log.info("Mapping Pet: " + pet + "to PetDto");
        T mappedPet = modelMapper.map(pet, type);
        log.info("Mapped Pet: " + pet + "to PetDto: " + mappedPet);
        return mappedPet;
    }

    public Pet mapToEntity(PetDto petDto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(PetDto.class, Pet.class).addMappings(mapper -> mapper.skip(Pet::setId));
        log.info("Mapping PetDto: " + petDto + "to Pet");
        Pet mappedPet = modelMapper.map(petDto, Pet.class);
        log.info("Mapped PetDto: " + petDto + "to Pet: " + mappedPet);
        return mappedPet;
    }

    public <T> List<T> mapToDtoList(List<Pet> pets, Class<T> type) {
        log.info("Mapping List<Pet> to List<PetDto>");
        List<T> petDtos = pets.stream()
                .map(src -> mapToDto(src, type))
                .collect(Collectors.toList());
        return petDtos;
    }

    public List<Pet> mapToEntityList(List<PetDto> petDtos) {
        log.info("Mapping List<PetDto> to List<Pet>");
        List<Pet> pets = petDtos.stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList());
        return pets;
    }

}
