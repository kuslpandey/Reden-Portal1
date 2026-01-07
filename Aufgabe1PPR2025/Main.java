/** here we processes German parliamentary member data by reading a JSON file
 *containing information about members of the Bundestag.he program filters the data to include only politicians from
 *legislative period 21 or later, extracting key details such as their profession, name, and political party
 * affiliation. It then organizes these members by their respective political parties using a HashMap for efficient
 * grouping and displays them in a structured format sorted alphabetically by party name. The output shows each party's members
 * along with individual counts and a final total, providing a clear overview of the current parliamentary composition.
 *
 */

import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * main class for processing German parliament member data
 * This program reads JSON data of german parliament members, filters by legislative period,
 * and displays them grouped by political party.
 */

public class Main {
    public static void main(String[] args) {
        try {
            // reads the entire JSON file provided as a string
            String content = new String(Files.readAllBytes(Paths.get("src/main/resources/mdb.json")));

            // convert the string content into a JSONArray for processing
            JSONArray data = new JSONArray(content);

            // create an empty list to store all member objects
            List<Members> allMembers = new ArrayList<>();

            // Lloop through each politician in the JSON array
            for (int i = 0; i < data.length(); i++) {
                // get one politician's data as a JSON object
                JSONObject person = data.getJSONObject(i);

                // Get the legislative period string (e.g., "21")
                String periodStr = person.getString("WP");

                // Skips this politician if they are not within WP 21
                if (getPeriodNumber(periodStr) < 21) {
                    continue; // jump to next iteration
                }

                // Extract the politician's profession
                String job = person.getString("beruf");
                // Extract first name
                String first = person.getString("vorname");
                // Extract last name
                String last = person.getString("name");
                // determine which party they belong to
                String party = findParty(person);

                // create a new Members object with the extracted data
                Members member = new Members(job, first, last, getPeriodNumber(periodStr), party);

                // Add the member to our list
                allMembers.add(member);
            }

            // Call method to display all members grouped by party
            showByParty(allMembers);

        } catch (Exception e) {
            // If anything goes wrong, print error message
            System.out.println("We have a problem/Error");
        }
    }

    /**
     * Convert period string to integer
     */
    static int getPeriodNumber(String period) {
        try {
            // Try to convert string like "21" to integer 21
            return Integer.parseInt(period);
        } catch (Exception e) {
            // If conversion fails, return 0
            return 0;
        }
    }

    /**
     * Figure out which political party the person belongs to
     */
    static String findParty(JSONObject person) {
        try {
            // Check if the person has membership information
            if (person.has("membership")) {
                // Get the list of groups they belong to
                JSONArray groups = person.getJSONArray("membership");

                // Check each group for party names
                for (int i = 0; i < groups.length(); i++) {
                    String group = groups.getString(i);

                    // Check for different party names in the group string
                    if (group.contains("Alternative")) return "AfD";
                    if (group.contains("GRÜNEN")) return "GRÜNE";
                    if (group.contains("Die Linke")) return "LINKE";
                    if (group.contains("Sozialdemokratischen")) return "SPD";
                    if (group.contains("Christlich Demokratischen")) return "CDU/CSU";
                }
            }
        } catch (Exception e) {
            // If there's an error reading membership data, just continue
        }
        // If no party found, return as independent
        return "Independent Candidate";
    }

    /**
     * Display all members organized by their political party
     */
    static void showByParty(List<Members> members) {
        // Map is like a dictionary that stores the key and its values.Create a map where key=party name, value=list of members in that party
        Map<String, List<Members>> byParty = new HashMap<>();

        // It is a for-each loop. Goes through each member in the list one by one and organizes each member into their respective party list
        for (Members m : members) {
            String party = m.getParty();

            // If this party isn't in the map yet, create an empty list for it
            if (!byParty.containsKey(party)) {
                byParty.put(party, new ArrayList<>());
            }
            // Add this member to their party's list
            byParty.get(party).add(m);
        }

        // Get all party names and sort them alphabetically
        List<String> parties = new ArrayList<>(byParty.keySet());
        Collections.sort(parties);

        // Print header
        System.out.println("Members of Parliament and their Party (from period 21):");
        System.out.println("======================================");

        int total = 0;

        // Loop through each party in sorted order
        for (String party : parties) {
            System.out.println("\n" + party + ":");

            // Get all members of this party
            List<Members> partyMembers = byParty.get(party);

            // Print each member in this party
            for (Members m : partyMembers) {
                System.out.println("  - " + m.toString());
            }

            // Print how many members are in this party
            System.out.println("  Count: " + partyMembers.size());

            // Add to total count
            total += partyMembers.size();
        }

        // Print final total
        System.out.println("\nTotal: " + total + " Members");
    }
}