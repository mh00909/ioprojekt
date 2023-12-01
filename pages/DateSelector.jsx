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

const DateSelector = ({ fetchData }) => {
  const [selectedDate, setSelectedDate] = useState(null);

  const handleDateChange = async (date) => {
    setSelectedDate(date);

    try {
      const response = await fetch(`http://localhost:8080/api/events/byDate?date=${date.toISOString().split('T')[0]}`);
      const data = await response.json();

      fetchData(data);
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