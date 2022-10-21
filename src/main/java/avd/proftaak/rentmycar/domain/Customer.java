package avd.proftaak.rentmycar.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;

@Slf4j
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer extends User{

    public Customer(String firstname, String lastname, String email, String password, String phoneNumber) {
        super(firstname, lastname, email, password, phoneNumber);
    }
}
