package com.word.repository.impl;

import com.word.domain.User;
import com.word.repository.IUserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Nahide on 09.02.2017.
 */
@Component
public class UserRepository extends CommonDao<User, Long> implements IUserRepository {

    @Override
    public User findByUserName(String username) {
        User user = new User();
        user.setUserName(username);
        user.setUserName("banane");
        user.setUserPassword("password");
        user.setId(1l);
        user.setFirstname("ahmedi");
        user.setUserMail("bla@gmail.com");

        //  List<User> userList = findByExample(user);
        return user;
    }

    @Override
    public boolean checkExistName(String username) {
        User exampleInstance = new User();
        exampleInstance.setUserName(username);
        List<User> userList = findByExample(exampleInstance);
        if (!userList.isEmpty())
            return true;
        else
            return false;
    }
}
