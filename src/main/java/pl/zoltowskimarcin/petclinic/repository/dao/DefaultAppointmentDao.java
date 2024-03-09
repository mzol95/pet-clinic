package pl.zoltowskimarcin.petclinic.repository.dao;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import pl.zoltowskimarcin.petclinic.exception.EntityDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntityReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntitySavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.EntityUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.jdbc.DataSource;
import pl.zoltowskimarcin.petclinic.jdbc.JdbcQueries;
import pl.zoltowskimarcin.petclinic.mapper.AppointmentMapper;
import pl.zoltowskimarcin.petclinic.repository.JpaStandardUtils;
import pl.zoltowskimarcin.petclinic.repository.NativeHibernateUtils;
import pl.zoltowskimarcin.petclinic.repository.entity.Appointment;
import pl.zoltowskimarcin.petclinic.repository.jpa.AppointmentRepository;
import pl.zoltowskimarcin.petclinic.web.model.appointment.AppointmentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public AppointmentDto saveAppointment(AppointmentDto appointmentDto) throws EntitySavingFailedException {
        log.info("save " + appointmentDto + ")");
        Appointment appointmentToPersist = AppointmentMapper.getMapper().map(appointmentDto, Appointment.class);

        try (Session session = NativeHibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(appointmentToPersist);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error while saving appointment", e);
            throw new EntitySavingFailedException("Error while saving appointment");
        }
        log.info("save(...) = " + appointmentToPersist);
        return AppointmentMapper.getMapper().map(appointmentToPersist, AppointmentDto.class);
    }

    //READ - JDBC
    @Override
    public Optional<AppointmentDto> getAppointmentById(Long id) throws EntityReadingFailedException {
        log.info("getAppointmentById with id: " + id);

        try (Connection connection = DataSource.getConnection();
             PreparedStatement readStatement = connection.prepareStatement(JdbcQueries.FIND_APPOINTMENT_BY_ID)) {

            readStatement.setLong(1, id);
            try (ResultSet resultSet = readStatement.executeQuery()) {
                if (resultSet.next()) {
                    AppointmentDto returnedAppointment = new AppointmentDto.Builder()
                            .appointmentDate(resultSet.getTimestamp("appointment_date").toLocalDateTime())
                            .finished(resultSet.getBoolean("finished"))
                            .build();
                    log.info("get(...) = " + returnedAppointment);
                    return Optional.ofNullable(returnedAppointment);
                }
            }
        } catch (SQLException e) {
            log.error("Error while getting client with id: " + id, e);
            throw new EntityReadingFailedException("Error while getting client with id: " + id);
        }
        return Optional.empty();
    }

    //UPDATE - Spring Data JPA
    @Transactional
    @Override
    public AppointmentDto updateAppointment(Long id, AppointmentDto appointmentDto) throws EntityUpdatingFailedException {
        log.info("update " + appointmentDto + " with id: " + id);

        Appointment appointmentToUpdate = appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityUpdatingFailedException("Appointment with id: " + id + " doesn't exists in database."));

        Appointment updatingAppointment = AppointmentMapper.getMapper().map(appointmentDto, Appointment.class);

        appointmentToUpdate.setAppointmentDate(updatingAppointment.getAppointmentDate());
        appointmentToUpdate.setFinished(updatingAppointment.isFinished());

        appointmentRepository.save(appointmentToUpdate);

        log.info("update(...) = " + appointmentToUpdate);
        return AppointmentMapper.getMapper().map(appointmentToUpdate, AppointmentDto.class);
    }

    //DELETE - JpaStandard
    @Override
    public void deleteAppointment(Long id) throws EntityDeletingFailedException {
        EntityManager entityManager = JpaStandardUtils.getEntityManager();
        entityManager.getTransaction().begin();

        Appointment appointmentToRemove = entityManager.find(Appointment.class, id);

        if (appointmentToRemove == null) {
            entityManager.close();
            log.error("Appointment with id: " + id + " doesn't exists in database.");
            throw new EntityDeletingFailedException("Appointment with id: " + id + " doesn't exists in database.");
        }
        entityManager.remove(appointmentToRemove);
        entityManager.getTransaction().commit();
        entityManager.close();
        log.info("delete(...) = " + appointmentToRemove);
    }

}
