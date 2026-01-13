package fleet_management.model;
import java.time.LocalDate;

public class MileageEntry {
    private LocalDate date;
    private long mileage;

    public MileageEntry(LocalDate date, long mileage) {
        this.date = date;
        this.mileage = mileage;
    }
    
    @Override
    public String toString() {
        return date + ": " + mileage + " km";
    }
}