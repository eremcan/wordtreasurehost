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
        List<User> userList = findByExample(user);
        if (userList.size()==0)
            return null;
        else {
            user.setUserName(userList.get(0).getUserName());
            user.setFirstname(userList.get(0).getFirstname());
            user.setSurname(userList.get(0).getSurname());
            user.setUserPassword(userList.get(0).getUserPassword());
            user.setUserMail(userList.get(0).getUserMail());
            return user;
        }
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
