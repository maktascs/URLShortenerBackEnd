package xyz.maktas.urlshortener.Controllers;


import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xyz.maktas.urlshortener.entities.User;
import xyz.maktas.urlshortener.services.UserService;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project urlshortener
 * @Author Muammer Matthew Aktas
 * @Date 7/4/2019
 */
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserService userService;
    @CrossOrigin
    @GetMapping("/all")
    public List<User> getAllUsers(){
        return this.userService.getAllUsers();
    }


    @CrossOrigin
    @PostMapping("/add")
    public User addUser(@RequestBody  User user){
        return this.userService.addUser(user);

    }
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/login",produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> authenticateUser(@RequestHeader("Authorization") String auth){
        String base64Credentials = auth.substring("Basic".length()).trim();
        byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(credDecoded, StandardCharsets.UTF_8);
        final String[] values = credentials.split(":", 2);
        User user = userService.findUserByUsername(values[0]);
        Map<String, String> map= new HashMap<>();
        map.put("id",String.valueOf(user.getUserid()));
        map.put("firstname",user.getFirstname());
        map.put("lastname",user.getLastname());

       return  map;
    }
}
