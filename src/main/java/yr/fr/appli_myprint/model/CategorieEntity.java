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
@Table(name = "categorie", schema = "public", catalog = "MYPRINT")
public class CategorieEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_categorie")
    private int idCategorie;

    @Column(name = "nom")
    private String nom;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "comporte",
            joinColumns = @JoinColumn(name = "id_categorie"),
            inverseJoinColumns = @JoinColumn(name = "id_option"))
    private Collection<OptionEntity> collectionOfOption = new ArrayList<>();
}
