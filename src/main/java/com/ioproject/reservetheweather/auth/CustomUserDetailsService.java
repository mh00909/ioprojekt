package com.ioproject.reservetheweather.auth;

import com.ioproject.reservetheweather.model.Account;
import com.ioproject.reservetheweather.model.Role;
import com.ioproject.reservetheweather.model.User;
import com.ioproject.reservetheweather.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user1= userRepository.findUserByName(username);
        if (!user1.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = user1.get();
        String userRole = user.getRoles();
        GrantedAuthority authority = new SimpleGrantedAuthority(userRole);

        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                Collections.singletonList(authority)
        );
    }
}
