package com.ioproject.reservetheweather;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Profile("test")
public class TestConfig{




    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER");
    }
    protected DefaultSecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api", "/api/register", "/api/events/all",
                                "/api/auth/signin", "/api/auth/checkLogged", "/api/auth/signup",
                                "/Main", "/AdminPanel", "/api/events/all", "/Account", "/api/**",
                                "/api/user/myEventsOnDay", "/api/user/allEventsOnDay", "/api/user/allEventsOnDay?date=&name=?", "/api/user/allEventsOnDay**",
                                "/api/user/myEventsOnDay?date=", "/api/user/events/signup?eventid=&name=", "/api/user/events/signup?eventid=1&name=123",
                                "/Reservations","/AdminPanel**",
                                "/api/admin/addEvent",
                                "/api/admin/removeEvent").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(withDefaults())
                .formLogin(withDefaults())
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
