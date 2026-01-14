package fleet_management;

import fleet_management.model.*;
import fleet_management.repositories.FleetRepository;
import fleet_management.repositories.Repository;
import java.time.LocalDate;

public class Fleet_management {

    public static void main(String[] args) {
        System.out.println("--- Test Repozytorium ---");

        // 1. Tworzymy repozytorium (nasz garaż)
        Repository<Vehicle> repository = new FleetRepository();

        // 2. Tworzymy dwa różne pojazdy
        Vehicle car1 = new Car("Toyota", "Yaris", "KR 11111", Color.RED, 2020, LocalDate.now(), LocalDate.now());
        Vehicle car2 = new Car("Volvo", "XC60", "WA 22222", Color.BLACK, 2022, LocalDate.now(), LocalDate.now());

        // 3. Zapisujemy je w repozytorium
        repository.save(car1);
        repository.save(car2);
        System.out.println("Zapisano 2 pojazdy.");

        // 4. Wyświetlamy listę wszystkich pojazdów (findAll)
        System.out.println("\nLista pojazdów w systemie:");
        for (Vehicle v : repository.findAll()) {
            System.out.println(v);
        }

        // 5. Test usuwania
        repository.delete(car1);
        System.out.println("\nUsunięto Toyotę. Aktualna lista:");
        for (Vehicle v : repository.findAll()) {
            System.out.println(v);
        }
    }
}