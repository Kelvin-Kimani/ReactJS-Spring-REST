package com.spring_rest.Services;

import com.spring_rest.Entities.User;
import com.spring_rest.Exceptions.BadRequestException;
import com.spring_rest.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;

    //Create User
    public void createUser(User user){
        Optional<User> userByEmailAddress = userRepository.findUserByEmailAddress(user.getEmailAddress());
        if (userByEmailAddress.isPresent()){
            throw new IllegalStateException("User with the email " +userByEmailAddress.get().getEmailAddress() + " exists");
        }
        userRepository.save(user);
    }

    //Read Users
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id){
        boolean userExists = userRepository.existsById(Math.toIntExact(id));
        if (userExists){
            return userRepository.findUserById(id);
        } else throw new BadRequestException("User with id " + id + " doesn't exist");
    }

    //Delete User
    public void deleteUserById(Long id){
        Optional<User> userExists = userRepository.findUserById(id);
        if (userExists.isPresent()){
            userRepository.deleteById(Math.toIntExact(id));
        } else throw new BadRequestException("User with id " + id + " doesn't exist");

    }

    public void deleteUserByEmailAddress(String emailAddress){
        Optional<User> userByEmailAddress = userRepository.findUserByEmailAddress(emailAddress);
        if (userByEmailAddress.isPresent()){
            userRepository.deleteUserByEmailAddress(emailAddress);
        } else throw new BadRequestException("User with email " + emailAddress + " doesn't exist");
    }
}
