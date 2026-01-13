package fleet_management.model;
import java.time.LocalDate;

public class Car extends Vehicle {
    public Car(String brand, String model, String registrationNumber, 
               Color color, int productionYear, 
               LocalDate insuranceValid, LocalDate inspectionValid) {
        super(brand, model, registrationNumber, color, productionYear, insuranceValid, inspectionValid);
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.CAR;
    }
}