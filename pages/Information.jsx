import React from "react";
import "./Information.css";

const Information = () => {
  return (
    <div className="information">
      <div className="container">
        <div className="overlap">
          <p className="text-wrapper">© 2024 ReserveTheWeather. All rights reserved.</p>
          <img className="clouds" alt="Clouds" src="https://c.animaapp.com/Z6LFCeVJ/img/chmury.png" />
          <p className="not-allowed">
          Prognozę pogody możesz na bieżąco sprawdzać w danych rezerwacji.
            <br /> Użytkownik ma możliwość uzyskania zniżki jeśli temperatura powietrza sprawdzona w dzień zajęć nie mieści się w zakresie podanym w opisie.
            <br /> Rezerwacje można sprawdzić po zalogowaniu w zakładce Moje Konto.
            <br /> Możliwe do rezerwacji terminy pojawiają po wybraniu daty.
            <br /> Brak dodanych terminów oznacza brak zajęć.
            <br /> Kontakt do zarządu można sprawdzić w zakładce kontakt.
          </p>
          <img className="group" alt="Group" src="https://c.animaapp.com/Z6LFCeVJ/img/group-17.png" />
          <div className="text-wrapper-2">Słowem wstępu:</div>
        </div>
        <div className="overlap-group">
          <div className="overlap-2">
            <img className="line" alt="Line" src="https://c.animaapp.com/Z6LFCeVJ/img/line-4.svg" />
            <img className="logo" alt="Logo" src="https://c.animaapp.com/Z6LFCeVJ/img/logo.png" />
          </div>
          <div className="account-button">
            <div className="overlap-3">
              <a href="/Without_registration">
                <img className="home" alt="Home" src="https://c.animaapp.com/Z6LFCeVJ/img/domek.png" />
                <div className="overlap-group-wrapper">
                  <div className="overlap-group-2">
                    <span className="text-wrapper-3">Moje konto</span>
                    <div className="rectangle" />
                  </div>
                </div>
              </a>
            </div>
          </div>
          <a href="/Contact">
            <div className="contact-button">
              <div className="container-wrapper">
                <div className="text-wrapper-4">Kontakt</div>
              </div>
            </div>
          </a>
          <div className="info-button">
            <div className="container-wrapper">
              <span className="text-wrapper-5">Informacje</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Information;
