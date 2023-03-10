package yr.fr.appli_myprint.securityConfig;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class JwtAuthentication {

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
//    private final AuthenticationManager authenticationManager;
    private final AuthenticationProvider authenticationProvider;
    private final UserDetailsService userDetailsService;

    public JwtAuthentication(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder, AuthenticationProvider authenticationProvider, UserDetailsService userDetailsService) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
        this.authenticationProvider = authenticationProvider;
//        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }
    @PostMapping("/authenticate")
    public Map<String, String> authenticate(
                                        String email,
                                        String password,
                                        boolean withRefreshToken,
                                        String grantType,
                                        String refreshToken) {
        String subject = null;
        String scope = null;
        Authentication authentication;

        if (grantType.equals(("password"))) {

//            authentication = authenticationManager.authenticate(
            authentication = authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            subject = authentication.getName();
            scope = authentication.getAuthorities()
                    .stream().map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(" "));

        } else if (grantType.equals("refreshToken")) {
            Jwt decodeJWT = jwtDecoder.decode(refreshToken);
            subject = decodeJWT.getSubject();
            UserDetails userDetails = userDetailsService.loadUserByUsername(subject);
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            scope = authorities.stream().map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(" "));
        }

        Map<String, String> idToken = new HashMap<>();
        String jwtAccessToken = generateAccessToken(subject, scope);
        idToken.put("accessToken", jwtAccessToken);

        if(withRefreshToken) {
            String jwtRefreshToken = generateRefreshToken(subject, scope);
            idToken.put("refreshToken", jwtRefreshToken);
        }

        return idToken;
    }

    private String generateAccessToken(String subject, String scope) {
        Instant instant = Instant.now();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject(subject)
                .issuedAt(instant)
                .expiresAt(instant.plus(5, ChronoUnit.MINUTES))
                .issuer("appli_myprint")
                .claim("scope", scope)
                .build();

        return  jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
    }

    private String generateRefreshToken(String subject, String scope) {
        Instant instant = Instant.now();
        JwtClaimsSet jwtClaimsSetRefresh = JwtClaimsSet.builder()
                .subject(subject)
                .issuedAt(instant)
                .expiresAt(instant.plus(30, ChronoUnit.MINUTES))
                .issuer("appli_myprint")
                .claim("scope", scope)
                .build();

        return  jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSetRefresh)).getTokenValue();
    }
}
