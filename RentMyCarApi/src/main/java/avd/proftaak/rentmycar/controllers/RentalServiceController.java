package avd.proftaak.rentmycar.controllers;

import avd.proftaak.rentmycar.domain.Car;
import avd.proftaak.rentmycar.domain.RentalService;
import avd.proftaak.rentmycar.repository.CarRepository;
import avd.proftaak.rentmycar.repository.UserRepository;
import avd.proftaak.rentmycar.services.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/rentalservices")
public class RentalServiceController {
    private final CarService carService;

    @Autowired
    public RentalServiceController(CarService carService) {
        this.carService = carService;
    }

    //Creates new car
    @PostMapping("/{rentalServiceId}/cars/{carId}")
    public ResponseEntity addCar(@PathVariable Long rentalServiceId, @PathVariable Long carId){
        if (carService.addCar(rentalServiceId, carId))
        {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}