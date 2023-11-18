package com.ioproject.reservetheweather.service;

import com.ioproject.reservetheweather.model.Account;
import com.ioproject.reservetheweather.model.User;
import com.ioproject.reservetheweather.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
@Configuration
public class AccountService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByName(username);
        return user.map(Account::new).orElseThrow(() -> new UsernameNotFoundException("użytkownik nie istnieje"));
    }
}
