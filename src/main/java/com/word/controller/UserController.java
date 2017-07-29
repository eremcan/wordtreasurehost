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
        }
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        if ((newUser.getUsername().contains(" ") || newUser.getUsername().equals(""))) {
            token.setToken("space problem");
            return new ResponseEntity<Object>(token, HttpStatus.OK);
        }
        newUser.setFirstname(user.getFirstname());
        newUser.setEmail(user.getEmail());
        newUser.setSurname(user.getSurname());
        newUser.setPassword(user.getPassword());
        if (!(newUser.getEmail().contains("@") && newUser.getEmail().contains("."))) {
            token.setToken("set proper mail address");
            return new ResponseEntity<Object>(token, HttpStatus.OK);
        }

        userService.saveUser(user);
        securityService.autologin(user.getUsername(), user.getPassword());

        TokenAuthenticationService tokenAuthenticationService = new TokenAuthenticationService();
        String a = tokenAuthenticationService.addAuthentication(response, user.getUsername());
        token.setToken(a);
        return new ResponseEntity<Object>(token, HttpStatus.OK);

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

    @RequestMapping(value = "/connectara", method = RequestMethod.POST)
    public ResponseEntity<?> loginUser(@RequestBody Token token, HttpServletResponse response) {

        AccessGrant accessGrant = new AccessGrant(token.getToken());
        Connection<Facebook> connection = facebookConnectionFactory.createConnection(accessGrant);
        Facebook facebook = connection.getApi();
        String[] fields = {"id", "email", "first_name", "last_name"};
        org.springframework.social.facebook.api.User userProfile = facebook.fetchObject("me", org.springframework.social.facebook.api.User.class, fields);
        User user = new User();
        if (userProfile.getEmail() == null) {
            user.setEmail(userProfile.getId() + "@facebook.com");
        } else {
            user.setEmail(userProfile.getEmail());
        }
        user.setSurname(userProfile.getLastName());
        user.setPassword(userProfile.getId() + "bbb");
        user.setFirstname(userProfile.getFirstName());
        user.setFacebookid(userProfile.getId());
        user.setUsername(userProfile.getFirstName() + userProfile.getLastName());

        if (!userService.checkExistFbId(userProfile.getId())) {
            userService.saveUser(user);
        }
        securityService.autologin(user.getUsername(), user.getPassword());
        TokenAuthenticationService tokenAuthenticationService = new TokenAuthenticationService();
        String a = tokenAuthenticationService.addAuthentication(response, user.getUsername());

        if (a.contains("Bearer "))
            token.setToken(a);
        else
            token.setToken("loginError");

        return new ResponseEntity<Object>(token, HttpStatus.OK);
    }

}
