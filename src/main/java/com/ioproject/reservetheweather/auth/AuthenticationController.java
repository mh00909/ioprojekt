package com.ioproject.reservetheweather.auth;

import com.ioproject.reservetheweather.model.User;
import com.ioproject.reservetheweather.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

   /* @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.signup(signUpRequest));
    }
  /*  @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest signInRequest ){
        return ResponseEntity.ok(authenticationService.signin(signInRequest));
    }

   */

    @PostMapping("/signup")
    public ResponseEntity<User> signup(
            @RequestParam("name") String name,
            @RequestParam("mail") String mail,
            @RequestParam("password") String password,
            @RequestParam("phoneNumber") long phoneNumber) {

        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setName(name);
        signUpRequest.setMail(mail);
        signUpRequest.setPassword(password);
        signUpRequest.setPhoneNumber(phoneNumber);
        return ResponseEntity.ok(authenticationService.signup(signUpRequest));
    }
  @PostMapping("/signin")
  public ResponseEntity<JwtAuthenticationResponse> signin(
          @RequestParam("username") String name,
          @RequestParam("password") String password) {

      SignInRequest signInRequest = new SignInRequest();
      signInRequest.setName(name);
      signInRequest.setPassword(password);

      return ResponseEntity.ok(authenticationService.signin(signInRequest));
  }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }




}



