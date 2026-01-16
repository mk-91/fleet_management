package fleet_management;

import fleet_management.model.*;
import fleet_management.repositories.FleetRepository;
import fleet_management.exceptions.DuplicateRegistrationException;
import fleet_management.exceptions.InvalidMileageException;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Prosty tekstowy program testowy.
 * Sprawdza podstawowe scenariusze działania modelu obiektowego.
 */
public class ProgramTestowy {

    public static void main(String[] args) {
        System.out.println("=== PROGRAM TESTOWY ===");

        FleetRepository repo = new FleetRepository();

        // 1) Dodanie pojazdu
        System.out.println("\n[1] Dodawanie pojazdu...");
        Car car1 = new Car(
                "Toyota", "Corolla", "PO12345",
                Color.SILVER, 2020,
                LocalDate.now().plusDays(200),
                LocalDate.now().plusDays(50)
        );

        try {
            repo.save(car1);
            System.out.println("OK: Dodano pojazd: " + car1.getRegistrationNumber());
        } catch (DuplicateRegistrationException e) {
            System.out.println("BŁĄD: Nie udało się dodać pojazdu (duplikat)!");
        }

        // 2) Próba dodania duplikatu
        System.out.println("\n[2] Próba dodania duplikatu rejestracji...");
        Car carDuplicate = new Car(
                "Ford", "Focus", "PO12345",
                Color.BLACK, 2018,
                LocalDate.now().plusDays(100),
                LocalDate.now().plusDays(100)
        );

        try {
            repo.save(carDuplicate);
            System.out.println("BŁĄD: Dodano duplikat (to nie powinno się udać).");
        } catch (DuplicateRegistrationException e) {
            System.out.println("OK: Wykryto duplikat rejestracji: " + e.getMessage());
        }

        // 3) Przypisanie użytkownika do pojazdu
        System.out.println("\n[3] Przypisanie użytkownika...");
        User user = new User("Jan", "Kowalski", "500600700");
        car1.assignUser(user);
        System.out.println("OK: Przypisano użytkownika do pojazdu: " + car1.getAssignedUser());

        // 4) Dodanie przebiegów (poprawny i błędny)
        System.out.println("\n[4] Dodanie przebiegów...");
        try {
            car1.addMileage(new MileageEntry(LocalDate.now().minusDays(5), 10000));
            car1.addMileage(new MileageEntry(LocalDate.now(), 10500));
            System.out.println("OK: Dodano rosnące przebiegi.");
        } catch (InvalidMileageException e) {
            System.out.println("BŁĄD: Nie powinno się zdarzyć dla rosnących przebiegów!");
        }

        System.out.println("\n[5] Próba dodania malejącego przebiegu (powinien być błąd)...");
        try {
            car1.addMileage(new MileageEntry(LocalDate.now().plusDays(1), 9000));
            System.out.println("BŁĄD: Przyjęto malejący przebieg (to nie powinno się udać).");
        } catch (InvalidMileageException e) {
            System.out.println("OK: Odrzucono malejący przebieg: " + e.getMessage());
        }

        // 5) Dodanie naprawy
        System.out.println("\n[6] Dodanie naprawy...");
        car1.addRepair(new RepairEntry(LocalDate.now().minusDays(2), new BigDecimal("799.99"), "Wymiana klocków hamulcowych"));
        System.out.println("OK: Dodano naprawę.");

        // 6) Sprawdzenie terminu (deadline soon)
        System.out.println("\n[7] Sprawdzenie deadline (czy termin blisko)...");
        boolean soon = car1.isDeadlineSoon();
        System.out.println("INFO: isDeadlineSoon() = " + soon + " (true oznacza termin w ciągu 30 dni)");

        // 7) Wyświetlenie stanu repozytorium
        System.out.println("\n[8] Stan repozytorium (findAll)...");
        for (Vehicle v : repo.findAll()) {
            System.out.println(v);
        }

        // 8) Usunięcie pojazdu
        System.out.println("\n[9] Usuwanie pojazdu...");
        repo.delete(car1);
        System.out.println("OK: Usunięto pojazd PO12345");

        System.out.println("\n[10] Sprawdzenie po usunięciu (powinno być pusto)...");
        System.out.println("Liczba pojazdów: " + repo.findAll().size());

        System.out.println("\n=== KONIEC TESTU ===");
    }
}