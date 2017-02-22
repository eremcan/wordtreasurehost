package com.word.service;

import com.word.domain.Kelime;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Nahide on 10.02.2017.
 */
public interface IKelimeService {

    Kelime findKelime(Long id);

    List<Kelime> findAllKelime();



}
