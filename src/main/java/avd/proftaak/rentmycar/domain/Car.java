package avd.proftaak.rentmycar.domain;

import avd.proftaak.rentmycar.FuelType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Car
{
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
    private FuelType fuelType;
    private int numberOfSeats;
    private String whereIsTheCar;

    @ManyToOne
    @JoinColumn(name="rentalServiceId", nullable=false)
    private RentalService rentalService;

    @OneToMany
    private Set<CarOrder> orders;

    public Car(Long carId, String brand, String model, int yearOfBuild, Integer kilometers, int numberOfDoors, double weight, FuelType fuelType, int numberOfSeats, String whereIsTheCar) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.yearOfBuild = yearOfBuild;
        this.kilometers = kilometers;
        this.numberOfDoors = numberOfDoors;
        this.weight = weight;
        this.fuelType = fuelType;
        this.numberOfSeats = numberOfSeats;
        this.whereIsTheCar = whereIsTheCar;
    }

    public Car(RentalService rentalService){
        this.rentalService = rentalService;
    }
}