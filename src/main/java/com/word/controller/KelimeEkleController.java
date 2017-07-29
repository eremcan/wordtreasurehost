package com.word.controller;

import com.word.domain.KelimeEkle;
import com.word.security.jwt.SecurityService;
import com.word.service.IKelimeEkleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

/**
 * Created by Aytan on 27.05.2017.
 */

@RestController
@Transactional
public class KelimeEkleController {
    @Autowired
    SecurityService securityService;
    @Autowired
    IKelimeEkleService kelimeEkleService;

    @RequestMapping(value = "/bildiginkelimeekle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void kelimeEkle(@RequestBody KelimeEkle kelimeEkle) {
        KelimeEkle yeniKelimeEkle = new KelimeEkle();
        yeniKelimeEkle.setEklenenKelimeKey(kelimeEkle.getEklenenKelimeKey());
        yeniKelimeEkle.setEklenenKelimeValue(kelimeEkle.getEklenenKelimeValue());
        Long id = securityService.activeUser().getId();
        yeniKelimeEkle.setKisiId(id);
        kelimeEkleService.bildigiKelimeEkle(yeniKelimeEkle);
    }

    @RequestMapping(value = "/bildiginkelimeegoster", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> kelimeEkleGoster() {
        KelimeEkle kelimeEkle = new KelimeEkle();
        kelimeEkle.setKisiId(securityService.activeUser().getId());

        return new ResponseEntity<Object>(kelimeEkleService.findAllEklenenKelime(kelimeEkle), HttpStatus.OK);
    }

}
