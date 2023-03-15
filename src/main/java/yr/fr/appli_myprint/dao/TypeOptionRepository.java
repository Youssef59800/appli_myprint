package yr.fr.appli_myprint.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import yr.fr.appli_myprint.model.TypeOptionEntity;

public interface TypeOptionRepository extends JpaRepository<TypeOptionEntity, Integer> {
    TypeOptionEntity findByIdTypeOption(Integer idType);
}
