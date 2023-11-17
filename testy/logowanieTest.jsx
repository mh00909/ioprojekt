import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect'; // dla dodatkowych matcherów
import Logowanie from './Logowanie';

// Mock dla modułu API
jest.mock('../api', () => ({
  post: jest.fn(() => Promise.resolve({ data: 'Strona domowa' })),
}));

describe('Komponent Logowanie', () => {
  it('renderuje formularz logowania', () => {
    render(<Logowanie />);
    
    expect(screen.getByLabelText(/login username/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/login password/i)).toBeInTheDocument();
    expect(screen.getByText(/zaloguj/i)).toBeInTheDocument();
  });

  it('obsługuje poprawne zalogowanie', async () => {
    render(<Logowanie />);
    
    fireEvent.change(screen.getByLabelText(/login username/i), { target: { value: 'testuser' } });
    fireEvent.change(screen.getByLabelText(/login password/i), { target: { value: 'testpassword' } });

    fireEvent.click(screen.getByText(/zaloguj/i));

    // Czekaj na asynchroniczną operację
    await waitFor(() => {
      expect(screen.getByText(/udało się zalogować/i)).toBeInTheDocument();
    });
  });

  it('obsługuje błąd podczas logowania', async () => {
    // Mock odpowiedzi z API, aby symulować błąd
    jest.spyOn(require('../api'), 'post').mockImplementation(() => Promise.reject({ response: { status: 401 } }));

    render(<Logowanie />);
    
    fireEvent.change(screen.getByLabelText(/login username/i), { target: { value: 'invaliduser' } });
    fireEvent.change(screen.getByLabelText(/login password/i), { target: { value: 'invalidpassword' } });

    fireEvent.click(screen.getByText(/zaloguj/i));

    // Czekaj na asynchroniczną operację
    await waitFor(() => {
      expect(screen.getByText(/niepoprawne logowania/i)).toBeInTheDocument();
    });
  });
});

