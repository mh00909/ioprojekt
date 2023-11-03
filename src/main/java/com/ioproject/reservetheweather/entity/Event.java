package com.ioproject.reservetheweather.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;

    @Column
    private LocalDateTime time;
    @Column
    private int duration;
    @Column
    private String description;
    @Column
    private int maxUsers;
    @Column
    private int signedUsers;
    @ManyToMany
    List<User> users;
    @Column
    int minTemperature;
    @Column
    int maxTemperature;

    boolean badWeather = false;


    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setMinTemperature(int minTemperature) {
        this.minTemperature = minTemperature;
    }

    public void setMaxTemperature(int maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public void setBadWeather(boolean badWeather) {
        this.badWeather = badWeather;
    }

    public Event(String name, String dateString, int duration, int maxUsers){
        this.name=name;
        time = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.duration = duration;
        this.signedUsers=0;
        this.maxUsers=maxUsers;
        description="";
    }

    public Event(String name, String dateString, int duration, int maxUsers,
                 int minTemperature, int maxTemperature){
        this.name=name;
        time = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.duration = duration;
        this.signedUsers=0;
        this.maxUsers=maxUsers;
        description="";

        this.minTemperature=minTemperature;
        this.maxTemperature = maxTemperature;
    }





    public void setId(Long id) {this.id = id;}
    public Long getId() {return id;}
    public String getName() {return name;}
    public int getDuration() {return duration;}
    public String getDescription() {return description;}
    public int getMaxUsers() {return maxUsers;}
    public int getSignedUsers() {return signedUsers;}
    public void setName(String name) {this.name = name;}
    public void setDuration(int duration) {this.duration = duration;}
    public void setDescription(String description) {this.description = description;}
    public void setMaxUsers(int maxUsers) {this.maxUsers = maxUsers;}
    public void setSignedUsers(int signedUsers) {this.signedUsers = signedUsers;}
    public LocalDateTime getTime() {
        return time;
    }

    public List<User> getUsers() {
        return users;
    }

    public int getMinTemperature() {
        return minTemperature;
    }

    public int getMaxTemperature() {
        return maxTemperature;
    }

    public boolean isBadWeather() {
        return badWeather;
    }

    public void setDate(String dateString){
        time = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}

