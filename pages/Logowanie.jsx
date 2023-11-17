import React, { useState } from "react";
import "./Logowanie.css";
import api from "../api";


//obsłuży logowanie i rejestracje
const Logowanie = () => {
  const [loginUsername, setLoginUsername] = useState("");
  const [loginPassword, setLoginPassword] = useState("");
  const [registerUsername, setRegisterUsername] = useState("");
  const [registerMail, setRegisterMail] = useState("");
  const [registerPassword, setRegisterPassword] = useState("");
  const [registerPhoneNumber, setRegisterPhoneNumber] = useState("");
  const [isRegistering, setIsRegistering] = useState(false);
  const apiBaseUrl = process.env.REACT_APP_API_BASE_URL || "http://localhost:8080";
  const loginEndpoint = `${apiBaseUrl}/login`;

  const [error, setError] = useState(""); // Define an error state variable


  const handleSubmit = async (e) => {
    e.preventDefault();

    const formData = new URLSearchParams();
    formData.append('username', loginUsername)
    formData.append('password', loginPassword)
    console.log("Dane logowania:", formData.toString());

    try {
      const response = await api.post('/login', formData);
     // console.log(response.data)
    // const isLoginSuccess = !response.url.includes('?error');


      if(response.data == "Strona domowa"){
        console.log('Udało się zalogować', response.data)
        window.location.href = '/Glowna';
      }
      else{
        // tu nie wiem co zrobić
        window.location.href = '/Informacje';
      //  setError("Niepoprawne logowania. Spróbuj ponownie.");
      }

    } catch (error) {
      console.error("Błąd podczas wysyłania danych:", error);
      if(error.response && error.response.status===401){
        setError("Niepoprawne logowania. Spróbuj ponownie.");
      }
      else{
        setError("Błąd logowania. Spróbuj ponownie.");
      }
      
    }
  };

  const handleRegisterSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post("http://localhost:8080/api/register", {
        username: registerUsername,
        mail: registerMail,
        password: registerPassword,
        phone: registerPhoneNumber, 
      });

      console.log(response.data);
      // Przekieruj użytkownika po udanej rejestracji
      history.push("/Konto");

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
    
    <div className="logowanie"> {/*To spina calosc*/}
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
        <img
          className="masz-ju-konto"
          alt="Masz ju konto"
          src="https://c.animaapp.com/lc2qlH2F/img/masz-ju--konto-.png"
        />
        <div className="formularz_logowania">
        <button className="przycisk-zaloguj" onClick={handleSubmit}>
          <img
            className="zaloguj_tlo"
            alt="Zaloguj_tlo"
            src="https://c.animaapp.com/lc2qlH2F/img/rectangle-15.png"
          />
          <img
            className="zaloguj-si"
            alt="Zaloguj si"
            src="https://c.animaapp.com/lc2qlH2F/img/zaloguj-si-.png"
          />
          </button>
          <img
            className="tlo_formularza"
            alt="Tlo_formularza"
            src="https://c.animaapp.com/lc2qlH2F/img/rectangle-6.png"
          />
          <img
            className="login"
            alt="Login"
            src="https://c.animaapp.com/lc2qlH2F/img/login-1.png"
          />
          <img
            className="haso"
            alt="Haso"
            src="https://c.animaapp.com/lc2qlH2F/img/has-o-1.png"
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
          <div className="rejestracja">
      {/* Formularz rejestracji */}
      <input
        type="text"
        placeholder=""
        value={registerUsername}
        onChange={(e) => setRegisterUsername(e.target.value)}  
      />
      </div>
          <form className="rejestracja">
            <input
              type="email" 
              placeholder="" 
              value={registerMail}
              onChange={(e) => setRegisterMail(e.target.value)}
            />
          </form>
           <form className="rejestracja">
            <input
              type="password" 
              placeholder="" 
              value={registerPassword}
              onChange={(e) => setRegisterPassword(e.target.value)}
            />
          </form>
          <form className="rejestracja">
            <input
              type="tel" 
              placeholder="" 
              value={registerPhoneNumber}
              onChange={(e) => setRegisterPhoneNumber(e.target.value)}
            />
          </form>
          <img
            className="tlo_formalarza_r"
            alt="Tlo_formularza_r"
            src="https://c.animaapp.com/04bVqCpf/img/rectangle-7.png"
          />
          <button className="przycisk-zarejestruj" onClick={handleSubmit}>
          <img
            className="zarejestruj_tlo"
            alt="Zarejestruj_tlo"
            src="https://c.animaapp.com/04bVqCpf/img/rectangle-16.png"
          />
          <img
            className="zarejestruj-si"
            alt="Zarejestruj si"
            src="https://c.animaapp.com/04bVqCpf/img/zarejestruj-si-.png"
          />
          </button>
          <img
            className="login_r"
            alt="Login"
            src="https://c.animaapp.com/lc2qlH2F/img/login-1.png"
          />
          <img
            className="mail"
            alt="Mail"
            src="https://c.animaapp.com/04bVqCpf/img/mail.png"
          />
          <img
            className="haso-2"
            alt="Haso"
            src="https://c.animaapp.com/lc2qlH2F/img/has-o-1.png"
          />
          <img
            className="numer-telefonu"
            alt="Numer telefonu"
            src="https://c.animaapp.com/lc2qlH2F/img/numer-telefonu.png"
          />
          <img
            className="wyprbuj-za-darmo"
            alt="Wyprbuj za darmo"
            src="https://c.animaapp.com/lc2qlH2F/img/wypr-buj-za-darmo.png"
          />
        </div>
        <img
          className="telefon_ikonka"
          alt="Telefon_ikonka"
          src="https://c.animaapp.com/lc2qlH2F/img/image-3.png"
        />
        <img
          className="email_ikonka"
          alt="Email_ikonka"
          src="https://c.animaapp.com/lc2qlH2F/img/image-1.png"
        />
        <img
          className="email"
          alt="Email"
          src="https://c.animaapp.com/lc2qlH2F/img/reservetheweather-gmail-com.png"
        />
        <img
          className="telefon"
          alt="Telefon"
          src="https://c.animaapp.com/lc2qlH2F/img/-48-517-574-182.png"
        />
        
    </div>
  );
};

export default Logowanie;
