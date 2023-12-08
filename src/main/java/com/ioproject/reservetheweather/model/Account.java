package com.ioproject.reservetheweather.model;
/*
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

// transformuje User do formatu używanego przez SpringSecurity

public class Account implements UserDetails {
    private String name;
    private String password;
    private List<GrantedAuthority> roles;
    private boolean accountExpired = false;
    private boolean accountLocked = false;
    private boolean credentialsExpired = false;
    private boolean accountDisabled = false;
    public Account(User user){
        this.name = user.getName();
        this.password = user.getPassword();
        String userRole = user.getRoles();
        this.roles = Collections.singletonList(new SimpleGrantedAuthority(userRole));

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return !accountDisabled;
    }

}


 */