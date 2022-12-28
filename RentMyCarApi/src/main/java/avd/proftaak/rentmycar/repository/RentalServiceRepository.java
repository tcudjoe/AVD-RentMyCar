package avd.proftaak.rentmycar.repository;

import avd.proftaak.rentmycar.domain.RentalService;
import avd.proftaak.rentmycar.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface RentalServiceRepository extends JpaRepository<RentalService, Long> {
    List<RentalService> rentalServices = new ArrayList<>();
}
