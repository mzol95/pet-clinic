package pl.zoltowskimarcin.petclinic.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import pl.zoltowskimarcin.petclinic.exception.Doctor.*;
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

@Service
@Slf4j
public class DoctorService {
    private DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    //CREATE - Native Hibernate
    public DoctorDto saveDoctor(DoctorDto doctorDto) throws DoctorException {
        log.info("save " + doctorDto + ")");
        Doctor doctorToPersist = DoctorMapper.getMapper().map(doctorDto, Doctor.class);

        try (Session session = NativeHibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(doctorToPersist);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error while saving doctor", e);
            throw new DoctorSavingFailedException("Error while saving doctor");
        }
        log.info("save(...) = " + doctorToPersist);
        return DoctorMapper.getMapper().map(doctorToPersist, DoctorDto.class);
    }

    //READ - JDBC
    public Optional<DoctorDto> getDoctorById(Long id) throws DoctorReadingFailedException {
        log.info("getDoctorById with id: " + id);

        try (Connection connection = DataSource.getConnection();
             PreparedStatement readStatement = connection.prepareStatement(JdbcQueries.FIND_DOCTORS_BY_ID)) {

            readStatement.setLong(1, id);
            try (ResultSet resultSet = readStatement.executeQuery()) {
                if (resultSet.next()) {
                    DoctorDto returnedDoctor = new DoctorDto();
                    returnedDoctor.setName(resultSet.getString("name"));
                    returnedDoctor.setSurname(resultSet.getString("surname"));
                    //todo - method implementation

                    log.info("get(...) = " + returnedDoctor);
                    return Optional.ofNullable(returnedDoctor);
                }
            }
        } catch (SQLException e) {
            log.error("Error while getting client", e);
            throw new DoctorReadingFailedException("Error while getting client");
        }
        return Optional.empty();
    }

    //UPDATE - Spring Data JPA
    @Transactional
    public DoctorDto updateDoctor(Long id, DoctorDto doctorDto) throws DoctorUpdatingFailedException {
        log.info("update " + doctorDto + " with id: " + id);

        Doctor doctorToUpdate = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorUpdatingFailedException("Doctor with id: " + id + " doesn't exists in database."));

        //todo - method implementation
        doctorRepository.save(doctorToUpdate);

        log.info("update(...) = " + doctorToUpdate);
        return DoctorMapper.getMapper().map(doctorToUpdate, DoctorDto.class);
    }

    //DELETE - JpaStandard
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
