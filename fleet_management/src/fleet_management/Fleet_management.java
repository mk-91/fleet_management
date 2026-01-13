package fleet_management;

// Importujemy nasze klasy z modelu
import fleet_management.model.*; 
import java.time.LocalDate;
import java.math.BigDecimal;

public class Fleet_management {

    public static void main(String[] args) {
        System.out.println("--- System Zarządzania Flotą - TEST ---");

        // 1. Tworzymy przykładowy samochód
        Vehicle auto1 = new Car(
            "Ford", 
            "Focus", 
            "WA 12345", 
            Color.BLUE, 
            2018, 
            LocalDate.of(2024, 6, 15), 
            LocalDate.of(2025, 1, 10)
        );

        // 2. Przypisujemy kierowcę
        User kierowca = new User("Adam", "Nowak", "123-456-789");
        auto1.assignUser(kierowca);

        // 3. Dodajemy historię (przebieg i naprawę)
        auto1.addMileage(new MileageEntry(LocalDate.now(), 120000));
        auto1.addRepair(new RepairEntry(LocalDate.now(), new BigDecimal("350.00"), "Wymiana klocków"));

        // 4. Wypisujemy dane w konsoli
        System.out.println("Pojazd: " + auto1);
        System.out.println("Typ: " + auto1.getVehicleType());
        System.out.println("Użytkownik: " + kierowca);
        System.out.println("--- Test zakończony pomyślnie ---");
    }
}