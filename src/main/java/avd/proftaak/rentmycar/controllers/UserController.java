package avd.proftaak.rentmycar.controllers;

import avd.proftaak.rentmycar.domain.Car;
import avd.proftaak.rentmycar.domain.User;
import avd.proftaak.rentmycar.repository.CarRepository;
import avd.proftaak.rentmycar.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/Users")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/Users/{id}")
    public Optional<User> getById(@PathVariable Long id){
        return userRepository.findById(id);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll(@RequestParam(required = false) String firstname){
        List<User> found = new ArrayList<>();
        if (firstname == null){
            found.addAll(userRepository.findAll());
        }else {
            found.addAll(userRepository.findUserByFirstnameIgnoreCase(firstname));
        }

        if (found.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(found);
    }

    @GetMapping("/Users/{email}")
    public ResponseEntity<List<User>> getByEmail(@PathVariable String email){
        List<User> found = new ArrayList<>();

        if (email == null){
            found.addAll(userRepository.findAll());
        }else {
            found.addAll(userRepository.findUserByEmailIgnoreCase(email));
        }

        if (found.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(found);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User newUser){
        try{
            User user = userRepository.save(newUser);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            log.info("Message: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
