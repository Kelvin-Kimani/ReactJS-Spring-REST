package com.spring_rest.Controller;

import com.spring_rest.Entities.User;
import com.spring_rest.Exceptions.ResourceNotFound;
import com.spring_rest.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/")
public class ProjectController {

    private UserRepository userRepository;

    @Autowired
    public ProjectController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    //get all users
    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }


    //create user
    @PostMapping("/create_user")
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }

    //get user by id
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable() Long id){
        User user = userRepository.findUserById(id)
                .orElseThrow(()-> new ResourceNotFound("User with id " + id + " does not exist."));

        return ResponseEntity.ok(user);
    }

    //update user
    @PutMapping("/users/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User userDetails){

        User user = userRepository.findUserById(userId)
                .orElseThrow(()-> new ResourceNotFound("User with id " + userId + " does not exist."));

        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmailAddress(userDetails.getEmailAddress());

        User updatedUser = userRepository.save(user);

        return ResponseEntity.ok(updatedUser);
    }

    //delete user rest api
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long userId){

        User user = userRepository.findUserById(userId)
                .orElseThrow(()-> new ResourceNotFound("User with id " + userId + " does not exist."));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);

        return ResponseEntity.ok(response);
    }
}
