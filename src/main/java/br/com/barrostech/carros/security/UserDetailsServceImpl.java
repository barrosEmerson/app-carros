package br.com.barrostech.carros.security;

import br.com.barrostech.carros.domain.User;
import br.com.barrostech.carros.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value = "userDetailsService")
public class UserDetailsServceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = userRepository.findByLogin(username);

        if(user == null){
            throw new UsernameNotFoundException("user not found");
        }

        return user;


    }
}
