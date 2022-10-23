package avd.proftaak.rentmycar;

import avd.proftaak.rentmycar.controllers.dto.Order;
import avd.proftaak.rentmycar.domain.*;
import avd.proftaak.rentmycar.repository.CarRepository;
import avd.proftaak.rentmycar.repository.OrderRepository;
import avd.proftaak.rentmycar.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Dummydate works fine, alles komt er bij in bij de database
@Slf4j
@Configuration
public class DummyData {
    @Bean
    CommandLineRunner initDatabase(CarRepository carRepository, UserRepository userRepository, OrderRepository orderRepository) {
        return args -> {
            Car car1 = new Car("330i", "BMW", 2012, 220000, 5, 1499.00, CarCategories.ICE, 5, "somewhere");
            Car car2 = new Car("XC60", "VOLVO", 2019, 95034, 5, 1799.00, CarCategories.BEV, 7, "somewhere");
            Car car3 = new Car("GTI", "VOLKSWAGEN", 2020, 55908, 3, 1299.00, CarCategories.FCEV, 5, "somewhere");

            log.info("Preloading... " + carRepository.save(car1));
            log.info("Preloading... " + carRepository.save(car2));
            log.info("Preloading... " + carRepository.save(car3));

            Customer customer1 = new Customer("Tonny", "Klas", "tonnyklas@gmail.com", "password", "0612345678");
            Customer customer2 = new Customer("Enerd", "Jern", "enerdjernd@gmail.com", "password", "0612345678");
            Customer customer3 = new Customer("Mert", "Bras", "mertbras@gmail.com", "password", "0612345678");

            log.info("Preloading... " + userRepository.save(customer1));
            log.info("Preloading... " + userRepository.save(customer2));
            log.info("Preloading... " + userRepository.save(customer3));

            RentalService rentalService1 = new RentalService("Jeremy", "Nasda", "jeremynasda@gmail.com", "password", "0612345678"/*, new HashSet<>(1), new HashSet<>(6000)*/);
            RentalService rentalService2 = new RentalService("Mason", "Posden", "masonposden@gmail.com", "password", "0612345678"/*, new HashSet<>(2), new HashSet<>(7000)*/);
            RentalService rentalService3 = new RentalService("Rene", "Slesny", "reneslesny@gmail.com", "password", "0612345678"/*, new HashSet<>(3), new HashSet<>(8000)*/);

            log.info("Preloading... " + userRepository.save(rentalService1));
            log.info("Preloading... " + userRepository.save(rentalService2));
            log.info("Preloading... " + userRepository.save(rentalService3));

            Order order1 = new Order(car1, customer1, "test 1");
            Order order2 = new Order(car2, customer2, "test 2");
            Order order3 = new Order(car3, customer3, "test 3");

            log.info("Preloading... " + orderRepository.save(order1));
            log.info("Preloading... " + orderRepository.save(order2));
            log.info("Preloading... " + orderRepository.save(order3));

        };
    }
}
