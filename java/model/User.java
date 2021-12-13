package model;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "users")
public class User {

    private Integer id;

    private String firstName;

    private String lastName;

    private String login;

    private String password;

    private Date birthday;

    private Date register;

    private boolean status;

    Role role;

    Set<Subscriptions> subscriptions;

    Set<Edition> editions;

    Set<Payment> payments;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "register")
    public Date getRegister() {
        return register;
    }

    public void setRegister(Date register) {
        this.register = register;
    }

    @Column(name = "status")
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public Set<Subscriptions> getSubscriptions() {
        if (subscriptions == null){
            subscriptions = new HashSet<>();
        }
        return subscriptions;
    }

    public void setSubscriptions(Set<Subscriptions> subscriptions) {
        this.subscriptions = subscriptions;
    }


    @ManyToMany
    @JoinTable(name = "users_editions",
    joinColumns = @JoinColumn(name = "users_id"),
    inverseJoinColumns = @JoinColumn(name = "editions_id"))
    public Set<Edition> getEditions() {
        if (editions == null){
            editions = new HashSet<>();
        }
        return editions;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public Set<Payment> getPayments() {
        if (payments == null){
            payments = new HashSet<>();
        }
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

}
