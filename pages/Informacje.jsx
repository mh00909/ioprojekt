import React from "react";
import "./Informacje.css";

const Informacje = () => {
  return (
    <div className="informacje">
      <div className="div">
        <div className="overlap">
          <p className="text-wrapper">© 2024 ReserveTheWeather. All rights reserved.</p>
          <img className="chmury" alt="Chmury" src="https://c.animaapp.com/Z6LFCeVJ/img/chmury.png" />
          <p className="nie-dopuszcza-si">
            Nie dopuszcza się odwoływania zajęć na mniej niż 24 godziny od planowanej daty.
            <br /> Użytkownik ma możliwość uzyskania zniżki jeśli pogoda sprawdzona 25 godzin przed datą zajęć wskazuje,
            że temperatura spadnie poniżej zera.
            <br /> Rezerwacje można sprawdzić po zalogowaniu w zakładce Moje Konto.
            <br /> Możliwe do rezerwacji terminy pojawiają się w zakładce Kalendarz.
            <br /> Brak dodanych terminów oznacza brak zajęć.
            <br /> Kontakt do zarządu można sprawdzić w zakłdace kontakt.
          </p>
          <img className="group" alt="Group" src="https://c.animaapp.com/Z6LFCeVJ/img/group-17.png" />
          <div className="text-wrapper-2">Zasady rezerwacji:</div>
        </div>
        <div className="overlap-group">
          <div className="overlap-2">
            <img className="line" alt="Line" src="https://c.animaapp.com/Z6LFCeVJ/img/line-4.svg" />
            <img className="logo" alt="Logo" src="https://c.animaapp.com/Z6LFCeVJ/img/logo.png" />
          </div>
          <div className="przycisk-konto">
            <div className="overlap-3">
            <a href="/Bez_rejestracji">
              <img className="domek" alt="Domek" src="https://c.animaapp.com/Z6LFCeVJ/img/domek.png" />
              <div className="overlap-group-wrapper">
                <div className="overlap-group-2">
                  <span className="text-wrapper-3">Moje Konto</span>
                  <div className="rectangle" />
                </div>
              </div>
            </a>
            </div>
          </div>
          <a href="/Kontakt">
          <div className="przycisk-kontakt">
            <div className="div-wrapper">
              <div className="text-wrapper-4">Kontakt</div>
            </div>
          </div>
          </a>
          <div className="przycisk-informacje">
            <div className="div-wrapper">
              <span className="text-wrapper-5">Informacje</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Informacje;
