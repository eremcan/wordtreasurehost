package com.word.controller;

import com.word.domain.User;
import com.word.security.jwt.JwtUserDetailsServiceImpl;
import com.word.security.jwt.SecurityService;
import com.word.security.jwt.TokenAuthenticationService;
import com.word.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Nahide on 09.02.2017.
 */

@RestController
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    JwtUserDetailsServiceImpl jwtUserDetailsService;
    @Autowired
    SecurityService securityService;


    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {

        try {

            User mUser = userService.getUserById(userId);

            if (mUser != null) {
                return new ResponseEntity<>(mUser, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Kullanıcı Bulunamadı", HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("Kullanıcı Bulunamadıcatch", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/getalluser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUser() {
        return new ResponseEntity<Object>(
                userService.findAllUser(), HttpStatus.OK);
    }

    @RequestMapping(value = "/createuser", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user, HttpServletResponse response) {

        if (userService.checkExistUserName(user.getUsername())) {

            return new ResponseEntity<Object>("bu kullanici adi ile bir kullanici bulunmaktadir. Lutfen baska bir kullanici adi ile deneyiniz", HttpStatus.NOT_IMPLEMENTED);
        }
        else{
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setFirstname(user.getFirstname());
        newUser.setEmail(user.getEmail());
        newUser.setSurname(user.getSurname());
        newUser.setPassword(user.getPassword());
        userService.saveUser(user);
        securityService.autologin(user.getUsername(), user.getPassword());

        TokenAuthenticationService tokenAuthenticationService = new TokenAuthenticationService();
        tokenAuthenticationService.addAuthentication(response, user.getUsername());

        return new ResponseEntity<Object>(user.getId().toString(), HttpStatus.OK);
    }}

    @RequestMapping(value = "/getadmin", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getadminUser() {
        return new ResponseEntity<Object>(
                userService.getUserbyUsername("admin"), HttpStatus.OK);

    }
}
