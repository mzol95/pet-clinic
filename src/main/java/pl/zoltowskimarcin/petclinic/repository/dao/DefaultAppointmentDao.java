package pl.zoltowskimarcin.petclinic.repository.dao;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import pl.zoltowskimarcin.petclinic.exception.appointment.AppointmentDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.appointment.AppointmentReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.appointment.AppointmentSavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.appointment.AppointmentUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.jdbc.DataSource;
import pl.zoltowskimarcin.petclinic.jdbc.JdbcQueries;
import pl.zoltowskimarcin.petclinic.repository.JpaStandardUtils;
import pl.zoltowskimarcin.petclinic.repository.NativeHibernateUtils;
import pl.zoltowskimarcin.petclinic.repository.entity.Appointment;
import pl.zoltowskimarcin.petclinic.repository.jpa.AppointmentRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@Slf4j
public class DefaultAppointmentDao implements AppointmentDao {

    private final AppointmentRepository appointmentRepository;

    public DefaultAppointmentDao(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    //CREATE - Native Hibernate
    @Override
    public Appointment saveAppointment(Appointment appointment) throws AppointmentSavingFailedException {
        log.info("save " + appointment + ")");
        Appointment appointmentToPersist = appointment;

        try (Session session = NativeHibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(appointmentToPersist);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error while saving appointment", e);
            throw new AppointmentSavingFailedException("Error while saving appointment");
        }
        log.info("save(...) = " + appointmentToPersist);
        return appointmentToPersist;
    }

    //READ - JDBC
    @Override
    public Optional<Appointment> getAppointmentById(Long id) throws AppointmentReadingFailedException {
        log.info("getAppointmentById with id: " + id);
        Appointment returnedAppointment = null;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement readStatement = connection.prepareStatement(JdbcQueries.FIND_APPOINTMENT_BY_ID)) {

            readStatement.setLong(1, id);
            try (ResultSet resultSet = readStatement.executeQuery()) {
                if (resultSet.next()) {
                    LocalDateTime appointmentDate = resultSet.getTimestamp("appointment_date").toLocalDateTime();
                    boolean finished = resultSet.getBoolean("finished");
                    returnedAppointment = new Appointment(appointmentDate, finished);
                    log.info("get(...) = " + returnedAppointment);
                }
            }
        } catch (SQLException e) {
            log.error("Error while getting client with id: " + id, e);
            throw new AppointmentReadingFailedException("Error while getting client with id: " + id);
        }
        return Optional.ofNullable(returnedAppointment);
    }

    //UPDATE - Spring Data JPA
    @Transactional
    @Override
    public Appointment updateAppointment(Long id, Appointment appointment) throws AppointmentUpdatingFailedException {
        log.info("update " + appointment + " with id: " + id);

        Appointment appointmentToUpdate = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentUpdatingFailedException("Appointment with id: " + id + " doesn't exists in database."));

        Appointment updatingAppointment = appointment;

        appointmentToUpdate.setAppointmentDate(updatingAppointment.getAppointmentDate());
        appointmentToUpdate.setFinished(updatingAppointment.isFinished());

        appointmentRepository.save(appointmentToUpdate);

        log.info("update(...) = " + appointmentToUpdate);
        return appointmentToUpdate;
    }

    //DELETE - JpaStandard
    @Override
    public void deleteAppointment(Long id) throws AppointmentDeletingFailedException {
        EntityManager entityManager = JpaStandardUtils.getEntityManager();
        entityManager.getTransaction().begin();

        Appointment appointmentToRemove = entityManager.find(Appointment.class, id);

        if (appointmentToRemove == null) {
            entityManager.close();
            log.error("Appointment with id: " + id + " doesn't exists in database.");
            throw new AppointmentDeletingFailedException("Appointment with id: " + id + " doesn't exists in database.");
        }
        entityManager.remove(appointmentToRemove);
        entityManager.getTransaction().commit();
        entityManager.close();
        log.info("delete(...) = " + appointmentToRemove);
    }

}
