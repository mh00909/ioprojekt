import React from "react";
import "./Glowna.css";
import "../app/globals.css"

const Glowna = () => {
  return (
    <div className="glowna">
      <div className="div">
        <div className="overlap">
          <div className="overlap-group">
           {/*<img className="line" alt="Line" src="https://c.animaapp.com/j7c1j9sQ/img/line-3.svg" />*/}
            <img className="laptop" alt="Laptop" src="https://c.animaapp.com/j7c1j9sQ/img/laptop.png" />
            <p className="chcesz-zarezerwowa">
              <span className="text-wrapper">
                Chcesz zarezerwować miejsce na zajęciach ?<br />
              </span>
              <span className="span">Pomożemy Ci w tym!</span>
            </p>
            <p className="p">
              ReserveTheWeather to aplikacja webowa umożliwiająca rezerwację miejsc na zajęciach w oparciu o dane
              pogodowe. Pogoda nie sprzyja? To nie problem, u nas przełożysz zajęcia lub otrzymasz zniżkę!
            </p>
            <div className="przycisk-dalej">
              <div className="overlap-group-2">
                <a href="/Logowanie">
                  <div className="przycisk-dalej-2" />
                  <div className="text-wrapper-2">Dalej</div>
                </a>
              </div>
            </div>
            <img className="logo" alt="Logo" src="https://c.animaapp.com/j7c1j9sQ/img/logo.png" />
          </div>
          <div className="przycisk-konto">
            <div className="overlap-2">
            <a href="/Bez_rejestracji">
              <img className="domek" alt="Domek" src="https://c.animaapp.com/j7c1j9sQ/img/domek.png" />
              <div className="overlap-group-wrapper">
                <div className="overlap-group-3">
                  <div className="text-wrapper-3">Moje Konto</div>
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
          <a href="/Informacje">
            <div className="przycisk-informacje">
              <div className="div-wrapper">
                <div className="text-wrapper-5">Informacje</div>
              </div>
            </div>
          </a>
        </div>
        <p className="text-wrapper-6">© 2024 ReserveTheWeather. All rights reserved.</p>
      </div>
    </div>
  );
};

export default Glowna;
