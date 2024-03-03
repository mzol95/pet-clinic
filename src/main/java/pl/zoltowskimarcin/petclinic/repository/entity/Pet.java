package pl.zoltowskimarcin.petclinic.repository.entity;

import jakarta.persistence.*;
import pl.zoltowskimarcin.petclinic.web.enums.Gender;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "gender")
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne(mappedBy = "pet")
    private Appointment appointments;

    Pet() {
    }

    private Pet(Builder builder) {
        name = builder.name;
        dateOfBirth = builder.dateOfBirth;
        gender = builder.gender;
        client = builder.client;
        appointments = builder.appointments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Appointment getAppointments() {
        return appointments;
    }

    public void setAppointments(Appointment appointments) {
        this.appointments = appointments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(id, pet.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                '}';
    }

    public static final class Builder {
        private String name;
        private LocalDate dateOfBirth;
        private Gender gender;
        private Client client;
        private Appointment appointments;

        public Builder() {
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder dateOfBirth(LocalDate val) {
            dateOfBirth = val;
            return this;
        }

        public Builder gender(Gender val) {
            gender = val;
            return this;
        }

        public Builder client(Client val) {
            client = val;
            return this;
        }

        public Builder appointments(Appointment val) {
            appointments = val;
            return this;
        }

        public Pet build() {
            return new Pet(this);
        }
    }
}
