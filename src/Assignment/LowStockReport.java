/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author Yung
 */
public class LowStockReport extends Report {

    private int lowStockLine;

    LowStockReport() {
        lowStockLine = 0;
    }

    public void setLowStockLine(int lowStockLine) {
        this.lowStockLine = lowStockLine;
    }

    public boolean checkLowStock(Stock book) {

        return book.getStockQuantity() <= lowStockLine;

    }

    @Override
    public void printReport() {

        boolean record = false;
        ArrayList<Book> bookArray = new ArrayList<>();

        try {
            Book.readFromFile(bookArray);
        } catch (FileNotFoundException ex) {
            System.out.println("Failed to read file");
        }

        Assignment.clearScreen();
        Assignment.logo();
        System.out.println("                                   Low Stock Report");
        System.out.println("========================================================================================");

        do {
            System.out.print("Please enter a low line > ");
            lowStockLine = Validation.getIntegerInput();

            if (lowStockLine == -1) {
                System.out.println(Assignment.RED + "Invalid number" + Assignment.RESET);
            }
        } while (lowStockLine == -1);

        Assignment.clearScreen();
        Assignment.logo();
        System.out.println("                                   Low Book Report");
        System.out.println("========================================================================================");
        System.out.printf("%-11s %-28s    %-8s    %-6s    %-9s    %-15s    %-13s    %-10s    %-10s    %-10s\n", "Book Id", "BookName", "Quantity", "Unit Price", "Sold Price", "Book Status", "Type",
                "Author Name", "YearOfBirth", "status");
        System.out.println("================================================================================================================================================================");

        for (Book bookCheck : bookArray) {
            if (checkLowStock(bookCheck)) {
                System.out.println(bookCheck);
                record = true;
            }
        }

        if (!record) {
            System.out.println(Assignment.RED + "No Record" + Assignment.RESET);
        }

        Assignment.systemPause(Assignment.RED + "Press Enter Show Low Stationary ..." + Assignment.RESET);
        Assignment.clearScreen();
        Assignment.logo();
        record = false;
        ArrayList<Stationary> staArray = new ArrayList<>();

        try {
            Stationary.readStaFromFile(staArray);
        } catch (FileNotFoundException ex) {
            System.out.println("Failed to read file");
        }

        System.out.println("                                   Low Stationary Report");
        System.out.println("========================================================================================");
        System.out.printf("%-15s %-28s    %-8s    %-6s    %-9s    %-15s    \n", "Stationary Id", "Stationary Name", "Quantity",
                "Unit Price", "Sold Price", "Stock Status");
        System.out.println("==========================================================================================================");

        for (Stationary staCheck : staArray) {
            if (checkLowStock(staCheck)) {
                System.out.println(staCheck);
                record = true;
            }

        }

        if (!record) {
            System.out.println(Assignment.RED + "No Record" + Assignment.RESET);
        }

        Assignment.systemPause();

    }

}
