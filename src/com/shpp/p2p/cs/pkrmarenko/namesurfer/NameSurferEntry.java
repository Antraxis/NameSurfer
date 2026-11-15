package com.shpp.p2p.cs.pkrmarenko.namesurfer;

/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

public class NameSurferEntry implements NameSurferConstants {

    // Variable for the name extracted from the data line.
    // Used by the getName method.
    String nameLine;

    // Array of integers parsed from the data line.
    // Used to retrieve the rank of the name by decades.
    int[] result;

    // StringBuilder used to format the rank numbers for the toString method.
    StringBuilder numberForToString = new StringBuilder();

    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file. Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     *
     * @param line a single line of text from the database file
     */
    public NameSurferEntry(String line) {
        // Initialize the array to hold ranks for all decades (plus space for safety)
        result = new int[N_DECADES + 1];

        String[] splitLine = line.split(" ");
        nameLine = splitLine[0];

        for (int i = 1; i < splitLine.length; i++) {
            result[i] = Integer.parseInt(splitLine[i]);
            numberForToString.append(splitLine[i]).append(" ");
        }
    }

    /**
     * Returns the name associated with this entry.
     *
     * @return the name string
     */
    public String getName() {
        return nameLine;
    }

    /**
     * Returns the rank associated with an entry for a particular
     * decade. The decade value is an integer indicating the year.
     * If a name does not appear in a decade, the rank value is 0.
     *
     * @param decade the year for which the rank is requested
     * @return the rank of the name in the given decade
     */
    public int getRank(int decade) {
        int walkDecade = (decade - START_DECADE) / INTERVAL + 1;
        if (walkDecade >= 0 && walkDecade < result.length) {
            return result[walkDecade];
        }
        return 0;
    }

    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry in a readable format.
     *
     * @return string representation of the entry
     */
    public String toString() {
        return nameLine + "[" + numberForToString.toString().trim() + "]";
    }
}