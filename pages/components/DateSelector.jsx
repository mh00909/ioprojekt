import React, { useState } from "react";
import "./DateSelector.css";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { format } from "date-fns";



/**
 * Komponent `DateSelector` umożliwia użytkownikowi wybieranie daty w celu sprawdzenia harmonogramu.
 * @component
 * @param {Object} props - Właściwości komponentu.
 * @param {Function} props.onSelectDate - Funkcja wywoływana po wybraniu daty.
 * @returns {JSX.Element} - Zwraca element JSX reprezentujący sekcję wyboru daty.
 */
const DateSelector = ({ onSelectDate }) => {
  const [selectedDate, setSelectedDate] = useState(null);
  /**
   * Obsługuje zmianę wybranej daty i wywołuje funkcję `onSelectDate` z sformatowaną datą.
   * @param {Date} date - Obiekt reprezentujący nowo wybraną datę.
   */
  const handleDateChange = (date) => {
    setSelectedDate(date);

    try {
      const formattedDate = format(date, "yyyy-MM-dd");
      onSelectDate(formattedDate);
    } catch (error) {
      console.error("Error fetching data:", error);
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
