package avd.proftaak.rentmycar.domain;

import avd.proftaak.rentmycar.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class RentalService extends User
{
    public static final UserType TYPE = UserType.RentalService;

    @OneToMany(cascade=ALL, mappedBy="costId")
    private Set<Cost> costs;
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy="carId",fetch = FetchType.LAZY)
    private Set<Car> cars;

    public RentalService(Set<Cost> costs, Set<Car> cars) {
        this.costs = costs;
        this.cars = cars;
    }

    public RentalService(String firstname, String lastname, String email, String password, String phonenumber) {
        super(firstname, lastname, email, password, phonenumber);
    }

    @Override
    public String GetDescription() {
        return getFirstname() + " " + getLastname() + " i am a rental service";
    }
}
