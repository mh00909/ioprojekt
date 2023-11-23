import React, { useEffect, useState } from 'react';
import "./PrognozaPogody.css";

const PrognozaPogody = () => {
  const [pogoda, setPogoda] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const apiKey = '2a22ea7242da86deb44ea46357b5236d';
        const city = 'Kraków';
        const apiUrl = `https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${apiKey}`;

        const response = await fetch(apiUrl);
        const data = await response.json();

        setPogoda(data);
      } catch (error) {
        console.error('Błąd podczas pobierania danych z OpenWeather API', error);
      }
    };

    fetchData();
  }, []);

  return (
    <div className="prognoza-pogody">
      {pogoda && (
        <div className="prognoza-pogody-content">
          <h2>{pogoda.name}</h2>
          <p>Temperatura: {Math.round(pogoda.main.temp - 273.15)}°C</p>
          <p>Warunki atmosferyczne: {pogoda.weather[0].description}</p>
          <p>Ciśnienie: {pogoda.main.pressure} hPa</p>
          <p>Wilgotność: {pogoda.main.humidity}%</p>
          <p>Wschód słońca: {new Date(pogoda.sys.sunrise * 1000).toLocaleTimeString()}</p>
          <p>Zachód słońca: {new Date(pogoda.sys.sunset * 1000).toLocaleTimeString()}</p>
          
          {pogoda.weather[0].icon && (
            <img
              src={`https://openweathermap.org/img/w/${pogoda.weather[0].icon}.png`}
              alt="Ikona pogodowa"
            />
          )}
        </div>
      )}
    </div>
  );
};

export default PrognozaPogody;
