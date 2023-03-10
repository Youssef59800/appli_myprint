package yr.fr.appli_myprint.service;

import yr.fr.appli_myprint.dto.CustomerDTO;
import yr.fr.appli_myprint.model.PersonneEntity;
import yr.fr.appli_myprint.model.RoleEntity;

import java.util.List;

public interface AccountService {
    //User
    PersonneEntity addNewUser (PersonneEntity user);

    PersonneEntity findUserById (Integer id);

    PersonneEntity findUserByEmail (String email);

    void deleteUserByEmail (String email);

    void deleteUserById (Integer id);

    PersonneEntity updateUser (PersonneEntity user);

    void addRoleToUser (Integer id, String roleName);

    void removeRoleToUser (Integer id, String roleName);

    PersonneEntity loadUserByEmail (String email);

    List<PersonneEntity> listUsers();
    List<CustomerDTO> listUsersDTO();

    //Role
    RoleEntity addNewRole (RoleEntity roleEntity);

    List<RoleEntity> listRoles();

    RoleEntity findRoleByIdRole (Integer idRole);
    RoleEntity findRoleByRoleName (String roleName);

    void deleteRoleByRoleName (String roleName);

    void deleteRoleById (Integer id);
}
