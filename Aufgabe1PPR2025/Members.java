/**
 * The Members class represents a member of parliament with their personal and political information.
 *
 *
 * Attributes:
 * - job: The profession of the member of Parliament
 * - firstName: The first name of the member
 * - lastName: The last name of the member
 * - period: The legislative period (WP = Wahlperiode) in which they serve
 * - party: The political party of the member
 *
 * Methods:
 * - Constructor: Initializes all member attributes
 * - getter methods: provide access to all private attributes
 * - toString(): returns a formatted string representation of the member
 */
public class Members {
    private String job;
    private String firstName;
    private String lastName;
    private int period;
    private String party;

    /**
     * Constructor to create a Members object with all required information
     *  job The profession/occupation of the member
     * firstName The first name of the member
     * lastName The last name of the member
     * period The legislative period number
     *  party The political party name
     */
    public Members(String job, String firstName, String lastName, int period, String party) {
        this.job = job;
        this.firstName = firstName;
        this.lastName = lastName;
        this.period = period;
        this.party = party;
    }

    // Getter methods for accessing private attributes

    /**
     * return The first name of the member
     */
    public String getFirstName() { return firstName; }

    /**
     * return The last name of the member
     */
    public String getLastName() { return lastName; }

    /**
     * return The political party of Member
     */
    public String getParty() { return party; }

    /**
     * return The legislative period number
     */
    public int getPeriod() { return period; }

    /**
     * return The profession/occupation of the member
     */
    public String getJob() { return job; }

    /**
     * Returns a formatted string representation of the member
     * Format: "Lastname, Firstname (Job) - WP Period"
     * Example: "Müller, Anna (Lawyer) - WP 20"
     * @return Formatted string with member information
     */
    public String toString() {
        return lastName + ", " + firstName + " (" + job + ") - WP " + period;
    }
}