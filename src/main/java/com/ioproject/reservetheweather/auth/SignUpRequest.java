package com.ioproject.reservetheweather.auth;


import lombok.Data;

/**
 * Klasa reprezentująca żądanie rejestracji do systemu.
 */
@Data
public class SignUpRequest {
    private String name;
    private String mail;
    private String password;
    private long phoneNumber;
}
