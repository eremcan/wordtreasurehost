package com.word.repository;

import com.word.domain.BilinenKelime;
import com.word.domain.Kelime;
import com.word.domain.User;

import java.util.List;

/**
 * Created by Nahide on 10.02.2017.
 */
public interface IBildigimKelimeRepository extends ICommonDao<BilinenKelime, Long> {

    List findByuser(long id);

    List<Kelime> findByuserAndGetRandomly(long id);

    List findBilinenAllKelimesByUserId(User id);
}



