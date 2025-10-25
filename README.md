# fleet_management
Object-oriented programming project

Temat projektu
System do zarządzania flotą samochodów w przedsiębiorstwie

Wymagania funkcjonalne
•	Dodanie nowego pojazdu – możliwość zapisania danych: marka, model, numer rejestracyjny, kolor, rok produkcji, termin ważności ubezpieczenia OC, termin ważności badań technicznych, dane użytkownika (imię, nazwisko, numer telefonu).
•	Wyświetlenie listy wszystkich pojazdów – w formie zestawienia podstawowych danych.
•	Wyświetlenie szczegółowych informacji o wybranym pojeździe – wszystkie dane wraz z historią.
•	Rejestrowanie historii przebiegu pojazdu – dodanie wpisu (data, stan licznika).
•	Rejestrowanie historii napraw – dodanie wpisu (data, koszt, opis).
•	Sprawdzanie krytycznych terminów – lista pojazdów, dla których OC lub badanie techniczne wygasają wkrótce (np. w ciągu 30 dni).

Model obiektowy – propozycja klas
Car (Samochód)
•	marka, model, numer rejestracyjny, kolor, rok produkcji
•	termin ważności OC, termin ważności badań technicznych
•	użytkownik (User)
•	listy: MileageHistory, RepairHistory
User (Użytkownik pojazdu)
•	imię, nazwisko, numer telefonu
MileageEntry (Wpis przebiegu)
•	data, przebieg
RepairEntry (Wpis naprawy)
•	data, koszt, opis
FleetManager (Zarządzanie flotą)
•	lista samochodów (Car)
•	metody: dodaj pojazd, usuń pojazd, wyszukaj pojazd, wyświetl flotę, dodaj wpis przebiegu, dodaj wpis naprawy, sprawdź krytyczne terminy

Forma aplikacji
Aplikacja konsolowa (tekstowa) z prostym menu, np.:
1. Dodaj pojazd
2. Wyświetl listę pojazdów
3. Pokaż szczegóły pojazdu
4. Dodaj przebieg
5. Dodaj naprawę
6. Sprawdź krytyczne terminy
0. Zakończ
