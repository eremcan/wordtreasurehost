package com.word.controller;

import com.word.domain.Token;
import com.word.domain.User;
import com.word.security.jwt.JwtUserDetailsServiceImpl;
import com.word.security.jwt.SecurityService;
import com.word.security.jwt.TokenAuthenticationService;
import com.word.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Nahide on 09.02.2017.
 */
@RequestMapping("/")
@RestController
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    JwtUserDetailsServiceImpl jwtUserDetailsService;
    @Autowired
    SecurityService securityService;
    private Facebook facebook;
    private ConnectionRepository connectionRepository;


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

    @RequestMapping(value = "/createuser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> createUser(@RequestBody User user, HttpServletResponse response) {
        Token token = new Token();
        if (userService.checkExistEmail(user.getEmail())) {
            token.setToken("mail duplicating");
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        if (userService.checkExistUserName(user.getUsername())) {
            token.setToken("user duplicating");
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            User newUser = new User();
            newUser.setUsername(user.getUsername());
            newUser.setFirstname(user.getFirstname());
            newUser.setEmail(user.getEmail());
            newUser.setSurname(user.getSurname());
            newUser.setPassword(user.getPassword());
            userService.saveUser(user);
            securityService.autologin(user.getUsername(), user.getPassword());

            TokenAuthenticationService tokenAuthenticationService = new TokenAuthenticationService();
            String a = tokenAuthenticationService.addAuthentication(response, user.getUsername());
            token.setToken(a);
            return new ResponseEntity<Object>(token, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/loginuser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> loginUser(@RequestBody User user, HttpServletResponse response) {
        Token token = new Token();
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        securityService.autologin(user.getUsername(), user.getPassword());
        TokenAuthenticationService tokenAuthenticationService = new TokenAuthenticationService();
        String a = tokenAuthenticationService.addAuthentication(response, user.getUsername());
        if (a.contains("Bearer "))
            token.setToken(a);
        else
            token.setToken("loginError");

        return new ResponseEntity<Object>(token, HttpStatus.OK);
    }

    @RequestMapping(value = "/getadmin", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getadminUser() {
        return new ResponseEntity<Object>(
                userService.getUserbyUsername("admin"), HttpStatus.OK);

    }

    @Autowired
    private FacebookConnectionFactory facebookConnectionFactory;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @RequestMapping(value = "/connectara", method = RequestMethod.POST)
    public String fb(@RequestBody Token token) {
        AccessGrant accessGrant = new AccessGrant("token");
        Connection<Facebook> connection = facebookConnectionFactory.createConnection(accessGrant);
        Facebook facebook = connection.getApi();
        String[] fields = {"first_name", "last_name", "email"};
        User userProfile = facebook.fetchObject("me", User.class, fields);
        String name = userProfile.getFirstname();

        // ...

        return "Done";
    }

}
