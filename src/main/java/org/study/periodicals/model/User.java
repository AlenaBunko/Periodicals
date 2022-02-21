package org.study.periodicals.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;


@Data
@Builder
@AllArgsConstructor
public class User {

    private Integer id;

    private String sessionId;

    private String firstName;

    private String lastName;

    private String login;

    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date register;

    private boolean status;

    private Role role;

    private Set<Subscription> subscriptions;

    private Set<Edition> editions;

    private Set<Payment> payments;

    public User() {

    }

    public User(String sessionId) {
        this.sessionId = sessionId;
    }

    public User(String sessionId, String login) {
        this.sessionId = sessionId;
        this.login = login;
    }
}
