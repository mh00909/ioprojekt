package com.ioproject.reservetheweather.auth;

import lombok.Data;


/**
 * Klasa służąca do przekazywania informacji potrzebnych do wygenerowania nowego tokena JWT.
 */
@Data
public class RefreshTokenRequest {
    private String token;
}
