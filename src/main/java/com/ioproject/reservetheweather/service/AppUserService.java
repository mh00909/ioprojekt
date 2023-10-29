package com.ioproject.reservetheweather.service;

import com.ioproject.reservetheweather.registration.AppUser;
import com.ioproject.reservetheweather.entity.User;
import com.ioproject.reservetheweather.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
@Configuration
public class AppUserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByMail(username);
        return user.map(AppUser::new).orElseThrow(() -> new UsernameNotFoundException("użytkownik nie istnieje"));
    }
}
