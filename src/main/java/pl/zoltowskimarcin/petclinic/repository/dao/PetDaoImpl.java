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
import pl.zoltowskimarcin.petclinic.mapper.PetMapper;
import pl.zoltowskimarcin.petclinic.repository.JpaStandardUtils;
import pl.zoltowskimarcin.petclinic.repository.NativeHibernateUtils;
import pl.zoltowskimarcin.petclinic.repository.entity.Pet;
import pl.zoltowskimarcin.petclinic.repository.jpa.PetRepository;
import pl.zoltowskimarcin.petclinic.web.enums.Gender;
import pl.zoltowskimarcin.petclinic.web.model.PetDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
@Slf4j
public class PetDaoImpl implements PetDao {

    private final PetRepository petRepository;

    public PetDaoImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    //CREATE - Native Hibernate
    @Override
    public PetDto savePet(PetDto petDto) throws EntitySavingFailedException {
        log.info("save " + petDto + ")");
        Pet petToPersist = PetMapper.getMapper().map(petDto, Pet.class);

        try (Session session = NativeHibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(petToPersist);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error while saving pet", e);
            throw new EntitySavingFailedException("Error while saving pet");
        }
        log.info("save(...) = " + petToPersist);
        return PetMapper.getMapper().map(petToPersist, PetDto.class);
    }

    //READ - JDBC
    @Override
    public Optional<PetDto> getPetById(Long id) throws EntityReadingFailedException {
        log.info("getPetById with id: " + id);

        try (Connection connection = DataSource.getConnection();
             PreparedStatement readStatement = connection.prepareStatement(JdbcQueries.FIND_PETS_BY_ID)) {

            readStatement.setLong(1, id);
            try (ResultSet resultSet = readStatement.executeQuery()) {
                if (resultSet.next()) {
                    PetDto returnedPet = new PetDto.Builder()
                            .id(resultSet.getLong("id"))
                            .name(resultSet.getString("name"))
                            .dateOfBirth(resultSet.getDate("date_of_birth").toLocalDate())
                            .gender(Gender.valueOfLabel(resultSet.getInt("gender")))
                            .build();
                    log.info("get(...) = " + returnedPet);
                    return Optional.ofNullable(returnedPet);
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
    public PetDto updatePet(Long id, PetDto petDto) throws EntityUpdatingFailedException {
        log.info("update " + petDto + " with id: " + id);

        Pet petToUpdate = petRepository.findById(id)
                .orElseThrow(() -> new EntityUpdatingFailedException("Pet with id: " + id + " doesn't exists in database."));

        Pet updatingPet = PetMapper.getMapper().map(petDto, Pet.class);

        petToUpdate.setName(updatingPet.getName());
        petToUpdate.setDateOfBirth(updatingPet.getDateOfBirth());
        petToUpdate.setAppointments(updatingPet.getAppointments());
        petToUpdate.setGender(updatingPet.getGender());
        petToUpdate.setClient(updatingPet.getClient());
        petRepository.save(petToUpdate);

        log.info("update(...) = " + petToUpdate);
        return PetMapper.getMapper().map(petToUpdate, PetDto.class);
    }

    //DELETE - JpaStandard
    @Override
    public void deletePet(Long id) throws EntityDeletingFailedException {
        EntityManager entityManager = JpaStandardUtils.getEntityManager();
        entityManager.getTransaction().begin();

        Pet petToRemove = entityManager.find(Pet.class, id);

        if (petToRemove == null) {
            entityManager.close();
            log.error("Pet with id: " + id + " doesn't exists in database.");
            throw new EntityDeletingFailedException("Pet with id: " + id + " doesn't exists in database.");
        }
        entityManager.remove(petToRemove);
        entityManager.getTransaction().commit();
        entityManager.close();
        log.info("delete(...) = " + petToRemove);
    }

}
