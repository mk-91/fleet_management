package fleet_management.repositories;

import fleet_management.model.Vehicle;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FleetRepository implements Repository<Vehicle> {
    
    // To jest nasza "baza danych" w pamięci
    private List<Vehicle> vehicles = new ArrayList<>();

    @Override
    public void save(Vehicle vehicle) {
        // Tu w przyszłości dodamy sprawdzanie duplikatów tablic (wymóg z README)
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