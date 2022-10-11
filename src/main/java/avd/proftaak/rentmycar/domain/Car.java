package avd.proftaak.rentmycar.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Car
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long carId;

    @ManyToOne
    @JoinColumn(name="carId", nullable=false)
    private RentalService rentalService;

    @OneToMany
    private Set<CarOrder> orders;

    public Car(RentalService rentalService){
        this.rentalService = rentalService;
    }
}
