package com.word.service.impl;

import com.word.domain.Kelime;
import com.word.repository.IBildigimKelimeRepository;
import com.word.service.IBildigimKelimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nahide on 10.02.2017.
 */
@Service
public class BildigimKelimeService implements IBildigimKelimeService {

    @Autowired
    IBildigimKelimeRepository bildigimKelimeRepository;

    @Override
    public List<Kelime> findBildigimKelime(Long id) {
        List<Kelime> kelimeList = bildigimKelimeRepository.findByuser(id);
        return kelimeList;
    }
}
