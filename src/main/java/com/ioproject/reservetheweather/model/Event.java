package com.ioproject.reservetheweather.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;

    @Column
    private String location;

    @Column
    private double price;

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
    double minTemperature;
    @Column
    double maxTemperature;

    public boolean badWeather = false;
    public boolean discount = false;

    //boolean status;
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

    public Event(String name, String dateString, String description, int duration, int maxUsers,
                 int minTemperature, int maxTemperature){
        this.name=name;
        time = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.duration = duration;
        this.signedUsers=0;
        this.maxUsers=maxUsers;
        this.description= description;

        this.minTemperature=minTemperature;
        this.maxTemperature = maxTemperature;
    }


    // przy dodawaniu przez admina
    public Event(String eventDate, String eventTime, int eventDuration,
                 String eventDescription, String eventName,
                 int maxUsers, String eventLocation, double eventPrice,
                 double eventMinTemperature, double eventMaxTemperature){
        setDate(eventDate + " " + eventTime);
        duration = eventDuration;
        description=eventDescription;
        this.maxUsers = maxUsers;
        this.signedUsers=0;
        name=eventName;
        location = eventLocation;
        price = eventPrice;
        minTemperature = eventMinTemperature;
        maxTemperature = eventMaxTemperature;
    }





    public void setDate(String dateString){
        time = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
    public void removeUser(User user){
        users.remove(user);
    }
}

