import React from "react";
import "./Bez_rejestracji.css";


const BezRejestracji = () => {
  return (
    <div className="bez-rejestracji">
      <div className="div">
        <div className="overlap">
          <div className="overlap-group">
            {/*<img className="line" alt="Line" src="https://c.animaapp.com/S851EPP9/img/line-4.svg" />*/}
            <img
              className="dodaj-nagwek"
              alt="Dodaj nagwek"
              src="https://c.animaapp.com/S851EPP9/img/dodaj-nag--wek--12--1.png"
            />
          </div>
          <div className="przycisk-konto">
            <div className="overlap-2">
              <img className="domek" alt="Domek" src="https://c.animaapp.com/S851EPP9/img/domek.png" />
              <div className="overlap-group-wrapper">
                <div className="overlap-group-2">
                  <span className="text-wrapper">Moje Konto</span>
                  <div className="rectangle" />
                </div>
              </div>
            </div>
          </div>
          <a href="/Kontakt">
          <div className="przycisk-kontakt">
            <div className="div-wrapper">
              <div className="text-wrapper-2">Kontakt</div>
            </div>
          </div>
          </a>
          <a href="/Informacje">
          <div className="przycisk-informacje">
            <div className="div-wrapper">
              <div className="text-wrapper-3">Informacje</div>
            </div>
          </div>
          </a>
        </div>
        <p className="p">© 2024 ReserveTheWeather. All rights reserved.</p>
        <div className="overlap-3">
          <div className="group">
            <div className="overlap-group-3">
            <a href="/Logowanie">
              <div className="rectangle-2" />
              <div className="text-wrapper-4">Dalej</div>
              </a>
            </div>
          </div>
          <p className="text-wrapper-5">
            Wygląda na to, że nie masz założonego konta. Kliknij dalej, a zostaniesz przekierowany do rejestracji.
          </p>
        </div>
      </div>
    </div>
  );
  
};
export default BezRejestracji;

