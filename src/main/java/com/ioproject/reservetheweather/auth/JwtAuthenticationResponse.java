package com.ioproject.reservetheweather.auth;
import lombok.Data;
@Data
public class JwtAuthenticationResponse {
    private String token;
    private String refreshToken;

}
