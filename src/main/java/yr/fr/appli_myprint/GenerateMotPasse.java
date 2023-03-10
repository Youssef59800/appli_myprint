package yr.fr.appli_myprint;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenerateMotPasse {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("user");

        System.out.println(hashedPassword);
    }
}
