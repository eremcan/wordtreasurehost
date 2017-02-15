package com.word.controller;

import com.word.domain.User;
import com.word.service.IUserService;
import com.word.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Nahide on 09.02.2017.
 */

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

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
}
