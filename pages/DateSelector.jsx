import React, { useState } from 'react';
import './DateSelector.css';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import { format } from 'date-fns';

const DateSelector = ({ onSelectDate }) => {
  const [selectedDate, setSelectedDate] = useState(null);

  const handleDateChange = (date) => {
    setSelectedDate(date);

    try {
      const formattedDate = format(date, 'yyyy-MM-dd');
      onSelectDate(formattedDate);
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
        dateFormat="yyyy-MM-dd" 
      />
    </div>
  );
};

export default DateSelector;




