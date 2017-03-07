package com.word.service.impl;

import com.word.domain.User;
import com.word.repository.impl.UserRepository;
import com.word.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Nahide on 09.02.2017.
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED,
        noRollbackFor = {EmptyResultDataAccessException.class}, rollbackFor = {Exception.class})
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAllUser() {
        List<User> findAllUser = userRepository.findAll();
        return findAllUser;
    }

    @Override
    public User getUserbyUsername(String username) {
        User user = userRepository.findByUserName(username);
        return user;
    }

    @Override
    public void saveUser(User user) {

        if (user != null) {
            userRepository.persist(user);
        }
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean checkExistUserName(String username) {
        if (userRepository.checkExistName(username)) {
            return true;
        } else
            return false;
    }

    @Override
    public void updateUser(User user) {

        User mUser = userRepository.findById(user.getId());

        mUser.setFirstname(user.getFirstname());
        mUser.setSurname(user.getSurname());
        mUser.setUserName(user.getUserName());
        mUser.setUserMail(user.getUserMail());
        mUser.setUserPassword(user.getUserPassword());

        userRepository.merge(mUser);
    }
}
