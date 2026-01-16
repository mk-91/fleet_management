package fleet_management.model;
import java.time.LocalDate;
import java.math.BigDecimal;

/**
 * Pojedynczy wpis historii napraw pojazdu.
 */
public class RepairEntry {
    private LocalDate date;
    private BigDecimal cost;
    private String description;

    /**
     * Tworzy wpis naprawy.
     *
     * @param date data naprawy
     * @param cost koszt naprawy
     * @param description opis
     */
    public RepairEntry(LocalDate date, BigDecimal cost, String description) {
        this.date = date;
        this.cost = cost;
        this.description = description;
    }

    /**
     * @return data naprawy
     */
    public LocalDate getDate() { return date; }

    /**
     * @return koszt naprawy
     */
    public BigDecimal getCost() { return cost; }

    /**
     * @return opis naprawy
     */
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return date + " | Koszt: " + cost + " | " + description;
    }
}