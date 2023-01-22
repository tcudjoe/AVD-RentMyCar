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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

//Dummydate works fine, alles komt er bij in bij de database
@Slf4j
@Configuration
public class DummyData {
    @Bean
    CommandLineRunner initDatabase(CarRepository carRepository, UserRepository userRepository, OrderRepository orderRepository) {
        return args -> {

            Car car1 = Car.getInstance("330i", "BMW", 2012, 220000, 1499.00, CarCategories.ICE, "somewhere", 9859.99);
            Car car2 = Car.getInstance("XC60", "VOLVO", 2019, 95034, 1799.00, CarCategories.BEV, "somewhere", 59999.00);
            Car car3 = Car.getInstance("GTI", "VOLKSWAGEN", 2020, 55908, 1299.00, CarCategories.FCEV, "somewhere", 20);

            log.info("Preloading... " + carRepository.save(car1));
            log.info("Preloading... " + carRepository.save(car2));
            log.info("Preloading... " + carRepository.save(car3));

            Customer customer1 = new Customer("Tonny", "Klas", "tonnyklas@gmail.com", "password", "0612345678");
            Customer customer2 = new Customer("Enerd", "Jern", "enerdjernd@gmail.com", "password", "0612345678");
            Customer customer3 = new Customer("Mert", "Bras", "mertbras@gmail.com", "password", "0612345678");

            log.info("Preloading... " + userRepository.save(customer1));
            log.info("Preloading... " + userRepository.save(customer2));
            log.info("Preloading... " + userRepository.save(customer3));

            RentalService rentalService1 = new RentalService("Jeremy", "Nasda", "jeremynasda@gmail.com", "password1", "0612345678");
            RentalService rentalService2 = new RentalService("Mason", "Posden", "masonposden@gmail.com", "password2", "0612345678");
            RentalService rentalService3 = new RentalService("Rene", "Slesny", "reneslesny@gmail.com", "password3", "0612345678");

            log.info("Preloading... " + userRepository.save(rentalService1));
            log.info("Preloading... " + userRepository.save(rentalService2));
            log.info("Preloading... " + userRepository.save(rentalService3));

            Order order1 = new Order(car1, customer1, rentalService1, "test 1", LocalDateTime.of(2022, Month.OCTOBER,24, 13, 00, 00), LocalDateTime.of(2022, Month.OCTOBER, 25, 12, 00, 00));
            Order order2 = new Order(car2, customer2, rentalService2,"test 2", LocalDateTime.of(2022, Month.OCTOBER,24, 13, 00, 00), LocalDateTime.of(2022, Month.OCTOBER, 26, 12, 00, 00));
            Order order3 = new Order(car3, customer3, rentalService3,"test 3", LocalDateTime.of(2022, Month.OCTOBER,24, 13, 00, 00), LocalDateTime.of(2022, Month.OCTOBER, 27, 12, 00, 00));

            log.info("Preloading... " + orderRepository.save(order1));
            log.info("Preloading... " + orderRepository.save(order2));
            log.info("Preloading... " + orderRepository.save(order3));

        };
    }
}
