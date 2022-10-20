package avd.proftaak.rentmycar.domain;

import avd.proftaak.rentmycar.UserType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType = DiscriminatorType.INTEGER)
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, property = "userType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Customer.class, name="CUSTOMER"),
        @JsonSubTypes.Type(value = RentalService.class, name = "RENTALSERVICE")
})
public abstract class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId", insertable = false, updatable = false)
    Long id;

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phonenumber;

    public User(String firstname, String lastname, String email, String password, String phonenumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.phonenumber = phonenumber;
    }

    public abstract String GetDescription();
}