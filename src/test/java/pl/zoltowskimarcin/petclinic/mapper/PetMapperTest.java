package pl.zoltowskimarcin.petclinic.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.zoltowskimarcin.petclinic.repository.entity.Pet;
import pl.zoltowskimarcin.petclinic.web.enums.Gender;
import pl.zoltowskimarcin.petclinic.web.model.pet.PetDto;

import java.time.LocalDate;


class PetMapperTest {

    private static final String PET_TEST_NAME = "Test name";
    private static final LocalDate PET_TEST_DATE_OF_BIRTH_01_01_2000 = LocalDate.of(2000, 1, 1);
    private static final Gender PET_TEST_GENDER_FEMALE = Gender.FEMALE;

    @Test
    void map_from_model_to_entity() {
        //given
        PetDto petDto = new PetDto
                .Builder()
                .name(PET_TEST_NAME)
                .dateOfBirth(PET_TEST_DATE_OF_BIRTH_01_01_2000)
                .gender(PET_TEST_GENDER_FEMALE)
                .build();

        //when
        Pet mappedPetEntity = new PetMapper().mapToEntity(petDto);

        String mappedName = mappedPetEntity.getName();
        LocalDate mappedDateOfBirth = mappedPetEntity.getDateOfBirth();
        Gender mappedGender = mappedPetEntity.getGender();

        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(PET_TEST_NAME, mappedName, "Name is not mapped correctly"),
                () -> Assertions.assertEquals(PET_TEST_DATE_OF_BIRTH_01_01_2000, mappedDateOfBirth, "Date of birth is not mapped correctly"),
                () -> Assertions.assertEquals(PET_TEST_GENDER_FEMALE, mappedGender)
        );
    }

    @Test
    void map_from_entity_to_model() {
        //given
        Pet pet = new Pet.Builder()
                .name(PET_TEST_NAME)
                .dateOfBirth(PET_TEST_DATE_OF_BIRTH_01_01_2000)
                .gender(PET_TEST_GENDER_FEMALE)
                .build();

        //when
        PetDto mappedPet = new PetMapper().mapToDto(pet, PetDto.class);
        String mappedName = mappedPet.getName();
        LocalDate mappedDateOfBirth = mappedPet.getDateOfBirth();
        Gender mappedGender = mappedPet.getGender();

        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(PET_TEST_NAME, mappedName, "Name is not mapped correctly"),
                () -> Assertions.assertEquals(PET_TEST_DATE_OF_BIRTH_01_01_2000, mappedDateOfBirth, "Date of birth is not mapped correctly"),
                () -> Assertions.assertEquals(PET_TEST_GENDER_FEMALE, mappedGender)
        );
    }

    @Test
    void from() {
        //given

        //when

        //then

    }
}