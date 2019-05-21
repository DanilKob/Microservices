package home.project.bookingapp.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(
        name = "Users", uniqueConstraints = {@UniqueConstraint(columnNames = "login")})
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login", nullable = false)
    private String login;

    @Embedded
    private FullName fullName;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    public User() {

    }

    public User(String login, FullName fullName) {
        this.login = login;
        this.fullName = fullName;
    }

    public void addOrder(Order order){
        order.setUser(this);
        orders.add(order);
    }

    public void removeOrder(Order order){
        order.setUser(null);
        orders.remove(order);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }
}
