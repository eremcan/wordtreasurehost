package com.word.service.impl;

import com.word.domain.BilinenKelime;
import com.word.domain.Kelime;
import com.word.repository.IBildigimKelimeRepository;
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
    public void addkelime(BilinenKelime bilinenKelime) {
        bildigimKelimeRepository.persist(bilinenKelime);
    }

    @Override
    public List<Kelime> findBildigimKelime(Long id) {
        List<Kelime> kelimeList = bildigimKelimeRepository.findByuser(id);
        return kelimeList;
    }
}
