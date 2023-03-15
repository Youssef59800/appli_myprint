package yr.fr.appli_myprint.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import yr.fr.appli_myprint.model.PersonneEntity;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<PersonneEntity, Integer>{
    PersonneEntity findByEmail(String email);
    PersonneEntity findByIdPersonne(Integer integer);
    @Override
    Optional<PersonneEntity> findById(Integer integer);
}
