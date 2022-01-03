package org.study.periodicals.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.study.periodicals.repository.impl.DefaultEditionsRepository;
import org.study.periodicals.repository.impl.DefaultUsersRepository;
import org.study.periodicals.repository.interfaces.EditionsRepository;
import org.study.periodicals.repository.interfaces.UsersRepository;

import javax.sql.DataSource;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.study.periodicals")
public class RepositoriesConfig implements WebMvcConfigurer {


    @Bean
    public JdbcTemplate defaultJdbcTemplate(DataSource dataSource) {

        return new JdbcTemplate(dataSource);
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:D:\\Users\\user\\IdeaProjects\\periodicals\\h2.db");
        dataSource.setUsername("h2");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public UsersRepository usersRepository(JdbcTemplate defaultJdbcTemplate) {
        return new DefaultUsersRepository(defaultJdbcTemplate);
    }

    @Bean
    public EditionsRepository editionsRepository(JdbcTemplate defaultJdbcTemplate) {
        return new DefaultEditionsRepository(defaultJdbcTemplate);
    }


}
