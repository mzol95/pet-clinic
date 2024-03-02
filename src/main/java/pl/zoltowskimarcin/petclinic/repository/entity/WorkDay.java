package pl.zoltowskimarcin.petclinic.repository.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "work_days")
public class WorkDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_of_work")
    private LocalDateTime dateOfWork;

    @Column(name = "startOfWork")
    private LocalTime startOfWork;

    @Column(name = "endOfWork")
    private LocalTime endOfWork;

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateOfWork() {
        return dateOfWork;
    }

    public void setDateOfWork(LocalDateTime dateOfWork) {
        this.dateOfWork = dateOfWork;
    }

    public LocalTime getStartOfWork() {
        return startOfWork;
    }

    public void setStartOfWork(LocalTime startOfWork) {
        this.startOfWork = startOfWork;
    }

    public LocalTime getEndOfWork() {
        return endOfWork;
    }

    public void setEndOfWork(LocalTime endOfWork) {
        this.endOfWork = endOfWork;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkDay workDay = (WorkDay) o;
        return Objects.equals(id, workDay.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "WorkDay{" +
                "id=" + id +
                ", dateOfWork=" + dateOfWork +
                ", startOfWork=" + startOfWork +
                ", endOfWork=" + endOfWork +
                '}';
    }
}

