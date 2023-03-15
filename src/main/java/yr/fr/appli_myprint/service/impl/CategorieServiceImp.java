package yr.fr.appli_myprint.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yr.fr.appli_myprint.dao.CategorieRepository;
import yr.fr.appli_myprint.model.CategorieEntity;
import yr.fr.appli_myprint.service.CategorieService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategorieServiceImp implements CategorieService {

    private final CategorieRepository categorieRepository;
    @Override
    public Integer save(CategorieEntity categorie) {
        return categorieRepository.save(categorie).getIdCategorie();
    }

    @Override
    public List<CategorieEntity> findAll() {
        return categorieRepository.findAll();
    }

    @Override
    public CategorieEntity findById(Integer id) {
        return categorieRepository.findByIdCategorie(id);
    }

    @Override
    public void delete(Integer id) {
        categorieRepository.deleteById(id);
    }
}
