# Library Management System-LMS
Contains Java files and a Java Archive (JAR) for a Library Management System (LMS)

## Description
This is a small simple console-based app for librarians to manage library patrons in the LMS.

## What it does?
The LMS app builds an in-memory directory of library patrons, where each patron has a unique 7-digit ID, full name, address, and an overdue fine amount.
Once patrons are added to the LMS, the app can display all or iterate over the entire directory and look up individual patrons by their ID for removal.
The `main()` runs an on-screen Main menu with the following:
  ```
   1. Add patrons from a text file
   2. Add one patron manually
   3. Remove a patron using their 7-digit ID
   4. Displays all patrons
   5. Exit using the menu
  ```

## The Classes
The `Patron` class represents one patron and stores the ID, name, address, and overdue fine amount.
The `Library` class represents a list of patrons and uses an `ArrayList<>` to store the patron objects.
The `Main` class represents the driver class housing the `main()`, displaying the Main Menu, and handling the input from the librarian user.

## How to compile and run the program
Because the three files use the same package name: `package com.librarymanagementsystem`, ensure all are stored in the same folder. While this project was built in IntelliJ IDEA, you could use any IDE to compile and run it, provided you have taken the necessary steps needed for setting up, compiling, and running Java projects in whatever IDE you choose. You could also just use the CLI by changing into the directory you store the project, then entering the bash commands for compiling and running:
`javac ...` (replace the ellipses with the directory path to your project).
`java ...`  (again replace the ellipses with the directory path to your project).
To run the `JAR` file, change into the directory with the JAR file then enter the following command:
`java -jar ...` (again replace the ellipses with the directory path to your project).
