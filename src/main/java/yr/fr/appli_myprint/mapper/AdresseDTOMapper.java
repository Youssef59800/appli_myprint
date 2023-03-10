package yr.fr.appli_myprint.mapper;

import org.springframework.stereotype.Component;
import yr.fr.appli_myprint.dto.AdresseDTO;
import yr.fr.appli_myprint.model.AdresseEntity;
@Component
public class AdresseDTOMapper  {

    public AdresseDTO toDto(AdresseEntity adresse) {
        Integer idAdresse = adresse.getIdAdresse();
        String rue = adresse.getRue();
        String complement = adresse.getComplement();
        String codePostal = adresse.getCodePostal();
        String ville = adresse.getVille();
        Integer idTypeAdresse = adresse.getIdTypeAdresse();

        return new AdresseDTO(rue, complement, codePostal, ville, idTypeAdresse);
    }

    public AdresseEntity toAdresse(AdresseDTO adresseDTO) {
        return new AdresseEntity(adresseDTO.getRue(), adresseDTO.getComplement(),
                adresseDTO.getCodePostal(), adresseDTO.getVille(), adresseDTO.getIdTypeAdresse());
    }
}
