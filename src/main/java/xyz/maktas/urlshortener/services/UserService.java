package xyz.maktas.urlshortener.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import xyz.maktas.urlshortener.Constants.Constants;
import xyz.maktas.urlshortener.entities.Role;
import xyz.maktas.urlshortener.entities.URL;
import xyz.maktas.urlshortener.entities.User;
import xyz.maktas.urlshortener.repositories.UserRepo;

import java.sql.Timestamp;
import java.util.*;

/**
 * @Project urlshortener
 * @Author Muammer Matthew Aktas
 * @Date 7/4/2019
 */
@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @PreAuthorize("hasAnyRole('USER')")
    public List<User> getAllUsers() {
        return this.userRepo.findAll();
    }

    public User addUser(User user){
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        user.setEnabled(1);
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("USER"));
        user.setRoles(roles);
        String encryptedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(encryptedPassword);
        Optional<User> t = this.userRepo.findByEmail(user.getEmail());
        if(t.isPresent()){
            throw new RuntimeException("User exists in the system.");
        }

        return this.userRepo.save(user);



    }


    public User findUserByUsername(String username) {
        return this.userRepo.findByUsername(username).get();
    }
}
