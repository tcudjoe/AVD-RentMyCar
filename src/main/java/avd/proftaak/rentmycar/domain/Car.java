package avd.proftaak.rentmycar.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Slf4j
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public abstract class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String model;
    private String brand;
    private int yearOfBuild;
    private Integer kilometers;
    private int numberOfDoors;
    private double weight;
    private String fuelType;
    private int numberOfSeats;
    private String whereIsTheCar;

    public Car(Long id, String model, String brand, int yearOfBuild, int kilometers, int numberOfDoors, double weight, String fuelType, int numberOfSeats, String whereIsTheCar) {
        this.id = id;
        this.model = model;
        this.brand = brand;
        this.yearOfBuild = yearOfBuild;
        this.kilometers = kilometers;
        this.numberOfDoors = numberOfDoors;
        this.weight = weight;
        this.fuelType = fuelType;
        this.numberOfSeats = numberOfSeats;
        this.whereIsTheCar = whereIsTheCar;
    }
}
