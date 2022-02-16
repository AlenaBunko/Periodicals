package org.study.periodicals.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.study.periodicals.model.User;
import org.study.periodicals.repository.impl.DefaultUsersRepository;

import java.util.HashMap;
import java.util.Map;

public class UserAuthorizationService {

    private Map<String, User> users = new HashMap<>();

    private DefaultUsersRepository usersRepository;

    private PasswordEncoder passwordEncoder;

    public UserAuthorizationService(DefaultUsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean isUserAuthorized(String sessionId) {
        return users.containsKey(sessionId);

    }

    public boolean isUserAuthenticated(String login, String password) {
        User user = usersRepository.findByLogin(login);
        if (user == null) {
            return false;
        }
        return passwordEncoder.matches(password, user.getPassword());
    }

    public void saveSession(String sessionId, String login) {
        User user = usersRepository.findByLogin(login);
        users.put(sessionId, user);
    }

    public void deleteSession(String sessionId){
        users.remove(sessionId);
        System.out.println("Мы удалили юзера по ключу "+ sessionId);
    }
}
