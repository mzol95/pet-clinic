package pl.zoltowskimarcin.petclinic.repository.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "appointment_date")
    private LocalDateTime appointmentDate;

    @Column(name = "finished")
    private boolean finished;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne()
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    Appointment() {
    }

    private Appointment(Builder builder) {
        appointmentDate = builder.appointmentDate;
        finished = builder.finished;
        client = builder.client;
        pet = builder.pet;
        doctor = builder.doctor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", appointmentDate=" + appointmentDate +
                ", finished=" + finished +
                '}';
    }

    public static final class Builder {
        private LocalDateTime appointmentDate;
        private boolean finished;
        private Client client;
        private Pet pet;
        private Doctor doctor;

        public Builder() {
        }

        public Builder appointmentDate(LocalDateTime val) {
            appointmentDate = val;
            return this;
        }

        public Builder finished(boolean val) {
            finished = val;
            return this;
        }

        public Builder client(Client val) {
            client = val;
            return this;
        }

        public Builder pet(Pet val) {
            pet = val;
            return this;
        }

        public Builder doctor(Doctor val) {
            doctor = val;
            return this;
        }

        public Appointment build() {
            return new Appointment(this);
        }
    }
}
