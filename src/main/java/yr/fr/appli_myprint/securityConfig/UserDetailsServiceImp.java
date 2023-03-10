package yr.fr.appli_myprint.securityConfig;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import yr.fr.appli_myprint.dao.UserRepository;
import yr.fr.appli_myprint.model.PersonneEntity;

@Component
public class UserDetailsServiceImp implements UserDetailsService {


    private UserRepository repository;

    public UserDetailsServiceImp(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        PersonneEntity user = repository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return user;
    }

}
