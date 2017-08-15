package com.word.service;

import com.word.domain.BilinenKelime;
import com.word.domain.Kelime;
import com.word.domain.User;

import java.util.List;

/**
 * Created by Nahide on 10.02.2017.
 */
public interface IBildigimKelimeService {

    List<Kelime> findBildigimKelime(Long id);
//randomly
    List<Kelime> findBildigimKelimeRandom(Long id);

    List <BilinenKelime> findBilinenAllKelime();

    void addKelime(BilinenKelime kelime);

//kelime modeli.
    List findBilinenAllKelimeByUserId(User id);
}

