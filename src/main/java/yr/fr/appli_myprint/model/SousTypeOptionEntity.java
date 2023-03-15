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
@Table(name = "sous_type_option", schema = "public", catalog = "MYPRINT")
public class SousTypeOptionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_sous_type_option")
    private int idSousTypeOption;

    @Column(name = "libelle")
    private String libelle;

    @OneToMany
    private Collection<TypeOptionEntity> listTypeOptions = new ArrayList<>();
}
