package com.tim04.school.trivia;

import com.tim04.school.trivia.config.JwtProvider;
import com.tim04.school.trivia.persistence.user.UserEntity;
import com.tim04.school.trivia.persistence.repositories.UserRepository;
import com.tim04.school.trivia.service.user.UserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<UserRequest> createUserRequest; // use to generate the request in Json format
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;


    public void preConditions() {
        UserEntity user = new UserEntity();
        user.setUser_id((long) 1);
        user.setUsername("username");
        user.setPassword("password");

        given(userRepository.findByUsername(any())).willReturn(user);
        given(userRepository.findById(any())).willReturn(Optional.of(user));
    }

    @Test
    public void createUser() throws Exception {

        UserRequest createUserRequestDto = new UserRequest();
        createUserRequestDto.setUsername("username");
        createUserRequestDto.setPassword("password");
        createUserRequestDto.setConfirmPassword("password");

        mvc.perform(
                post(new URI("/api/user/create"))
                        .content(createUserRequest.write(createUserRequestDto).getJson())
                        .contentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void findById() throws Exception {
        mvc.perform(
                get(new URI("/api/user/id/1"))
                        .header("Authorization", "Bearer " + jwtProvider.createToken("username"))
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void findByUserName() throws Exception {
        mvc.perform(
                get(new URI("/api/user/username"))
                        .header("Authorization", "Bearer " + jwtProvider.createToken("username"))
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }
}
