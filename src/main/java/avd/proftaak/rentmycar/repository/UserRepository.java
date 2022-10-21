package avd.proftaak.rentmycar.repository;

import avd.proftaak.rentmycar.domain.Car;
import avd.proftaak.rentmycar.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> user = new ArrayList<>();

    List<User> findUserByFirstnameAndLastnameIgnoreCase(String firstname, String lastname);
    List<User> findUserByEmailIgnoreCase(String email);

}
