package avd.proftaak.rentmycar.controllers.dto;

import avd.proftaak.rentmycar.domain.Car;
import avd.proftaak.rentmycar.domain.Customer;
import avd.proftaak.rentmycar.domain.RentalService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderId", nullable = false)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "carId", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "rentalserviceId")
    private RentalService rentalService;

    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

//    @ManyToOne
//    @JoinColumn(name = "costId")
//    private Cost cost;


    public Order(Car car, Customer customer, RentalService rentalService, String description, LocalDateTime startTime, LocalDateTime endTime) {
        this.car = car;
        this.customer = customer;
        this.rentalService = rentalService;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
