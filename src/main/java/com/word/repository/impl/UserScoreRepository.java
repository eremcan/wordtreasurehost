package com.word.repository.impl;

import com.word.domain.UserScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Nahide on 29.03.2017.
 */
@Component
public class UserScoreRepository extends CommonDao<UserScore, Long> {
    @Autowired
    EntityManager entityManager;

    public List<UserScore> userScoreList() {
        List<UserScore> userScores = entityManager.createQuery("SELECT k " +
                "FROM UserScore as k order by k.userBildigiKelimeToplami desc").getResultList();
        return userScores;
    }
}
