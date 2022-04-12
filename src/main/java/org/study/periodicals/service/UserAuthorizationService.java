package org.study.periodicals.service;

import org.bson.types.ObjectId;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.study.periodicals.model.User;
import org.study.periodicals.repository.impl.DefaultUsersRepository;


public class UserAuthorizationService {

    private MongoSessionManager mongoSessionManager;

    private DefaultUsersRepository usersRepository;

    private PasswordEncoder passwordEncoder;

    public UserAuthorizationService(DefaultUsersRepository usersRepository, PasswordEncoder passwordEncoder, MongoSessionManager mongoSessionManager) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.mongoSessionManager = mongoSessionManager;
    }

    public boolean isUserAuthorized(String sessionId) {
        return mongoSessionManager.findKey(sessionId);

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
        mongoSessionManager.saveSession(sessionId, userByLogin, userByLogin.getRole());
    }

    public Integer findRole (String sessionId){
        Integer role = mongoSessionManager.findRole(sessionId);
        return role;
    }

    public void deleteSession(String sessionId) {
       mongoSessionManager.deleteSessionUser(sessionId);
    }
}
