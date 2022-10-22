package avd.proftaak.rentmycar.repository;

import avd.proftaak.rentmycar.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> order = new ArrayList<>();

    List<Order> findOrderByCustomerId(int customerId);
    List<Order> findOrderByCarId(int carId);
}
