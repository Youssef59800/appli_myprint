package yr.fr.appli_myprint.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yr.fr.appli_myprint.dto.AuthenticationRequest;
import yr.fr.appli_myprint.dto.AuthenticationResponse;
import yr.fr.appli_myprint.dto.CustomerDTO;
import yr.fr.appli_myprint.mapper.CustomerDTOMapper;
import yr.fr.appli_myprint.model.PersonneEntity;
import yr.fr.appli_myprint.securityConfig.JwtService;
import yr.fr.appli_myprint.service.AccountService;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "authentication")
public class AuthenticationController {

    private final JwtService jwtService;
    private final AccountService accountService;

    private final CustomerDTOMapper customerDTOMapper;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody CustomerDTO user) {
        PersonneEntity newUser = customerDTOMapper.toCustomer(user);
        accountService.addNewUser(newUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/authenticate")
    public String authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return jwtService.jwtToken(request.getEmail(), request.getPassword());
    }

}
