package com.word.controller;

import com.word.domain.BilinenKelime;
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


    @RequestMapping(value = "/bilinenkelime/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBildigimKelimSorguById(@PathVariable Long id){
        return new ResponseEntity<Object>(
                iBildigimKelimeService.findBildigimKelime(id), HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/bilinenkelimeekle",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addBilinenKelime(@RequestBody BilinenKelime bilinenKelime) {
        BilinenKelime mybiBilinenKelime1 = new BilinenKelime();
        mybiBilinenKelime1.setKelime(bilinenKelime.getKelime());
        mybiBilinenKelime1.setUser(bilinenKelime.getUser());
        iBildigimKelimeService.addkelime(mybiBilinenKelime1);
    }

    @RequestMapping(value = "/bilinenkelimegetir",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getallBilinenKelime(){
        return new ResponseEntity<Object>(
                iBildigimKelimeService.findBilinenAllKelime(), HttpStatus.OK);
    }
}
