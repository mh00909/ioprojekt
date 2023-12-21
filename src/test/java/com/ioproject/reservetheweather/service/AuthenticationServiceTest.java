package com.ioproject.reservetheweather.service;
import com.ioproject.reservetheweather.auth.*;
import com.ioproject.reservetheweather.model.User;
import com.ioproject.reservetheweather.model.UserDto;
import com.ioproject.reservetheweather.repository.EventRepository;
import com.ioproject.reservetheweather.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthenticationService authenticationService;


    // testy signup
    @Test
    void signUpTest_OK() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setName("test123");
        signUpRequest.setMail("test@mail.com");
        signUpRequest.setPassword("password");
        signUpRequest.setPhoneNumber(123456789);

        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        User mockUser = new User();
        mockUser.setName("test123");
        when(userRepository.save(Mockito.<User>any())).thenReturn(mockUser);
        User result = authenticationService.signup(signUpRequest);
        assertEquals("test123", result.getName());
    }


    // testy signin
    @Test
    void signInTest_OK() {
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setName("testUser");
        signInRequest.setPassword("password");

        User testUser = new User();
        testUser.setName("testUser");
        testUser.setRoles("USER");

        when(userRepository.findUserByName("testUser")).thenReturn(Optional.of(testUser));
        when(jwtService.generateToken(Mockito.<UserDetails>any())).thenReturn("jwtToken");
        when(jwtService.generateRefreshToken(anyMap(), Mockito.<UserDetails>any())).thenReturn("refreshToken");

        JwtAuthenticationResponse response = authenticationService.signin(signInRequest);

        assertEquals("jwtToken", response.getToken());
        assertEquals("refreshToken", response.getRefreshToken());
        assertEquals("USER", response.getRole());
    }
    @Test
    void signInTest_wrong() {
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setName("wrongUser");
        signInRequest.setPassword("wrongPassword");

        when(userRepository.findUserByName("wrongUser")).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            authenticationService.signin(signInRequest);
        });
    }


    // testy refreshtoken
    @Test
    void refreshTokenTest_OK() {
        RefreshTokenRequest request = new RefreshTokenRequest();
        request.setToken("oldToken");
        User testUser = new User();
        testUser.setName("testUser");
        when(jwtService.extractUserName("oldToken")).thenReturn("testUser");
        when(userRepository.findUserByName("testUser")).thenReturn(Optional.of(testUser));
        when(jwtService.isTokenValid("oldToken", testUser)).thenReturn(true);
        when(jwtService.generateToken(testUser)).thenReturn("newJwtToken");
        JwtAuthenticationResponse response = authenticationService.refreshToken(request);
        assertNotNull(response);
        assertEquals("newJwtToken", response.getToken());
    }

    // testy getcurrentdetails
    @Test
    void getCurrentUserDetailsTest_OK() {
        User testUser = new User();
        testUser.setName("testUser");
        testUser.setMail("test@mail.com");
        testUser.setRoles("USER");

        UserDetails mockUserDetails = org.springframework.security.core.userdetails.User.builder().username("testUser").password("password").authorities("USER").build();
        Authentication mockAuthentication = new UsernamePasswordAuthenticationToken(mockUserDetails, null, mockUserDetails.getAuthorities());
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);

        when(securityContext.getAuthentication()).thenReturn(mockAuthentication);
        when(userRepository.findUserByName("testUser")).thenReturn(Optional.of(testUser));
        UserDto userDetails = authenticationService.getCurrentUserDetails();
        assertEquals("testUser", userDetails.getName());
        assertEquals("test@mail.com", userDetails.getMail());
    }

    @Test
    void getCurrentUserDetailsWithNoAuthenticationTest_NoAuthentication() {
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);

        when(securityContext.getAuthentication()).thenReturn(null);

        assertNull(authenticationService.getCurrentUserDetails());
    }


    // testy converttodto

    @Test
    void convertToDTOTest() {
        User testUser = new User();
        testUser.setName("testUser");
        testUser.setMail("test@mail.com");
        testUser.setRoles("USER");
        // ma mieć te same dane, co User
        UserDto result = authenticationService.convertToDTO(testUser, "USER");
        assertEquals("testUser", result.getName());
        assertEquals("test@mail.com", result.getMail());
        assertEquals("USER", result.getRole());
    }

}