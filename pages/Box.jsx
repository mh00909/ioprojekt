import React from "react";
import "./Box.css";
import "../app/globals.css"

const Box = () => {
    return (
        <div className="box">
            <div className="group">
                <div className="karteczka">
                    <div className="overlap">
                        <div className="zajecie-wrapper">
                            <div className="zajecie">
                                <div className="overlap-group">
                                    <img className="status-guzik" alt="Status guzik" src="https://c.animaapp.com/RraaDVLa/img/status-guzik@2x.png" />
                                    <div className="text-wrapper">miejsca</div>
                                    <div className="div">lokalizacja</div>
                                    <div className="text-wrapper-2">Rodzaj zajęć</div>
                                    <div className="overlap-2">
                                        <div className="text-wrapper-3">Dzień</div>
                                        <div className="text-wrapper-4">Godzina</div>
                                    </div>
                                    <div className="text-wrapper-5">Czas trwania</div>
                                </div>
                            </div>
                        </div>
                        <img className="map" alt="Map" src="https://c.animaapp.com/RraaDVLa/img/map@2x.png" />
                        <img className="users" alt="Users" src="https://c.animaapp.com/RraaDVLa/img/users@2x.png" />
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Box;
