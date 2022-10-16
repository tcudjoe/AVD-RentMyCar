package avd.proftaak.rentmycar.domain;

import avd.proftaak.rentmycar.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Customer extends User
{
    public static UserType TYPE = UserType.Customer;

    @OneToMany
    private Set<CarOrder> orders;

    @Override
    public String GetDescription() {
        return getFirstname() + " " + getLastname() + " i am a customer";
    }
}
