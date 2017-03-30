package com.word.service.impl;

import com.word.domain.User;
import com.word.domain.UserScore;
import com.word.repository.impl.UserScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Nahide on 29.03.2017.
 */
@Service
@Transactional
public class UserScoreService {
    @Autowired
    UserScoreRepository userScoreRepository;

    public void kelimeArttir(Long activeUserId) {
        UserScore userScore = new UserScore();
        userScore.setUserid(activeUserId);
        List<UserScore> bulunanUser = userScoreRepository.findByExample(userScore);

        if (bulunanUser.isEmpty()) {
            UserScore userScore1 = new UserScore();
            userScore1.setUserid(activeUserId);
            userScore1.setUserBildigiKelimeToplami(0L);
            userScoreRepository.persist(userScore1);
        } else {
            userScore = bulunanUser.get(0);
            if (bulunanUser.get(0).getUserBildigiKelimeToplami() == null)
                bulunanUser.get(0).setUserBildigiKelimeToplami(0L);
            bulunanUser.get(0).setUserBildigiKelimeToplami(bulunanUser.get(0).getUserBildigiKelimeToplami() + 1);
            userScoreRepository.merge(userScore);
        }
    }

    public List<User> getListofUser() {

        return userScoreRepository.userScoreList();
    }
}
