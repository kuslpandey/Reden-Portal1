/**
 * The Profession class represents the occupation of a member of parliament.

 * Attributes title: The designation of the profession (e.g., "Lawyer", "Doctor", etc.)
 *
 * Methods:
 * - Constructor: initializes the profession with a title
 * - getTitle(): gives the profession title
 * - toString(): gives a string representation of the profession
 */
public class Profession {
    private String title;


    public Profession(String title) {
        this.title = title;
    }

    /**
     * Getter method for the profession title
     * returns the title of the profession as String
     */
    public String getTitle() {
        return title;
    }

    /**
     * returns a string representation of the profession
     * gives the profession title as String
     */
    public String toString() {
        return title;
    }
}