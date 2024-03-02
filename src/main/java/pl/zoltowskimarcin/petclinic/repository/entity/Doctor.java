package pl.zoltowskimarcin.petclinic.repository.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;

    @OneToOne
    @JoinColumn(name = "work_schedule_id")
    private WorkSchedule workSchedule;

    public Doctor() {
    }

    private Doctor(Builder builder) {
        name = builder.name;
        surname = builder.surname;
        appointments = builder.appointments;
        workSchedule = builder.workSchedule;
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
        appointment.setDoctor(this);
    }

    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
        appointment.setDoctor(null);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(id, doctor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public WorkSchedule getWorkSchedule() {
        return workSchedule;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void setWorkSchedule(WorkSchedule workSchedule) {
        this.workSchedule = workSchedule;
    }

    public static final class Builder {
        private String name;
        private String surname;
        private List<Appointment> appointments;
        private WorkSchedule workSchedule;

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

        public Builder appointments(List<Appointment> val) {
            appointments = val;
            return this;
        }

        public Builder workSchedule(WorkSchedule val) {
            workSchedule = val;
            return this;
        }

        public Doctor build() {
            return new Doctor(this);
        }
    }
}
