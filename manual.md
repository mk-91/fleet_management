# Fleet Management System
## Instrukcja obsługi

---

## 1. Informacje ogólne

**Fleet Management System** to konsolowa aplikacja napisana w języku Java, służąca do zarządzania flotą pojazdów. Program umożliwia:

- dodawanie i usuwanie pojazdów,
- przypisywanie użytkowników (kierowców),
- rejestrowanie przebiegów,
- rejestrowanie napraw,
- przeglądanie aktualnego stanu floty,
- trwały zapis danych do pliku tekstowego (`fleet_data.txt`).

Dane są **automatycznie wczytywane przy starcie programu** oraz **zapisywane przy każdej modyfikacji i przy zamknięciu aplikacji**.

---

## 2. Wymagania systemowe

- Java JDK **8 lub nowsza**
- System operacyjny: Windows / Linux / macOS
- Dostęp do konsoli (terminala)

---

## 3. Uruchomienie programu

1. Skompiluj projekt (np. w IDE lub z linii poleceń):
   ```bash
   javac fleet_management/*.java
   ```

2. Uruchom aplikację:
   ```bash
   java fleet_management.Fleet_management
   ```

3. Przy pierwszym uruchomieniu plik `fleet_data.txt` zostanie utworzony automatycznie.

---

## 4. Trwałość danych

- Wszystkie dane są przechowywane w pliku:
  ```
  fleet_data.txt
  ```
- Plik znajduje się w katalogu roboczym aplikacji.
- **Nie należy edytować pliku ręcznie**, ponieważ może to spowodować błędy przy wczytywaniu danych.

---

## 5. Menu główne aplikacji

Po uruchomieniu programu wyświetlane jest menu główne:

```
1. Wyświetl wszystkie pojazdy
2. Dodaj nowy pojazd
3. Usuń pojazd
4. Dodaj przebieg
5. Dodaj naprawę
6. Przypisz użytkownika do pojazdu
0. Zakończ program
```

Użytkownik wybiera opcję, wpisując odpowiednią cyfrę i zatwierdzając klawiszem ENTER.

---

## 6. Opis funkcjonalności

### 6.1 Wyświetlanie pojazdów
Opcja umożliwia wyświetlenie listy wszystkich pojazdów zapisanych w systemie wraz z:
- danymi podstawowymi,
- przypisanym użytkownikiem (jeśli istnieje),
- historią przebiegów,
- historią napraw.

---

### 6.2 Dodawanie nowego pojazdu
Użytkownik podaje kolejno:
- typ pojazdu,
- markę,
- model,
- numer rejestracyjny,
- kolor,
- rok produkcji,
- datę ważności ubezpieczenia,
- datę ważności przeglądu technicznego.

!!! Numer rejestracyjny musi być unikalny.

---

### 6.3 Usuwanie pojazdu
Pojazd usuwany jest na podstawie numeru rejestracyjnego.

!!! Operacja jest nieodwracalna.

---

### 6.4 Dodawanie przebiegu
Użytkownik podaje:
- numer rejestracyjny pojazdu,
- datę,
- stan licznika (km).

!!! Nowy przebieg nie może być mniejszy niż ostatnio zapisany.

---

### 6.5 Dodawanie naprawy
Użytkownik podaje:
- numer rejestracyjny pojazdu,
- datę naprawy,
- koszt naprawy,
- opis naprawy.

---

### 6.6 Przypisanie użytkownika do pojazdu
Użytkownik podaje:
- numer rejestracyjny pojazdu,
- imię kierowcy,
- nazwisko kierowcy,
- numer telefonu.

Każdy pojazd może mieć przypisanego jednego użytkownika.

---

## 7. Zamykanie programu

Opcja:
```
0. Zakończ program
```

Powoduje:
- zapis wszystkich danych do pliku `fleet_data.txt`,
- bezpieczne zakończenie działania aplikacji.

---

## 8. Obsługa błędów

Program automatycznie obsługuje m.in.:
- duplikaty numerów rejestracyjnych,
- błędne formaty dat,
- niepoprawne wartości liczbowe,
- brak pojazdu o podanym numerze rejestracyjnym.

Komunikaty o błędach wyświetlane są w konsoli.

---

## 9. Uwagi końcowe

- Program jest przeznaczony do obsługi z poziomu konsoli.
- Dane są zapisywane lokalnie – brak integracji sieciowej.
- Aplikacja może być dalej rozbudowywana (GUI, baza danych, eksport CSV).

---

## 10. Autor i przeznaczenie

Projekt wykonany w celach edukacyjnych  
Autor: **Jakub Bąk, Mateusz Kurzawa**  
Przedmiot: **Programowanie Obiektowe / Java**  
Forma: **aplikacja konsolowa**

---
