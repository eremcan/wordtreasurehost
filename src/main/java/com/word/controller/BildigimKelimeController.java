package com.word.controller;

import com.word.domain.BilinenKelime;
import com.word.domain.Kelime;
import com.word.domain.User;
import com.word.security.jwt.SecurityService;
import com.word.service.IBildigimKelimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Nahide on 10.02.2017.
 */
@RestController
public class BildigimKelimeController {

    @Autowired
    IBildigimKelimeService iBildigimKelimeService;
    @Autowired
    SecurityService securityService;


    @RequestMapping(value = "/bilinenkelime/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBildigimKelimSorguById(@PathVariable Long id) {
        return new ResponseEntity<Object>(

                iBildigimKelimeService.findBildigimKelime(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/bilinenkelimerandom", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBildigimKelimSorguByIdAndGetRandomly() {

        Long id = securityService.activeUser().getId();

        return new ResponseEntity<Object>(
                iBildigimKelimeService.findBildigimKelimeRandom(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/bilinenkelimeekle", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addBilinenKelime(@RequestBody Kelime bilinenKelime) {

        BilinenKelime mybiBilinenKelime1 = new BilinenKelime();
        mybiBilinenKelime1.setKelime(bilinenKelime);
        mybiBilinenKelime1.setUser(securityService.activeUser());
        iBildigimKelimeService.addKelime(mybiBilinenKelime1);
    }

    @RequestMapping(value = "/bilinenkelimegetir", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getallBilinenKelime() {
        return new ResponseEntity<Object>(
                iBildigimKelimeService.findBilinenAllKelime(), HttpStatus.OK);
    }
}
