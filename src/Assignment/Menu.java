/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Assignment;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Objects;
import static Assignment.Assignment.RED;
import static Assignment.Assignment.RESET;
import static Assignment.Assignment.clearScreen;
import static Assignment.Assignment.logo;
import static Assignment.Assignment.systemPause;

import static Assignment.StaticStorage.*;

/**
 *
 * @author xinru
 */

public class Menu {

    public static void salesMenu(){
        int option;

        while (true) {
            clearScreen();
            logo();
            System.out.println("========================================");
            System.out.println("             SALES MENU                 ");
            System.out.println("========================================");
            System.out.println("=        1. ADD ORDER                  =");
            System.out.println("=        2. DISPLAY ORDER CART         =");
            System.out.println("=        3. PAYMENT                    =");
            System.out.println("=        0. EXIT                       =");
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
                    if (currentOrder == null) {
                        System.out.println(RED + "No order has been made yet!" + RESET);
                        break;
                    }
                    cartOptionMenu();
                    System.out.println("\n");
                    break;
                case 3:
                    paymentMenu();
                    break;
                case 0:
                    return;
                default:
                    System.out.println(RED + "Invalid input!" + RESET);
            }
        }
    }

    public static void bookListMenu(ArrayList<String[]> bookList) {
        System.out.printf("%-8s\t%-20s\t\t%-10s\t%-15s\n", "BookID", "Book", "Price(RM)", "Stock Quantity");

        for (String[] book : bookList) {
            System.out.printf("%-8s\t%-20s\t\t%-10s\t%-15s\n", book[0], book[1], book[4], book[2]);
        }
    }

    public static void stationaryListMenu(ArrayList<String[]> stationaryList) {
        System.out.printf("%-8s\t%-20s\t\t%-10s\t%-15s\n", "StaID", "Stationary", "Price(RM)", "Stock Quantity");

        for (String[] stationary : stationaryList) {
            System.out.printf("%-8s\t%-20s\t\t%-10s\t%-15s\n", stationary[0], stationary[1], stationary[4], stationary[2]);
        }
    }

    public static void makeOrderMenu() {
        if(currentOrder == null) currentOrder = new Order();

        while (true) {
            clearScreen();
            System.out.println("===========================================");
            System.out.println("             MAKE ORDER                 ");
            System.out.println("===========================================");
            System.out.println("(Book)");
            bookListMenu(Tools.getAvailableBookList());

            System.out.println("\n(Stationary)");
            stationaryListMenu(Tools.getAvailableStationaryList());

            System.out.println("================================================================================");

            String itemID = Validation.getStringInput("Pick an item (BookID/ StationaryID)\t > ");
            if (!FileHandler.checkIDExist(FileHandler.BOOK_DB,itemID)
                    && !FileHandler.checkIDExist(FileHandler.STATIONARY_DB,itemID)
                    && !Objects.equals(itemID, ""))
            {
                System.out.println(RED + "Input Item's ID does not exist! \n\n\n" + RESET);
                continue;
            }

            int quantity = Validation.getIntegerInput("Quantity\t\t > ");
            if (!Tools.checkItemStock(itemID, quantity)) {
                System.out.println(RED + "Not enough stock!\n" + RESET);
                continue;
            }

            boolean isContinue = Validation.checkIsContinue("Anymore book/stationary? (Y/N)\t > ");

            assert itemID != null;
            if (itemID.startsWith("B"))
                currentOrder.addBookOrder(itemID, quantity);
            if (itemID.startsWith("S"))
                currentOrder.addStationaryOrder(itemID, quantity);
            System.out.println("\n\nAdd to cart! \n\n");
            if (!isContinue) break;
        }
    }

    public static void cartOptionMenu() {
        if (currentOrder == null) {
            System.out.println(RED + "No order has been made yet! " + RESET);
            System.out.println("\n\n");
            return;
        }
        if (currentCart == null) currentCart = new Cart(currentOrder);
        while (true) {
            currentCart.displayCart();
            System.out.println("\n1. Edit Cart \t\t\t2. Clear Cart \t\t\t3. Back");
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
                default -> System.out.println(RED + "Invalid input!" + RESET);
            }
            System.out.println("\n\n\n\n\n");
        }
    }

    private static void editCartMenu() {
        if(currentCart == null) {
            System.out.println(RED + "No order has been made yet!" + RESET);
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
            clearScreen();
            System.out.println("=================================================================");
            System.out.println("             EDIT CART                  ");
            System.out.println("=================================================================");
            System.out.println("1. Add Item \t\t\t\t\t2. Remove Item");
            System.out.println("3. Edit Item Quantity \t\t\t\t4. Back");
            System.out.println("=================================================================");
            int option = Validation.getIntegerInput("Enter your choice > ");
            switch (option) {
                case 1:
                    System.out.println("\nAdd Item");
                    makeOrderMenu();
                    break;
                case 2:
                    System.out.println("\nEnter the item ID to remove");
                    String itemID = Validation.getStringInput("Item ID > ");
                    if (itemID == null) break;
                    if (currentOrder.checkItemIsExist(itemID)) {
                        System.out.println(RED + "Item does not exist in cart!" + RESET);
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
                    System.out.println("\nEnter the item ID to edit");
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
                        System.out.println(RED + "Item does not exist in cart!" + RESET);
                        break;
                    }
                    if (Tools.checkCurrentStockAvailable(itemID, quantity)) {
                        currentCart.editItemQuantity(itemID, quantity);
                        System.out.println("Stock updated!");
                    } else {
                        System.out.println(RED + "Not enough stock!" + RESET);
                    }
                    break;
                case 4:
                    return;
                default:
                    System.out.println(RED + "Invalid input!" + RESET);
            }
            System.out.println("\n\n\n\n\n");
        }
    }

    public static void paymentMenu() {
        if (currentOrder == null) {
            System.out.println(RED + "No order has been made yet! \n" + RESET);
            return;
        }
        if (currentCart == null) currentCart = new Cart(currentOrder);
        System.out.println("\nPayment");
        currentCart.displayCart();
        
        Scanner scanner = new Scanner(System.in);

        System.out.print("Is a member? (Y/N) > ");
        String userInput = scanner.nextLine();

        boolean isMember = Validation.checkYesNo(userInput.charAt(0));
        if (isMember && userInput.charAt(0) == 'Y' || userInput.charAt(0) == 'y') {
            System.out.println("Enter member ID");
            String memberID = Validation.getStringInput("Member ID > ");
            if (memberID == null) return;
            if (!FileHandler.checkIDExist(FileHandler.MEMBER_DB, memberID)) {
                System.out.println(RED + "Member ID does not exist!\n" + RESET);
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
                System.out.println(RED + "Invalid input! \n" + RESET);
                return;
            }
        }
        System.out.println("\nPayment Successful!\n");
        currentTransaction = new Transaction(method, currentOrder);
        currentTransaction.storeTransaction();
        displayReceipt();
    }

    private static void displayReceipt() {
        logo();
        System.out.println("=============================================");
        System.out.println("\nReceipt\n");
        System.out.println("Transaction Method: " + currentTransaction.getMethod() + "\t\t" + "Transaction ID: " + currentTransaction.getTransactionID());
        System.out.println("Date: " + currentTransaction.getDatetime());
        currentCart.displayCart();
        System.out.println("=============================================");
        System.out.println("\t\t\tThank you for purchasing. HAVE A NICE DAY!\n\n");
        currentCart = null;
        currentOrder = null;
        currentTransaction = null;
        systemPause();
    }

    
           
}


