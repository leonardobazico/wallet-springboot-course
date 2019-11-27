package com.wallet.repository;

import com.wallet.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void testSave() {
        User user = new User();

        user.setName("Some User");
        user.setPassword("some-password");
        user.setEmail("some@email.com");

        User savedUser = userRepository.save(user);

        assertNotNull(savedUser);
    }

    @Test
    void testFindByEmail() {
        User user = new User();

        user.setName("Some User");
        user.setPassword("some-password");
        user.setEmail("some@email.com");

        User savedUser = userRepository.save(user);

        Optional<User> foundUser = userRepository.findByEmailEquals("some@email.com");

        assertTrue(foundUser.isPresent());
        assertEquals(foundUser.get().getEmail(), "some@email.com");
    }
}
