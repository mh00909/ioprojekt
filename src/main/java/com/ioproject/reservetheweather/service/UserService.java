package com.ioproject.reservetheweather.service;
import com.ioproject.reservetheweather.model.Event;
import com.ioproject.reservetheweather.model.User;
import com.ioproject.reservetheweather.repository.EventRepository;
import com.ioproject.reservetheweather.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    private PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, EventRepository eventRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.passwordEncoder = passwordEncoder;
    }

   public List<User> getUsers(){
        return userRepository.findAll();
    }


    //@Transactional
    public int addNewUser(User user) {
        Optional<User> exists = userRepository.findUserByMail(user.getMail());
        Optional<User> exists2 = userRepository.findUserByName(user.getName());
        if(exists.isPresent()){
            return 0;
        }
        if(exists2.isPresent()){
            return 1;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return 2;
    }

    public boolean deleteUser(Long userID) {
    boolean exists = userRepository.existsById(userID);
    if(!exists){
        throw new IllegalStateException("Użytkownik nie istnieje!");
    }
        userRepository.deleteById(userID);
        return true;
    }

    @Transactional
    public boolean updateUserMail(Long userID, String email) {
        User user = userRepository.findById(userID)
                .orElseThrow( ()->new IllegalStateException("Użytkownik z podanym ID nie istnieje.") );

        if(email!=null && email.length()>0){
            Optional<User> userOptional = userRepository.findUserByMail(email);
            if(userOptional.isPresent()){
                throw new IllegalStateException("Podany e-mail jest zajęty.");
            }
            user.setMail(email);
            return true;
        }
        return false;
    }

    public boolean updateUserName(Long userID, String name){
        User user = userRepository.findById(userID)
                .orElseThrow( ()->new IllegalStateException("Użytkownik z podanym ID nie istnieje.") );
        if(name!=null && name.length()>0 ){
            user.setName(name);
            return true;
        }
        return false;
    }



    public List<Event> showMyEvents(Long userID) {
        User user = userRepository.findById(userID)
                .orElseThrow( ()->new IllegalStateException("Użytkownik z podanym ID nie istnieje.") );
        return user.getMyEvents();
    }

    public boolean joinEvent(Long eventid, User user){
        Optional<Event> event = eventRepository.findById(eventid);
        if(event.isPresent()){
            user.joinEvent(event.get());
            return true;
        }
        return false;
    }

    public boolean resign(Long eventID, Optional<User> user){
        Optional<Event> event = eventRepository.findById(eventID);
        if(event.isPresent()){
            user.get().resign(event.get());
            return true;
        }
        return false;
    }
}

