package avd.proftaak.rentmycar.services;

import avd.proftaak.rentmycar.domain.Car;
import avd.proftaak.rentmycar.domain.RentalService;
import avd.proftaak.rentmycar.domain.User;
import avd.proftaak.rentmycar.repository.CarRepository;
import avd.proftaak.rentmycar.repository.RentalServiceRepository;
import avd.proftaak.rentmycar.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService {
    private final RentalServiceRepository rentalServiceRepository;
    private final CarRepository carRepository;

    public CarService(RentalServiceRepository rentalServiceRepository, CarRepository carRepository) {
        this.carRepository = carRepository;
        this.rentalServiceRepository = rentalServiceRepository;
    }

    public boolean addCar(Long rentalServiceId, Long carId) {
        Optional<RentalService> maybeRentalService = rentalServiceRepository.findById(rentalServiceId);
        Optional<Car> maybeCar = carRepository.findById(carId);

        if (maybeCar.isPresent() && maybeRentalService.isPresent()) {
            maybeRentalService.get().AddCar(maybeCar.get());
            rentalServiceRepository.save(maybeRentalService.get());
            carRepository.save(maybeCar.get());
            return true;
        } else {
            return false;
        }
    }
}
