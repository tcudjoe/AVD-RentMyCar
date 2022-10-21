package avd.proftaak.rentmycar.domain;

import avd.proftaak.rentmycar.CarCategories;
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
    @Column(name = "carid", nullable = false)
    private Long id;
    private String model;
    private String brand;
    private int yearOfBuild;
    private Integer kilometers;
    private int numberOfDoors;
    private double weight;
    private CarCategories categorie;
    private int numberOfSeats;
    private String whereIsTheCar;

    public Car(Long id, String model, String brand, int yearOfBuild, Integer kilometers, int numberOfDoors, double weight, CarCategories categorie, int numberOfSeats, String whereIsTheCar) {
        this.id = id;
        this.model = model;
        this.brand = brand;
        this.yearOfBuild = yearOfBuild;
        this.kilometers = kilometers;
        this.numberOfDoors = numberOfDoors;
        this.weight = weight;
        this.categorie = categorie;
        this.numberOfSeats = numberOfSeats;
        this.whereIsTheCar = whereIsTheCar;
    }
}
