import React, { useEffect, useState } from 'react';
import "./PrognozaPogody.css";

const PrognozaPogody = () => {
  const [pogoda, setPogoda] = useState(null);

  useEffect(() => {
    // Tutaj dodaj kod do pobierania danych prognozy pogody z API OpenWeather
    // Pamiętaj o zabezpieczeniu klucza API i obsłudze błędów.
    const fetchData = async () => {
      try {
        const apiKey = '2a22ea7242da86deb44ea46357b5236d';
        const city = 'Kraków'; // można również użyć współrzędnych geograficznych
        const apiUrl = `https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${apiKey}`;

        const response = await fetch(apiUrl);
        const data = await response.json();

        setPogoda(data); // Ustawienie danych prognozy pogody w stanie komponentu
      } catch (error) {
        console.error('Błąd podczas pobierania danych z OpenWeather API', error);
      }
    };

    fetchData();
  }, []);

  return (
    <div className="prognoza-pogody">
      {/* Tutaj umieść kod do wyświetlania danych prognozy pogody */}
      {pogoda && (
        <div className="prognoza-pogody-content">
        <h2>{pogoda.name}</h2>
        <p>Temperatura: {Math.round(pogoda.main.temp - 273.15)}°C</p>
          <p>Warunki atmosferyczne: {pogoda.weather[0].description}</p>
          {/* Tu będą inne dane prognozy pogody  */}
          {/* Na przykład, ikona pogodowa: */}
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