package org.study.periodicals.repository.interfaces;

import org.study.periodicals.model.Payment;
import org.study.periodicals.model.Subscription;
import org.study.periodicals.model.User;

import java.util.List;

public interface UsersRepository {

    void createUser(User user);

    List<User> findAll();

    User findByLogin(String login);

    User findByStatus(boolean status);

    List<Payment> findAllPayments(User user);

    void addSubscription (Subscription subscription);

    List<Subscription> findAllSubscriptions();

    void deleteSubscription(User user, Integer id);

    void update(User user);

    void deleteUser(Integer id);
}
