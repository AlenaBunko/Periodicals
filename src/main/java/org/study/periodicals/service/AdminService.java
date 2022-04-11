package org.study.periodicals.service;

import org.springframework.stereotype.Service;
import org.study.periodicals.model.User;
import org.study.periodicals.repository.impl.DefaultUsersRepository;

import java.util.List;

public class AdminService {

    private DefaultUsersRepository usersRepository;

    public AdminService(DefaultUsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<User> findAllUsers(){
        return usersRepository.findAll();
    }
}
