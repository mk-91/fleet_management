package fleet_management.model;
import java.time.LocalDate;
import java.math.BigDecimal;

public class RepairEntry {
    private LocalDate date;
    private BigDecimal cost;
    private String description;

    public RepairEntry(LocalDate date, BigDecimal cost, String description) {
        this.date = date;
        this.cost = cost;
        this.description = description;
    }

    @Override
    public String toString() {
        return date + " | Koszt: " + cost + " | " + description;
    }

    public LocalDate getDate() { return date; }
    public BigDecimal getCost() { return cost; }
    public String getDescription() { return description; }
}