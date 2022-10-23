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
    public RentalService(String firstname, String lastname, String email, String password, String phonenumber/*, Set<Car> car, Set<Cost> cost*/) {
        super(firstname, lastname, email, password, phonenumber);
//        this.car = car;
//        this.cost = cost;
    }

//    public RentalService(/*Set<Cost> cost,*/ Set<Car> car) {
////        this.cost = cost;
//        this.car = car;
//    }

//    @OneToMany(mappedBy = "costId")
//    private Set<Cost> cost;

//    @OneToMany(mappedBy = "id")
//    private Set<Car> car;

}
