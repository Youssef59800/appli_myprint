package yr.fr.appli_myprint.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import yr.fr.appli_myprint.model.AdresseEntity;

public interface AdresseRepository extends JpaRepository<AdresseEntity,Integer> {
    AdresseEntity findByVille (String ville);
    AdresseEntity findByIdAdresse (Integer idAdresse);
}
