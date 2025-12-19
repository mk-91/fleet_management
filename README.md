# fleet_management
Object-oriented programming project

## Temat projektu
System do zarządzania flotą samochodów w przedsiębiorstwie

## Zespół
Jakub Bąk, Mateusz Kurzawa

## Wymagania funkcjonalne
- Dodanie nowego pojazdu – możliwość zapisania danych: marka, model, numer rejestracyjny (unikalny), kolor, rok produkcji, termin ważności ubezpieczenia OC, termin ważności badań technicznych, dane użytkownika (imię, nazwisko, numer telefonu).
- Wyświetlenie listy wszystkich pojazdów – w formie zestawienia podstawowych danych.
-	Wyświetlenie szczegółowych informacji o wybranym pojeździe – wszystkie dane wraz z historią.
-	Rejestrowanie historii przebiegu pojazdu – dodanie wpisu (data, stan licznika).
-	Rejestrowanie historii napraw – dodanie wpisu (data, koszt, opis).
-	Sprawdzanie krytycznych terminów – lista pojazdów, dla których OC lub badanie techniczne wygasają wkrótce (np. w ciągu 30 dni).

## Forma aplikacji
Aplikacja konsolowa (tekstowa) z prostym menu, np.:
1. Wyświetl listę pojazdów
2. Pokaż szczegóły pojazdu
3. Dodaj przebieg
4. Dodaj naprawę
5. Sprawdź krytyczne terminy
6. Dodaj pojazd
7. Aktualizuj pojazd
8. Usuń pojazd
0. Zakończ

## Model obiektowy – propozycja klas
1. Vehicle (abstrakcyjna klasa bazowa)
  - marka, model, numer rejestracyjny (unikalny), kolor, rok produkcji
  - termin ważności OC, termin ważności badań technicznych
  - użytkownik (User)
  - listy: List<MileageEntry>, List<RepairEntry>
  - metoda abstrakcyjna: getVehicleType()
  - metody: sprawdzenie krytycznych terminów, dodawanie wpisów historii
2. Car (Samochód) – dziedziczy po Vehicle
  - implementacja metody getVehicleType()
3. User (Użytkownik pojazdu)
  - imię, nazwisko, numer telefonu
4. MileageEntry (Wpis przebiegu)
  - data, przebieg
  - walidacja poprawności przebiegu (nie mniejszy niż poprzedni)
5. RepairEntry (Wpis naprawy)
  - data, koszt, opis
6. FleetManager (logika zarządzania flotą)
  - dodaj / usuń / aktualizuj pojazd
  - wyszukaj pojazd
  - dodaj przebieg
  - dodaj naprawę
  - sprawdź krytyczne terminy
7. Repository<T> (interfejs)
  - metody: save, findById, findAll, delete
8. InMemoryVehicleRepository
  - implementuje Repository<Vehicle>
  - przechowuje pojazdy w kolekcji
  - statyczne pole do generowania identyfikatorów
9. VehicleType (enum)
  - np. CAR
  - umożliwia łatwe rozszerzenie systemu o inne typy pojazdów (np. TRUCK)
10. Color (enum)
  - predefiniowane kolory pojazdów
11. DuplicateRegistrationException
  - wyjątek zgłaszany przy próbie dodania pojazdu z istniejącym numerem rejestracyjnym
12. InvalidMileageException
  - wyjątek zgłaszany przy nieprawidłowym wpisie przebiegu
13. DateUtils (klasa narzędziowa)
  - statyczne metody do obsługi dat i sprawdzania terminów
