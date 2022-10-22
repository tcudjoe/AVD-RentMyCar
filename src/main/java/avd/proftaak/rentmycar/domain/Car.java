package avd.proftaak.rentmycar.domain;

import avd.proftaak.rentmycar.CarCategories;

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
    private String model;
    private String brand;
    private int yearOfBuild;
    private Integer kilometers;
    private int numberOfDoors;
    private double weight;
    private CarCategories category;
    private int numberOfSeats;
    private String whereIsTheCar;

    @OneToMany
    Set<Order> orders;

    public Car(String model, String brand, int yearOfBuild, Integer kilometers, int numberOfDoors, double weight, CarCategories category, int numberOfSeats, String whereIsTheCar) {
        this.model = model;
        this.brand = brand;
        this.yearOfBuild = yearOfBuild;
        this.kilometers = kilometers;
        this.numberOfDoors = numberOfDoors;
        this.weight = weight;
        this.category = category;
        this.numberOfSeats = numberOfSeats;
        this.whereIsTheCar = whereIsTheCar;
    }
}
