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

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class NameSurferDataBase implements NameSurferConstants {


    HashMap<String, int[]> nameSurfersMap = new HashMap<>();


    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     * exception if the requested file does not exist or if an error
     * occurs as the file is being read.
     */
    public NameSurferDataBase(String filename) {

        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            while ((line = br.readLine()) != null) {
                int[] ranks = extractNumbers(line);
                String name = extractName(line);
                nameSurfersMap.put(name, ranks);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "File not found");
        }

    }

    /**
     * Returns the NameSurferEntry associated with this name, if one
     * exists.  If the name does not appear in the database, this
     * method returns null.
     */
    public NameSurferEntry findEntry(String name) {
        if (nameSurfersMap.get(name) != null) {
            return new NameSurferEntry(name);
        }
        return null;

    }

    private String extractName(String filename) {
        int number = filename.indexOf(" ");
        return filename.substring(0, number).toLowerCase();
    }

    private int[] extractNumbers(String input) {
        String[] parts = input.trim().split(" ");
        int[] numbers = new int[input.length() - 1];

        for (int i = 1; i < parts.length; i++) {
            numbers[i - 1] = Integer.parseInt(parts[i]);
        }
        return numbers;
    }

}

