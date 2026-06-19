/**
 * Ronald Bryant
 * CEN 3024C-31032 - Software Development 1
 * June 19, 2026
 * Library.java
 * This class manages library patron objects for adding, removing, displaying, and
 * reading data from a text file.
 */

package com.librarymanagementsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {

    private ArrayList<Patron> patrons;

    public Library() {
        patrons = new ArrayList<>();
    }

    /**
     * method: addPatron
     * parameters: Patron patron
     * return: boolean
     * purpose: Adds a patron to the system if the ID is valid and unique, and the
     *          fine is within limits.
     */
    public boolean addPatron(Patron patron) {
        if (!isValidId(patron.getId())) {
            return false;
        }
        if (isIdTaken(patron.getId())) {
            return false;
        }
        if (!isValidFine(patron.getOverdueFine())) {
            return false;
        }
        patrons.add(patron);
        return true;
    }

    /**
     * method: removePatron
     * parameters: int id
     * return: boolean
     * purpose: Searches for a patron by their ID number and removes them from the
     *          list if found.
     */
    public boolean removePatron(int id) {
        for (int i = 0; i < patrons.size(); i++) {
            if (patrons.get(i).getId() == id) {
                patrons.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * method: displayPatrons
     * parameters: none
     * return: void
     * purpose: Either prints every patron in the system or that the list is empty.
     */
    public void displayPatrons() {
        if (patrons.isEmpty()) {
            System.out.println("\nThere are no patrons in the system.");
            return;
        }
        System.out.println("\n-------------------------------------------     Library Patrons     --------------------------------------------");
        for (Patron patron : patrons) {
            System.out.println(patron);
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------");
    }

    /**
     * method: isIdTaken
     * parameters: int id
     * return: boolean
     * purpose: Checks whether a patron with the given ID already exists in the list.
     */
    public boolean isIdTaken(int id) {
        for (Patron patron : patrons) {
            if (patron.getId() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * method: isValidId
     * parameters: int id
     * return: boolean
     * purpose: Verifies that the given ID is exactly 7 digits long.
     */
    public boolean isValidId(int id) {
        return id >= 1000000 && id <= 9999999;
    }

    /**
     * method: isValidFine
     * parameters: double fine
     * return: boolean
     * purpose: Checks that the overdue fine is between $0 and $250.
     */
    public boolean isValidFine(double fine) {
        return fine >= 0 && fine <= 250;
    }

    /**
     * method: addPatronsFromFile
     * parameters: String fileName
     * return: void
     * purpose: Reads a formatted text file, creates patron objects from the data, and
     *          adds them to the list.
     */
    public void addPatronsFromFile(String fileName) {
        try {
            Scanner fileScanner = new Scanner(new File(fileName));
            int addedCount = 0;
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split("-");

                if (parts.length != 4) {
                    System.out.println("\nSkipped a badly formatted line: " + line);
                    continue;
                }
                try {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    String address = parts[2].trim();
                    double fine = Double.parseDouble(parts[3].trim());
                    Patron patron = new Patron(id, name, address, fine);
                    if (addPatron(patron)) {
                        addedCount++;
                    } else {
                        System.out.println("\nSkipped an invalid/duplicate patron ID or out of range fine amount: " +
                                line);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\nSkipped a line with bad overdue fine format: " + line);
                }
            }
            fileScanner.close();
            System.out.println("\n" + addedCount + " patron(s) loaded from the file successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("\nCould not find the file: " + fileName);
        }
    }
}

