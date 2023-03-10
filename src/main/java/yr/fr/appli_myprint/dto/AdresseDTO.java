package yr.fr.appli_myprint.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdresseDTO {
        private String rue;
        private String complement;
        private String codePostal;
        private String ville;
        private Integer idTypeAdresse;
}
