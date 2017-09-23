package com.word.repository;

import com.word.domain.User;

import java.util.List;

/**
 * Created by Nahide on 09.02.2017.
 */
public interface IUserRepository extends ICommonDao<User,Long>{
    boolean checkExistName(String username);
    List findByUserName(String username);

    boolean checkExistEmail(String email);

    boolean checkExistFbId(String id);
}
