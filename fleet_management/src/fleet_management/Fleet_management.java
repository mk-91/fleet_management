package fleet_management;

import fleet_management.model.*;
import fleet_management.repositories.FleetRepository;
import fleet_management.repositories.Repository;
import fleet_management.exceptions.*;

import java.time.LocalDate;
import java.math.BigDecimal; // Potrzebne do kosztów naprawy
import java.util.Scanner;

public class Fleet_management {

    private static Repository<Vehicle> repository = new FleetRepository();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Dane startowe
        repository.save(new Car("Toyota", "Corolla", "KR 12345", Color.SILVER, 2020, LocalDate.now(), LocalDate.now()));
        
        boolean running = true;
        while (running) {
            printMenu();
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    showAllVehicles();
                    break;
                case "2":
                    showVehicleDetails();
                    break;
                case "3":
                    addMileage();
                    break;
                case "4":
                    addRepair();
                    break;
                case "5":
                checkDeadlines();
                    break;
                case "6":
                    addNewVehicle();
                    break;
                case "7":
                updateVehicle();
                    break;
                case "8":
                    deleteVehicle();
                    break;
                case "0":
                    running = false;
                    System.out.println("Zamykanie systemu...");
                    break;
                default:
                    System.out.println("Nieznana opcja.");
            }
            if(running) {
                System.out.println("\nWciśnij ENTER, aby kontynuować...");
                scanner.nextLine();
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=================================");
        System.out.println("   SYSTEM ZARZĄDZANIA FLOTĄ");
        System.out.println("=================================");
        System.out.println("1. Wyświetl listę pojazdów");
        System.out.println("2. Pokaż szczegóły pojazdu (Historia)");
        System.out.println("3. Dodaj przebieg");
        System.out.println("4. Dodaj naprawę");
        System.out.println("5. Sprawdź krytyczne terminy");
        System.out.println("6. Dodaj nowy pojazd");
        System.out.println("7. Aktualizuj pojazd");
        System.out.println("8. Usuń pojazd");
        System.out.println("0. Zakończ");
        System.out.print("Wybierz opcję: ");
    }

    // --- METODY OBSŁUGI ---

    private static void showAllVehicles() {
        System.out.println("\n--- LISTA POJAZDÓW ---");
        var list = repository.findAll();
        if (list.isEmpty()) {
            System.out.println("Brak pojazdów.");
        } else {
            for (Vehicle v : list) {
                System.out.println(v);
            }
        }
    }

    private static void showVehicleDetails() {
        Vehicle v = findVehicleByReg(); // Używamy metody pomocniczej
        if (v != null) {
            System.out.println("\n--- SZCZEGÓŁY POJAZDU ---");
            System.out.println("Marka/Model: " + v.getBrand() + " " + v.getModel());
            System.out.println("Rejestracja: " + v.getRegistrationNumber());
            System.out.println("Typ: " + v.getVehicleType());
            // Tu można dodać wyświetlanie dat ubezpieczenia itp.
            
            System.out.println("\n[Historia Przebiegu]:");
            if (v.getMileageHistory().isEmpty()) System.out.println(" - Brak wpisów.");
            for (MileageEntry m : v.getMileageHistory()) {
                System.out.println(" - " + m);
            }

            System.out.println("\n[Historia Napraw]:");
            if (v.getRepairHistory().isEmpty()) System.out.println(" - Brak wpisów.");
            for (RepairEntry r : v.getRepairHistory()) {
                System.out.println(" - " + r);
            }
        }
    }

