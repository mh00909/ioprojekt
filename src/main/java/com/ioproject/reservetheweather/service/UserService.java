package com.ioproject.reservetheweather.service;
import com.ioproject.reservetheweather.model.Event;
import com.ioproject.reservetheweather.model.User;
import com.ioproject.reservetheweather.repository.EventRepository;
import com.ioproject.reservetheweather.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


/**
 * Klasa służąca do zarządzania użytkownikami.
 * Oferuje funkcjonalności związane z tworzeniem, usuwaniem i aktualizacją danych.
 * Umożliwia również przeglądanie wydarzeń powiązanych z użytkownikiem.
 *
 */
@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Pobiera listę wszystkich użytkowników
     * @return Lista użytkowników
     */
   public List<User> getUsers(){
        return userRepository.findAll();
    }


    /**
     * Dodaje nowego użytkownika.
     * @param user Obiekt użytkownika do dodania
     * @return Kod statusu operacji (0 - istnieje e-mail, 1 - istnieje nazwa, 2 - sukces)
     */
    @Transactional
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
        user.setRoles("USER");

        userRepository.save(user);
        return 2;
    }

    /**
     * Usuwa użytkownika na podstawie jego ID
     * @param userID ID użytkownika do usunięcia
     * @return true, jeśli operacja się powiedzie
     * @throws IllegalStateException, jeśli użytkownik nie istnieje
     */
    public boolean deleteUser(Long userID) {
        boolean exists = userRepository.existsById(userID);
        if(!exists){
            throw new IllegalStateException("Użytkownik nie istnieje!");
        }
        userRepository.deleteById(userID);
        return true;
    }

    /**
     * Aktualizuje adres e-mail użytkownika.
     *
     * @param userID ID użytkownika
     * @param email Nowy adres e-mail
     * @return true, jeśli aktualizacja się powiedzie
     * @throws IllegalStateException jeśli użytkownik nie istnieje lub e-mail jest zajęty
     */
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


    /**
     * Aktualizuje nazwę użytkownika.
     *
     * @param userID ID użytkownika
     * @param name Nowa nazwa użytkownika
     * @return true, jeśli aktualizacja się powiedzie
     */
    public boolean updateUserName(Long userID, String name){
        User user = userRepository.findById(userID)
                .orElseThrow( ()->new IllegalStateException("Użytkownik z podanym ID nie istnieje.") );
        if(name!=null && name.length()>0 ){
            user.setName(name);
            return true;
        }
        return false;
    }



    /**
     * Wyświetla wydarzenia, w których uczestniczy dany użytkownik.
     *
     * @param userID ID użytkownika
     * @return Lista wydarzeń użytkownika
     * @throws IllegalStateException jeśli użytkownik nie istnieje
     */

    public List<Event> showMyEvents(Long userID) {
        User user = userRepository.findById(userID)
                .orElseThrow( ()->new IllegalStateException("Użytkownik z podanym ID nie istnieje.") );
        return user.getMyEvents();
    }



    /**
     * Pozwala użytkownikowi dołączyć do wydarzenia.
     *
     * @param eventid ID wydarzenia
     * @param user Użytkownik dołączający do wydarzenia
     * @return true, jeśli dołączenie się powiedzie
     */
    public boolean joinEvent(Long eventid, User user){
        Optional<Event> event = eventRepository.findById(eventid);
        if(event.isPresent()){
            user.joinEvent(event.get());
            return true;
        }
        return false;
    }


    /**
     * Pozwala użytkownikowi zrezygnować z udziału w wydarzeniu.
     *
     * @param eventID ID wydarzenia
     * @param user Użytkownik rezygnujący z wydarzenia
     * @return true, jeśli rezygnacja się powiedzie
     */
    public boolean resign(Long eventID, Optional<User> user){
        Optional<Event> event = eventRepository.findById(eventID);
        if(event.isPresent()){
            user.get().resign(event.get());
            return true;
        }
        return false;
    }



/*
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Optional<User> user = userRepository.findUserByName(username);

                return userRepository.findUserByName(username)
                        .orElseThrow(()->new UsernameNotFoundException("Nie znaleziono użytkownika"));
            }
        };
    }

 */

}

