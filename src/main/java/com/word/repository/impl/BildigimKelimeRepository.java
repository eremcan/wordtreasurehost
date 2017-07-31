package com.word.repository.impl;

import com.word.domain.BilinenKelime;
import com.word.domain.Kelime;
import com.word.domain.User;
import com.word.repository.IBildigimKelimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component
public class BildigimKelimeRepository extends CommonDao<BilinenKelime, Long> implements IBildigimKelimeRepository {

    private final EntityManager entityManager;

    public BildigimKelimeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Kelime> findByuserAndGetRandomly(long id) {
        List<Kelime> bilmedigimKelimeRandomLimit1 = entityManager.createQuery("SELECT k " +
                "FROM Kelime as k  WHERE k.id NOT IN \n" +
                "(SELECT bl.kelime.id\n" +
                "FROM BilinenKelime as bl WHERE bl.user.id = " + id + ") ORDER BY rand()" +
                "\n").setMaxResults(1).getResultList();

        List<Kelime> gelenUcYanlisSecenek = entityManager.createQuery("SELECT k " +
                "FROM Kelime as k \n" +
                "ORDER BY rand()" +
                "\n").setMaxResults(4).getResultList();
        boolean isAyniobje = false;
        for (Kelime item : gelenUcYanlisSecenek) {
            if (!gelenUcYanlisSecenek.contains(bilmedigimKelimeRandomLimit1.get(0)))
                bilmedigimKelimeRandomLimit1.add(item);
            else {
                bilmedigimKelimeRandomLimit1.add(item);
                isAyniobje = true;
            }
        }
        if (isAyniobje) {
            bilmedigimKelimeRandomLimit1.remove(0);
        }

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

    @Override
    public List findKelimesWithUser(User id) {


        Query query = entityManager.createNativeQuery("select k.id,k.kelime_key,k.kelime_value from Kelime as k " +
                "where k.id in\n" + "(select bl.kelime_id from bilinenkelime as bl where bl.user_id = "+id.getId()+ ") " +
                "order by k.id asc ", Kelime.class);

        return query.getResultList();
    }
}
