package com.word.controller;

import com.word.service.impl.UserScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Nahide on 29.03.2017.
 */
@RestController
public class UserScoreController {
    @Autowired
    UserScoreService userScoreService;

    @RequestMapping(value = "/getscoreboard", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserScores() {
        return new ResponseEntity<Object>(
                userScoreService.getListofUser(), HttpStatus.OK);
    }
}
