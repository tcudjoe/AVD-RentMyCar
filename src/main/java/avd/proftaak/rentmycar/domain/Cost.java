package avd.proftaak.rentmycar.domain;

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
    Long costId;

    @ManyToOne
    @JoinColumn(name="costId", nullable=false)
    private RentalService rentalService;
}
