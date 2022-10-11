package avd.proftaak.rentmycar.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Customer extends User
{
    @OneToMany
    private Set<CarOrder> orders;

    @Override
    public String GetDescription() {
        return getFirstname() + " " + getLastname() + " i am a customer";
    }
}
