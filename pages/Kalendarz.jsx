
/*import React, { useState, useEffect } from 'react';
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';

const MyCalendar = () => {
  const [date, setDate] = useState(new Date());
  const [dynamicText, setDynamicText] = useState('Twój kalendarz');

  const onChange = (newDate) => {
    setDate(newDate);
  };

  useEffect(() => {
    // Tutaj możesz umieścić kod, który wprowadza dynamiczne zmiany w tekście
    // w zależności od danych na stronie.
    if (date.getDay() === 1) {
      setDynamicText('Dziś jest poniedziałek!');
    } else {
      setDynamicText('Twój kalendarz');
    }
  }, [date]); // Wskaż date jako zależność

  return (
    <div>
      <h2>{dynamicText}</h2>
      <Calendar
        onChange={onChange}
        value={date}
      />
    </div>
  );
};

export default MyCalendar;
*/