package com.word.service;

import com.word.domain.KelimeEkle;

import java.util.List;

/**
 * Created by Aytan on 27.05.2017.
 */
public interface IKelimeEkleService {
    void bildigiKelimeEkle(KelimeEkle kelimeEkle);

    List<KelimeEkle> findAllEklenenKelime(KelimeEkle kelimeEkle);
}
