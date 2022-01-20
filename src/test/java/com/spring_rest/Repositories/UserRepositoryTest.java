package com.spring_rest.Repositories;

import com.spring_rest.Entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void findUserById() {
        //given
        User user = new User(
                1L,
                "Kelvin",
                "Kimani",
                "kelvinkim996@gmail.com"
        );

        userRepository.save(user);

        //when
        Optional<User> userById = userRepository.findUserById(user.getUserId());

        //then
        assertThat(user.getEmailAddress()).isNotEmpty();
        assertThat(userById).isNotNull();

    }

    @Test
    void findUserByEmailAddress() {
        //given
        User user = new User(
                1L,
                "Kelvin",
                "Kimani",
                "kelvinkim996@gmail.com"
        );

        userRepository.save(user);

        //when
        Optional<User> userByEmailAddress = userRepository.findUserByEmailAddress(user.getEmailAddress());

        //then
        assertThat(user.getEmailAddress()).isNotEmpty();
        assertThat(userByEmailAddress).isNotNull();


    }

    @Test
    void deleteUserByEmailAddress() {

        //given
        User user = new User(
                1L,
                "Kelvin",
                "Kimani",
                "kelvinkim996@gmail.com"
        );

        userRepository.save(user);

        //when
        userRepository.deleteUserByEmailAddress(user.getEmailAddress());

        //then
        assertThat(userRepository.findUserByEmailAddress(user.getEmailAddress())).isEmpty();
    }
}