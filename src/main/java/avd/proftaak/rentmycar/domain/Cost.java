package avd.proftaak.rentmycar.domain;

import avd.proftaak.rentmycar.FuelType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Cost
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long costId;

    private FuelType type;
    
    @ManyToOne
    @JoinColumn(name="id", nullable=false)
    private RentalService rentalService;
}
