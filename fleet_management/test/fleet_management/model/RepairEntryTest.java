package fleet_management.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RepairEntryTest {

    @Test
    void toStringShouldContainDateCostAndDescription() {
        RepairEntry entry = new RepairEntry(
                LocalDate.of(2025, 3, 10),
                new BigDecimal("199.99"),
                "Wymiana oleju"
        );

        String s = entry.toString();
        assertTrue(s.contains("2025-03-10"));
        assertTrue(s.contains("199.99"));
        assertTrue(s.contains("Wymiana oleju"));
    }
}
