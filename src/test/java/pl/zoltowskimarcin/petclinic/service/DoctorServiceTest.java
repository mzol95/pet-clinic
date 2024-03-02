package pl.zoltowskimarcin.petclinic.service;

import liquibase.exception.CommandExecutionException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.zoltowskimarcin.petclinic.repository.jpa.DoctorRepository;
import pl.zoltowskimarcin.petclinic.utils.DatabaseInitializer;
import pl.zoltowskimarcin.petclinic.web.model.DoctorDto;

@SpringBootTest
class DoctorServiceTest {

    public static final String DOCTOR_TEST_NAME = "Test name";
    public static final String DOCTOR_TEST_SURNAME = "Test surname";

    public static final String DOCTOR_UPDATE_SURNAME = "Update surname";
    public static final String DOCTOR_UPDATE_NAME = "Update name";

    @Autowired
    private DoctorService doctorService;
    private DoctorRepository doctorRepository;

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
        //given

        //when

        //then

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