package org.study.periodicals.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.study.periodicals.service.MongoSessionManager;

@PropertySource("classpath:application.properties")
@Configuration
public class MongoConnectorConfiguration {

    @Value("${mongo.connectString}")
    private String connectString;

    @Bean
    public MongoSessionManager sessionManager(){
        return new MongoSessionManager(connectString);
    }
}
