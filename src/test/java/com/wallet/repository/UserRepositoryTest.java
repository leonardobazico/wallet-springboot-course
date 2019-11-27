package com.wallet.repository;

import com.wallet.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    void testSave() {
        User user = new User();

        user.setName("Some User");
        user.setPassword("some-password");
        user.setEmail("some@email.com");

        User savedUser = userRepository.save(user);

        assertNotNull(savedUser);
    }
}
