package com.ioproject.reservetheweather.api;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ioproject.reservetheweather.entity.User;
import com.ioproject.reservetheweather.registration.SecurityConfig;
import com.ioproject.reservetheweather.repository.EventRepository;
import com.ioproject.reservetheweather.repository.UserRepository;
import com.ioproject.reservetheweather.service.EventService;
import com.ioproject.reservetheweather.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Autowired private MockMvc mockMvc;
    @MockBean private UserService userService;
    @MockBean private  UserRepository userRepository;

    @Autowired private ObjectMapper objectMapper;

    @MockBean private EventService eventService;
    @MockBean private EventRepository eventRepository;
    @MockBean private SecurityConfig securityConfig;

    private User user;
    private User admin;
    @BeforeEach
    public void init(){
        user = User.builder().name("Jacek Nowak").mail("user123@gmail.com").password("abc").phoneNumber(123456789L).roles("USER").build();
        admin = User.builder().name("Eliza Pancakes").mail("eliza@gmail.com").password("abc").phoneNumber(213131L).roles("ADMIN").build();
    }
    @Test
    public void saveUserTest() throws Exception {

        given(userService.addNewUser(ArgumentMatchers.any(User.class))).willReturn(true);

        ResultActions response = mockMvc.perform(post("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));

        response.andExpect(status().isOk()).andExpect(content().string("Użytkownik zarejestrowany."));

    }
    @Test
    public void saveAdminTest() throws Exception {
        given(userService.addNewUser(ArgumentMatchers.any(User.class))).willReturn(true);
        ResultActions response = mockMvc.perform(post("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(admin)));
        response.andExpect(status().isOk()).andExpect(content().string("Użytkownik zarejestrowany."));

    }



}