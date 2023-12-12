import React, { useState, useEffect } from "react";
import "./UserAuth.css";
import api from "../api";


//obsłuży logowanie i rejestracje
const UserAuth = () => {
  const [loginUsername, setLoginUsername] = useState("");
  const [loginPassword, setLoginPassword] = useState("");
  const [registerUsername, setRegisterUsername] = useState("");
  const [registerMail, setRegisterMail] = useState("");
  const [registerPassword, setRegisterPassword] = useState("");
  const [registerPhoneNumber, setRegisterPhoneNumber] = useState("");
  const [isRegistering, setIsRegistering] = useState(false);
  const apiBaseUrl = process.env.REACT_APP_API_BASE_URL || "http://localhost:8080";
  const loginEndpoint = `${apiBaseUrl}/api/auth/signin`;

  const [error, setError] = useState(""); 


  const handleSubmit = async (e) => {
    e.preventDefault();

    const formData = new URLSearchParams();
    formData.append('username', loginUsername)
    formData.append('password', loginPassword)
    console.log("Dane logowania:", formData.toString());

    try {
      const response = await api.post('/api/auth/signin', formData);
    if (response.status === 200) {
      const data = response.data;
      const role = data.role; 
      localStorage.setItem('token', data.token);
      localStorage.setItem('login', data.name);
      localStorage.setItem('test' , 'test12345');
      console.log('token:  ', localStorage.getItem('token'));
      console.log('login:  ', localStorage.getItem('login'));
      console.log('test:  ', localStorage.getItem('test'));
      if (role === ("ADMIN")) {
        window.location.href = '/AdminPanel';
      } else {
        window.location.href = '/Account';
      }
    } else {
      setError("Nie udało się zalogować.");
    }
  } catch (error) {
    console.error("Error during sign in:", error);
    setError("Login error. Please try again.");

  }
  };






  
  const handleRegisterSubmit = async (e) => {
    e.preventDefault();

    const formData = new URLSearchParams();
    formData.append('name', registerUsername)
    formData.append('mail', registerMail)
    formData.append('password', registerPassword)
    formData.append('phoneNumber', registerPhoneNumber)
    console.log("Dane rejestracji:", formData.toString());
    
    try {
      const response = await api.post("/api/auth/signup", formData);

         if(response.data == "Błąd: podany E-mail już zajęty"){
          alert("Błąd: podany E-mail jest już zajęty");

         }
         else if(response.data == "Błąd: podany login już zajęty"){
          alert("Błąd: podany login jest już zajęty");
         }
         else{
          window.location.href = '/Account';
       }
      
      // Przełącz formularz na tryb logowania
      setIsRegistering(false);

      // Opcjonalnie, wyczyść pola formularza rejestracji
      setRegisterUsername("");
      setRegisterPassword("");
    } catch (error) {
      console.error("Błąd podczas rejestracji:", error);
      setError("Błąd rejestracji. Spróbuj ponownie.");
    }
  };


  return (
    
    <div className="user-auth"> 
        <img
          className="rights_reserved"
          alt="Rights_reserved"
          src="https://c.animaapp.com/lc2qlH2F/img/--2024-reservetheweather--all-rights-reserved-.png"
        />
        <img
          className="logo"
          alt="Logo"
          src="https://c.animaapp.com/lc2qlH2F/img/dodaj-nag--wek--12--1.png"
        />
        <img className="clouds" alt="Chmurki" src="https://c.animaapp.com/x6s48Cpz/img/rectangle-17.png" />
        
        <img
          className="masz-ju-konto"
          alt="Masz ju konto"
          src="https://c.animaapp.com/x6s48Cpz/img/masz-ju--konto-.png"
        />
        <div className="login-form">
        <button className="przycisk-zaloguj" onClick={handleSubmit}>
          <img
            className="zaloguj_tlo"
            alt="Zaloguj_tlo"
            src="https://c.animaapp.com/x6s48Cpz/img/rectangle-15.png"
          />
          <img
            className="zaloguj-si"
            alt="Zaloguj si"
            src="https://c.animaapp.com/x6s48Cpz/img/zaloguj-si-.png"
          />
          </button>
          <img
            className="background-form"
            alt="Tlo_formularza"
            src="https://c.animaapp.com/lc2qlH2F/img/rectangle-6.png"
          />
          <img
            className="login"
            alt="Login"
            src="https://c.animaapp.com/x6s48Cpz/img/login-1.png"
          />
          <img
            className="haso"
            alt="Haso"
            src="https://c.animaapp.com/x6s48Cpz/img/has-o-1.png"
          />
        </div>
        <div className="overlap">
      <div className="logowanie">
      {/* Formularz logowania */}
      <input
        type="text"
        placeholder=""
        value={loginUsername}
        onChange={(e) => setLoginUsername(e.target.value)} // Aktualizuje stan na podstawie zmian wprowadzonych przez użytkownika  
      />
      </div>
           <div className="logowanie">
            <input
              type="password"  
              placeholder="" // tu mozna wstawiac napisy typu podaj haslo
              value={loginPassword}
              onChange={(e) => setLoginPassword(e.target.value)}
            />
          </div>
          <div className="registration">
      {/* Formularz rejestracji */}
      <img
            className="tlo_formalarza_r formularz_rejestracji"
            alt="Tlo_formularza_r"
            src="https://c.animaapp.com/04bVqCpf/img/rectangle-7.png"
          />
      <input
        type="text"
        placeholder=""
        value={registerUsername}
        onChange={(e) => setRegisterUsername(e.target.value)}  
      />
      </div>
          <form className="registration">
            <input
              type="email" 
              placeholder="" 
              value={registerMail}
              onChange={(e) => setRegisterMail(e.target.value)}
            />
          </form>
           <form className="registration">
            <input
              type="password" 
              placeholder="" 
              value={registerPassword}
              onChange={(e) => setRegisterPassword(e.target.value)}
            />
          </form>
          <form className="registration">
            <input
              type="tel" 
              placeholder="" 
              value={registerPhoneNumber}
              onChange={(e) => setRegisterPhoneNumber(e.target.value)}
            />
          </form>
          <button className="przycisk-zarejestruj" onClick={handleRegisterSubmit}>
          <img
            className="register-background"
            alt="Zarejestruj_tlo"
            src="https://c.animaapp.com/x6s48Cpz/img/rectangle-16.png"
          />
          <img
            className="register"
            alt="Register"
            src="https://c.animaapp.com/x6s48Cpz/img/zarejestruj-si-.png"
          />
          </button>
          <img
            className="login_r"
            alt="Login"
            src="https://c.animaapp.com/x6s48Cpz/img/login-1.png"
          />
          <img
            className="mail"
            alt="Mail"
            src="https://c.animaapp.com/x6s48Cpz/img/mail.png"
          />
          <img
            className="password-2"
            alt="Haso"
            src="https://c.animaapp.com/x6s48Cpz/img/has-o-1.png"
          />
          <img
            className="phone-number"
            alt="Phone Number"
            src="https://c.animaapp.com/x6s48Cpz/img/numer-telefonu.png"
          />
          <img
            className="try_for_free"
            alt="Try for free"
            src="https://c.animaapp.com/x6s48Cpz/img/wypr-buj-za-darmo.png"
          />
        </div>
        <img
          className="phone_icon"
          alt="Phone_icon"
          src="https://c.animaapp.com/lc2qlH2F/img/image-3.png"
        />
        <img
          className="email_icon"
          alt="Email_icon"
          src="https://c.animaapp.com/lc2qlH2F/img/image-1.png"
        />
        <img
          className="email"
          alt="Email"
          src="https://c.animaapp.com/lc2qlH2F/img/reservetheweather-gmail-com.png"
        />
        <img
          className="phone"
          alt="Phone"
          src="https://c.animaapp.com/lc2qlH2F/img/-48-517-574-182.png"
        />
    </div>
  );
};


export default UserAuth;