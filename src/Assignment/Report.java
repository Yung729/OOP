/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

import java.util.ArrayList;

/**
 *
 * @author Yung
 */
public class Report {

    private final ArrayList<Transaction> transactionList;

    public Report() {
        transactionList = new ArrayList<>();
        initTransactionList();
    }

    private void initTransactionList() {
        ArrayList<String> transactionIDList = FileHandler.getColumnByType(FileHandler.TRANSACTION_DB, 0);
        for (String transactionID : transactionIDList) {
            transactionList.add(new Transaction(transactionID));
        }
    }

    public void printReport() {
        System.out.println("Display Report");
    }

    protected ArrayList<Transaction> getTransactionList() {
        return transactionList;
    }

    public static void salesReportMenu() {
        SummaryReport sReport = new SummaryReport();
        HotSellReport hotSellReport = new HotSellReport();

        while (true) {
            Assignment.clearScreen();
            Assignment.logo();
            System.out.println("========================================");
            System.out.println("             REPORT MENU                ");
            System.out.println("========================================");
            System.out.println("1. View Sales Summary Report");
            System.out.println("2. View Hot Sell Report");
            System.out.println("0. Exit");
            System.out.println("========================================");
            int option = Validation.getIntegerInput("Enter your choice > ");

            switch (option) {
                case 1 -> {
                    System.out.println("\n(Sales Summary Report)");
                    sReport.printReport();
                }
                case 2 -> {
                    System.out.println("\nHot Sales Report (Top 5 Items)");
                    hotSellReport.printReport();
                }
                case 0 -> {
                    return;
                }
                default ->
                    System.out.println(Assignment.RED + "Invalid input!" + Assignment.RESET);
            }
            System.out.println("\n");
        }
    }

    public void stockReportMenu() {
        int choice;

        do {
            Assignment.clearScreen();
            Assignment.logo();
            System.out.println("=========================");
            System.out.println("   Stock Report List  ");
            System.out.println("=========================");
            System.out.println("1. Low Stock Report");
            System.out.println("2. Stock Discount Report");
            System.out.println("3. Stock Flow Report");
            System.out.println("0. Exit");
            System.out.println("=========================");

            choice = Validation.getIntegerInput("Enter your choice : ");

            switch (choice) {
                case 1 -> {
                    new LowStockReport().printReport();
                }

                case 2 -> {
                    new StockDiscountReport().printReport();
                }

                case 3 -> {
                    new StockFlowReport().printReport();
                }

                default ->
                    System.out.println(Assignment.RED + "Invalid input!" + Assignment.RESET);
            }

        } while (choice != 0);
    }

}
