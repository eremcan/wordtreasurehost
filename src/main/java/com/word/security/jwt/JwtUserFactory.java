package com.word.security.jwt;

import com.word.domain.User;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUserName(),
                user.getFirstname(),
                user.getSurname(),
                user.getUserMail(),
                user.getUserPassword()
        );
    }

}
