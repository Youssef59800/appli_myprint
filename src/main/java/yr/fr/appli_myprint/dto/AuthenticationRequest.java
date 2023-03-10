package yr.fr.appli_myprint.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email;
    private String password;
}
