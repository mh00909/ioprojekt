package com.ioproject.reservetheweather.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String mail;
    @Column(name = "phone number")
    private long phoneNumber;
    @Column()
    private String password;
    @Column
    private String roles;

    @ManyToMany
    private List<Event> myEvents;



    public User(Long id, String name, String mail, String password, long phoneNumber, String roles) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
    }

    // ten w rejestracji
    public User(String name, String mail, String password, long phoneNumber) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.roles = "USER";
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

    public void joinEvent(Event event){
        myEvents.add(event);
    }
    public boolean resign(Event event){
        if(myEvents.contains(event)){
            myEvents.remove(event);
            return true;
        }
        else{
            return false;
        }

    }
}



