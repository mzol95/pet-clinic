package pl.zoltowskimarcin.petclinic.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.zoltowskimarcin.petclinic.repository.entity.Doctor;
import pl.zoltowskimarcin.petclinic.web.model.DoctorDto;

class DoctorMapperTest {

    private static final String DOCTOR_TEST_NAME = "Test name";
    private static final String DOCTOR_TEST_SURNAME = "Test surname";

    @Test
    void map_from_model_to_entity() {
        //given
        DoctorDto doctorDto = new DoctorDto
                .Builder()
                .name(DOCTOR_TEST_NAME)
                .surname(DOCTOR_TEST_SURNAME)
                .build();

        //when
        Doctor mappedDoctorEntity = DoctorMapper.getMapper().map(doctorDto, Doctor.class);
        String mappedName = mappedDoctorEntity.getName();
        String mappedSurname = mappedDoctorEntity.getSurname();

        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(DOCTOR_TEST_NAME, mappedName, "Name is not mapped correctly"),
                () -> Assertions.assertEquals(DOCTOR_TEST_SURNAME, mappedSurname, "Surname is not mapped correctly")
        );
    }

    @Test
    void map_from_entity_to_model() {
        //given
        Doctor doctor = new Doctor.Builder()
                .name(DOCTOR_TEST_NAME)
                .surname(DOCTOR_TEST_SURNAME)
                .build();

        //when
        DoctorDto mappedDoctor = DoctorMapper.getMapper().map(doctor, DoctorDto.class);
        String mappedName = mappedDoctor.getName();
        String mappedSurname = mappedDoctor.getSurname();

        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(DOCTOR_TEST_NAME, mappedName, "Name is not mapped correctly"),
                () -> Assertions.assertEquals(DOCTOR_TEST_SURNAME, mappedSurname, "Surname is not mapped correctly")
        );
    }
}