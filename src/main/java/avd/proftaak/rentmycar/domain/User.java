package avd.proftaak.rentmycar.domain;

import avd.proftaak.rentmycar.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public abstract class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Integer phonenumber;
    private UserType type;

    public User(UserType type)
    {
        this.type = type;
    }

    public abstract String GetDescription();
}
