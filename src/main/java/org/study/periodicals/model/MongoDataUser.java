package org.study.periodicals.model;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
public class MongoDataUser {

    private String sessionId;

    private String login;

    public MongoDataUser(String sessionId, String login) {
        this.sessionId = sessionId;
        this.login = login;
    }

    public MongoDataUser() {
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getLogin() {
        return login;
    }

}
