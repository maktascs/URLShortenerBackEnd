package xyz.maktas.urlshortener.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.maktas.urlshortener.entities.CustomUserDetails;
import xyz.maktas.urlshortener.entities.User;
import xyz.maktas.urlshortener.repositories.UserRepo;

import java.util.Optional;

/**
 * @Project urlshortener
 * @Author Muammer Matthew Aktas
 * @Date 7/4/2019
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUsers = userRepo.findByUsername(username);
        optionalUsers.orElseThrow(()->new UsernameNotFoundException("User not found."));
        return optionalUsers.map(CustomUserDetails::new).get();

    }
}
