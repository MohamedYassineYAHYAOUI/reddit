package fr.uge.reddit.config;


import fr.uge.reddit.entity.UserEntity;
import fr.uge.reddit.entity.UserRoles;
import fr.uge.reddit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        UserEntity userWithSameLogin = userRepository.findByLogin(login);
        if(userWithSameLogin == null){
            throw new UsernameNotFoundException("invalid Login or Password");
        }
        var pwd = userWithSameLogin.getPassword();
        var user= User.withUsername(userWithSameLogin.getLogin())
                .password(pwd)
                .roles("USER");
        if(userWithSameLogin.getUserRole().equals(UserRoles.ADMIN)){
            user.roles("ADMIN");
        }
        return user.build();
    }

}
