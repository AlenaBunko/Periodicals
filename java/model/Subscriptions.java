package model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "subscriptions")
public class Subscriptions {

    private Integer id;

    private Integer actualPrice;

    private Integer quantity;

    private Date startDate;

    private Date finishtDate;

    private Date subscriptionDate;

    User user;

    Set<Edition> editions;

    Payment payment;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "actual_price")
    public Integer getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Integer actualPrice) {
        this.actualPrice = actualPrice;
    }

    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "finish_date")
    public Date getFinishtDate() {
        return finishtDate;
    }

    public void setFinishtDate(Date finishtDate) {
        this.finishtDate = finishtDate;
    }

    @Column(name = "subscription_date")
    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "subscriptions", cascade = CascadeType.ALL)
    public Set<Edition> getEditions() {
        if (editions == null){
            editions = new HashSet<>();
        }
        return editions;
    }

    public void setEditions(Set<Edition> editions) {
        this.editions = editions;
    }

    @OneToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="payments_id")
    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

}
