package org.study.periodicals.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Set;


@Data
@Builder
public class User {

    private Integer id;

    private String firstName;

    private String lastName;

    private String login;

    private String password;

    private Date birthday;

    private Date register;

    private boolean status;

    private Role role;

    private Set<Subscription> subscriptions;

    private Set<Edition> editions;

    private Set<Payment> payments;

}
