/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sales;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Objects;

import static sales.StaticStorage.*;

/**
 *
 * @author xinru
 */

public class Menu {

    public static void salesMenu(){
        int option;

        while (true) {
//            clearScreen();
            System.out.println("             SALES MENU                 ");
            System.out.println("========================================");
            System.out.println("=        1. ADD ORDER                  =");
            System.out.println("=        2. DISPLAY ORDER CART         =");
            System.out.println("=        3. PAYMENT                    =");
            System.out.println("=        4. EXIT                       =");
            System.out.println("========================================");
            System.out.print("Enter your choice: ");
            option = Validation.getIntegerInput();

            switch (option) {
                case 1:
                    System.out.println("\n\n");
                    makeOrderMenu();
                    break;
                case 2:
                    System.out.println("Display Order Cart");
                    //change the logic here
                    if (currentOrder == null) {
                        System.out.println("No order has been made yet!");
                        break;
                    }
                    cartOptionMenu();
                    System.out.println("\n");
                    break;
                case 3:
                    paymentMenu();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid input!");
            }
        }
    }

    public static void bookListMenu(ArrayList<String[]> bookList) {
        System.out.println("BookID\t\tBook\t\t\t\t\t\tPrice(RM)\t\tStock Quantity");

        for (String[] book : bookList) {
            System.out.printf("%-8s\t%-20s\t\t%-8s\t%-15s\n", book[0], book[1], book[2], book[3]);
        }
    }

    public static void stationaryListMenu(ArrayList<String[]> stationaryList) {
        System.out.println("StaID\tStationary\t\t\t\tPrice(RM)\t\tStock Quantity");

        for (String[] stationary : stationaryList) {
            System.out.printf("%-8s\t%-20s\t\t%-8s\t%-15s\n", stationary[0], stationary[1], stationary[2], stationary[3]);
        }
    }

    public static void makeOrderMenu() {
        if(currentOrder == null) currentOrder = new Order();

        while (true) {
            System.out.println("========================================");
            System.out.println("             MAKE ORDER                 ");
            System.out.println("========================================");
            System.out.println("(Book)");
            bookListMenu(Tools.getAvailableBookList());

            System.out.println("\n(Stationary)");
            stationaryListMenu(Tools.getAvailableStationaryList());

            System.out.println("========================================");

            String itemID = Validation.getStringInput("Pick an item (BookID/ StationaryID)\t > ");
            int quantity = Validation.getIntegerInput("Quantity\t\t > ");
            boolean isContinue = Validation.checkIsContinue("Anymore book/stationary? (Y/N)\t > ");

            if (!FileHandler.checkIDExist(FileHandler.BOOK_STORE_DB,itemID)
                    && !FileHandler.checkIDExist(FileHandler.STATIONARY_DB,itemID)
                    && !Objects.equals(itemID, ""))
            {
                System.out.println("Input Item's ID does not exist! \n\n\n");
            }
            else {
                assert itemID != null;
                if (itemID.startsWith("B"))  currentOrder.addBookOrder(itemID, quantity);
                if (itemID.startsWith("S"))  currentOrder.addStationaryOrder(itemID, quantity);
                System.out.println("\n\nAdd to cart! \n\n");

                if (!isContinue) {
                    break;
                }
            }
        }
    }

    public static void cartOptionMenu() {
        if (currentOrder == null) {
            System.out.println("No order has been made yet!");
            System.out.println("\n\n");
            return;
        }
        if (currentCart == null) currentCart = new Cart(currentOrder);
        while (true) {
            currentCart.displayCart();
            System.out.println("1. Edit Cart \t\t\t2. Clear Cart \t\t\t3. Back");
            int option = Validation.getIntegerInput("Enter your choice > ");
            switch (option) {
                case 1 -> editCartMenu();
                case 2 -> {
                    System.out.println("\nClear Cart");
                    currentCart.clearCart();
                    currentCart = null;
                    currentOrder = null;
                    System.out.println("Cart cleared! Directing back to main menu...");
                    return;
                }
                case 3 -> {
                    return;
                }
                default -> System.out.println("Invalid input!");
            }
            System.out.println("\n\n\n\n\n");
        }
    }

