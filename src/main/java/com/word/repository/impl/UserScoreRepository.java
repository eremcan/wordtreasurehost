package com.word.repository.impl;

import com.word.domain.User;
import com.word.domain.UserScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nahide on 29.03.2017.
 */
@Component
public class UserScoreRepository extends CommonDao<UserScore, Long> {
    @Autowired
    EntityManager entityManager;
    @Autowired
    UserRepository userRepository;

    public List<User> userScoreList() {
        List<UserScore> userScores = entityManager.createQuery("SELECT k " +
                "FROM UserScore as k order by k.userBildigiKelimeToplami desc").getResultList();
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < userScores.size() - 1; i++) {

            userList.add(userRepository.findById(userScores.get(i).getUserid()));

        }
        return userList;
    }
}
