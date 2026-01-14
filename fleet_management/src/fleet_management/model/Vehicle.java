package fleet_management.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle {
    private static long idCounter = 1;

    private long id;
    private String brand;
    private String model;
    private String registrationNumber;
    private Color color;
    private int productionYear;
    private LocalDate insuranceValid;
    private LocalDate inspectionValid;
    private User assignedUser;
    
    // Listy historii
    private List<MileageEntry> mileageHistory = new ArrayList<>();
    private List<RepairEntry> repairHistory = new ArrayList<>();

    public Vehicle(String brand, String model, String registrationNumber, 
                   Color color, int productionYear, 
                   LocalDate insuranceValid, LocalDate inspectionValid) {
        this.id = idCounter++;
        this.brand = brand;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.color = color;
        this.productionYear = productionYear;
        this.insuranceValid = insuranceValid;
        this.inspectionValid = inspectionValid;
    }

    public abstract VehicleType getVehicleType();

    public void addMileage(MileageEntry entry) {
        this.mileageHistory.add(entry);
    }

    public void addRepair(RepairEntry entry) {
        this.repairHistory.add(entry);
    }

    public void assignUser(User user) {
        this.assignedUser = user;
    }

    @Override
    public String toString() {
        return id + ". " + brand + " " + model + " [" + registrationNumber + "]";
    }

    public long getId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}