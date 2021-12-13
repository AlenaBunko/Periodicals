package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "editions")
public class Edition {

    private Integer id;

    private String title;

    private Integer index;

    private String publishingHouse;

    private Integer recommendedPrice;

    Set<User> users;

    Subscriptions subscriptions;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "index")
    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    @Column(name = "publishing_house")
    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    @Column(name = "recommended_price")
    public Integer getRecommendedPrice() {
        return recommendedPrice;
    }

    public void setRecommendedPrice(Integer recommendedPrice) {
        this.recommendedPrice = recommendedPrice;
    }

    @ManyToMany
    @JoinTable(name = "users_editions",
            joinColumns = @JoinColumn(name = "editions_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id"))
    public Set<User> getUsers() {
        if (users == null){
            users = new HashSet<>();
        }
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @ManyToOne
    @JoinColumn(name = "subscriptions_id")
    public Subscriptions getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Subscriptions subscriptions) {
        this.subscriptions = subscriptions;
    }
}
