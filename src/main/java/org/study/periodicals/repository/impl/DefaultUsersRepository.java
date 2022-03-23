package org.study.periodicals.repository.impl;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.study.periodicals.model.*;
import org.study.periodicals.repository.interfaces.UsersRepository;


import javax.sql.DataSource;
import java.util.*;

@Repository
@AllArgsConstructor
public class DefaultUsersRepository implements UsersRepository {

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void createUser(User user) {
        String saveUserQuery = "INSERT INTO PUBLIC.USERS(FIRST_NAME, LAST_NAME, LOGIN, PASSWORD, BIRTHDAY, REGISTER, STATUS, ROLE) " +
                "VALUES(?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(saveUserQuery, user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword(), user.getBirthday(), user.getRegister(),
                user.isStatus(), user.getRole().getRoleId());

    }

    @Override
    public List<User> findAll() {
        String findAllUsers = "SELECT * FROM USERS";

        RowMapper<User> rowMapper = (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("ID"));
            user.setFirstName(rs.getString("FIRST_NAME"));
            user.setLastName(rs.getString("LAST_NAME"));
            user.setBirthday(rs.getDate("BIRTHDAY"));
            user.setRegister(rs.getDate("REGISTER"));
            user.setStatus(rs.getBoolean("STATUS"));
            user.setRole(Role.getById(rs.getInt("ROLE")));
            return user;
        };

        return jdbcTemplate.query(findAllUsers, rowMapper);

    }

    @Override
    public User findByLogin(String login) {
        String findByLoginUser = "SELECT * FROM USERS WHERE LOGIN='" + login + "'";

        ResultSetExtractor<User> extractor = (rs) -> {
            User user = new User();
            if (rs.next())
                user.setId(rs.getInt("ID"));
            user.setUsername(rs.getString("LOGIN"));
            user.setPassword(rs.getString("PASSWORD"));
            user.setFirstName(rs.getString("FIRST_NAME"));
            user.setLastName(rs.getString("LAST_NAME"));
            user.setBirthday(rs.getDate("BIRTHDAY"));
            user.setRegister(rs.getDate("REGISTER"));
            user.setStatus(rs.getBoolean("STATUS"));
            user.setRole(Role.getById(rs.getInt("ROLE")));
            return user;
        };

        return jdbcTemplate.query(findByLoginUser, extractor);
    }

    @Override
    public User findByStatus(boolean status) {
        String findByStatusUser = "SELECT * FROM USERS WHERE STATUS='" + status + "'";

        ResultSetExtractor<User> extractor = (rs) -> {
            User user = new User();
            user.setFirstName(rs.getString("FIRST_NAME"));
            user.setLastName(rs.getString("LAST_NAME"));
            user.setBirthday(rs.getDate("BIRTHDAY"));
            user.setRegister(rs.getDate("REGISTER"));
            user.setStatus(rs.getBoolean("STATUS"));
            user.setRole((Role) rs.getObject("ROLE"));
            return user;
        };
        return jdbcTemplate.query(findByStatusUser, extractor);
    }

    @Override
    public List<Payment> findAllPayments(User user) {

        String findAllPaymentsUsers = "SELECT U.FIRST_NAME, U.LAST_NAME, P.TOTAL_AMOUNT" +
                " FROM PAYMENTS P, USERS U" +
                " WHERE U.USERS_ID = P.USERS_ID";

        RowMapper<Payment> rowMapper = (rs, rowNum) -> {
            Set<Payment> payments = new LinkedHashSet<>();
            user.setPayments((Set<Payment>) rs.getObject("TOTAL_AMOUNT", Payment.class));
            payments.contains(user);
            return (Payment) payments;
        };
        return jdbcTemplate.query(findAllPaymentsUsers, rowMapper);
    }

    @Override
    public void updateUser(User user) {
        String updateUser = "UPDATE USERS SET FIRST_NAME=?, LAST_NAME=?, BIRTHDAY=?, REGISTER=?, STATUS=?, ROLE=? WHERE ID=?";
        jdbcTemplate.update(updateUser, user.getFirstName(), user.getLastName(), user.getBirthday(), user.getRegister(),
                user.isStatus(), user.getRole(), user.getId());
    }

    @Override
    public void deleteUser(Integer id) {
        String deleteUser = "DELETE FROM USERS WHERE ID=" + id;
        jdbcTemplate.update(deleteUser);

    }

    @Override
    public void addSubscription(Subscription subscription) {
        String saveSubscriptionQuery = "INSERT INTO PUBLIC.SUBSCRIPTIONS(ACTUAL_PRICE, QUANTITY, START_DATE, FINISH_DATE, SUBSCRIPTION_DATE) " +
                "VALUES(?,?,?,?,?)";
        jdbcTemplate.update(saveSubscriptionQuery, subscription.getActualPrice(), subscription.getQuantity(),subscription.getStartDate(),
                subscription.getFinishDate(), subscription.getSubscriptionDate(), subscription.getUser().getId());
    }

    @Override
    public List<Subscription> findAllSubscriptions() {
        return null;
    }

    @Override
    public void deleteSubscription(User user, Integer id) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public Edition addEditionInSubscription(String title) {
        return null;
    }
}



