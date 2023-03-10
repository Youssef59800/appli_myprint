package yr.fr.appli_myprint.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import yr.fr.appli_myprint.dto.CustomerDTO;
import yr.fr.appli_myprint.model.PersonneEntity;
import yr.fr.appli_myprint.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User")
public class UserController {

    private final AccountService accountService;

    @GetMapping("/usersDTO")
    public List<CustomerDTO> getUsersDTO() {
        return accountService.listUsersDTO();
    }

    @GetMapping("/users")
    public List<PersonneEntity> getUsers() {
        return accountService.listUsers();
    }

    @GetMapping("/findUserById")
    public ResponseEntity<PersonneEntity> getUserById(@PathParam("id") Integer id) {
        PersonneEntity user = accountService.findUserById(id);
        if (user != null)
            return ResponseEntity.ok().body(user);
        else
            return ResponseEntity.noContent().build();
    }

    @GetMapping("/findUserByEmail")
    public ResponseEntity<PersonneEntity> getUserByEmail(@PathParam("email") String email) {
        PersonneEntity user = accountService.findUserByEmail(email);
        if (user != null)
            return ResponseEntity.ok().body(user);
        else
            return ResponseEntity.noContent().build();
    }

    @PostMapping("/addNewUser")
    public ResponseEntity<PersonneEntity> addNewUser(@RequestBody PersonneEntity user) {
        return ResponseEntity.ok().body(accountService.addNewUser(user));
    }

    @PostMapping("/addRoleToUser")
    public void addRoleToUser(@RequestParam(value = "id") Integer id,@RequestParam(value = "roleName") String roleName) {
        accountService.addRoleToUser(id, roleName);
    }

    @PostMapping("/removeRoleToUser")
    public void removeRoleToUser(@RequestParam(value = "id") Integer id,@RequestParam(value = "roleName") String roleName) {
        accountService.removeRoleToUser(id, roleName);
    }

    @DeleteMapping("/deleteUserByEmail")
    public void deleteByName(@PathParam(value = "email") String email) {
        accountService.deleteUserByEmail(email);
    }

    @DeleteMapping("/deleteUserById")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public void deleteById(@RequestParam(value = "id") Integer id) {
        accountService.deleteUserById(id);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<PersonneEntity> updateUser(
            @PathVariable(value = "id") Integer userId,
            @Valid @RequestBody PersonneEntity userDetails) throws ResourceNotFoundException {
        PersonneEntity user = accountService.findUserById(userId);

        if(user == null) {
            throw new ResourceNotFoundException("User not found on :: "+ userId);
        } else {
            user.setFirstName(userDetails.getFirstName());
            user.setLastName(userDetails.getLastName());
            user.setEmail(userDetails.getEmail());
        }
        final PersonneEntity updatedUser = accountService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

//    @PostMapping("/register")
//    public Status registerUser(@Valid @RequestBody PersonneEntity newUser) {
//        List<PersonneEntity> users = accountService.listUsers();
//        for (PersonneEntity user : users) {
//            if ((user.getEmail()).equals(newUser.getEmail())) {
//                return Status.USER_ALREADY_EXISTS;
//            } else if ((newUser.getEmail()) == null || (newUser.getPassword()) == null) {
//                return Status.FAILURE;
//            }
//        }
//        accountService.addNewUser(newUser);
//        return Status.SUCCESS;
//    }
}

