package avd.proftaak.rentmycar.controllers;

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
@RequestMapping("/Cars")
public class CarController {
    private final CarRepository carRepository;

    @Autowired
    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAll(@RequestParam(required = false) String model){
        List<Car> found = new ArrayList<>();
        if (model == null){
            found.addAll(carRepository.findAll());
        }else {
            found.addAll(carRepository.findCarByModelIgnoreCase(model));
        }

        if (found.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(found);
    }

    @GetMapping("/Cars/{carId}")
    public Optional<Car> getById(@PathVariable Long carId){
        return carRepository.findById(carId);
    }

    @GetMapping("/Cars/{brand}")
    public ResponseEntity<List<Car>> getByBrand(@PathVariable String brand){
        List<Car> found = new ArrayList<>();

        if (brand == null){
            found.addAll(carRepository.findAll());
        }else {
            found.addAll(carRepository.findCarByBrandIgnoreCase(brand));
        }

        if (found.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(found);
    }

    @GetMapping("/Cars/{kilometers}")
    public ResponseEntity<List<Car>> getByKilometers(@PathVariable Integer kilometers){
        List<Car> found = new ArrayList<>();

        if (kilometers == null){
            found.addAll(carRepository.findAll());
        }else {
            found.addAll(carRepository.findCarByKilometersContaining(kilometers));
        }

        if (found.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(found);
    }

    @PostMapping
    public ResponseEntity<Car> create(@RequestBody Car newCar){
        try{
            Car car = carRepository.save(newCar);
            return new ResponseEntity<>(car, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            log.info("Error creating new car " + newCar + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long carId){
        if(!carRepository.existsById(carId)){
            return ResponseEntity.notFound().build();
        }

        carRepository.deleteById(carId);
        return ResponseEntity.ok().build();
    }
}