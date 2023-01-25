package avd.proftaak.rentmycar.domain;

import avd.proftaak.rentmycar.controllers.dto.Order;
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
    public Customer(Customer customer) {

    }

    public Customer clone(){
        return new Customer(this);
    }

    @OneToMany
    Set<Order> orders;

    public void AddOrder(Order order)
    {
        this.orders.add(order);
    }

    @Override
    public String Name()
    {
        return String.join(",", "A customer called: ", this.getFirstname(), this.getLastname());
    }
}
