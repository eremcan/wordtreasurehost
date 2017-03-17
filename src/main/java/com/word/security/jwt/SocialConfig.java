package com.word.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;

import javax.sql.DataSource;

/**
 * Created by Nahide on 16.03.2017.
 */
@Configuration
@EnableSocial
public class SocialConfig implements SocialConfigurer {

    @Autowired
    private DataSource dataSource;

    @Bean
    public FacebookConnectionFactory facebookConnectionFactory() {
        FacebookConnectionFactory facebookConnectionFactory = new FacebookConnectionFactory("AppID", "AppSecret");
        facebookConnectionFactory.setScope("email");
        return facebookConnectionFactory;
    }

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
        cfConfig.addConnectionFactory(facebookConnectionFactory());
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
    }

    // Other @Bean maybe ...
}