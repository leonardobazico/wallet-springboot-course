package com.wallet.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.entity.User;
import com.wallet.service.UserService;
import dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {
    private static final String USERS_ENDPOINT = "/user";
    private static final String SOME_NAME = "Some Name";
    private static final String SOME_PASSWORD = "some-password";
    private static final String SOME_EMAIL = "some@email.com";

    @MockBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testSave() throws Exception {
        given(userService.save(any(User.class))).willReturn(getMockUser());

        mockMvc.perform(
                post(USERS_ENDPOINT)
                        .content(getJsonPayload())
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    private User getMockUser() {
        User user = new User();

        user.setName(SOME_NAME);
        user.setPassword(SOME_PASSWORD);
        user.setEmail(SOME_EMAIL);

        return user;
    }

    private String getJsonPayload() throws JsonProcessingException {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(SOME_NAME);
        userDTO.setPassword(SOME_PASSWORD);
        userDTO.setEmail(SOME_EMAIL);

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(userDTO);
    }
}
