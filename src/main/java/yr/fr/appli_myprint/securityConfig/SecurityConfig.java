package yr.fr.appli_myprint.securityConfig;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] SECURED_URLs_ADMIN = {"/users/**"};
    private static final String[] SECURED_URLs_USER = {"/users/**"};
    private static final String[] SECURED_URLs_MANAGER = {"/users/**"};

    private static final String[] UN_SECURED_URLs = {
            "/authenticate",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"
    };
    private RsaKeysConfig rsaKeysConfig;
    private UserDetailsService userDetailsService;

    public SecurityConfig(RsaKeysConfig rsaKeysConfig, UserDetailsService userDetailsService) {
        this.rsaKeysConfig = rsaKeysConfig;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public AuthenticationManager authenticationManager (UserDetailsService userDetailsService) {
//        var authProvider = new DaoAuthenticationProvider();
//        authProvider.setPasswordEncoder(passwordEncoder());
//        authProvider.setUserDetailsService(userDetailsService);
//        return new ProviderManager(authProvider);
//    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(UN_SECURED_URLs).permitAll()
                .requestMatchers(SECURED_URLs_ADMIN)
                .hasAnyAuthority("SCOPE_ADMIN","SCOPE_MANAGER")
                .requestMatchers(SECURED_URLs_MANAGER)
                .hasAnyAuthority("SCOPE_MANAGER")
                .requestMatchers(SECURED_URLs_USER)
                .hasAnyAuthority("SCOPE_USER","SCOPE_MANAGER","SCOPE_ADMIN")
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .authenticationProvider(authenticationProvider())
                .build();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(rsaKeysConfig.publicKey()).build();
    }

    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(rsaKeysConfig.publicKey()).privateKey(rsaKeysConfig.privateKey()).build();
        JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwkSource);
    }

}
