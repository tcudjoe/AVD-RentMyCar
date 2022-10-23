//package avd.proftaak.rentmycar.domain;
//
//import avd.proftaak.rentmycar.CarCategories;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//
//import javax.persistence.*;
//
//@Slf4j
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//public class Cost {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "costId", nullable = false)
//    private Long costId;
//
//    private CarCategories carCategories;
//    private double cost;
//
//    @ManyToOne
//    @JoinColumn(name = "rentalserviceId", nullable = false)
//    private RentalService rentalService;
//
//    public Cost(CarCategories carCategories, double cost, RentalService rentalService) {
//        this.carCategories = carCategories;
//        this.cost = cost;
//        this.rentalService = rentalService;
//    }
//}
