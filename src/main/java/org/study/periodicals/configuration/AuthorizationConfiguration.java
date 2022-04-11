package org.study.periodicals.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.study.periodicals.repository.impl.DefaultEditionsRepository;
import org.study.periodicals.repository.impl.DefaultUsersRepository;
import org.study.periodicals.service.AdminService;
import org.study.periodicals.service.MongoSessionManager;
import org.study.periodicals.service.SubscriptionService;
import org.study.periodicals.service.UserAuthorizationService;

@Configuration
public class AuthorizationConfiguration {


    @Bean
    public UserAuthorizationService userAuthorizationService(DefaultUsersRepository usersRepository, PasswordEncoder passwordEncoder, MongoSessionManager mongoSessionManager) {
        return new UserAuthorizationService(usersRepository, passwordEncoder, mongoSessionManager);
    }

    @Bean
    public AdminService adminService (DefaultUsersRepository usersRepository){
        return new AdminService(usersRepository);
    }

    @Bean
    public SubscriptionService subscriptionService(DefaultEditionsRepository editionsRepository, DefaultUsersRepository usersRepository){
        return new SubscriptionService(editionsRepository, usersRepository);
    }
}
