package fleet_management.repositories;

import fleet_management.exceptions.DuplicateRegistrationException;
import fleet_management.model.Car;
import fleet_management.model.Color;
import fleet_management.model.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FleetRepositoryTest {

    private FleetRepository repository;

    @BeforeEach
    void setUp() {
        repository = new FleetRepository();
    }

    private Car car(String reg) {
        return new Car("Toyota", "Corolla", reg, Color.SILVER, 2020,
                LocalDate.now().plusDays(365), LocalDate.now().plusDays(365));
    }

    @Test
    void saveShouldStoreVehicleAndFindByIdShouldReturnIt() {
        Vehicle v = car("KR11111");
        repository.save(v);

        assertTrue(repository.findById(v.getId()).isPresent());
        assertSame(v, repository.findById(v.getId()).get());
    }

    @Test
    void saveShouldRejectDuplicateRegistrationNumber() {
        repository.save(car("KR22222"));

        assertThrows(DuplicateRegistrationException.class, () -> repository.save(car("KR22222")));
    }

    @Test
    void findAllShouldReturnCopyNotLiveList() {
        repository.save(car("KR33333"));

        List<Vehicle> list = repository.findAll();
        assertEquals(1, list.size());

        // Modyfikujemy zwróconą listę
        list.clear();

        // Repozytorium nadal ma pojazd
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void deleteShouldRemoveVehicle() {
        Vehicle v = car("KR44444");
        repository.save(v);
        assertEquals(1, repository.findAll().size());

        repository.delete(v);
        assertEquals(0, repository.findAll().size());
        assertFalse(repository.findById(v.getId()).isPresent());
    }
}
