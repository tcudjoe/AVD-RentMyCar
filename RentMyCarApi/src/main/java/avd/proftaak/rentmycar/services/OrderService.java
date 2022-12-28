package avd.proftaak.rentmycar.services;

import avd.proftaak.rentmycar.domain.Car;
import avd.proftaak.rentmycar.domain.Customer;
import avd.proftaak.rentmycar.domain.User;
import avd.proftaak.rentmycar.repository.CarRepository;
import avd.proftaak.rentmycar.repository.OrderRepository;
import avd.proftaak.rentmycar.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    public OrderService(UserRepository userRepository, CarRepository carRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

//    public boolean addOrder(Long customerId, Long carId) {
//        Optional<Car> maybeCar = carRepository.findById(carId);
//        Optional<User> maybeCustomer = userRepository.findById(customerId);
//
//        if (maybeCar.isPresent() && maybeCustomer.isPresent()) {
//            maybeCar.get().addOrder(maybeCustomer.get());
//            studentRepository.save(maybeStudent.get());
//            courseRepository.save(maybeCourse.get());
//            return true;
//        } else {
//            return false;
//        }
//    }
}
