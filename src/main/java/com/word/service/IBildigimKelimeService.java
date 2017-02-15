package com.word.service;

import com.word.domain.BilinenKelime;
import com.word.domain.Kelime;

import java.util.List;

/**
 * Created by Nahide on 10.02.2017.
 */
public interface IBildigimKelimeService {

    List<Kelime> findBildigimKelime(Long id);
}
