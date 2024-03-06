package pl.zoltowskimarcin.petclinic.jdbc;

public class JdbcQueries {

    //Client
    public static final String FIND_CLIENT_BY_ID = "SELECT * FROM clients WHERE id = ?";
    public static final String FIND_PETS_BY_CLIENT_ID = "SELECT * FROM pets WHERE client_id = ?";
    public static final String FIND_APPOINTMENTS_BY_CLIENT_ID = "SELECT * FROM appointments WHERE client_id = ?";
    public static final String FIND_ALL_CLIENTS = "SELECT * FROM clients";

    public static final String FIND_CLIENT_BY_ID_WITH_DETAILS =
            "SELECT c.id, c.name, c.surname, c.street, c.city, c.postal_code, c.phone," +
            "p.id AS pet_id, p.name AS pet_name, p.date_of_birth AS pet_date_of_birth, p.gender AS pet_gender," +
            "a.id AS appointment_id, a.appointment_date AS appointment_date, a.finished as appointment_finished " +
            "FROM clients c " +
            "LEFT JOIN pets p ON c.id = p.client_id " +
            "LEFT JOIN appointments a ON c.id = a.client_id " +
            "WHERE c.id = ?";

    public static final String FIND_ALL_APPOINTMENTS_BY_CLIENT_ID = "SELECT * FROM appointments WHERE client_id = ?";


    public static final String FIND_DOCTORS_BY_ID = "SELECT * FROM doctors WHERE id = ?";
    public static final String FIND_PETS_BY_ID = "SELECT * FROM pets WHERE id = ?";
    public static final String FIND_APPOINTMENT_BY_ID = "SELECT * FROM appointments WHERE id = ?";
}


//private String name;
//private LocalDate dateOfBirth;
//private Gender gender;
//
//private ClientDto clientDto;
//private List<AppointmentDto> appointmentDtos;
