package com.ioproject.reservetheweather.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;


@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, length = 45)
    private String name;
    @Column(name = "email", nullable = false, unique = true, length = 45)
    private String mail;
    @Column(name = "phone number", length = 15)
    private long phoneNumber;
    @Column(nullable = false, length = 64)
    private String password;
    private String roles;

    public User() {
    }

    public User(Long id, String name, String mail, String password, long phoneNumber) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public User(String name, String mail, String password, long phoneNumber) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public User(User user) {}

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", e-mail='" + mail + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public String getRoles() {
        return roles;
    }
}



