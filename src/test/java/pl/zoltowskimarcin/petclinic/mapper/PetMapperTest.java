package pl.zoltowskimarcin.petclinic.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.zoltowskimarcin.petclinic.repository.entity.Pet;
import pl.zoltowskimarcin.petclinic.web.enums.Gender;
import pl.zoltowskimarcin.petclinic.web.model.pet.PetDto;

import java.time.LocalDate;

import static pl.zoltowskimarcin.petclinic.utils.TestUtils.*;


class PetMapperTest {

    @Test
    void map_from_model_to_entity() {
        //given
        PetDto petDto = new PetDto(PET_NAME_GARFIELD, PET_DATE_OF_BIRTH_19_06_1978, PET_GENDER_MALE);

        //when
        Pet mappedPetEntity = new PetMapper().mapToEntity(petDto);
        String mappedName = mappedPetEntity.getName();
        LocalDate mappedLocalDate = mappedPetEntity.getDateOfBirth();
        Gender mappedGender = mappedPetEntity.getGender();

        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(PET_NAME_GARFIELD, mappedName, "Name is not mapped correctly"),
                () -> Assertions.assertEquals(PET_DATE_OF_BIRTH_19_06_1978, mappedLocalDate, "Date of birth is not mapped correctly"),
                () -> Assertions.assertEquals(PET_GENDER_MALE, mappedGender, "Gender is not mapped correctly")
        );
    }

    @Test
    void map_from_entity_to_model() {
        //given
        Pet pet = new Pet(PET_NAME_GARFIELD, PET_DATE_OF_BIRTH_19_06_1978, PET_GENDER_MALE);

        //when
        PetDto mappedPet = new PetMapper().mapToDto(pet, PetDto.class);
        String mappedName = mappedPet.getName();
        LocalDate mappedLocalDate = mappedPet.getDateOfBirth();
        Gender mappedGender = mappedPet.getGender();


        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(PET_NAME_GARFIELD, mappedName, "Name is not mapped correctly"),
                () -> Assertions.assertEquals(PET_DATE_OF_BIRTH_19_06_1978, mappedLocalDate, "Date of birth is not mapped correctly"),
                () -> Assertions.assertEquals(PET_GENDER_MALE, mappedGender, "Gender is not mapped correctly")
        );
    }
}