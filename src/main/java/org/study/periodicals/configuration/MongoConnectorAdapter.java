package org.study.periodicals.configuration;

import org.study.periodicals.model.MongoDataUser;
import org.study.periodicals.model.User;

public class MongoConnectorAdapter extends MongoDataUser {

    private User user;

    public MongoConnectorAdapter(User user) {
        this.user = user;
    }

    @Override
    public String getSessionId() {
        return user.getSessionId();
    }

    @Override
    public String getLogin() {
        return user.getLogin();
    }

}