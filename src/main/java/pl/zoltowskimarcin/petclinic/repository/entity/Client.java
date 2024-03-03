package pl.zoltowskimarcin.petclinic.repository.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "phone")
    private String phone;
    @Embedded
    private Address addresses;
    @OneToMany(mappedBy = "client")
    private List<Pet> pets;
    @OneToMany(mappedBy = "client")
    private List<Appointment> appointments;

    Client() {
    }

    private Client(Builder builder) {
        name = builder.name;
        surname = builder.surname;
        phone = builder.phone;
        addresses = builder.addresses;
        pets = builder.pets;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddresses() {
        return addresses;
    }

    public void setAddresses(Address addresses) {
        this.addresses = addresses;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public static final class Builder {
        private String name;
        private String surname;
        private String phone;
        private Address addresses;
        private List<Pet> pets;
        private List<Appointment> appointments;

        public Builder() {
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder surname(String val) {
            surname = val;
            return this;
        }

        public Builder phone(String val) {
            phone = val;
            return this;
        }

        public Builder addresses(Address val) {
            addresses = val;
            return this;
        }

        public Builder pets(List<Pet> val) {
            pets = val;
            return this;
        }

        public Builder appointments(List<Appointment> val) {
            appointments = val;
            return this;
        }

        public Client build() {
            return new Client(this);
        }
    }
}
