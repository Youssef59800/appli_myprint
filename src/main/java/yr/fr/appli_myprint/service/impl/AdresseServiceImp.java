package yr.fr.appli_myprint.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yr.fr.appli_myprint.dao.AdresseRepository;
import yr.fr.appli_myprint.dto.AdresseDTO;
import yr.fr.appli_myprint.mapper.AdresseDTOMapper;
import yr.fr.appli_myprint.model.AdresseEntity;
import yr.fr.appli_myprint.service.AdresseService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdresseServiceImp implements AdresseService {

    private final AdresseRepository adresseRepository;
    private final AdresseDTOMapper adresseDTOMapper;

    @Override
    public Integer save(AdresseDTO dto) {
        AdresseEntity adresse = adresseDTOMapper.toAdresse(dto);
        return adresseRepository.save(adresse).getIdAdresse();
    }

    @Override
    public List<AdresseDTO> findAll() {
        return adresseRepository.findAll()
                .stream()
                .map(adresseDTOMapper::toDto)
                .toList();
    }

    @Override
    public AdresseDTO findById(Integer id) {
        return adresseRepository.findById(id)
                .map(adresseDTOMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("No address found with the ID : " + id));
    }

    @Override
    public void delete(Integer id) {
        adresseRepository.deleteById(id);
    }

}
