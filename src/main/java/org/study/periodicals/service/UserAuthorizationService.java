package org.study.periodicals.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.study.periodicals.model.User;
import org.study.periodicals.repository.impl.DefaultUsersRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserAuthorizationService {

    private DefaultUsersRepository usersRepository;

     private  PasswordEncoder passwordEncoder;

    public UserAuthorizationService(DefaultUsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean isUserAuthorized(String sessionId) {

        return true;

    }

    public boolean isUserAuthenticated (String login, String password){
        User user = usersRepository.findByLogin(login);
        if (user == null) {
            return false;
        }
//        if (!user.getPassword().equals(passwordEncoder.encode(password))) {
//            return false;
//        }
        if(!passwordEncoder.matches(password, user.getPassword())){
            return false;
        }
        return true;
    }
}
