package yr.fr.appli_myprint.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import yr.fr.appli_myprint.model.RoleEntity;
import yr.fr.appli_myprint.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Rôle")
public class RoleController {

    private final AccountService accountService;

    @GetMapping("/allRoles")
    public ResponseEntity<List<RoleEntity>> getAllRoles() {
        return ResponseEntity.ok().body(accountService.listRoles());
    }

    @PostMapping("/addNewRole")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<RoleEntity> addNewRole(@RequestBody RoleEntity role) {
        return ResponseEntity.ok().body(accountService.addNewRole(role));
    }

    @GetMapping("/findRoleById")
    public ResponseEntity<RoleEntity> getRoleById(@PathParam("id") Integer id) {
        RoleEntity role = accountService.findRoleByIdRole(id);
        if (role != null)
            return ResponseEntity.ok().body(role);
        else
            return ResponseEntity.noContent().build();
    }

    @GetMapping("/findRoleByRoleName")
    public ResponseEntity<RoleEntity> getRoleByRoleName(@PathParam("roleName") String roleName) {
        RoleEntity role = accountService.findRoleByRoleName(roleName);
        if (role != null)
            return ResponseEntity.ok().body(role);
        else
            return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteRoleById")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public void deleteById(@RequestParam(value = "id") Integer id) {
        accountService.deleteRoleById(id);
    }

    @DeleteMapping("/deleteRoleByRoleName")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public void deleteByName(@RequestParam(value = "roleName") String roleName) {
        accountService.deleteRoleByRoleName(roleName);
    }
}
