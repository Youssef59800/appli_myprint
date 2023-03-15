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
@Table(name = "option", schema = "public", catalog = "MYPRINT")
public class OptionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_option")
    private int idOption;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "prix")
    private double prix;

    @Column(name = "id_type_option")
    private int idTypeOption;

}
