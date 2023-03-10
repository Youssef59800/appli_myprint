package yr.fr.appli_myprint.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import yr.fr.appli_myprint.model.AdresseEntity;
import yr.fr.appli_myprint.model.RoleEntity;


import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

        private int idPersonne;
        private String firstName;
        private String lastName;
        private String email;
        private Collection<RoleEntity> roles;
        private Collection<AdresseEntity> adresseList;

}
