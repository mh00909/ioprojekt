package com.ioproject.reservetheweather.service;
import com.ioproject.reservetheweather.entity.User;
import com.ioproject.reservetheweather.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

   public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void addNewUser(User user) {
        Optional<User> userOptional = userRepository.findUserByMail(user.getMail());
        if(userOptional.isPresent()){
            throw new IllegalStateException("Podany e-mail jest zajęty.");
        }
        userRepository.save(user);
    }

    public void deleteUser(Long userID) {
    boolean exists = userRepository.existsById(userID);
    if(!exists){
        throw new IllegalStateException("Użytkownik nie istnieje!");
    }
        userRepository.deleteById(userID);
    }

    @Transactional
    public void updateUser(Long userID, String name, String email) {
        User user = userRepository.findById(userID)
                .orElseThrow( ()->new IllegalStateException("Użytkownik z podanym ID nie istnieje.") );
        if(name!=null && name.length()>0 ){
            user.setName(name);
        }
        if(email!=null && email.length()>0){
            Optional<User> userOptional = userRepository.findUserByMail(email);
            if(userOptional.isPresent()){
                throw new IllegalStateException("Podany e-mail jest zajęty.");
            }
            user.setMail(email);
        }
    }

    public void deleteSelf() {

    }

    public void signUp(Long eventID) {

    }
}

