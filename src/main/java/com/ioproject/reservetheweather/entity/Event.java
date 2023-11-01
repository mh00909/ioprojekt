package com.ioproject.reservetheweather.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name="events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    String datePattern = "yyyy-MM-dd hh:mm:ss";
    private SimpleDateFormat startingTime = new SimpleDateFormat(datePattern);
    @Column
    private Date date;
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
    public Event(){signedUsers=0;description="";}
    public Event(String name, String dateString, int duration, int maxUsers){
        this.name=name;
        try{
            date=startingTime.parse(dateString);
        }catch(ParseException e){
            System.out.println("Nieprawidłowy format daty. Format: dd-M-yyyy hh:mm:ss");
        }
        this.duration = duration;
        this.signedUsers=0;
        this.maxUsers=maxUsers;
        description="";
    }

    public Event(String name, String dateString, int duration, int maxUsers,
                 int minTemperature, int maxTemperature){
        this.name=name;
        try{
            date=startingTime.parse(dateString);
        }catch(ParseException e){
            System.out.println("Nieprawidłowy format daty. Format: dd-M-yyyy hh:mm:ss");
        }
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
    public Date getDate() {
        return date;
    }


    public void setDate(String dateString){
        try{
            date=startingTime.parse(dateString);
        }catch(ParseException e){
            System.out.println("Nieprawidłowy format daty. Format: dd-M-yyyy hh:mm:ss");
        }
    }
}

