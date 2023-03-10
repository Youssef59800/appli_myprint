package yr.fr.appli_myprint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import yr.fr.appli_myprint.securityConfig.RsaKeysConfig;


@SpringBootApplication
@EnableConfigurationProperties(RsaKeysConfig.class)
public class AppliMyprintApplication {

    public static void main(String[] args) {

        SpringApplication.run(AppliMyprintApplication.class, args);
    }

}
