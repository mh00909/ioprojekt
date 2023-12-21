package com.ioproject.reservetheweather.auth;
import lombok.Data;


/**
 * Klasa reprezentująca odpowiedź po uwierzytelnieniu JWT.
 * Zawiera token JWT, odświeżony token oraz rolę użytkownika.
 */
@Data
public class JwtAuthenticationResponse {
    private String token;
    private String refreshToken;
    private String role;

}
