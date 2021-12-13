package repository;

import model.Payment;
import model.Subscriptions;
import model.User;

import java.util.List;

public interface UsersDAO {

    void createUser(User user);

    List<User> findAll();

    User findByLogin(String login);

    User findByStatus(boolean status);

    List<Payment> findAllPayments(User user);

    void addSubscription (User user);

    List<Subscriptions> findAllSubscriptions(User user);

    void deleteSubscription(User user,Integer id);

    void update(User user);

    void delete(Integer id);
}
