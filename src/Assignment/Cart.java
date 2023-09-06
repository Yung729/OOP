/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sales;
import java.util.ArrayList;

import static sales.StaticStorage.currentOrder;
/**
 *
 * @author user
 */
public class Cart {
    private Order order;

    public Cart(){
        order = new Order();
    }

    public Cart(Order order) {
        this.order = order;
    }

    public void setOrder(Order order){
        this.order = order;
    }

    public Order getOrder(){
        return order;
    }

    public void addBook(String bookID, int quantity){
        order.addBookOrder(bookID, quantity);
    }

    public void addStationary(String stationaryID, int quantity){
        order.addStationaryOrder(stationaryID, quantity);
    }

    public void removeItem(String itemID){
        if (itemID.charAt(0) == 'B') {
            removeBook(itemID);
        } else if (itemID.charAt(0) == 'S') {
            removeStationary(itemID);
        }
    }

    private void removeBook(String bookID){
        order.removeBookOrder(bookID);
    }


    private void removeStationary(String stationaryID){
        order.removeStationaryOrder(stationaryID);
    }

    public void clearCart(){
        order.clearAllItems();
    }

    private ArrayList<String> getOrderBookList(){
        return order.getBookOrder();
    }

    private ArrayList<String> getOrderStationaryList(){
        return order.getStationaryOrder();
    }

    private ArrayList<Integer> getOrderBookQuantity(){
        return order.getBookQuantity();
    }

    private ArrayList<Integer> getOrderStationaryQuantity(){
        return order.getStationaryQuantity();
    }

    public void editItemQuantity(String itemID, int quantity){
        if (itemID.charAt(0) == 'B') {
            editBookQuantity(itemID, quantity);
        } else if (itemID.charAt(0) == 'S') {
            editStationaryQuantity(itemID, quantity);
        }
    }

    private void editBookQuantity(String bookID, int quantity){
        order.updateBookOrderQuantity(bookID, quantity);
    }

    private void editStationaryQuantity(String stationaryID, int quantity){
        order.updateStationaryOrderQuantity(stationaryID, quantity);
    }

    public boolean isCartEmpty(){
        return order.getBookOrder().isEmpty() && order.getStationaryOrder().isEmpty();
    }

    public void displayCart() {
        System.out.println("====================================");
        System.out.println("|            Order Cart            |");
        System.out.println("====================================");
        System.out.println("| Order ID : " + order.getOrderID());
        System.out.println("| Book Selected:");
        System.out.println("| Book ID\t\t\tBook Name\t\t\tQuantity\t\tPrice(RM)");
        if (getOrderBookList().size() == 0) {
            System.out.println("| -   No Book Selected");
        }
        for (int i = 0; i < getOrderBookList().size(); i++) {
            System.out.printf("| %-15s\t%-25s\t%-5d\t\t%-12.2f\n",
                    getOrderBookList().get(i), Tools.getBookNameByID(getOrderBookList().get(i)),
                    getOrderBookQuantity().get(i), getOrderBookQuantity().get(i) * Tools.getBookPriceByID(getOrderBookList().get(i))
            );
        }

        System.out.println("\n| Stationary Selected:");
        System.out.println("| Stationary ID\t\tStationary Name\t\tQuantity\t\tPrice(RM)");
        if (getOrderStationaryList().size() == 0) {
            System.out.println("| -   No Stationary Selected");
        }
        for (int i = 0; i < getOrderStationaryList().size(); i++) {
            System.out.printf("| %-15s\t%-25s\t%-5d\t\t%-12.2f\n",
                    getOrderStationaryList().get(i), Tools.getStationaryNameByID(getOrderStationaryList().get(i)),
                    getOrderStationaryQuantity().get(i), getOrderStationaryQuantity().get(i) * Tools.getStationaryPriceByID(getOrderStationaryList().get(i))
            );
        }

        System.out.println("\n\t\t\t\t\t\t\t\t\t\tTax:\t\t\t"+ order.getOrderTax());
        System.out.println("\t\t\t\t\t\t\t\t\t\tTotal:\t\t\t" + (order.getTotalPrice() == 0 ? "0.00" : order.getTotalPriceWithTax()));
        System.out.println("=============================================================\n");

    }
}
