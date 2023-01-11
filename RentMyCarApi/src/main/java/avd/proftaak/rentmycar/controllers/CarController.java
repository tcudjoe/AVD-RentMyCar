package avd.proftaak.rentmycar.controllers;

import avd.proftaak.rentmycar.domain.RentalService;
import avd.proftaak.rentmycar.domain.User;
import avd.proftaak.rentmycar.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import avd.proftaak.rentmycar.domain.Car;
import avd.proftaak.rentmycar.repository.CarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Carcontroller doet post en get mappings correct. Delete mappings hebik nog niet geprobeerd maar dat kan ik zelf wel doen
@Slf4j
@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    @Autowired
    public CarController(CarRepository carRepository, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    //Gets all cars based on the model
    @GetMapping
    public ResponseEntity<List<Car>> getAll(
        @RequestParam(required = false, name = "maxKilometers") Integer maxKilometers,
        @RequestParam(required = false, name = "maxCost") Double maxCost)
    {
        List<Car> found = new ArrayList<>();

        if(maxKilometers == null && maxCost == null)
        {
            found.addAll(carRepository.findAll());
        }
        else {
            found.addAll(carRepository.customFindCars(maxKilometers, maxCost));
        }
        if (found.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(found);
    }

    //Gets a car by its id
    @GetMapping("/{carId}")
    public Optional<Car> getById(@PathVariable Long carId){
        return carRepository.findById(carId);
    }

    //Creates new car
    public ResponseEntity<Car> create(@RequestBody Car newCar){
        try{
            Car car = carRepository.save(newCar);
            return new ResponseEntity<>(car, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            log.info("Error creating new car " + newCar + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }


    //Deletes car based on id
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long carId){
        if(!carRepository.existsById(carId)){
            return ResponseEntity.notFound().build();
        }

        carRepository.deleteById(carId);
        return ResponseEntity.ok().build();
    }
}