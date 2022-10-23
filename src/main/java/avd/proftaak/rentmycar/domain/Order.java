package avd.proftaak.rentmycar.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderId", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "carId", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    private String description;

    public Order(Car car, Customer customer, String description) {
        this.car = car;
        this.customer = customer;
        this.description = description;
    }
}
