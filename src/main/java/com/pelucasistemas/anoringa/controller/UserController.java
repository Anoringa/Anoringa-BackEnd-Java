package com.pelucasistemas.anoringa.controller;

import com.pelucasistemas.anoringa.aimtools.NameGenerator;
import com.pelucasistemas.anoringa.aimtools.TokenGenerator;
import com.pelucasistemas.anoringa.exception.ResourceNotFoundException;
import com.pelucasistemas.anoringa.aimtools.Validator;
import com.pelucasistemas.anoringa.requestmodels.PostRequest;
import com.pelucasistemas.anoringa.model.user.User;
import com.pelucasistemas.anoringa.requestmodels.UserRequest;
import com.pelucasistemas.anoringa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @PostMapping("/hello")
    public String sayHello() {
        return "Hola";
    }


    @PostMapping("/search")
    public void searchUser(@Valid @RequestBody PostRequest postRequest) throws Exception {


        System.out.println("New Search");
        System.out.println(postRequest.getUsername());

        try {
            User findedUser = userRepository.findByUsername(postRequest.getUsername());

            System.out.println("Username");
            System.out.println(findedUser.getUsername());
            System.out.println("Token");
            System.out.println(findedUser.getPassword());
        } catch (NullPointerException e) {
            System.out.print("Caught the NullPointerException");
        }
    }

    @PostMapping("/auth")
    @CrossOrigin(origins = {"http://localhost:5500", "http://127.0.0.1:5500"})
    public void validateUser(@Valid @RequestBody User user) throws Exception {
        System.out.println("New Request");
        System.out.println(user.getPassword());
        //Validator valideitor = new Validator();
        Validator.sendToValitation(user.getPassword());
    }
    //https://ricardogeek.com/spring-boot-y-la-anotacion-crossorigin/
    @CrossOrigin(origins = {"http://localhost:5500", "http://127.0.0.1:5500","0.0.0.0","http://test.mydomain.com/"})
    @PostMapping("/users")
    public User createUser(@Valid @RequestBody UserRequest userRequest) throws Exception {
        System.out.println("New Request");
        System.out.println(userRequest.getToken());
        //System.out.println(Validator.sendToValitation(user.getToken()));

        //String testeable = "123";
        //Validator.sendToValitation(user.getToken()
        // Validator.sendToValitation(userRequest.getToken())
        if (Validator.sendToValitation(userRequest.getToken())){
            System.out.println("OK");

            User user = new User(NameGenerator.getName(), TokenGenerator.generateNewToken(),"asdasd");

            return userRepository.save(user);
        }
        else {
            System.out.println("KO");
            User usererror = new User("ERROR","ERROR","ERROR");
            return usererror;
        }
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable(value = "id") Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable(value = "id") Long userId,
                                           @Valid @RequestBody User userDetails) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        //user.setUsername(userDetails.getUsername());
        //user.setIp(userDetails.getIp());
        user.setLastActivity();

        User updatedUser = userRepository.save(user);
        return updatedUser;
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        userRepository.delete(user);

        return ResponseEntity.ok().build();
    }



    @PostMapping("/jwtgen")
    @CrossOrigin(origins = {"http://localhost:5500", "http://127.0.0.1:5500"})
    public void generateJWT(@Valid @RequestBody User user) throws Exception {
        System.out.println("New Request");
        System.out.println(user.getPassword());
        String jwttest = user.genJWT();
        System.out.println(jwttest);

        boolean jwtaftertest = user.checkJWT(jwttest);
        System.out.println(jwtaftertest);

        //Validator valideitor = new Validator();
        //Validator.sendToValitation(user.getToken());
    }
}
