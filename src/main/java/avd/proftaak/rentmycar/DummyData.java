package avd.proftaak.rentmycar;

import avd.proftaak.rentmycar.domain.Car;
import avd.proftaak.rentmycar.domain.User;
import avd.proftaak.rentmycar.repository.CarRepository;
import avd.proftaak.rentmycar.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class DummyData {
    @Bean
    CommandLineRunner initDatabase(CarRepository carRepository, UserRepository userRepository) {
        return args -> {
            Car car1 = new Car("330i", "BMW", 2012, 220000, 5, 1499.00, CarCategories.ICE, 5, "somewhere");
            Car car2 = new Car("XC60", "VOLVO", 2019, 95034, 5, 1799.00, CarCategories.BEV, 7, "somewhere");
            Car car3 = new Car("GTI", "VOLKSWAGEN", 2020, 55908, 3, 1299.00, CarCategories.FCEV, 5, "somewhere");

            log.info("Preloading... " + carRepository.save(car1));
            log.info("Preloading... " + carRepository.save(car2));
            log.info("Preloading... " + carRepository.save(car3));

            User user1 = new User("Tonny", "Klas", "tonnyklas@gmail.com", "password", "0612345678");
//            User user2 = new User("Enerd", "Jern", "enerdjernd@gmail.com", "password", "0612345678");
//            User user3 = new User("Mert", "Bras", "mertbras@gmail.com", "password", "0612345678");
//
            log.info("Preloading... " + userRepository.save(user1));
//            log.info("Preloading... " + userRepository.save(user2));
//            log.info("Preloading... " + userRepository.save(user3));
        };
    }
}
