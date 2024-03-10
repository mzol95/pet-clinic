package pl.zoltowskimarcin.petclinic.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.zoltowskimarcin.petclinic.repository.entity.Doctor;
import pl.zoltowskimarcin.petclinic.web.model.DoctorDto;

import static pl.zoltowskimarcin.petclinic.utils.TestUtils.DOCTOR_NAME_GREGORY;
import static pl.zoltowskimarcin.petclinic.utils.TestUtils.DOCTOR_SURNAME_HOUSE;

class DoctorMapperTest {

    @Test
    void map_from_model_to_entity() {
        //given
        DoctorDto doctorDto = new DoctorDto(DOCTOR_NAME_GREGORY, DOCTOR_SURNAME_HOUSE);

        //when
        Doctor mappedDoctorEntity = new DoctorMapper().mapToEntity(doctorDto);
        String mappedName = mappedDoctorEntity.getName();
        String mappedSurname = mappedDoctorEntity.getSurname();

        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(DOCTOR_NAME_GREGORY, mappedName, "Name is not mapped correctly"),
                () -> Assertions.assertEquals(DOCTOR_SURNAME_HOUSE, mappedSurname, "Surname is not mapped correctly")
        );
    }

    @Test
    void map_from_entity_to_model() {
        //given
        Doctor doctor = new Doctor(DOCTOR_NAME_GREGORY, DOCTOR_SURNAME_HOUSE);

        //when
        DoctorDto mappedDoctor = new DoctorMapper().mapToDto(doctor, DoctorDto.class);
        String mappedName = mappedDoctor.getName();
        String mappedSurname = mappedDoctor.getSurname();

        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(DOCTOR_NAME_GREGORY, mappedName, "Name is not mapped correctly"),
                () -> Assertions.assertEquals(DOCTOR_SURNAME_HOUSE, mappedSurname, "Surname is not mapped correctly")
        );
    }
}