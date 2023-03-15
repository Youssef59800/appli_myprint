package yr.fr.appli_myprint.securityConfig;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;
import yr.fr.appli_myprint.dto.AuthenticationRequest;
import yr.fr.appli_myprint.model.PersonneEntity;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JwtService {

    private final JwtEncoder jwtEncoder;
//    private final AuthenticationManager authenticationManager;
    private final AuthenticationProvider authenticationProvider;

    public JwtService(JwtEncoder jwtEncoder, AuthenticationProvider authenticationProvider) {
        this.jwtEncoder = jwtEncoder;
        this.authenticationProvider = authenticationProvider;
//        this.authenticationManager = authenticationManager;
    }

    public String jwtToken(String email, String password) {

        Authentication authentication;

//            authentication = authenticationManager.authenticate(
        authentication = authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            String subject = authentication.getName();
            String scope = authentication.getAuthorities()
                    .stream().map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(" "));

            String idToken;
            String jwtAccessToken = generateAccessToken(subject, scope);
            idToken = (jwtAccessToken);

        return idToken;
    }

    private String generateAccessToken(String subject, String scope) {
        Instant instant = Instant.now();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject(subject)
                .issuedAt(instant)
                .expiresAt(instant.plus(10, ChronoUnit.MINUTES))
                .issuer("appli_myprint")
                .claim("scope", scope)
                .build();

        return  jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
    }

}
