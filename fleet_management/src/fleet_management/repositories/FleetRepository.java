package fleet_management.repositories;

import fleet_management.model.Vehicle;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import fleet_management.exceptions.DuplicateRegistrationException;

public class FleetRepository implements Repository<Vehicle> {
    
    // To jest nasza "baza danych" w pamięci
    private List<Vehicle> vehicles = new ArrayList<>();

    @Override
    public void save(Vehicle vehicle) {
        // Sprawdzamy, czy auto z taką rejestracją już jest na liście
        for (Vehicle v : vehicles) {
            if (v.getRegistrationNumber().equals(vehicle.getRegistrationNumber())) {
                throw new DuplicateRegistrationException("Pojazd o numerze " + vehicle.getRegistrationNumber() + " już istnieje!");
            }
        }
        vehicles.add(vehicle);
    }

    @Override
    public Optional<Vehicle> findById(long id) {
        // Przeszukujemy listę w poszukiwaniu auta o danym ID
        return vehicles.stream()
                .filter(v -> v.getId() == id)
                .findFirst();
    }

    @Override
    public List<Vehicle> findAll() {
        return new ArrayList<>(vehicles); // Zwracamy kopię listy dla bezpieczeństwa
    }

    @Override
    public void delete(Vehicle vehicle) {
        vehicles.remove(vehicle);
    }
}