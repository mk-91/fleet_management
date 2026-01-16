package fleet_management.model;
import java.time.LocalDate;

/**
 * Pojedynczy wpis historii przebiegu pojazdu.
 */
public class MileageEntry {
    private LocalDate date;
    private long mileage;

    /**
     * Tworzy wpis przebiegu.
     *
     * @param date data wpisu
     * @param mileage stan licznika w kilometrach
     */
    public MileageEntry(LocalDate date, long mileage) {
        this.date = date;
        this.mileage = mileage;
    }

    /**
     * @return przebieg (km)
     */
    public long getMileage() {
        return mileage;
    }

    /**
     * @return data wpisu
     */
    public LocalDate getDate() { return date; }

    @Override
    public String toString() {
        return date + ": " + mileage + " km";
    }

}