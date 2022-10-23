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
    @Column(name = "carid", nullable = false)
    private Long id;

    private String brand;
    private String model;
    private int yearOfBuild;
    private Integer kilometers;
    private int numberOfDoors;
    private double weight;
    private CarCategories category;
    private int numberOfSeats;
    private String whereIsTheCar;

    @OneToMany
    Set<Order> orders;

    //car constructor
    public Car(String model, String brand, int yearOfBuild, Integer kilometers, int numberOfDoors, double weight, CarCategories category, int numberOfSeats, String whereIsTheCar) {
        this.brand = brand;
        this.model = model;
        this.yearOfBuild = yearOfBuild;
        this.kilometers = kilometers;
        this.numberOfDoors = numberOfDoors;
        this.weight = weight;
        this.category = category;
        this.numberOfSeats = numberOfSeats;
        this.whereIsTheCar = whereIsTheCar;
    }
}