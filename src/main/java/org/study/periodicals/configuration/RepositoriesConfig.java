package org.study.periodicals.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.study.periodicals.repository.impl.DefaultEditionsRepository;
import org.study.periodicals.repository.impl.DefaultUsersRepository;
import org.study.periodicals.repository.interfaces.EditionsRepository;
import org.study.periodicals.repository.interfaces.UsersRepository;

import javax.sql.DataSource;

@PropertySource("classpath:application.properties")
@Configuration
public class RepositoriesConfig implements WebMvcConfigurer {

    @Value("${datasource.driver}")
    private String datasourceDriver;

    @Value("${datasource.url}")
    private String datasourceUrl;

    @Value("${datasource.username}")
    private String datasourceUsername;

    @Value("${datasource.password}")
    private String datasourcePassword;

    @Bean
    public JdbcTemplate defaultJdbcTemplate(DataSource dataSource) {

        return new JdbcTemplate(dataSource);
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(datasourceDriver);
        dataSource.setUrl(datasourceUrl);
        dataSource.setUsername(datasourceUsername);
        dataSource.setPassword(datasourcePassword);
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

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
