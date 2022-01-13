package org.study.periodicals.repository.impl;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.study.periodicals.model.Payment;
import org.study.periodicals.model.Role;
import org.study.periodicals.model.Subscription;
import org.study.periodicals.model.User;
import org.study.periodicals.repository.interfaces.UsersRepository;


import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
@AllArgsConstructor
public class DefaultUsersRepository implements UsersRepository {

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void createUser(User user) {
        String saveUserQuery = "INSERT INTO USERS(FIRST_NAME, LAST_NAME, BIRTHDAY, REGISTER, STATUS, ROLE) " +
                "VALUES(?,?,?,?,?,?)";
        jdbcTemplate.update(saveUserQuery, user.getFirstName(), user.getLastName(), user.getBirthday(), user.getRegister(),
                user.isStatus(), user.getRole());

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
            user.setRole(rs.getObject("ROLE", Role.class));
            return user;
    };

        return jdbcTemplate.query(findAllUsers,rowMapper);

}

    @Override
    public User findByLogin(String login) {
        String findByLoginUser = "SELECT * FROM USERS WHERE LOGIN=" + login;

        ResultSetExtractor<User> extractor = (rs) -> {
            User user = new User();
            user.setFirstName(rs.getString("FIRST_NAME"));
            user.setLastName(rs.getString("LAST_NAME"));
            user.setBirthday(rs.getDate("BIRTHDAY"));
            user.setRegister(rs.getDate("REGISTER"));
            user.setStatus(rs.getBoolean("STATUS"));
            user.setRole(rs.getObject("ROLE", Role.class));
            return user;
        };

        return jdbcTemplate.query(findByLoginUser, extractor);
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
        String updateUser = "UPDATE USERS SET FIRST_NAME=?, LAST_NAME=?, BIRTHDAY=?, REGISTER=?, STATUS=?, ROLE=? WHERE ID=?";
        jdbcTemplate.update(updateUser, user.getFirstName(), user.getLastName(), user.getBirthday(), user.getRegister(),
                user.isStatus(), user.getRole(), user.getId());
    }

    @Override
    public void delete(Integer id) {
        String deleteUser = "DELETE FROM USERS WHERE ID=" + id;
        jdbcTemplate.update(deleteUser);

    }
}
