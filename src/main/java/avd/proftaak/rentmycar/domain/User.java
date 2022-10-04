package avd.proftaak.rentmycar.domain;

import lombok.extern.slf4j.Slf4j;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Slf4j
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
    private boolean isVerhuurder;
}
