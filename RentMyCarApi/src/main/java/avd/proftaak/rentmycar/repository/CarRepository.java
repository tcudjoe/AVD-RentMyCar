package avd.proftaak.rentmycar.repository;

import avd.proftaak.rentmycar.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public interface CarRepository extends JpaRepository<Car, Long> {
    public List<Car> cars = new ArrayList<>();

    @Query("SELECT c FROM Car c WHERE c.kilometers IS NULL OR c.kilometers <= ?1 AND c.cost IS NULL OR c.cost <= ?2")
    List<Car> customFindCars(Integer maxKilometers, Double maxCost);

    List<Car> findCarByModelIgnoreCase(String model);
    List<Car> findCarByBrandIgnoreCase(String brand);
    List<Car> findCarByKilometersContaining(Integer kilometers);
}
