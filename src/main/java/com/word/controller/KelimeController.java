package com.word.controller;

import com.word.domain.Kelime;
import com.word.service.IKelimeService;
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
public class KelimeController {

    @Autowired
    IKelimeService kelimeService;

    @RequestMapping(value = "/kelime/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getKelimeById(@PathVariable Long id) {
        Kelime mKelime = kelimeService.findKelime(id);
        if (mKelime != null)
            return new ResponseEntity<Object>(mKelime, HttpStatus.OK);
        else
            return new ResponseEntity<Object>("kelimeBulunamadi", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/getallkelimelerim", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllKelime() {
        return new ResponseEntity<Object>(
                kelimeService.findAllKelime(), HttpStatus.OK);
    }
    //public ResponseEntity<Kelime> getTwoFalseOptionKelime(Long id)

}
