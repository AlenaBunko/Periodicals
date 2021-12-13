package org.study.periodicals.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.study.periodicals.repository.impl.DefaultEditionsRepository;
import org.study.periodicals.repository.interfaces.EditionsRepository;

@Configuration
public class RepositoriesConfig {

    @Bean
    public JdbcTemplate defaultJdbcTemplate() {
        return new JdbcTemplate();
    }

    @Bean
    public EditionsRepository editionsRepository(JdbcTemplate defaultJdbcTemplate) {
        return new DefaultEditionsRepository(defaultJdbcTemplate);
    }

}
