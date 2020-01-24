package com.tim04.school.trivia;

import com.tim04.school.trivia.persistence.user.UserDto;
import com.tim04.school.trivia.persistence.user.UserEntity;
import com.tim04.school.trivia.persistence.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class LoginControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserRepository userRepository;
    @Autowired
    private JacksonTester<UserDto> userDtoJson; // use to generate the request in Json format


    public void preConditions() {
        UserEntity user = new UserEntity();
        user.setUser_id((long) 1);
        user.setUsername("username");
        user.setPassword(new BCryptPasswordEncoder().encode("password"));

        given(userRepository.findByUsername(any())).willReturn(user);
    }

    @Test
    public void createAuthenticationToken() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUsername("username");
        userDto.setPassword("password");

        mvc.perform(
                post(new URI("/login"))
                        .content(userDtoJson.write(userDto).getJson())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

    }
}
