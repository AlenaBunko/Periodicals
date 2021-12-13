package org.study.periodicals.repository.impl;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.study.periodicals.model.Edition;
import org.study.periodicals.model.User;
import org.study.periodicals.repository.interfaces.EditionsRepository;

import java.util.List;

@AllArgsConstructor
public class DefaultEditionsRepository implements EditionsRepository {

    private JdbcTemplate jdbcTemplate;

    @Override
    public void addEdition() {

    }

    @Override
    public List<Edition> findAllEditions(User login) {
        return null;
    }

    @Override
    public void updateEdition(Edition edition) {
        //TODO: to be implemented
        throw new IllegalStateException("Is not implemented");
    }

    @Override
    public void delete(Integer index) {

    }
}
