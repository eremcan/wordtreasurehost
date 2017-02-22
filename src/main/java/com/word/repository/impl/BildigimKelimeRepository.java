package com.word.repository.impl;

import com.word.domain.BilinenKelime;
import com.word.domain.Kelime;
import com.word.repository.IBildigimKelimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class BildigimKelimeRepository extends CommonDao<BilinenKelime, Long> implements IBildigimKelimeRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Kelime> findByuserAndGetRandomly(long id) {
        List<Kelime> bilmedigimKelimeRandomLimit1 = entityManager.createQuery("SELECT k.id,k.kelimeKey,k.kelimeValue\n" +
                "FROM Kelime as k  where k.id not in \n" +
                "(SELECT bl.kelime.id\n" +
                "FROM BilinenKelime as bl where bl.user.id = " + id + ") order by rand()" +
                "\n").getResultList();

        return bilmedigimKelimeRandomLimit1;
    }

    @Override
    public List<Kelime> findByuser(long id) {
        List<Kelime> bilmedigimKelimeList = entityManager.createQuery("SELECT k.id,k.kelimeKey,k.kelimeValue\n" +
                "FROM Kelime as k  where k.id not in \n" +
                "(SELECT bl.kelime.id\n" +
                "FROM BilinenKelime as bl where bl.user.id = " + id + ") \n" +
                "\n").getResultList();

        return bilmedigimKelimeList;
    }
}
