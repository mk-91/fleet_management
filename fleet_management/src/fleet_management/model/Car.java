package fleet_management.model;
import java.time.LocalDate;

/**
 * Pojazd typu samochód osobowy.
 */
public class Car extends Vehicle {
    /**
     * Tworzy samochód osobowy.
     *
     * @param brand marka
     * @param model model
     * @param registrationNumber numer rejestracyjny
     * @param color kolor
     * @param productionYear rok produkcji
     * @param insuranceValid data ważności OC
     * @param inspectionValid data ważności przeglądu
     */
    public Car(String brand, String model, String registrationNumber, 
               Color color, int productionYear, 
               LocalDate insuranceValid, LocalDate inspectionValid) {
        super(brand, model, registrationNumber, color, productionYear, insuranceValid, inspectionValid);
    }

    /**
     * @return typ pojazdu ({@link VehicleType#CAR})
     */
    @Override
    public VehicleType getVehicleType() {
        return VehicleType.CAR;
    }
}