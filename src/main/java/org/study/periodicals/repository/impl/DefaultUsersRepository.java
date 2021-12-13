package org.study.periodicals.repository.impl;

import org.study.periodicals.model.Payment;
import org.study.periodicals.model.Subscription;
import org.study.periodicals.model.User;
import org.study.periodicals.repository.interfaces.UsersRepository;

import java.util.List;

public class DefaultUsersRepository implements UsersRepository {

    @Override
    public void createUser(User user) {

    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findByLogin(String login) {
        return null;
    }

    @Override
    public User findByStatus(boolean status) {
        return null;
    }

    @Override
    public List<Payment> findAllPayments(User user) {
        return null;
    }

    @Override
    public void addSubscription(User user) {

    }

    @Override
    public List<Subscription> findAllSubscriptions(User user) {
        return null;
    }

    @Override
    public void deleteSubscription(User user, Integer id) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(Integer id) {

    }
}
