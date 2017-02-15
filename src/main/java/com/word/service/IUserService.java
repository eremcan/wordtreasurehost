package com.word.service;

import com.word.domain.User;

/**
 * Created by Nahide on 09.02.2017.
 */
public interface IUserService {

    void saveUser(User user);

    User getUserById(Long id);

    void updateUser(User user);

}
