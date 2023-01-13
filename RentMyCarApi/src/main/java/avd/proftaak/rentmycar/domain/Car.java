package avd.proftaak.rentmycar.domain;

import avd.proftaak.rentmycar.CarCategories;

import avd.proftaak.rentmycar.controllers.dto.Order;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import javax.persistence.*;

@Slf4j
@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "carId", nullable = false)
    private Long carId;
    private String brand;
    private String model;
    private int yearOfBuild;
    private int kilometers;
    private double weight;
    private CarCategories category;
    private String location;
    private double cost;
    @OneToMany
    Set<Order> orders;
    @ManyToOne
    @JoinColumn
    //@JoinColumn(name = "rentalserviceId", nullable = false)
    private RentalService rentalService;

    public Car(String brand, String model, int yearOfBuild, Integer kilometers, double weight, CarCategories category, String location, double cost) {
        this.brand = brand;
        this.model = model;
        this.yearOfBuild = yearOfBuild;
        this.kilometers = kilometers;
        this.weight = weight;
        this.category = category;
        this.location = location;
        this.cost = cost;
    }
}