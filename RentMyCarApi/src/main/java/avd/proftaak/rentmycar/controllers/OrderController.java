package avd.proftaak.rentmycar.controllers;

import avd.proftaak.rentmycar.controllers.dto.Order;
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
@RequestMapping("/orders")
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

    //Gets all cars based on description
    @GetMapping
    public ResponseEntity<List<Order>> getAll(@RequestParam(required = false) String description){
        List<Order> found = new ArrayList<>();
        if (description == null){
            found.addAll(orderRepository.findAll());
        }else {
            found.addAll(orderRepository.findOrderByDescriptionIgnoreCase(description));
        }

        if (found.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(found);
    }

    //Creates new order
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
