package com.spring_rest.Services;

import com.spring_rest.Entities.User;
import com.spring_rest.Exceptions.BadRequestException;
import com.spring_rest.Repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

//Add Mockito
@ExtendWith(MockitoExtension.class)
class UserServiceTest {


    @Mock
    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
    }

    @Test
    void createUser() {
        //given
        User user = new User(
                1L,
                "Kelvin",
                "Kimani",
                "kelvinkim996@gmail.com"
        );

        userService.createUser(user);

        //when
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);

        //then
        //Verify we get the user from the save method in the service class
        verify(userRepository).save(argumentCaptor.capture());

        User capturedUser = argumentCaptor.getValue();

        assertThat(capturedUser).isEqualTo(user);
    }


    @Test
    void willThrowWhenEmailIsTaken() {
        //given
        User user = new User(
                1L,
                "Kelvin",
                "Kimani",
                "kelvinkim996@gmail.com"
        );

        //when
        //we have to tell the condition what to return
        given(userRepository.findUserByEmailAddress(user.getEmailAddress()))
                .willReturn(Optional.ofNullable(user));

        //then

        //Takes lambda function
        assertThatThrownBy(() -> userService.createUser(user))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("User with the email " +user.getEmailAddress() + " exists");

        //Here we verify that the method that comes after the exception is never called
        verify(userRepository, never()).save(any());

    }

    @Test
    void getUsers() {
        //when
        userService.getUsers();

        //then
        //Verify that the repository was invoked using the method findAll
        //Mocking makes it very fast
        verify(userRepository).findAll();
    }

    @Test
    void getUserById() {
        //given
        User user = new User(
                1L,
                "Kelvin",
                "Kimani",
                "kelvinkim996@gmail.com"
        );

        given(userRepository.existsById((int) user.getUserId()))
                .willReturn(true);

        given(userRepository.findUserById(user.getUserId())).willReturn(Optional.of(user));

       //When
       //we invoke the method we want to test
        Optional<User> userById = userService.getUserById(user.getUserId());

        //Then
        assertThat(userById.isPresent()).isTrue();
        userById.get();
    }

    @Test
    void getUserByIdThrowException() {
        //given
        User user = new User(
                1L,
                "Kelvin",
                "Kimani",
                "kelvinkim996@gmail.com"
        );

        //by default, the return will be false
        given(userRepository.existsById((int) user.getUserId()))
                .willReturn(false);


        assertThatThrownBy(() -> userService.getUserById(user.getUserId()))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("User with id " + user.getUserId() + " doesn't exist");

    }

    @Test
    void deleteUserById() {
        //given
        User user = new User(
                1L,
                "Kelvin",
                "Kimani",
                "kelvinkim996@gmail.com"
        );

       //when
       given(userRepository.findUserById(user.getUserId()))
               //what we want it to return
               .willReturn(Optional.of(user));

       //then
       userService.deleteUserById(user.getUserId());

        Optional<User> userById = userRepository.findUserById(user.getUserId());

        assertThat(userById)
                .isEqualTo(Optional.of(user));

    }

    @Test
    void deleteUserByIdWillThrowException() {
        //given
        User user = new User(
                1L,
                "Kelvin",
                "Kimani",
                "kelvinkim996@gmail.com"
        );

        //then
        assertThatThrownBy(() -> userService.deleteUserById(user.getUserId()))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("User with id " + user.getUserId() + " doesn't exist");


    }

    @Test
    void deleteUserByEmailAddress() {
        User user = new User(
                1L,
                "Kelvin",
                "Kimani",
                "kelvinkim996@gmail.com"
        );

        given(userRepository.findUserByEmailAddress(user.getEmailAddress()))
                .willReturn(Optional.of(user));

        userService.deleteUserByEmailAddress(user.getEmailAddress());

        //assert that the method delete is reached, and it is reached if this User object is present
        assertThat(userRepository.findUserByEmailAddress(user.getEmailAddress()))
                .isPresent();

    }


    @Test
    void deleteUserByEmailAddressThrowException() {

        //given
        User user = new User(
                1L,
                "Kelvin",
                "Kimani",
                "kelvinkim996@gmail.com"
        );

        //then
        //Here, the user is by default empty which will go to the else part of the condition
        assertThatThrownBy(() -> userService.deleteUserByEmailAddress(user.getEmailAddress()))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("User with email " + user.getEmailAddress() + " doesn't exist");

    }
}