    private static void addMileage() {
        System.out.println("\n--- DODAWANIE PRZEBIEGU ---");
        Vehicle v = findVehicleByReg();
        if (v != null) {
            try {
                System.out.print("Podaj aktualny przebieg (km): ");
                long km = Long.parseLong(scanner.nextLine());
                
                // Dodajemy wpis (walidacja jest w klasie Vehicle)
                v.addMileage(new MileageEntry(LocalDate.now(), km));
                System.out.println("SUKCES: Przebieg zaktualizowany.");
            } catch (InvalidMileageException e) {
                System.out.println("BŁĄD: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("BŁĄD: To nie jest liczba!");
            }
        }
    }

    private static void addRepair() {
        System.out.println("\n--- REJESTRACJA NAPRAWY ---");
        Vehicle v = findVehicleByReg();
        if (v != null) {
            try {
                System.out.print("Opis naprawy: ");
                String desc = scanner.nextLine();
                
                System.out.print("Koszt (np. 150.50): ");
                BigDecimal cost = new BigDecimal(scanner.nextLine().replace(",", ".")); // zamiana przecinka na kropkę
                
                v.addRepair(new RepairEntry(LocalDate.now(), cost, desc));
                System.out.println("SUKCES: Naprawa zarejestrowana.");
            } catch (Exception e) {
                System.out.println("BŁĄD: Niepoprawne dane (np. koszt).");
            }
        }
    }

    private static void addNewVehicle() {
        try {
            System.out.println("\n--- DODAWANIE NOWEGO POJAZDU ---");
            System.out.print("Podaj markę: ");
            String brand = scanner.nextLine();
            System.out.print("Podaj model: ");
            String model = scanner.nextLine();
            System.out.print("Numer rejestracyjny: ");
            String reg = scanner.nextLine();
            System.out.print("Rok produkcji: ");
            int year = Integer.parseInt(scanner.nextLine());
            
            Vehicle newCar = new Car(brand, model, reg, Color.OTHER, year, LocalDate.now().plusYears(1), LocalDate.now().plusYears(1));
            repository.save(newCar);
            System.out.println("SUKCES: Dodano pojazd.");
        } catch (DuplicateRegistrationException e) {
            System.out.println("BŁĄD: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("BŁĄD: " + e.getMessage());
        }
    }

    private static void deleteVehicle() {
        System.out.println("\n--- USUWANIE POJAZDU ---");
        Vehicle v = findVehicleByReg();
        if (v != null) {
            repository.delete(v);
            System.out.println("SUKCES: Pojazd usunięty.");
        }
    }

    // --- METODA POMOCNICZA ---
    // Pyta o numer rejestracyjny i szuka auta w bazie
    private static Vehicle findVehicleByReg() {
        System.out.print("Podaj numer rejestracyjny pojazdu: ");
        String reg = scanner.nextLine();
        
        for (Vehicle v : repository.findAll()) {
            if (v.getRegistrationNumber().equalsIgnoreCase(reg)) {
                return v;
            }
        }
        System.out.println("BŁĄD: Nie znaleziono pojazdu o numerze: " + reg);
        return null;
    }
    private static void checkDeadlines() {
        System.out.println("\n--- KRYTYCZNE TERMINY (Poniżej 30 dni) ---");
        boolean found = false;
        
        for (Vehicle v : repository.findAll()) {
            if (v.isDeadlineSoon()) {
                found = true;
                System.out.println("ALARM: " + v.getBrand() + " " + v.getModel() + " [" + v.getRegistrationNumber() + "]");
                System.out.println("  -> OC ważne do: " + v.getInsuranceValid());
                System.out.println("  -> Przegląd do: " + v.getInspectionValid());
            }
        }
        
        if (!found) {
            System.out.println("Wszystkie pojazdy mają aktualne terminy.");
        }
    }
    private static void updateVehicle() {
        System.out.println("\n--- AKTUALIZACJA DANYCH POJAZDU ---");
        Vehicle v = findVehicleByReg(); // Najpierw szukamy auta
        
        if (v != null) {
            System.out.println("Wybrano: " + v.getBrand() + " " + v.getModel());
            System.out.println("1. Zmień kierowcę");
            System.out.println("2. Przedłuż ważność badań/OC");
            System.out.println("3. Zmień kolor");
            System.out.println("0. Anuluj");
            System.out.print("Wybierz co chcesz zmienić: ");
            
            String choice = scanner.nextLine();
            
            try {
                switch (choice) {
                    case "1":
                        System.out.print("Podaj imię nowego kierowcy: ");
                        String imie = scanner.nextLine();
                        System.out.print("Podaj nazwisko: ");
                        String nazwisko = scanner.nextLine();
                        System.out.print("Podaj telefon: ");
                        String tel = scanner.nextLine();
                        v.assignUser(new User(imie, nazwisko, tel));
                        System.out.println("SUKCES: Zmieniono kierowcę.");
                        break;
                        
                    case "2":
                        System.out.println("Aktualne OC: " + v.getInsuranceValid());
                        System.out.println("Aktualny przegląd: " + v.getInspectionValid());
                        
                        // Dla uproszczenia przedłużamy o rok od dzisiaj
                        v.setInsuranceValid(LocalDate.now().plusYears(1));
                        v.setInspectionValid(LocalDate.now().plusYears(1));
                        
                        System.out.println("SUKCES: Terminy zostały odnowione (ważne przez rok od dziś).");
                        break;
                        
                    case "3":
                        System.out.println("Dostępne kolory: BLACK, WHITE, SILVER, RED, BLUE, OTHER");
                        System.out.print("Wpisz nowy kolor (po angielsku): ");
                        String colorStr = scanner.nextLine().toUpperCase();
                        v.setColor(Color.valueOf(colorStr)); // Zamienia napis na Enum
                        System.out.println("SUKCES: Zmieniono kolor.");
                        break;
                        
                    case "0":
                        System.out.println("Anulowano.");
                        break;
                        
                    default:
                        System.out.println("Niepoprawna opcja.");
                }
            } catch (IllegalArgumentException e) {
                // Ten błąd wyskoczy, jak ktoś wpisze zły kolor
                System.out.println("BŁĄD: Nie ma takiego koloru na liście!");
            } catch (Exception e) {
                System.out.println("BŁĄD: " + e.getMessage());
            }
        }
    }
}