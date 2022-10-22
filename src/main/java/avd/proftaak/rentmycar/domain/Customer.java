package avd.proftaak.rentmycar.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Slf4j
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer extends User{


    public Customer(String firstname, String lastname, String email, String password, String phonenumber) {
        super(firstname, lastname, email, password, phonenumber);
    }

    @OneToMany
    Set<Order> orders;
}
