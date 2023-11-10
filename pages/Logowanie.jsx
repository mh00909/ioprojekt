import React, { useState } from "react";
import "./Logowanie.css";
import axios from "axios";

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

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(loginEndpoint, {
        username: loginUsername,
        password: loginPassword,
      });

      console.log(response.data);
      // Obsłuż odpowiedź od serwera (response.data)
      history.push("/Konto");
    } catch (error) {
      console.error("Błąd podczas wysyłania danych:", error);
      setError("Błąd logowania. Spróbuj ponownie.");
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
    <div className="logowanie">
      <div className="div">
        <img
          className="element"
          alt="Element"
          src="https://c.animaapp.com/lc2qlH2F/img/--2024-reservetheweather--all-rights-reserved-.png"
        />
        
        <img
          className="dodaj-nagwek"
          alt="Dodaj nagwek"
          src="https://c.animaapp.com/lc2qlH2F/img/dodaj-nag--wek--12--1.png"
        />
        <img
          className="masz-ju-konto"
          alt="Masz ju konto"
          src="https://c.animaapp.com/lc2qlH2F/img/masz-ju--konto-.png"
        />
        <div className="overlap-group">
        <button className="przycisk-zaloguj" onClick={handleSubmit}>
          <img
            className="rectangle"
            alt="Rectangle"
            src="https://c.animaapp.com/lc2qlH2F/img/rectangle-15.png"
          />
          <img
            className="zaloguj-si"
            alt="Zaloguj si"
            src="https://c.animaapp.com/lc2qlH2F/img/zaloguj-si-.png"
          />
          </button>

          
          <img
            className="img"
            alt="Rectangle"
            src="https://c.animaapp.com/lc2qlH2F/img/rectangle-6.png"
          />
          <img
            className="rectangle-2"
            alt="Rectangle"
            src="https://c.animaapp.com/lc2qlH2F/img/rectangle-13.png"
          />
          <img
            className="rectangle-3"
            alt="Rectangle"
            src="https://c.animaapp.com/lc2qlH2F/img/rectangle-13.png"
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
          <img
            className="rectangle-4"
            alt="Rectangle"
            src="https://c.animaapp.com/lc2qlH2F/img/rectangle-7.png"
          />
      <div className="logowanie">
      {/* Formularz logowania */}
      {/* Input, który pozwala użytkownikowi wprowadzić login */}
      <input
        type="text"
        placeholder=""
        value={loginUsername}
        onChange={(e) => setLoginUsername(e.target.value)} // Aktualizuje stan na podstawie zmian wprowadzonych przez użytkownika
        
      />
          <img
            className="rectangle-login"
            alt="Rectangle"
            src="https://c.animaapp.com/lc2qlH2F/img/rectangle-13.png"
          />
           </div>
           <div className="logowanie">
            <input
              type="password"  // Użyj type="password", aby ukryć wprowadzone znaki
              placeholder="" // tu mozna wstawiac napisy typu podaj haslo
              value={loginPassword}
              onChange={(e) => setLoginPassword(e.target.value)}
            />
          </div>
          <img
            className="rectangle-6"
            alt="Rectangle"
            src="https://c.animaapp.com/lc2qlH2F/img/rectangle-13.png"
          />
          <img
            className="rectangle-7"
            alt="Rectangle"
            src="https://c.animaapp.com/lc2qlH2F/img/rectangle-13.png"
          />
          <img
            className="rectangle-8"
            alt="Rectangle"
            src="https://c.animaapp.com/lc2qlH2F/img/rectangle-13.png"
          />
          <img
            className="rectangle-9"
            alt="Rectangle"
            src="https://c.animaapp.com/lc2qlH2F/img/rectangle-13.png"
          />
          <img
            className="imi"
            alt="Imi"
            src="https://c.animaapp.com/lc2qlH2F/img/imi-.png"
          />
          <img
            className="login-2"
            alt="Login"
            src="https://c.animaapp.com/lc2qlH2F/img/login-1.png"
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
            className="nazwisko"
            alt="Nazwisko"
            src="https://c.animaapp.com/lc2qlH2F/img/nazwisko.png"
          />
          <img
            className="wyprbuj-za-darmo"
            alt="Wyprbuj za darmo"
            src="https://c.animaapp.com/lc2qlH2F/img/wypr-buj-za-darmo.png"
          />
        </div>
        <img
          className="image"
          alt="Image"
          src="https://c.animaapp.com/lc2qlH2F/img/image-3.png"
        />
        <img
          className="image-2"
          alt="Image"
          src="https://c.animaapp.com/lc2qlH2F/img/image-1.png"
        />
        <img
          className="reservetheweather"
          alt="Reservetheweather"
          src="https://c.animaapp.com/lc2qlH2F/img/reservetheweather-gmail-com.png"
        />
        <img
          className="element-2"
          alt="Element"
          src="https://c.animaapp.com/lc2qlH2F/img/-48-517-574-182.png"
        />
      </div>
    </div>
  );
};

export default Logowanie;
