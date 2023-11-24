import React from "react";
import "./EventAdmin.css";
import "../app/globals.css"

const EventAdmin = () => {
    return (  <div className="box">
    <div className="wydarzenie">
      <div className="overlap">
        <div className="text-wrapper">Opis</div>
        <div className="div">Cena</div>
        <div className="text-wrapper-2">miejsca</div>
        <div className="overlap-group">
          <div className="text-wrapper-3">lokalizacja</div>
          <img className="map" alt="Map" src="https://c.animaapp.com/t0STnSju/img/map-2@2x.png" />
        </div>
        <div className="text-wrapper-4">Nazwa</div>
        <div className="overlap-2">
          <div className="text-wrapper-5">Dzień</div>
          <div className="text-wrapper-6">Godzina</div>
        </div>
        <div className="text-wrapper-7">Czas trwania</div>
        <img className="users" alt="Users" src="https://c.animaapp.com/t0STnSju/img/users-2@2x.png" />
        <div className="przycisk-usun">
          <div className="overlap-group-2">
            <img className="status-guzik" alt="Status guzik" src="https://c.animaapp.com/t0STnSju/img/status-guzik-6@2x.png" />
            <img className="trash" alt="Trash" src="https://c.animaapp.com/t0STnSju/img/trash-1-2@2x.png" />
            <div className="text-wrapper-8">usuń</div>
          </div>
        </div>
        <div className="przycisk-przeloz">
          <div className="overlap-3">
            <img className="img" alt="Status guzik" src="https://c.animaapp.com/t0STnSju/img/status-guzik-5@2x.png" />
            <img className="pencil" alt="Pencil" src="https://c.animaapp.com/t0STnSju/img/pencil-1-2@2x.png" />
            <div className="text-wrapper-9">przełóż</div>
          </div>
        </div>
        <div className="overlap-4">
          <img className="data-rect" alt="Data rect" src="https://c.animaapp.com/t0STnSju/img/data-rect-2.png" />
          <img className="godzina-rect" alt="Godzina rect" src="https://c.animaapp.com/t0STnSju/img/data-rect-2.png" />
          <div className="text-wrapper-10">Data:</div>
          <div className="text-wrapper-11">Godzina:</div>
        </div>
      </div>
    </div>
  </div>
);
};

export default EventAdmin;