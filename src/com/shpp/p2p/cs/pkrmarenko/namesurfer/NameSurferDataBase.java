package com.shpp.p2p.cs.pkrmarenko.namesurfer;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class NameSurferDataBase implements NameSurferConstants {
    ArrayList<String> nameSurfersList = new ArrayList<>();

    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     * exception if the requested file does not exist or if an error
     * occurs as the file is being read.
     */
    public NameSurferDataBase(String filename) {

        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null) {
                String newLine = line.toLowerCase();
                nameSurfersList.add(newLine);
            }
        } catch (IOException e) {
            System.err.println("Error opening file " + ": " + e);
        }

    }

    /**
     * Returns the NameSurferEntry associated with this name, if one
     * exists.  If the name does not appear in the database, this
     * method returns null.
     */
    public NameSurferEntry findEntry(String name) {
        String newName = name.toLowerCase();
        for (String s : nameSurfersList) {
            NameSurferEntry entry = new NameSurferEntry(s);
            String stringName = entry.getName();
            if (stringName.equals(newName)) {
                return  entry;
            }
        }

        return  null;

    }
}

