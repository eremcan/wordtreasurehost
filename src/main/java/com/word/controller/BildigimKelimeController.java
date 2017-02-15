package com.word.controller;

import com.word.service.IBildigimKelimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Nahide on 10.02.2017.
 */
@RestController
public class BildigimKelimeController {

    @Autowired
    IBildigimKelimeService iBildigimKelimeService;


    @RequestMapping(value = "/bilinenkelime/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBildigimKelimeById(@PathVariable Long id){
        return new ResponseEntity<Object>(
                iBildigimKelimeService.findBildigimKelime(id), HttpStatus.OK);
    }
}
