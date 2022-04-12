package org.study.periodicals.repository.impl;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.study.periodicals.model.Edition;
import org.study.periodicals.model.Role;
import org.study.periodicals.model.User;
import org.study.periodicals.repository.interfaces.EditionsRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@AllArgsConstructor
public class DefaultEditionsRepository implements EditionsRepository {

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addEdition(Edition edition) {

        String saveEditionQuery = "INSERT INTO PUBLIC.EDITIONS(TITLE, INDEX, PUBLISHING_HOUSE, RECOMMENDED_PRICE) " +
                "VALUES(?,?,?,?)";
        jdbcTemplate.update(saveEditionQuery, edition.getTitle(), edition.getIndex(), edition.getPublishingHouse(), edition.getRecommendedPrice());

    }

    @Override
    public Edition findEditionById(Integer id) {
        String findByTitle = "SELECT * FROM EDITIONS WHERE ID='" + id + "'";

        ResultSetExtractor<Edition> extractor = (rs) -> {
            Edition edition = new Edition();
            if (rs.next())
                edition.setId(rs.getInt("ID"));
                edition.setTitle(rs.getString("TITLE"));
                edition.setIndex(rs.getInt("INDEX"));
                edition.setPublishingHouse(rs.getString("PUBLISHING_HOUSE"));
                edition.setRecommendedPrice(rs.getInt("RECOMMENDED_PRICE"));
                return edition;

        };
        return jdbcTemplate.query(findByTitle, extractor);

    }

    @Override
    public List<Edition> findAllEditions() {
        String findAllEditions = "SELECT * FROM EDITIONS";

        RowMapper<Edition> rowMapper = DefaultEditionsRepository::mapRow;

        return jdbcTemplate.query(findAllEditions, rowMapper);
    }

    @Override
    public List<Edition> findEditionsByUser(int id) {
        String findEditions = "SELECT * EDITIONS INNER JOIN USERS ON EDITIONS.USERS_ID = USER.ID";
        RowMapper<Edition> rowMapper = DefaultEditionsRepository::mapRow;

        return jdbcTemplate.query(findEditions, rowMapper);
    }


    @Override
    public void delete(Integer id) {
        String deleteEdition = "DELETE FROM EDITIONS WHERE ID = ?";
        jdbcTemplate.update(deleteEdition, id);

    }

    private static Edition mapRow(ResultSet rs, int rowNum) throws SQLException {
        Edition edition = new Edition();
        edition.setId(rs.getInt("ID"));
        edition.setTitle(rs.getString("TITLE"));
        edition.setIndex(rs.getInt("INDEX"));
        edition.setPublishingHouse(rs.getString("PUBLISHING_HOUSE"));
        edition.setRecommendedPrice(rs.getInt("RECOMMENDED_PRICE"));
        return edition;
    }

}
