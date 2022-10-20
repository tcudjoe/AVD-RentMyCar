package avd.proftaak.rentmycar;

import avd.proftaak.rentmycar.domain.Car;
import avd.proftaak.rentmycar.domain.Customer;
import avd.proftaak.rentmycar.domain.RentalService;
import avd.proftaak.rentmycar.domain.User;
import avd.proftaak.rentmycar.repository.CarRepository;
import avd.proftaak.rentmycar.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class DummyDataUser {
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, CarRepository carRepository){
        return args -> {
            User jenny = new Customer( "Jenny", "butter", "jennybutter@gmail.com", "password", "06123456787");
            User derek = new Customer( "Derek", "denner", "derekdenner@gmail.com", "password", "06123456787");
            User tommy = new Customer( "Tommy", "jason", "tommyjason@gmail.com", "password", "06123456787");

            log.info("Preloading " + userRepository.save(jenny));
            log.info("Preloading " + userRepository.save(derek));
            log.info("Preloading " + userRepository.save(tommy));

            Car car1 = new Car(1L, "BMW", "320i", 2016, 170000, 5, 1740.00, FuelType.Gasoline, 5, "somewhere");
            Car car2 = new Car(2L, "VOLKSWAGEN", "Golf GTI", 2012, 220987, 3, 1240.00, FuelType.Gasoline, 5, "somewhere");
            Car car3 = new Car(3L, "VOLVO", "XC60", 2019, 95003, 5, 1940.00, FuelType.Gasoline, 7, "somewhere");

            log.info("Preloading " + carRepository.save(car1));
            log.info("Preloading " + carRepository.save(car2));
            log.info("Preloading " + carRepository.save(car3));

            User terry = new RentalService( "Jenny", "butter", "jennybutter@gmail.com", "password", "06123456787");
            User archer = new RentalService( "Derek", "denner", "derekdenner@gmail.com", "password", "06123456787");
            User mike = new RentalService( "Tommy", "jason", "tommyjason@gmail.com", "password", "06123456787");

            log.info("Preloading " + userRepository.save(terry));
            log.info("Preloading " + userRepository.save(archer));
            log.info("Preloading " + userRepository.save(mike));
        };
    }
}
