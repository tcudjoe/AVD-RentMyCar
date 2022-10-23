package avd.proftaak.rentmycar.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class CarOrder
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long carOrderId;

    @ManyToOne
    @JoinColumn(name="carId", nullable=false)
    private Car car;

    @ManyToOne
    @JoinColumn(name="id", nullable=false)
    private Customer customer;

    public CarOrder(Car car, Customer customer){
        this.car = car;
        this.customer = customer;
    }
}
