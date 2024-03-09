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

    public PetDto mapToDto(Pet pet) {
        ModelMapper modelMapper = new ModelMapper();
        log.info("Mapping Pet: " + pet + "to PetDto");
        PetDto mappedPet = modelMapper.map(pet, PetDto.class);
        log.info("Mapped Pet: " + pet + "to PetDto: " + mappedPet);
        return mappedPet;
    }

    public Pet mapToEntity(PetDto petDto) {
        ModelMapper modelMapper = new ModelMapper();
        log.info("Mapping PetDto: " + petDto + "to Pet");
        Pet mappedPet = modelMapper.map(petDto, Pet.class);
        log.info("Mapped PetDto: " + petDto + "to Pet: " + mappedPet);
        return mappedPet;
    }

    public List<PetDto> mapToDtoList(List<Pet> pets) {
        log.info("Mapping List<Pet> to List<PetDto>");
        List<PetDto> petDtos = pets.stream()
                .map(this::mapToDto)
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
