package com.ioproject.reservetheweather.service;

import com.ioproject.reservetheweather.auth.*;
import com.ioproject.reservetheweather.model.User;

import com.ioproject.reservetheweather.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public User signup(SignUpRequest signUpRequest){
        User user = new User();
        user.setMail(signUpRequest.getMail());
        user.setName(signUpRequest.getName());
        user.setPhoneNumber(signUpRequest.getPhoneNumber());
        user.setRoles("USER");

        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        return userRepository.save(user);
    }

    public JwtAuthenticationResponse signin(SignInRequest signInRequest){

        var user = userRepository.findUserByName(signInRequest.getName()).orElseThrow(
                ()->new IllegalArgumentException("Nieprawidłowy login lub hasło."));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        System.out.println(user.getName());
        return jwtAuthenticationResponse;
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest request){
        String userName = jwtService.extractUserName(request.getToken());
        User user = userRepository.findUserByName(userName).orElseThrow();
        if(jwtService.isTokenValid(request.getToken(), user)){
            var jwt = jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(request.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }

}
