package yr.fr.appli_myprint.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yr.fr.appli_myprint.dao.AdresseRepository;
import yr.fr.appli_myprint.dao.RoleRepository;
import yr.fr.appli_myprint.dao.UserRepository;
import yr.fr.appli_myprint.dto.AdresseDTO;
import yr.fr.appli_myprint.dto.CustomerDTO;
import yr.fr.appli_myprint.exception.UserNotFoundException;
import yr.fr.appli_myprint.mapper.CustomerDTOMapper;
import yr.fr.appli_myprint.model.AdresseEntity;
import yr.fr.appli_myprint.model.PersonneEntity;
import yr.fr.appli_myprint.model.RoleEntity;
import yr.fr.appli_myprint.service.AccountService;
import java.util.List;

@Service
@Transactional
public class AccountServiceImp implements AccountService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AdresseRepository adresseRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomerDTOMapper customerDTOMapper;

    public AccountServiceImp(UserRepository userRepository, RoleRepository roleRepository, AdresseRepository adresseRepository, PasswordEncoder passwordEncoder, CustomerDTOMapper customerDTOMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.adresseRepository = adresseRepository;
        this.passwordEncoder = passwordEncoder;
        this.customerDTOMapper = customerDTOMapper;
    }
    //All Fonctions for User
    @Override
    public PersonneEntity addNewUser(PersonneEntity user) {
        String pw = user.getPassword();
        user.setPassword(passwordEncoder.encode(pw));
        RoleEntity role = new RoleEntity(1,"USER");
        user.getRoles().add(role);
        return userRepository.save(user);
    }
    @Override
    public PersonneEntity updateUser(PersonneEntity user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserByEmail(String email) {
        PersonneEntity user = userRepository.findByEmail(email);
        userRepository.delete(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        PersonneEntity user = userRepository.findByIdPersonne(id);
        userRepository.delete(user);
    }
    @Override
    public PersonneEntity findUserById(Integer id) {

        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id "+id+" was not found"));
    }

    @Override
    public PersonneEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    @Override
    public void addRoleToUser(Integer id, String roleName) {
        PersonneEntity user = userRepository.findByIdPersonne(id);
        RoleEntity role = roleRepository.findByRoleName(roleName);
        user.getRoles().add(role);
    }
    @Override
    public void removeRoleToUser(Integer id, String roleName) {
        PersonneEntity user = userRepository.findByIdPersonne(id);
        RoleEntity role = roleRepository.findByRoleName(roleName);
        user.getRoles().remove(role);
    }

    @Override
    public PersonneEntity loadUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<CustomerDTO> listUsersDTO() {

        return userRepository.findAll()
                .stream()
                .map(customerDTOMapper::toDto)
                .toList();
    }
    @Override
    public void addAdresseToUser(Integer idUser, Integer idAdresse) {
        PersonneEntity user = userRepository.findByIdPersonne(idUser);
        AdresseEntity adresse = adresseRepository.findByIdAdresse(idAdresse);
        user.getAdresseList().add(adresse);
    }
    @Override
    public void removeAdresseToUser(Integer idUser, Integer idAdresse) {
        PersonneEntity user = userRepository.findByIdPersonne(idUser);
        AdresseEntity adresse = adresseRepository.findByIdAdresse(idAdresse);
        user.getAdresseList().remove(adresse);
    }

    @Override
    public List<PersonneEntity> listUsers() {
        return userRepository.findAll();
    }

    //All Fonctions for Role

    @Override
    public List<RoleEntity> listRoles() {
        return roleRepository.findAll();
    }
    @Override
    public RoleEntity findRoleByIdRole(Integer idRole) {
        return roleRepository.findByIdRole(idRole);
    }
    @Override
    public void deleteRoleById(Integer id) {
        RoleEntity role = roleRepository.findByIdRole(id);
        roleRepository.delete(role);
    }
    @Override
    public void deleteRoleByRoleName(String roleName) {
        RoleEntity role = roleRepository.findByRoleName(roleName);
        roleRepository.delete(role);
    }
    @Override
    public RoleEntity findRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }
    @Override
    public RoleEntity addNewRole(RoleEntity roleName) {
        return roleRepository.save(roleName);
    }
}
