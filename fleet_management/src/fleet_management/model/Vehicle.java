package fleet_management.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import fleet_management.exceptions.InvalidMileageException;

/**
 * Bazowa klasa abstrakcyjna opisująca pojazd we flocie.
 *
 * <p>Przechowuje dane identyfikacyjne pojazdu, terminy OC i przeglądu, przypisanego kierowcę,
 * a także historię przebiegów i napraw.</p>
 */
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

    /** Historia wpisów przebiegu. */
    private List<MileageEntry> mileageHistory = new ArrayList<>();
    /** Historia wpisów napraw. */
    private List<RepairEntry> repairHistory = new ArrayList<>();

    /**
     * Tworzy pojazd o podanych parametrach.
     *
     * @param brand marka
     * @param model model
     * @param registrationNumber numer rejestracyjny
     * @param color kolor
     * @param productionYear rok produkcji
     * @param insuranceValid data ważności OC
     * @param inspectionValid data ważności przeglądu
     */
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

    /**
     * Zwraca typ pojazdu (np. CAR, TRUCK).
     *
     * @return typ pojazdu
     */
    public abstract VehicleType getVehicleType();

    /**
     * Dodaje wpis przebiegu do historii.
     *
     * <p>Weryfikuje monotoniczność historii - nowy przebieg nie może być mniejszy
     * niż ostatni zapisany.</p>
     *
     * @param entry wpis przebiegu
     * @throws InvalidMileageException gdy nowy przebieg jest mniejszy od poprzedniego
     */
    public void addMileage(MileageEntry entry) {
        // Jeśli lista nie jest pusta, sprawdzamy ostatni wpis
        if (!mileageHistory.isEmpty()) {
            MileageEntry lastEntry = mileageHistory.get(mileageHistory.size() - 1);
            if (entry.getMileage() < lastEntry.getMileage()) {
                throw new InvalidMileageException("Nowy przebieg (" + entry.getMileage() + 
                        ") nie może być mniejszy niż poprzedni (" + lastEntry.getMileage() + ")!");
            }
        }
        this.mileageHistory.add(entry);
    }

    /**
     * Dodaje wpis naprawy do historii napraw.
     *
     * @param entry wpis naprawy
     */
    public void addRepair(RepairEntry entry) {
        this.repairHistory.add(entry);
    }

    /**
     * Przypisuje kierowcę (użytkownika) do pojazdu.
     *
     * @param user użytkownik
     */
    public void assignUser(User user) {
        this.assignedUser = user;
    }

    @Override
    public String toString() {
        return id + ". " + brand + " " + model + " [" + registrationNumber + "]";
    }

    /** @return numer rejestracyjny */
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /** @return identyfikator pojazdu (nadawany automatycznie) */
    public long getId() {
        return id;
    }

    /** @return marka */
    public String getBrand() {
        return brand;
    }

    /** @return model */
    public String getModel() {
        return model;
    }

    /** @return historia przebiegów */
    public List<MileageEntry> getMileageHistory() {
        return mileageHistory;
    }

    /** @return historia napraw */
    public List<RepairEntry> getRepairHistory() {
        return repairHistory;
    }

    /**
     * Sprawdza, czy w ciągu kolejnych 30 dni upływa ważność OC lub przeglądu.
     *
     * @return {@code true} jeśli termin jest bliski
     */
    public boolean isDeadlineSoon() {
        LocalDate warningDate = LocalDate.now().plusDays(30);
        // Zwraca true, jeśli ubezpieczenie LUB przegląd są przed datą ostrzegawczą
        return insuranceValid.isBefore(warningDate) || inspectionValid.isBefore(warningDate);
    }

    /** @return data ważności OC */
    public LocalDate getInsuranceValid() {
        return insuranceValid;
    }

    /** @return data ważności przeglądu */
    public LocalDate getInspectionValid() {
        return inspectionValid;
    }

    /**
     * Ustawia datę ważności OC.
     *
     * @param insuranceValid nowa data
     */
    public void setInsuranceValid(LocalDate insuranceValid) {
        this.insuranceValid = insuranceValid;
    }

    /**
     * Ustawia datę ważności przeglądu.
     *
     * @param inspectionValid nowa data
     */
    public void setInspectionValid(LocalDate inspectionValid) {
        this.inspectionValid = inspectionValid;
    }

    /**
     * Ustawia kolor pojazdu.
     *
     * @param color nowy kolor
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /** @return kolor */
    public Color getColor() { return color; }

    /** @return rok produkcji */
    public int getProductionYear() { return productionYear; }

    /** @return przypisany użytkownik lub {@code null} */
    public User getAssignedUser() { return assignedUser; }
    
}