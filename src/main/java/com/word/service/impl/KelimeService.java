package com.word.service.impl;

import com.word.domain.Kelime;
import com.word.repository.impl.KelimeRepository;
import com.word.service.IKelimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nahide on 10.02.2017.
 */

@Service
public class KelimeService implements IKelimeService {
    @Autowired
    KelimeRepository kelimeRepository;


    @Override
    public List<Kelime> findAllKelime() {
        return kelimeRepository.findAll();
    }

    @Override
    public Kelime findKelime(Long id) {
        return kelimeRepository.findById(id);
    }
}