    private static void editCartMenu() {
        if(currentCart == null) {
            System.out.println("No order has been made yet!");
            return;
        }

        if (currentCart.isCartEmpty()) {
            System.out.println("Cart is empty!");
            currentCart = null;
            currentOrder = null;
            return;
        }

        while (true) {
            currentCart.displayCart();
            System.out.println("             EDIT CART                  ");
            System.out.println("1. Add Item \t\t\t\t\t2. Remove Item");
            System.out.println("3. Edit Item Quantity \t\t\t4. Back");
            int option = Validation.getIntegerInput("Enter your choice > ");
            switch (option) {
                case 1:
                    System.out.println("\nAdd Item");
                    makeOrderMenu();
                    break;
                case 2:
                    System.out.println("\nRemove Item");
                    System.out.println("Enter the item ID to remove");
                    String itemID = Validation.getStringInput("Item ID > ");
                    if (itemID == null) break;
                    if (currentOrder.checkItemIsExist(itemID)) {
                        System.out.println("Item does not exist in cart!");
                        break;
                    }
                    currentCart.removeItem(itemID);
                    System.out.println("Item removed!");

                    if (currentCart.isCartEmpty()) {
                        currentCart = null;
                        currentOrder = null;
                        System.out.println("Cart is empty! Directing back to main menu...");
                        return;
                    }

                    break;
                case 3:
                    System.out.println("\nEdit Item Quantity");
                    System.out.println("Enter the item ID to edit");
                    itemID = Validation.getStringInput("Item ID > ");
                    if (itemID == null) break;
                    int quantity = Validation.getIntegerInput("Quantity > ");
                    if (quantity == -1) break;
                    if (quantity == 0) {
                        currentCart.removeItem(itemID);
                    }

                    if (currentCart.isCartEmpty()) {
                        currentCart = null;
                        currentOrder = null;
                        System.out.println("Cart is empty! Directing back to main menu...");
                        return;
                    }

                    if (currentOrder.checkItemIsExist(itemID)) {
                        System.out.println("Item does not exist in cart!");
                        break;
                    }
                    if (Tools.checkCurrentStockAvailable(itemID, quantity)) {
                        currentCart.editItemQuantity(itemID, quantity);
                        //update stock here
                        System.out.println("Stock updated!");
                    } else {
                        System.out.println("Not enough stock!");
                    }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid input!");
            }
            System.out.println("\n\n\n\n\n");
        }
    }

    public static void paymentMenu() {
        if (currentOrder == null) {
            System.out.println("No order has been made yet! \n");
            return;
        }
        if (currentCart == null) currentCart = new Cart(currentOrder);
        System.out.println("\nPayment");
        currentCart.displayCart();
        
        Scanner scanner = new Scanner(System.in);

        System.out.print("Is a member? (Y/N) > ");
        String userInput = scanner.nextLine();

        boolean isMember = Validation.checkYesNo(userInput);
        if (isMember) {
            System.out.println("Enter member ID");
            String memberID = Validation.getStringInput("Member ID > ");
            if (memberID == null) return;
            if (!FileHandler.checkIDExist(1FileHandler.MEMBER_DB, memberID)) {
                System.out.println("Member ID does not exist!\n");
                return;
            }
            currentOrder.setMemberDiscount(true);
            System.out.println("\tDiscount Active !!!  10% off");
            System.out.println("\tTotal Price: " + currentOrder.getTotalPriceWithTax());
        }

        System.out.println("\nPayment By: ");
        System.out.println("1. Bank \t\t2. Ewallet \t\t3. Cash");
        String method;
        int option = Validation.getIntegerInput("Enter choice > ");
        switch (option) {
            case 1 -> method = "Bank";
            case 2 -> method = "Ewallet";
            case 3 -> method = "Cash";
            default -> {
                System.out.println("Invalid input! \n");
                return;
            }
        }
        System.out.println("\nPayment Successful!\n");
        currentTransaction = new Transaction(method, currentOrder);
        currentTransaction.storeTransaction();
        displayReceipt();
    }

    private static void displayReceipt() {
        System.out.println("\nReceipt\n");
        System.out.println("Transaction Method: " + currentTransaction.getMethod() + "\t\t" + "Transaction ID: " + currentTransaction.getTransactionID());
        System.out.println("Date: " + currentTransaction.getDatetime());
        currentCart.displayCart();
        System.out.println("=============================================");
        System.out.println("\t\t\tThank you for purchasing\n\n");
        currentCart = null;
        currentOrder = null;
        currentTransaction = null;
    }

    public static void reportMenu() {
        SummaryReport report = new SummaryReport();
        HotSellReport hotSellReport = new HotSellReport();
        while(true) {
            System.out.println("========================================");
            System.out.println("             REPORT MENU                ");
            System.out.println("========================================");
            System.out.println("1. View Sales Summary Report");
            System.out.println("2. View Famous Book Report");
            System.out.println("3. Exit");
            System.out.println("========================================");
            int option = Validation.getIntegerInput("Enter your choice > ");
            switch(option) {
                case 1:
                    System.out.println("\n(Sales Summary Report)");
                    report.printReport();
                    break;
                case 2:
                    System.out.println("\nHot Sales Report (Top 5 Items)");
                    hotSellReport.printReport();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid input!");
            }
            System.out.println("\n");
        }
    }
        



    
}
