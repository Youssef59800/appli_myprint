package yr.fr.appli_myprint.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yr.fr.appli_myprint.dao.TypeOptionRepository;
import yr.fr.appli_myprint.model.TypeOptionEntity;
import yr.fr.appli_myprint.service.TypeOptionService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeOptionServiceImp implements TypeOptionService {

    private final TypeOptionRepository typeOptionRepository;

    @Override
    public Integer save(TypeOptionEntity typeOption) {
        return typeOptionRepository.save(typeOption).getIdTypeOption();
    }

    @Override
    public List<TypeOptionEntity> findAll() {
        return typeOptionRepository.findAll();
    }

    @Override
    public TypeOptionEntity findById(Integer id) {
        return typeOptionRepository.findByIdTypeOption(id);
    }

    @Override
    public void delete(Integer id) {
        typeOptionRepository.deleteById(id);
    }
}
