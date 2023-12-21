package com.ioproject.reservetheweather.service;
import com.ioproject.reservetheweather.auth.*;
import com.ioproject.reservetheweather.model.User;
import com.ioproject.reservetheweather.model.UserDto;
import com.ioproject.reservetheweather.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;


/**
 * Serwis do autentykacji użytkowników.
 * Odpowiada za zarządzanie procesem rejestracji, logowania oraz odświeżania tokenów JWT.
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    /**
     * Rejestruje nowego użytkownika w systemie.
     * @param signUpRequest Żądanie rejestracji zawierające dane użytkownika.
     * @return Zapisany obiekt użytkownika.
     */
    public User signup(SignUpRequest signUpRequest){
        User user = new User();
        user.setMail(signUpRequest.getMail());
        user.setName(signUpRequest.getName());
        user.setPhoneNumber(signUpRequest.getPhoneNumber());
        user.setRoles("USER");

        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        return userRepository.save(user);
    }


    /**
     * Loguje użytkownika i generuje token JWT.
     * @param signInRequest Żądanie logowania zawierające dane uwierzytelniające.
     * @return Odpowiedź zawierająca token JWT i odświeżony token.
     */
    public JwtAuthenticationResponse signin(SignInRequest signInRequest){

        var user = userRepository.findUserByName(signInRequest.getName()).orElseThrow(
                ()->new IllegalArgumentException("Nieprawidłowy login lub hasło."));

        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        jwtAuthenticationResponse.setRole(user.getRoles());

        System.out.println(user.getName());
        return jwtAuthenticationResponse;
    }


    /**
     * Odświeża token JWT.
     * @param request Żądanie z aktualnym tokenem JWT.
     * @return Odpowiedź zawierająca nowy token JWT.
     */
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



    /**
     * Pobiera informacje o zalogowanym użytkowniku.
     * @return Informacje o użytkowniku przekonwertowane do klasy UserDto.
     */
    public UserDto getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userRepository.findUserByName(userDetails.getUsername()).orElse(null);
            if (user != null) {
                String role = user.getRoles();
                return convertToDTO(user, role);
            }
        }
        return null;
    }

    /**
     * Konwertuje obiekt użytkownika User na obiekt klasy UserDto.
     * @param user Obiekt użytkownika User.
     * @param role Rola użytkownika.
     * @return obiekt UserDto.
     */
    public UserDto convertToDTO(User user, String role) {
        UserDto dto = new UserDto();
        dto.setName(user.getName());
        dto.setMail(user.getMail());
        dto.setRole(role);
        return dto;
    }


}
