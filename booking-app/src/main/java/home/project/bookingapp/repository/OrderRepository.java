package home.project.bookingapp.repository;

import home.project.bookingapp.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    //List<Order> findAllByUser(User user);
}
