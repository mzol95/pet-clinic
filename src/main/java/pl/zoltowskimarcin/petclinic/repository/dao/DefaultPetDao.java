package pl.zoltowskimarcin.petclinic.repository.dao;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import pl.zoltowskimarcin.petclinic.exception.pet.PetDeletingFailedException;
import pl.zoltowskimarcin.petclinic.exception.pet.PetReadingFailedException;
import pl.zoltowskimarcin.petclinic.exception.pet.PetSavingFailedException;
import pl.zoltowskimarcin.petclinic.exception.pet.PetUpdatingFailedException;
import pl.zoltowskimarcin.petclinic.jdbc.DataSource;
import pl.zoltowskimarcin.petclinic.jdbc.JdbcQueries;
import pl.zoltowskimarcin.petclinic.repository.JpaStandardUtils;
import pl.zoltowskimarcin.petclinic.repository.NativeHibernateUtils;
import pl.zoltowskimarcin.petclinic.repository.entity.Pet;
import pl.zoltowskimarcin.petclinic.repository.jpa.PetRepository;
import pl.zoltowskimarcin.petclinic.web.enums.Gender;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

@Repository
@Slf4j
public class DefaultPetDao implements PetDao {

    private final PetRepository petRepository;

    public DefaultPetDao(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    //CREATE - Native Hibernate
    @Override
    public Pet savePet(Pet pet) throws PetSavingFailedException {
        log.info("save " + pet + ")");
        Pet petToPersist = pet;

        try (Session session = NativeHibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(petToPersist);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error while saving pet", e);
            throw new PetSavingFailedException("Error while saving pet");
        }
        log.info("save(...) = " + petToPersist);
        return petToPersist;
    }

    //READ - JDBC
    @Override
    public Optional<Pet> getPetById(Long id) throws PetReadingFailedException {
        log.info("getPetById with id: " + id);
        Pet returnedPet = null;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement readStatement = connection.prepareStatement(JdbcQueries.FIND_PETS_BY_ID)) {
            readStatement.setLong(1, id);
            try (ResultSet resultSet = readStatement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    Gender gender = Gender.valueOfLabel(resultSet.getInt("gender"));
                    LocalDate dateOfBirth = resultSet.getDate("date_of_birth").toLocalDate();
                    returnedPet = new Pet(name, dateOfBirth, gender);
                }
            }
        } catch (SQLException e) {
            log.error("Error while getting client with id: " + id, e);
            throw new PetReadingFailedException("Error while getting client with id: " + id);
        }
        log.info("get(...) = " + returnedPet);
        return Optional.ofNullable(returnedPet);
    }

    //UPDATE - Spring Data JPA
    @Transactional
    @Override
    public Pet updatePet(Long id, Pet pet) throws PetUpdatingFailedException {
        log.info("update " + pet + " with id: " + id);

        Pet petToUpdate = petRepository.findById(id)
                .orElseThrow(() -> new PetUpdatingFailedException("Pet with id: " + id + " doesn't exists in database."));

        petToUpdate.setName(pet.getName());
        petToUpdate.setDateOfBirth(pet.getDateOfBirth());
        petToUpdate.setGender(pet.getGender());

        petRepository.save(petToUpdate);

        log.info("update(...) = " + petToUpdate);
        return petToUpdate;
    }

    //DELETE - JpaStandard
    @Override
    public void deletePet(Long id) throws PetDeletingFailedException {
        EntityManager entityManager = JpaStandardUtils.getEntityManager();
        entityManager.getTransaction().begin();

        Pet petToRemove = entityManager.find(Pet.class, id);

        if (petToRemove == null) {
            entityManager.close();
            log.error("Pet with id: " + id + " doesn't exists in database.");
            throw new PetDeletingFailedException("Pet with id: " + id + " doesn't exists in database.");
        }
        entityManager.remove(petToRemove);
        entityManager.getTransaction().commit();
        entityManager.close();
        log.info("delete(...) = " + petToRemove);
    }

}
