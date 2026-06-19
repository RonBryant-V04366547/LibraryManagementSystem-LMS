/**
 * Ronald Bryant
 * CEN 3024C-31032 - Software Development 1
 * June 19, 2026
 * Main.java
 * This application will allow a librarian to manage patrons in the Library Management System
 * (LMS), through a simple, console-based on-screen menu. The librarian will be able to add
 * patrons manually, import them from a file, remove them with ID's, and display all patrons.
 */

package com.librarymanagementsystem;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        boolean running = true;

        System.out.println("\nWelcome to the Library Management System (LMS)!");

        while (running) {
            displayMenu();
            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                System.out.print("Enter the name (or path) of the text file: ");
                String fileName = scanner.nextLine().trim();
                library.addPatronsFromFile(fileName);
            } else if (choice.equals("2")) {
                Patron newPatron = createPatronFromInput(scanner);
                if (library.addPatron(newPatron)) {
                    System.out.println("\nPatron added successfully.");
                } else {
                    System.out.println("\nThat ID is already in use. The patron was not added.");
                }
            } else if (choice.equals("3")) {
                System.out.print("Enter the 7-digit ID of the patron to remove: ");
                String line = scanner.nextLine().trim();
                try {
                    int id = Integer.parseInt(line);
                    if (library.removePatron(id)) {
                        System.out.println("\nPatron " + id + " was successfully removed.");
                    } else {
                        System.out.println("\nNo patron was found with ID " + id + ".");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\nThat is not a valid ID number.");
                }
            } else if (choice.equals("4")) {
                library.displayPatrons();
            } else if (choice.equals("5")) {
                System.out.println("\nThanks for using the Library Management System (LMS)!");
                running = false;
            } else {
                System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }
        }

        scanner.close();
    }

    /**
     * method: displayMenu
     * parameters: none
     * return: void
     * purpose: Prints the on-screen main menu to the console so the librarian can see the available
     *          options.
     */
    public static void displayMenu() {
        System.out.println("\n----    Main Menu    ----");
        System.out.println("1. Add patrons from file");
        System.out.println("2. Add patron manually");
        System.out.println("3. Remove patron by ID");
        System.out.println("4. Display all patrons");
        System.out.println("5. Exit");
        System.out.println("------------------------");
        System.out.print("\nEnter your choice: ");
    }

    /**
     * method: createPatronFromInput
     * parameters: Scanner scanner
     * return: Patron
     * purpose: Prompts the user to enter patron data in the console to create a new patron object.
     */
    public static Patron createPatronFromInput(Scanner scanner) {
        int id;
        while (true) {
            System.out.print("Enter the patron's 7-digit ID number: ");
            String line = scanner.nextLine().trim();
            try {
                id = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("That is not a valid number. Please try again.");
                continue;
            }
            if (id < 1000000 || id > 9999999) {
                System.out.println("The ID must be exactly 7 digits. Please try again.");
                continue;
            }
            break;
        }

        System.out.print("Enter the patron's name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter the patron's address: ");
        String address = scanner.nextLine().trim();

        double fine;
        while (true) {
            System.out.print("Enter the patron's overdue fine ($0 to $250): ");
            String line = scanner.nextLine().trim();
            try {
                fine = Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("That is not a valid amount. Please try again.");
                continue;
            }
            if (fine < 0 || fine > 250) {
                System.out.println("The fine must be between $0 and $250. Please try again.");
                continue;
            }
            break;
        }

        return new Patron(id, name, address, fine);
    }
}

