package com.word.service.impl;

import com.word.domain.BilinenKelime;
import com.word.domain.Kelime;
import com.word.domain.User;
import com.word.repository.impl.BildigimKelimeRepository;
import com.word.service.IBildigimKelimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Nahide on 10.02.2017.
 */
@Service
@Transactional
public class BildigimKelimeService implements IBildigimKelimeService {

    @Autowired
    BildigimKelimeRepository bildigimKelimeRepository;

    @Override
    public List<BilinenKelime> findBilinenAllKelime() {
        List<BilinenKelime> bilinenKelimeList = bildigimKelimeRepository.findAll();
        return bilinenKelimeList;
    }

    @Override
    public void addKelime(BilinenKelime bilinenKelime) {
        bildigimKelimeRepository.persist(bilinenKelime);
    }

    @Override
    public List<Kelime> findBildigimKelimeRandom(Long id) {
        List<Kelime> kelime = bildigimKelimeRepository.findByuserAndGetRandomly(id);
        return kelime;
    }

    @Override
    public List<Kelime> findBildigimKelime(Long id) {
        List<Kelime> kelimeList = bildigimKelimeRepository.findByuser(id);
        return kelimeList;
    }

    @Override
    public List<Kelime> findBilinenAllKelimeByUserId(User id) {
        List<Kelime> findBilinenKelimesByUserId = bildigimKelimeRepository.findKelimesWithUser(id);
        return findBilinenKelimesByUserId;
    }
}
