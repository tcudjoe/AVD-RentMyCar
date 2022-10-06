package avd.proftaak.rentmycar.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Slf4j
@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Integer phonenumber;
}
