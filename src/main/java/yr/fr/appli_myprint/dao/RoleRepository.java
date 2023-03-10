package yr.fr.appli_myprint.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import yr.fr.appli_myprint.model.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    RoleEntity findByRoleName(String roleName);
    RoleEntity findByIdRole(Integer id);

}
