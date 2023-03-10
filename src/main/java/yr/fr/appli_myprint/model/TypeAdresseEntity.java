package yr.fr.appli_myprint.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "type_adresse", schema = "public", catalog = "MYPRINT")
public class TypeAdresseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_type_adresse")
    private int idTypeAdresse;

    @Column(name = "libelle")
    private String libelle;

    @OneToMany
    private Collection<AdresseEntity> allAdresses = new ArrayList<>();
}
