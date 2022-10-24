package avd.proftaak.rentmycar.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;

@Slf4j
@Entity
@Getter
@Setter
@NoArgsConstructor
public class RentalService extends User{
    public RentalService(String firstname, String lastname, String email, String password, String phonenumber) {
        super(firstname, lastname, email, password, phonenumber);
    }

    @OneToMany(mappedBy = "costId")
    private Set<Cost> costs;

    @OneToMany(mappedBy = "id")
    private Set<Car> cars;

    public void AddCar(Car car)
    {
        this.cars.add(car);
    }
}
