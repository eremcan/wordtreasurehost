package com.word.repository.impl;

import com.word.domain.User;
import com.word.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Nahide on 09.02.2017.
 */
@Component
public class UserRepository extends CommonDao<User, Long> implements IUserRepository {

    private final
    EntityManager entityManager;

    @Autowired
    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean checkExistFbId(String id) {
        User user = new User();
        user.setFacebookid(id);
        List<User> userList = findByExample(user);
        if (!userList.isEmpty())
            return true;
        else
            return false;
    }


    @Override
    public List<User> findByUserName(String username) {

        Query ahmet= entityManager.createQuery("select u From User as u where u.username = ?1",User.class).setParameter(1,username);
        return (List<User>)ahmet.getResultList();
    }

    @Override
    public boolean checkExistName(String username) {
        User exampleInstance = new User();
        exampleInstance.setUsername(username);
        List<User> userList = findByExample(exampleInstance);
        if (!userList.isEmpty())
            return true;
        else
            return false;
    }

    @Override
    public boolean checkExistEmail(String email) {
        User exampleInstance = new User();
        exampleInstance.setEmail(email);
        List<User> userList = findByExample(exampleInstance);
        if (!userList.isEmpty())
            return true;
        else
            return false;
    }
}
