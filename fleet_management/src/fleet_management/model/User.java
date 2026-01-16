package fleet_management.model;

/**
 * Reprezentuje użytkownika/kierowcę przypisanego do pojazdu.
 */
public class User {
    private String firstName;
    private String lastName;
    private String phoneNumber;

    /**
     * Tworzy nowego użytkownika.
     *
     * @param firstName imię
     * @param lastName nazwisko
     * @param phoneNumber numer telefonu
     */
    public User(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return imię
     */
    public String getFirstName() { return firstName; }
    /**
     * @return nazwisko
     */
    public String getLastName() { return lastName; }
    /**
     * @return numer telefonu
     */
    public String getPhoneNumber() { return phoneNumber; }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + phoneNumber + ")";
    }

}