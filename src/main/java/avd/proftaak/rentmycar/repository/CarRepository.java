package avd.proftaak.rentmycar.repository;

import avd.proftaak.rentmycar.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public interface CarRepository extends JpaRepository<Car, Long> {
    public List<Car> cars = new ArrayList<>();

    List<Car> findCarByModelIgnoreCase(String model);
    List<Car> findCarByBrand(String brand);
    List<Car> findCarByKilometersContaining(Integer kilometers);
}
