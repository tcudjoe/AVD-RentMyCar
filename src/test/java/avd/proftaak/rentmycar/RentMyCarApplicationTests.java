package avd.proftaak.rentmycar;

import avd.proftaak.rentmycar.domain.Car;
import avd.proftaak.rentmycar.domain.Customer;
import avd.proftaak.rentmycar.domain.RentalService;
import avd.proftaak.rentmycar.domain.User;
import avd.proftaak.rentmycar.repository.CarRepository;
import avd.proftaak.rentmycar.repository.UserRepository;
import avd.proftaak.rentmycar.services.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class RentMyCarApplicationTests {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarService carService;

    Car car1 = new Car("330i", "BMW", 2012, 220000, 5, 1499.00, CarCategories.ICE, 5, "somewhere");
    Car car2 = new Car("XC60", "VOLVO", 2019, 95034, 5, 1799.00, CarCategories.BEV, 7, "somewhere");

    Customer customer1 = new Customer("Tonny", "Klas", "test@gmail.com", "password", "0612345678");

    RentalService rentalService1 = new RentalService("Jeremy", "Nasda", "jeremynasda@gmail.com", "password", "0612345678"/*, new HashSet<>(1), new HashSet<>(6000)*/);

    @Test
    public void whenDeleteByIdFromRepository_thenDeletingShouldBeSuccessful() {
        // arrange
        long countBefore = carRepository.count();
        carRepository.save(car1);
        carRepository.save(car2);

        //act
        carRepository.deleteById(car1.getCarId());

        //assert
        assertThat(carRepository.count()).isEqualTo(countBefore + 1);
    }
    @Test
    public void addCarToRentalServiceShouldBeSuccesFul() {
        // arrange
        carRepository.save(car1);
        userRepository.save(rentalService1);

        //act
        boolean result = carService.addCar(rentalService1.getId(), car1.getCarId());

        //assert
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void getCustomerByEmailAddress() {
        // arrange
        userRepository.save(customer1);

        //act
        List<User> customers = userRepository.findUserByEmailIgnoreCase(customer1.getEmail());

        //assert
        assertThat(customers.size()).isEqualTo(1);
    }
}
