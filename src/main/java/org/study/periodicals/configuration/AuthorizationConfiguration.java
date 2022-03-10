package org.study.periodicals.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.study.periodicals.repository.impl.DefaultUsersRepository;
import org.study.periodicals.service.UserAuthorizationService;

@Configuration
public class AuthorizationConfiguration {


    @Bean
    public UserAuthorizationService userAuthorizationService(DefaultUsersRepository usersRepository, PasswordEncoder passwordEncoder, MongoSessionManager mongoSessionManager) {
        return new UserAuthorizationService(usersRepository, passwordEncoder, mongoSessionManager);
    }

}
