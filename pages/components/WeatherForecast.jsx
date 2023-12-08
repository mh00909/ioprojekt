import React, { useEffect, useState } from 'react';
import "./WeatherForecast.css";

const PrognozaPogody = () => {
  const [weather, setWeather] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const apiKey = '2a22ea7242da86deb44ea46357b5236d';
        const city = 'Krakow';
        const apiUrl = `https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${apiKey}`;

        const response = await fetch(apiUrl);
        const data = await response.json();

        setWeather(data);
      } catch (error) {
        console.error('Błąd podczas pobierania danych z OpenWeather API', error);
      }
    };

    fetchData();
  }, []);

  return (
    <div className="weather-forecast">
      {weather && (
        <div className="weather-forecast-content">
          <h2>{weather.name}</h2>
          <p>Temperatura: {Math.round(weather.main.temp - 273.15)}°C</p>
          <p>Warunki atmosferyczne: {weather.weather[0].description}</p>
          <p>Ciśnienie: {weather.main.pressure} hPa</p>
          <p>Wilgotność: {weather.main.humidity}%</p>
          <p>Wschód słońca: {new Date(weather.sys.sunrise * 1000).toLocaleTimeString()}</p>
          <p>Zachód słońca: {new Date(weather.sys.sunset * 1000).toLocaleTimeString()}</p>
          
          {weather.weather[0].icon && (
            <img
              src={`https://openweathermap.org/img/w/${weather.weather[0].icon}.png`}
              alt="Weather Icon"
            />
          )}
        </div>
      )}
    </div>
  );
};

export default PrognozaPogody;
