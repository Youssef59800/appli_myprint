package yr.fr.appli_myprint.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yr.fr.appli_myprint.dao.OptionRepository;
import yr.fr.appli_myprint.model.OptionEntity;
import yr.fr.appli_myprint.service.OptionService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OptionServiceImp implements OptionService {

    private final OptionRepository optionRepository;
    @Override
    public Integer save(OptionEntity option) {
        return optionRepository.save(option).getIdOption();
    }

    @Override
    public List<OptionEntity> findAll() {
        return optionRepository.findAll();
    }

    @Override
    public OptionEntity findById(Integer id) {
        return optionRepository.findByIdOption(id);
    }

    @Override
    public void delete(Integer id) {
        optionRepository.deleteById(id);
    }
}
