package com.ioproject.reservetheweather.auth;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String token;
}
