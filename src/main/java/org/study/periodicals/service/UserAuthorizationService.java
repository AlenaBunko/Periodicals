package org.study.periodicals.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.study.periodicals.configuration.MongoConnectorAdapter;
import org.study.periodicals.configuration.MongoSessionManager;
import org.study.periodicals.model.User;
import org.study.periodicals.repository.impl.DefaultUsersRepository;

import java.util.List;


public class UserAuthorizationService {

    MongoSessionManager mongoSessionManager;

    private DefaultUsersRepository usersRepository;

    private PasswordEncoder passwordEncoder;

    public UserAuthorizationService(DefaultUsersRepository usersRepository, PasswordEncoder passwordEncoder, MongoSessionManager mongoSessionManager) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.mongoSessionManager = mongoSessionManager;
    }

    public boolean isUserAuthorized(String sessionId) {
        MongoConnectorAdapter adapter = new MongoConnectorAdapter(new User(sessionId));
        return mongoSessionManager.findKey(adapter);

    }

    public boolean isUserAuthenticated(String login, String password) {
        User user = usersRepository.findByLogin(login);
        if (user == null) {
            return false;
        }
        return passwordEncoder.matches(password, user.getPassword());
    }

    public void saveSession(String sessionId, String login) {
        User userByLogin = usersRepository.findByLogin(login);
        MongoConnectorAdapter adapter = new MongoConnectorAdapter(new User(sessionId, userByLogin.getLogin()));
        mongoSessionManager.saveSession(adapter);
    }


    public void deleteSession(String sessionId) {
       MongoConnectorAdapter adapter = new MongoConnectorAdapter(new User(sessionId));
       mongoSessionManager.deleteSessionUser(adapter);
    }
}
