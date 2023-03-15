package yr.fr.appli_myprint.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import yr.fr.appli_myprint.model.CategorieEntity;

public interface CategorieRepository extends JpaRepository<CategorieEntity,Integer> {
    CategorieEntity findByIdCategorie(Integer idCategorie);
}

