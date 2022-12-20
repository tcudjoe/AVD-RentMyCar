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
    private Integer kilometers;
    private int numberOfDoors;
    private double weight;
    private CarCategories category;
    private int numberOfSeats;
    private String whereIsTheCar;
    private double cost;

    @OneToMany
    Set<Order> orders;

    @ManyToOne
    @JoinColumn
    //@JoinColumn(name = "rentalserviceId", nullable = false)
    private RentalService rentalService;

    //car constructor

    public Car(String brand, String model, int yearOfBuild, Integer kilometers, int numberOfDoors, double weight, CarCategories category, int numberOfSeats, String whereIsTheCar, double cost) {
        this.brand = brand;
        this.model = model;
        this.yearOfBuild = yearOfBuild;
        this.kilometers = kilometers;
        this.numberOfDoors = numberOfDoors;
        this.weight = weight;
        this.category = category;
        this.numberOfSeats = numberOfSeats;
        this.whereIsTheCar = whereIsTheCar;
        this.cost = cost;
    }
}