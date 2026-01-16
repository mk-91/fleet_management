package fleet_management.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void toStringShouldContainNameAndPhone() {
        User user = new User("Jan", "Kowalski", "600-700-800");
        String s = user.toString();

        assertTrue(s.contains("Jan"));
        assertTrue(s.contains("Kowalski"));
        assertTrue(s.contains("600-700-800"));
    }
}
