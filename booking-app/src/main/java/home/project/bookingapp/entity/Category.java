package home.project.bookingapp.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(
        name = "Categories",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "category_key")
        }
)
public class Category {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "category_key", nullable = false)
    private String categoryKey;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private List<Order> orders;

    public void addOrder(Order order){
        order.addCategory(this);
        orders.add(order);
    }

    public void removeOrder(Order order){
        order.removeCategory(null);
        orders.remove(order);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryKey() {
        return categoryKey;
    }

    public void setCategoryKey(String categoryKey) {
        this.categoryKey = categoryKey;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
