package com.ioproject.reservetheweather.auth;

import com.ioproject.reservetheweather.model.User;
import com.ioproject.reservetheweather.model.UserDto;
import com.ioproject.reservetheweather.repository.UserRepository;
import com.ioproject.reservetheweather.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


/**
 * Kontroler REST API obsługujący autentykację użytkowników.
 * Umożliwia rejestrację, logowanie oraz odświeżanie tokenów JWT.
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;


    /**
     * Obsługuje rejestrację nowego użytkownika.
     * @param name Login użytkownika.
     * @param mail Adres e-mail.
     * @param password Hasło.
     * @param phoneNumber Numer telefonu.
     * @return ResponseEntity z informacjem, czy rejestracja się powiodła.
     */
    @PostMapping("/signup")
    public ResponseEntity<Object> signup(
            @RequestParam("name") String name,
            @RequestParam("mail") String mail,
            @RequestParam("password") String password,
            @RequestParam("phoneNumber") long phoneNumber) {


        Optional<User> user1 = userRepository.findUserByName(name);
        Optional<User> user2 = userRepository.findUserByMail(mail);
        if(user1.isPresent()){
            return ResponseEntity.ok("Błąd: podany login już zajęty");
        }
        if(user2.isPresent()){
            return ResponseEntity.ok("Błąd: podany E-mail już zajęty");
        }

        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setName(name);
        signUpRequest.setMail(mail);
        signUpRequest.setPassword(password);
        signUpRequest.setPhoneNumber(phoneNumber);


        return ResponseEntity.ok(authenticationService.signup(signUpRequest));


    }


    /**
     * Obsługuje logowanie użytkownika.
     * @param name Login użytkownika.
     * @param password Hasło.
     * @return ResponseEntity z odpowiedzią JWT po pomyślnym logowaniu.
     */
  @PostMapping("/signin")
  public ResponseEntity<JwtAuthenticationResponse> signin(
          @RequestParam("username") String name,
          @RequestParam("password") String password) {

      SignInRequest signInRequest = new SignInRequest();
      signInRequest.setName(name);
      signInRequest.setPassword(password);

      return ResponseEntity.ok(authenticationService.signin(signInRequest));
  }


    /**
     * Odświeża token JWT.
     * @param refreshTokenRequest Żądanie z obecnym tokenem JWT.
     * @return ResponseEntity z nowym tokenem JWT.
     */
    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }



    /**
     * Sprawdza, czy użytkownik jest zalogowany.
     * @return ResponseEntity z danymi użytkownika lub informacją o braku autoryzacji.
     */
    @GetMapping("/checkLogged")
    public ResponseEntity<Object> checkIfLoggedIn(){
        UserDto userDTO = authenticationService.getCurrentUserDetails();
        if (userDTO != null) {
            return ResponseEntity.ok(userDTO);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
}



