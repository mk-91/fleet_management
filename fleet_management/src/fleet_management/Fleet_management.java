package fleet_management;

import fleet_management.model.*;
import fleet_management.repositories.FleetRepository;
import fleet_management.repositories.Repository;
import fleet_management.exceptions.*; // Importujemy nasze wyjątki
import java.time.LocalDate;

public class Fleet_management {

    public static void main(String[] args) {
        System.out.println("--- Test Obsługi Błędów ---");
        Repository<Vehicle> repository = new FleetRepository();

        // 1. Dodajemy poprawne auto
        Vehicle auto1 = new Car("Opel", "Astra", "DW 12345", Color.SILVER, 2019, LocalDate.now(), LocalDate.now());
        repository.save(auto1);
        System.out.println("Dodano auto: DW 12345");

        // 2. Test duplikatu (to samo auto lub inna instancja z tą samą tablicą)
        try {
            Vehicle autoDuplicate = new Car("Opel", "Astra", "DW 12345", Color.BLACK, 2020, LocalDate.now(), LocalDate.now());
            repository.save(autoDuplicate); // To powinno rzucić błąd
        } catch (DuplicateRegistrationException e) {
            System.out.println("BŁĄD ZŁAPANY: " + e.getMessage());
        }

        // 3. Test przebiegu
        try {
            auto1.addMileage(new MileageEntry(LocalDate.now(), 100000)); // Startowy przebieg
            System.out.println("Dodano przebieg 100.000");
            
            auto1.addMileage(new MileageEntry(LocalDate.now(), 90000));  // Próba cofnięcia licznika
        } catch (InvalidMileageException e) {
            System.out.println("BŁĄD ZŁAPANY: " + e.getMessage());
        }
    }
}