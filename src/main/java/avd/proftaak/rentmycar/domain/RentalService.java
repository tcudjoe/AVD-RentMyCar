package avd.proftaak.rentmycar.domain;

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
    @OneToMany(cascade=ALL, mappedBy="costId")
    private Set<Cost> costs;

    @OneToMany(cascade=ALL, mappedBy="carId")
    private Set<Car> cars;

    @Override
    public String GetDescription() {
        return getFirstname() + " " + getLastname() + " i am a rental service";
    }
}
