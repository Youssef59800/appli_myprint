package yr.fr.appli_myprint.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import yr.fr.appli_myprint.model.OptionEntity;

public interface OptionRepository extends JpaRepository<OptionEntity, Integer> {
    OptionEntity findByIdOption(Integer idOption);
}
