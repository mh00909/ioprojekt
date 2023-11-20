import React from "react";
import "./Kontakt.css";

const Kontakt = () => {
  return (
    <div className="kontakt">
    <div className="overlap-wrapper">
      <div className="overlap">
        <div className="overlap-group">
          <p className="text-wrapper">© 2024 ReserveTheWeather. All rights reserved.</p>
          <div className="tresc-strony">
            <div className="div">
              <img className="group" alt="Group" src="https://c.animaapp.com/OsgJfIi2/img/group-17.png" />
              <div className="group-2">
                <img className="image" alt="Image" src="https://c.animaapp.com/OsgJfIi2/img/image-3@2x.png" />
                <div className="group-3">
                  <img className="img" alt="Image" src="https://c.animaapp.com/OsgJfIi2/img/image-1@2x.png" />
                  <div className="text-wrapper-2">Reservetheweather@gmail.com</div>
                </div>
                <div className="text-wrapper-3">+48 517 574 182</div>
              </div>
              <p className="p">Chcesz zarezerwować miejsce na zajęciach ?</p>
              <div className="text-wrapper-4">Pomożemy Ci w tym!</div>
              <p className="text-wrapper-5">
                W razie pytań służymy pomocą. Napisz do nas! Zwykle odpisujemy w przeciągu 24 godzin. Jeśli tak się
                nie stanie, skontaktuj się z nami telefonicznie, czasem jakaś wiadomość trafia do spamu.
              </p>
            </div>
          </div>
          <img className="line" alt="Line" src="https://c.animaapp.com/OsgJfIi2/img/line-4.svg" />
          <img
            className="dodaj-nagwek"
            alt="Dodaj nagwek"
            src="https://c.animaapp.com/OsgJfIi2/img/dodaj-nag--wek--12--1.png"
          />
        </div>
        <div className="przycisk-konto">
          <div className="overlap-2">
          <a href="/Bez_rejestracji">
            <img className="domek" alt="Domek" src="https://c.animaapp.com/OsgJfIi2/img/domek.png" />
            <div className="overlap-group-wrapper">
              <div className="overlap-group-2">
                <div className="text-wrapper-6">Moje Konto</div>
                <div className="rectangle" />
              </div>
            </div>
            </a>
          </div>
        </div>
        <div className="przycisk-kontakt">
          <div className="div-wrapper">
            <span className="text-wrapper-7">Kontakt</span>
          </div>
        </div>
        <a href="/Informacje">
        <div className="przycisk-informacje">
          <div className="div-wrapper">
            <div className="text-wrapper-8">Informacje</div>
          </div>
        </div>
        </a>
      </div>
    </div>
  </div>
);
};

export default Kontakt;
