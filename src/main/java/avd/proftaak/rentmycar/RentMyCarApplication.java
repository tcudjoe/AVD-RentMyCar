package avd.proftaak.rentmycar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories
public class RentMyCarApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentMyCarApplication.class, args);
    }

}
