import java.util.ArrayList;

/**
 * The Fraction class represents a political faction/group in parliament.
 * it manages a collection of members belonging to this faction.
 *
 * Attributes:
 * - name: The name of the political fraction
 * - mitglieder: List of members belonging to this faction
 *
 * Methods:
 * - Constructor: initializes the faction with a name and empty member list
 * - getName(): returns the name of the faction
 * - getMitglieder(): returns the list of members in this faction
 */
public class Fraktion {
    private String name;
    private ArrayList<Members> mitglieder;

    /**
     * Constructor to create a Fraktion object
     *  name The name of the political faction
     */
    public Fraktion(String name) {
        this.name = name;
        this.mitglieder = new ArrayList<>();
    }

    /**
     * Getter method for the faction name
     * return The name of the faction as String
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for the member list
     * return ArrayList containing all members of this faction
     */
    public ArrayList<Members> getMitglieder() {
        return mitglieder;
    }
}