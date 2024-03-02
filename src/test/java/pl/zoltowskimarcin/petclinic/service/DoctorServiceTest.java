package pl.zoltowskimarcin.petclinic.service;

import liquibase.exception.CommandExecutionException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.zoltowskimarcin.petclinic.utils.DatabaseInitializer;
import pl.zoltowskimarcin.petclinic.web.model.DoctorDto;

class DoctorServiceTest {

    public static final String DOCTOR_TEST_NAME = "Test name";
    public static final String DOCTOR_TEST_SURNAME = "Test surname";
    public static final String DOCTOR_UPDATE_SURNAME = "Update surname";
    public static final String DOCTOR_UPDATE_NAME = "Update name";
    private DoctorDto doctorDto;
    private DoctorDto updatedDoctorDto;

    @BeforeEach
    void setUp() throws CommandExecutionException {
        DatabaseInitializer.initializeDatabase();

        doctorDto = new DoctorDto
                .Builder()
                .name(DOCTOR_TEST_NAME)
                .surname(DOCTOR_TEST_SURNAME)
                .build();

        updatedDoctorDto = new DoctorDto.Builder()
                .name(DOCTOR_UPDATE_NAME)
                .surname(DOCTOR_UPDATE_SURNAME)
                .build();
    }

    @AfterEach
    void tearDown() throws CommandExecutionException {
        DatabaseInitializer.dropDatabase();
    }

    @Test
    void saveDoctor() {
    }

    @Test
    void getDoctor() {
    }

    @Test
    void updateDoctor() {
    }

    @Test
    void deleteDoctor() {
    }
}