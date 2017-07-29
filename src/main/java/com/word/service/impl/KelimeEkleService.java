package com.word.service.impl;

import com.word.domain.KelimeEkle;
import com.word.repository.impl.KelimeEkleRepository;
import com.word.service.IKelimeEkleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Aytan on 27.05.2017.
 */
@Service
public class KelimeEkleService  implements IKelimeEkleService{
    @Autowired
    KelimeEkleRepository kelimeEkleRepository;
    @Override
    public void bildigiKelimeEkle(KelimeEkle kelimeEkle) {
        kelimeEkleRepository.persist(kelimeEkle);
    }

    @Override
    public List<KelimeEkle> findAllEklenenKelime(KelimeEkle kelimeEkle) {
        return kelimeEkleRepository.findByExample(kelimeEkle);
    }
}
