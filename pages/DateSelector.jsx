{/*import React, { useState } from 'react';
import "./DateSelector.css";
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';

const DateSelector = () => {
  const [selectedDate, setSelectedDate] = useState(null);

  const handleDateChange = (date) => {
    setSelectedDate(date);
  };

  return (
    <div>
      <h1>Wybierz datę, żeby sprawdzić harmonogram:</h1>
      <DatePicker
        selected={selectedDate}
        onChange={handleDateChange}
        dateFormat="dd/MM/yyyy" 
      />
    </div>
  );
};

export default DateSelector;*/}



import React, { useState } from 'react';
import "./DateSelector.css";
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';

const DateSelector = ({ onSelectDate }) => {
  const [selectedDate, setSelectedDate] = useState(null);

  const handleDateChange = async (date) => {
    setSelectedDate(date);

    try {
      // Poprawa: Przekazujemy datę z powrotem do rodzica
      onSelectDate(date);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  return (
    <div>
      <h1>Wybierz datę, żeby sprawdzić harmonogram:</h1>
      <DatePicker
        selected={selectedDate}
        onChange={handleDateChange}
        dateFormat="dd/MM/yyyy" 
      />
    </div>
  );
};

export default DateSelector;



