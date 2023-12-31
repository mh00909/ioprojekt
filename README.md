# System rezerwacji miejsc na zajęcia w oparciu o dane pogodowe ReserveTheWeather

## Instrukcje Kompilacji i Uruchomienia

### Backend (Java Spring)

**Upewnij się, że masz zainstalowaną Javę 21, a także Spring Boot.**
1. **Pod linkiem https://github.com/mh00909/ioprojekt/tree/master999 skopiuj ades url do schowka:**
 ![image](https://github.com/mh00909/ioprojekt/assets/115782747/cd0c80eb-4edb-444e-933e-cac4c8458063)

-  W wybranym środowisku programistycznym utwórz nowy plik z wersji kontrolnych:
 ![image](https://github.com/mh00909/ioprojekt/assets/115782747/6c678e1a-deee-4c00-993b-252ee235a698)
 - Podaj adres url:
   
 ![image](https://github.com/mh00909/ioprojekt/assets/115782747/49ebc53a-2a3e-4fcd-a1f3-1c17684c2ab6)


2. **Konfiguracja bazy danych:**
    - Upewnij się, że masz założoną lokalną bazę danych PostgreSQL, jeśli jeszcze tego nie zrobiłeś wykonaj ten krok.
    - Otwórz plik `ioprojekt/src/main/resources/application.yml`.
    - Zmodyfikuj sekcję dotyczącą bazy danych, ustawiając odpowiednie dane dostępowe (username, password) do lokalnej bazy PostgreSQL.

3. **Kompilacja i Uruchomienie backendu:**
 - W pliku: `src/main/java/com/ioproject/reservetheweather/ReservetheweatherApplication.java`wybierz opcję "Run"


Jeśli kroki zostaną wykonane prawidłowo backend zostanie uruchomiony na `http://localhost:8080`.

### Frontend (Next.js)

1. **Pod linkiem https://github.com/mh00909/ioprojekt/tree/master999 skopiuj ades url do schowka**
 W wybranym środowisku programistycznym utwórz nowy plik z wersji kontrolnych podając adres url 


2. **Instalacja zależności:**
    ```bash
    npm install
    ```

3. **Uruchomienie frontendu:**
    ```bash
    npm run dev
    ```

Frontend zostanie uruchomiony na `http://localhost:3000`.

## Konfiguracja Bazy Danych:

W pliku `src/main/resources/application.yml` znajdziesz sekcję dotyczącą konfiguracji bazy danych PostgreSQL. Ustaw odpowiednie dane dostępowe do swojej lokalnej bazy danych.

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/twoja_baza
    username: twoj_uzytkownik
    password: twoje_haslo
```

## Poszczególne etapy projektu:
 
Prezentacja Etap 25.10.2023:  (https://github.com/mh00909/ioprojekt/blob/1eb8261e076a1925cea559602b2635c548fd3f9e/Prezentacja%20Etap%2025.10.2023.pdf)

Etap 15.11.2023: (https://github.com/mh00909/ioprojekt/blob/1eb8261e076a1925cea559602b2635c548fd3f9e/Etap%2015.11.2023.pdf)

Etap 10.12.2023: (https://github.com/mh00909/ioprojekt/blob/1eb8261e076a1925cea559602b2635c548fd3f9e/10.12%20-%20In%C5%BCynieria%20Oprogramowania.pdf)

## Dokumentacja użytkownika (Samouczek nawigacji po stronach):
[Przejście do logowaniarejestracji (2).pdf](https://github.com/mh00909/ioprojekt/files/13801710/Przejscie.do.logowaniarejestracji.2.pdf)

