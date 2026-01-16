package fleet_management.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MileageEntryTest {

    @Test
    void getMileageShouldReturnValueProvidedInConstructor() {
        MileageEntry entry = new MileageEntry(LocalDate.of(2025, 1, 1), 12345);
        assertEquals(12345, entry.getMileage());
    }

    @Test
    void toStringShouldContainDateAndMileage() {
        MileageEntry entry = new MileageEntry(LocalDate.of(2025, 1, 1), 123);
        String s = entry.toString();
        assertTrue(s.contains("2025-01-01"));
        assertTrue(s.contains("123"));
        assertTrue(s.contains("km"));
    }
}
