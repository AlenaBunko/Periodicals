package entity;

import java.util.Date;

public class User {

    private Integer id;

    private String firstName;

    private String lastName;

    private  String login;

    private String password;

// or LocalDate
    private Date birthday;

    private Date register;

    private boolean status;

    Role role;
}
