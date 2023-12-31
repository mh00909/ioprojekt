# System rezerwacji miejsc na zajęcia w oparciu o dane pogodowe ReserveTheWeather

## Instrukcje Kompilacji i Uruchomienia

### Backend (Java Spring)

**Upewnij się, że masz zainstalowaną Javę 21, a także Spring Boot.**
1. **Pod linkiem https://github.com/mh00909/ioprojekt/tree/master999 skopiuj ades url do schowka.
 W wybranym środowisku programistycznym utwórz nowy plik z wersji kontrolnych podając adres url **


2. **Konfiguracja bazy danych:**
    - Upewnij się, że masz założoną lokalną bazę danych PostgreSQL, jeśli jeszcze tego nie zrobiłeś wykonaj ten krok.
    - Otwórz plik `ioprojekt/src/main/resources/application.yml`.
    - Zmodyfikuj sekcję dotyczącą bazy danych, ustawiając odpowiednie dane (username, password) dostępowe do lokalnej bazy PostgreSQL.

3. **Kompilacja i Uruchomienie backendu:**
 - W pliku: `src/main/java/com/ioproject/reservetheweather/ReservetheweatherApplication.java`wybierz opcję "Run"


Jeśli kroki zostaną wykonane prawidłowo backend zostanie uruchomiony na `http://localhost:8080`.

### Frontend (Next.js)

1. **Pod linkiem https://github.com/mh00909/ioprojekt/tree/master999 skopiuj ades url do schowka.
 W wybranym środowisku programistycznym utwórz nowy plik z wersji kontrolnych podając adres url **


2. **Instalacja zależności:**
    ```bash
    npm install
    ```

3. **Uruchomienie frontendu:**
    ```bash
    npm run dev
    ```

Frontend zostanie uruchomiony na `http://localhost:3000`.

## Konfiguracja Bazy Danych

W pliku `backend/src/main/resources/application.yml` znajdziesz sekcję dotyczącą konfiguracji bazy danych PostgreSQL. Ustaw odpowiednie dane dostępowe do swojej lokalnej bazy danych.

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/twoja_baza
    username: twoj_uzytkownik
    password: twoje_haslo
