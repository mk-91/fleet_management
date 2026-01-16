package fleet_management.model;

import fleet_management.exceptions.InvalidMileageException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    private Car newCar(LocalDate insuranceValid, LocalDate inspectionValid) {
        return new Car(
                "Toyota",
                "Corolla",
                "KR12345",
                Color.SILVER,
                2020,
                insuranceValid,
                inspectionValid
        );
    }

    @Test
    void carShouldReturnProperVehicleType() {
        Car car = newCar(LocalDate.now().plusDays(365), LocalDate.now().plusDays(365));
        assertEquals(VehicleType.CAR, car.getVehicleType());
    }

    @Test
    void addMileageShouldAcceptIncreasingValues() {
        Car car = newCar(LocalDate.now().plusDays(365), LocalDate.now().plusDays(365));

        car.addMileage(new MileageEntry(LocalDate.of(2025, 1, 1), 10_000));
        car.addMileage(new MileageEntry(LocalDate.of(2025, 2, 1), 12_000));

        assertEquals(2, car.getMileageHistory().size());
        assertEquals(12_000, car.getMileageHistory().get(1).getMileage());
    }

    @Test
    void addMileageShouldRejectDecreasingValues() {
        Car car = newCar(LocalDate.now().plusDays(365), LocalDate.now().plusDays(365));

        car.addMileage(new MileageEntry(LocalDate.of(2025, 1, 1), 10_000));

        InvalidMileageException ex = assertThrows(
                InvalidMileageException.class,
                () -> car.addMileage(new MileageEntry(LocalDate.of(2025, 1, 15), 9_999))
        );

        assertTrue(ex.getMessage().contains("nie może być mniejszy"));
        assertEquals(1, car.getMileageHistory().size());
    }

    @Test
    void isDeadlineSoonShouldBeTrueIfInsuranceSoon() {
        Car car = newCar(LocalDate.now().plusDays(10), LocalDate.now().plusDays(100));
        assertTrue(car.isDeadlineSoon());
    }

    @Test
    void isDeadlineSoonShouldBeTrueIfInspectionSoon() {
        Car car = newCar(LocalDate.now().plusDays(100), LocalDate.now().plusDays(5));
        assertTrue(car.isDeadlineSoon());
    }

    @Test
    void isDeadlineSoonShouldBeFalseIfBothDeadlinesAreFar() {
        Car car = newCar(LocalDate.now().plusDays(120), LocalDate.now().plusDays(120));
        assertFalse(car.isDeadlineSoon());
    }

    @Test
    void toStringShouldContainBasicVehicleInfo() {
        Car car = newCar(LocalDate.now().plusDays(365), LocalDate.now().plusDays(365));
        String s = car.toString();

        assertTrue(s.contains("Toyota"));
        assertTrue(s.contains("Corolla"));
        assertTrue(s.contains("KR12345"));
    }
}
