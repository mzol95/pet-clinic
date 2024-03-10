package pl.zoltowskimarcin.petclinic.repository.dao;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import pl.zoltowskimarcin.petclinic.exception.doctor.DoctorDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.doctor.DoctorReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.doctor.DoctorSavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.doctor.DoctorUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.jdbc.DataSource;
import pl.zoltowskimarcin.petclinic.jdbc.JdbcQueries;
import pl.zoltowskimarcin.petclinic.mapper.DoctorMapper;
import pl.zoltowskimarcin.petclinic.repository.JpaStandardUtils;
import pl.zoltowskimarcin.petclinic.repository.NativeHibernateUtils;
import pl.zoltowskimarcin.petclinic.repository.entity.Doctor;
import pl.zoltowskimarcin.petclinic.repository.jpa.DoctorRepository;
import pl.zoltowskimarcin.petclinic.web.model.DoctorDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
@Slf4j
public class DefaultDoctorDao implements DoctorDao {

    private final DoctorRepository doctorRepository;

    public DefaultDoctorDao(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    //CREATE - Native Hibernate
    @Override
    public DoctorDto saveDoctor(DoctorDto doctorDto) throws DoctorSavingFailedException {
        log.info("save " + doctorDto + ")");
        Doctor doctorToPersist = new DoctorMapper().mapToEntity(doctorDto);

        try (Session session = NativeHibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(doctorToPersist);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error while saving doctor", e);
            throw new DoctorSavingFailedException("Error while saving doctor");
        }
        log.info("save(...) = " + doctorToPersist);
        return new DoctorMapper().mapToDto(doctorToPersist, DoctorDto.class);
    }

    //READ - JDBC
    @Override
    public Optional<DoctorDto> getDoctorById(Long id) throws DoctorReadingFailedException {
        log.info("getDoctorById with id: " + id);

        try (Connection connection = DataSource.getConnection();
             PreparedStatement readStatement = connection.prepareStatement(JdbcQueries.FIND_DOCTORS_BY_ID)) {

            readStatement.setLong(1, id);
            try (ResultSet resultSet = readStatement.executeQuery()) {
                if (resultSet.next()) {
                    DoctorDto returnedDoctor = new DoctorDto.Builder()
                            .name(resultSet.getString("name"))
                            .surname(resultSet.getString("surname"))
                            .build();
                    log.info("get(...) = " + returnedDoctor);
                    return Optional.ofNullable(returnedDoctor);
                }
            }
        } catch (SQLException e) {
            log.error("Error while getting client with id: " + id, e);
            throw new DoctorReadingFailedException("Error while getting client with id: " + id);
        }
        return Optional.empty();
    }

    //UPDATE - Spring Data JPA
    @Transactional
    @Override
    public DoctorDto updateDoctor(Long id, DoctorDto doctorDto) throws DoctorUpdatingFailedException {
        log.info("update " + doctorDto + " with id: " + id);

        Doctor doctorToUpdate = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorUpdatingFailedException("Doctor with id: " + id + " doesn't exists in database."));

        Doctor updatingDoctor = new DoctorMapper().mapToEntity(doctorDto);

        doctorToUpdate.setName(updatingDoctor.getName());
        doctorToUpdate.setSurname(updatingDoctor.getSurname());
        doctorToUpdate.setAppointments(updatingDoctor.getAppointments());
        doctorRepository.save(doctorToUpdate);

        log.info("update(...) = " + doctorToUpdate);
        return new DoctorMapper().mapToDto(doctorToUpdate, DoctorDto.class);
    }

    //DELETE - JpaStandard
    @Override
    public void deleteDoctor(Long id) throws DoctorDeletingFailedException {
        EntityManager entityManager = JpaStandardUtils.getEntityManager();
        entityManager.getTransaction().begin();

        Doctor doctorToRemove = entityManager.find(Doctor.class, id);

        if (doctorToRemove == null) {
            entityManager.close();
            log.error("Doctor with id: " + id + " doesn't exists in database.");
            throw new DoctorDeletingFailedException("Doctor with id: " + id + " doesn't exists in database.");
        }
        entityManager.remove(doctorToRemove);
        entityManager.getTransaction().commit();
        entityManager.close();
        log.info("delete(...) = " + doctorToRemove);
    }

}
