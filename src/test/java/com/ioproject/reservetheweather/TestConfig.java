package com.ioproject.reservetheweather;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@TestConfiguration
public class TestConfig {

    @Bean
    public MockMvc mockMvc(){
        return MockMvcBuilders.standaloneSetup().build();
    }

}


