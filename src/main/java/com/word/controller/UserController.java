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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
            return new ResponseEntity<>("Kullanıcı Bulunamadı", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/getalluser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUser() {
        return new ResponseEntity<Object>(
                userService.findAllUser(), HttpStatus.OK);
    }

    @RequestMapping(value = "/createuser", method = RequestMethod.POST)
    public String createUser(@RequestBody User user, Model md, HttpServletRequest request, HttpServletResponse response) {

        if (userService.checkExistUserName(user.getUserName())) {
            md.addAttribute("LoginError", true);
            return "bu kullanici adi ile bir kullanici bulunmaktadir. Lutfen baska bir kullanici adi ile deneyiniz";
        }
        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setFirstname(user.getFirstname());
        newUser.setUserMail(user.getUserMail());
        newUser.setSurname(user.getSurname());
        newUser.setUserPassword(user.getUserPassword());
        userService.saveUser(user);
        // jwtUserDetailsService.signedUpwithUsername(user.getUserName(),user.getUserPassword());
        securityService.autologin(user.getUserName(), user.getUserPassword());

        TokenAuthenticationService tokenAuthenticationService = new TokenAuthenticationService();

        tokenAuthenticationService.addAuthentication(response, user.getUserName());
        return user.getId().toString();
    }

    @RequestMapping(value = "/getadmin", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getadminUser() {
        return new ResponseEntity<Object>(
                userService.getUserbyUsername("admin"), HttpStatus.OK);
        /**
         @RequestMapping(value = "/createuser",method = {RequestMethod.GET,RequestMethod.POST})
         public String createUser(@RequestParam String userorjName,
         @RequestParam String surname,
         @RequestParam String username ,
         @RequestParam String usermail,
         Model md){
         if(userService.checkExistUserName(username)){
         md.addAttribute("LoginError",true);
         return "bu kullanici adi ile bir kullanici bulunmaktadir. Lutfen baska bir kullanici adi ile deneyiniz";
         }
         User user = new User();
         user.setUserName(username);
         user.setFirstname(userorjName);
         user.setUserMail(usermail);
         user.setSurname(surname);
         userService.saveUser(user);
         return "saved";
         }
         */

    }
}
