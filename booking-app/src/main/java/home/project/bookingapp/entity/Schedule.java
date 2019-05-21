package home.project.bookingapp.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(
        name = "Schedule"
)
public class Schedule {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "create_date")
    private Timestamp createDate;
    @Column(name = "finish_date")
    private Timestamp finishDate;
    @Column(name = "master_login")
    private String masterLogin;

    @OneToMany(mappedBy = "schedule")
    private List<Order> orders;

    public void addOrder(Order order){
        order.setSchedule(this);
        orders.add(order);
    }

    public void removeOrder(Order order){
        order.setSchedule(null);
        orders.remove(order);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Timestamp finishDate) {
        this.finishDate = finishDate;
    }

    public String getMasterLogin() {
        return masterLogin;
    }

    public void setMasterLogin(String masterLogin) {
        this.masterLogin = masterLogin;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
