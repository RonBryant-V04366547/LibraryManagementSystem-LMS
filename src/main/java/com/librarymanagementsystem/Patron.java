/**
 * Ronald Bryant
 * CEN 3024C-31032 - Software Development 1
 * June 19, 2026
 * Patron.java
 * This class holds a single library patron's ID, name, address, and overdue fine amount,
 * with simple methods to retrieve or update this data.
 */

package com.librarymanagementsystem;

public class Patron {

    private int id;
    private String name;
    private String address;
    private double overdueFine;

    public Patron(int id, String name, String address, double overdueFine) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.overdueFine = overdueFine;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getOverdueFine() {
        return overdueFine;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setOverdueFine(double overdueFine) {
        this.overdueFine = overdueFine;
    }

    /**
     * method: toString
     * parameters: none
     * return: String
     * purpose: Returns the patron's information (ID, name, address, and overdue fine),
     *          when the patron list is shown.
     */
    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Address: " + address + " | Overdue Fine: $" +
                String.format("%.2f", overdueFine);
    }

}
