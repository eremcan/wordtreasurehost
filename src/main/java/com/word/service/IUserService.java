package com.word.service;

import com.word.domain.User;

import java.util.List;

/**
 * Created by Nahide on 09.02.2017.
 */
public interface IUserService {

    void saveUser(User user);

    User getUserById(Long id);

    List<User> findAllUser();

    void updateUser(User user);

    boolean checkExistUserName(String username);
}
