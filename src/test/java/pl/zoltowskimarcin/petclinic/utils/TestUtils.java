package pl.zoltowskimarcin.petclinic.utils;

import pl.zoltowskimarcin.petclinic.web.enums.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TestUtils {
    public static final String CLIENT_NAME_JON = "Jon";
    public static final String CLIENT_SURNAME_SNOW = "Snow";
    public static final String CLIENT_JON_PHONE_123_456_789 = "123-456-789";
    public static final String CLIENT_JON_ADDRESS_STREET = "street1";
    public static final String CLIENT_JON_ADDRESS_CITY = "city1";
    public static final String CLIENT_JON_ADDRESS_POSTAL_CODE = "postal code1";

    public static final String CLIENT_NAME_NED = "Ned";
    public static final String CLIENT_SURNAME_STARK = "Stark";
    public static final String CLIENT_NED_PHONE_123_000_987 = "123-000-987";
    public static final String CLIENT_NED_ADDRESS_STREET = "street2";
    public static final String CLIENT_NED_ADDRESS_CITY = "city2";
    public static final String CLIENT_NED_ADDRESS_POSTAL_CODE = "postal code2";

    public static final String CLIENT_NAME_KHAL = "Khal";
    public static final String CLIENT_SURNAME_DROGO = "Drogo";
    public static final String CLIENT_KHAL_PHONE_111_222_333 = "111-222-333";
    public static final String CLIENT_KHAL_ADDRESS_STREET = "street3";
    public static final String CLIENT_KHAL_ADDRESS_CITY = "city3";
    public static final String CLIENT_KHAL_ADDRESS_POSTAL_CODE = "postal code3";

    public static final String UPDATE_CLIENT_NAME_TYRION = "Tyrion";
    public static final String UPDATE_CLIENT_SURNAME_LANNISTER = "Lannister";
    public static final String UPDATE_CLIENT_PHONE_987_654_321 = "987-654-321";


    public static final int LIST_SIZE_2 = 2;
    public static final Long ID_1 = 1L;

    public static final String DOCTOR_NAME_GREGORY = "Gregory";
    public static final String DOCTOR_SURNAME_HOUSE = "House";

    public static final String UPDATE_DOCTOR_NAME_ALLISON = "Allison";
    public static final String UPDATE_DOCTOR_SURNAME_CAMERON = "Cameron";

    public static final String PET_NAME_GARFIELD = "Garfield";
    public static final Gender PET_GENDER_MALE = Gender.MALE;
    public static final LocalDate PET_DATE_OF_BIRTH_19_06_1978 = LocalDate.of(1978, 6, 19);

    public static final String UPDATE_PET_NAME_TOM = "Tom";
    public static final Gender UPDATE_PET_GENDER_MALE = Gender.MALE;
    public static final LocalDate UPDATE_PET_DATE_OF_BIRTH_10_02_1980 = LocalDate.of(1980, 2, 10);

    public static final LocalDateTime APPOINTMENT_DATE_TIME = LocalDateTime.of(1900, 02, 02, 13, 11);
    public static final boolean APPOINTMENT_FINISHED = true;

    public static final LocalDateTime UPDATE_APPOINTMENT_DATE_TIME = LocalDateTime.of(2000, 01, 01, 12, 00);
    public static final boolean UPDATE_APPOINTMENT_FINISHED = false;


}
