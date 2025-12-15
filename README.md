# fleet_management
Object-oriented programming project

## Temat projektu
System do zarządzania flotą samochodów w przedsiębiorstwie

## Zespół
Jakub Bąk, Mateusz Kurzawa

## Wymagania funkcjonalne
- Dodanie nowego pojazdu – możliwość zapisania danych: marka, numer rejestracyjny, kolor, rok produkcji, termin ważności ubezpieczenia OC, termin ważności badań technicznych, dane użytkownika (imię, nazwisko, numer telefonu).
- Wyświetlenie listy wszystkich pojazdów – w formie zestawienia podstawowych danych.
-	Wyświetlenie szczegółowych informacji o wybranym pojeździe – wszystkie dane wraz z historią.
-	Rejestrowanie historii przebiegu pojazdu – dodanie wpisu (data, stan licznika).
-	Rejestrowanie historii napraw – dodanie wpisu (data, koszt, opis).
-	Sprawdzanie krytycznych terminów – lista pojazdów, dla których OC lub badanie techniczne wygasają wkrótce (np. w ciągu 30 dni).
**Jeżeli chodzi o pojazd, musimy zapewnić operację CRUD (Create, Read, Update, and Delete) + warto też wymyślić nietypową opcję żeby dostać lepszą ocenę**

## Forma aplikacji
Aplikacja konsolowa (tekstowa) z prostym menu, np.:
1. Dodaj pojazd
2. Wyświetl listę pojazdów
3. Pokaż szczegóły pojazdu
4. Dodaj przebieg
5. Dodaj naprawę
6. Sprawdź krytyczne terminy
0. Zakończ

## Model obiektowy – propozycja klas
1. Car (Samochód)
  -	marka, model, numer rejestracyjny, kolor, rok produkcji
  -	termin ważności OC, termin ważności badań technicznych
  -	użytkownik (User)
  -	listy: MileageHistory, RepairHistory
2. User (Użytkownik pojazdu)
  -	imię, nazwisko, numer telefonu
3. MileageEntry (Wpis przebiegu)
  -	data, przebieg
4. RepairEntry (Wpis naprawy)
  -	data, koszt, opis
5. FleetManager (Zarządzanie flotą)
  -	lista samochodów (Car)
  -	metody: dodaj pojazd, usuń pojazd, wyszukaj pojazd, wyświetl flotę, dodaj wpis przebiegu, dodaj wpis naprawy, sprawdź krytyczne terminy
