package yr.fr.appli_myprint.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "adresse", schema = "public", catalog = "MYPRINT")
public class AdresseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_adresse")
    private int idAdresse;

    @Column(name = "rue")
    private String rue;

    @Column(name = "complement")
    private String complement;

    @Column(name = "code_postal")
    private String codePostal;

    @Column(name = "ville")
    private String ville;

    @Column(name = "id_type_adresse")
    private int idTypeAdresse;

    public AdresseEntity(String rue, String complement, String codePostal, String ville, Integer idTypeAdresse) {
        this.rue = rue;
        this.complement = complement;
        this.codePostal = codePostal;
        this.ville = ville;
    }
}
