package yr.fr.appli_myprint.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "personne", schema = "public", catalog = "MYPRINT")
public class PersonneEntity implements UserDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_personne")
    private int idPersonne;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;
    @Column(name = "password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "identifier",
            joinColumns = @JoinColumn(name = "id_personne"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Collection<RoleEntity> roles = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "posseder",
            joinColumns = @JoinColumn(name = "id_adresse"),
            inverseJoinColumns = @JoinColumn(name = "id_personne"))
    private Collection<AdresseEntity> adresseList = new ArrayList<>();

    public PersonneEntity(int idPersonne, String firstName, String lastName, String email, Collection<RoleEntity> roles, Collection<AdresseEntity> adresseList) {
        this.idPersonne = idPersonne;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
        this.adresseList = adresseList;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

            List<GrantedAuthority> authorities
                    = new ArrayList<>();
            for (RoleEntity role: roles) {
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }

            return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
