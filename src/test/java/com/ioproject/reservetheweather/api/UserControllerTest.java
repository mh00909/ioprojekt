package com.ioproject.reservetheweather.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ioproject.reservetheweather.TestConfig;
import com.ioproject.reservetheweather.entity.User;
import com.ioproject.reservetheweather.repository.UserRepository;
import com.ioproject.reservetheweather.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
class UserControllerTest {

    @Autowired
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private  UserRepository userRepository;
    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void hello() {

    }

    @Test
    void testSaveUser_SuccessfulRegistration() throws Exception {
        User user = new User(15L,"Adam Wojeciechowski",
                "u@wp.pl", "123", 12345, "USER");


        ObjectMapper objectMapper = new ObjectMapper();

       Mockito.when(userRepository.save(user)).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/register")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Użytkownik zarejestrowany."));
    }

    @Test
    void getAllEvents() {
    }

    @Test
    void showEventDescription() {
    }

    @Test
    void signUpForEvent() {
    }

    @Test
    void addEvent() {
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void getMyDetails() {
    }

    @Test
    void showMyEvents() {
    }

    @Test
    void checkWeather() {
    }

    @Test
    void resignEvent() {
    }

    @Test
    void discountEvent() {
    }

    @Test
    void getLoggedIn() {
    }
    private String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}