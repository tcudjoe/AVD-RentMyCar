package avd.proftaak.rentmycar.controllers;

import avd.proftaak.rentmycar.domain.Car;
import avd.proftaak.rentmycar.domain.Order;
import avd.proftaak.rentmycar.domain.User;
import avd.proftaak.rentmycar.repository.OrderRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/Orders")
public class OrderController {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @GetMapping("/{id}")
    public Optional<Order> getById(@PathVariable Long id){
        return orderRepository.findById(id);
    }

    //Getmapping has a bug, kijk even of je dat kan fixen
    @GetMapping
    public ResponseEntity<List<Order>> getAll(@RequestParam(required = false) Long orderId){
        List<Order> found = new ArrayList<>();
        if (orderId == 0){
            found.addAll(orderRepository.findAll());
        }else {
            found.addAll(orderRepository.findOrderByCustomerId(Math.toIntExact(orderId)));
        }

        if (found.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(found);
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody Order newOrder){
        try{
            Order order = orderRepository.save(newOrder);
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            log.info("Message: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
