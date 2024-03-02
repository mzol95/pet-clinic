package pl.zoltowskimarcin.petclinic.jdbc;

public class JdbcQueries {
    public static final String FIND_CLIENTS_BY_ID = "SELECT * FROM clients WHERE id = ?";
    public static final String FIND_DOCTORS_BY_ID = "SELECT * FROM doctors WHERE id = ?";
    public static final String FIND_DOCTORS_BY_ID1 =
            "SELECT * FROM doctors d LEFT JOIN appointments a ON d.id = a.doctor_id WHERE d.id = ?";
}
