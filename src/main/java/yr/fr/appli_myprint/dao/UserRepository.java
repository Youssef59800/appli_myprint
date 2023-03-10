package yr.fr.appli_myprint.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import yr.fr.appli_myprint.model.PersonneEntity;

public interface UserRepository  extends JpaRepository<PersonneEntity, Integer>{
    PersonneEntity findByEmail(String email);
    PersonneEntity findByIdPersonne(Integer id);
}
