/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

import java.util.ArrayList;


/**
 *
 * @author user
 */

public class Order {
    private String orderID;
    private ArrayList<String> bookOrder;
    private ArrayList<String> stationaryOrder;
    private ArrayList<Integer> bookQuantity;
    private ArrayList<Integer> stationaryQuantity;
    private double totalPrice;
    private boolean memberDiscount = false;

    public Order() {
        this.orderID = initNewOrderID();
        bookOrder = new ArrayList<>();
        stationaryOrder = new ArrayList<>();
        bookQuantity = new ArrayList<>();
        stationaryQuantity = new ArrayList<>();
        totalPrice = 0;
    }

    public Order(String id, ArrayList<String> bookOrder, ArrayList<Integer> bookQuantity,
                 ArrayList<String> stationaryOrder, ArrayList<Integer> stationaryQuantity, double totalPrice) {
        this.orderID = id;
        this.bookOrder = bookOrder;
        this.bookQuantity = bookQuantity;
        this.stationaryOrder = stationaryOrder;
        this.stationaryQuantity = stationaryQuantity;
        this.totalPrice = totalPrice;
    }

    private String initNewOrderID(){
        int newID = FileHandler.getLastRowID(FileHandler.ORDER_DB);
        newID++;
        return Tools.getIDStrFormat('I', newID);
    }

    public void setOrderIDByInt(int orderID){
        this.orderID = Tools.getIDStrFormat('I', orderID);
    }

    public void setOrderID(String orderID){
        this.orderID = orderID;
    }

    public String getOrderID(){
        return orderID;
    }

    public void setBookOrder(ArrayList<String> bookOrder){
        this.bookOrder = bookOrder;
    }

    public void setStationaryOrder(ArrayList<String> stationaryOrder){
        this.stationaryOrder = stationaryOrder;
    }

    public ArrayList<String> getBookOrder(){
        return bookOrder;
    }

    public ArrayList<String> getStationaryOrder(){
        return stationaryOrder;
    }

    public void setBookQuantity(ArrayList<Integer> bookQuantity){
        this.bookQuantity = bookQuantity;
    }

    public void setStationaryQuantity(ArrayList<Integer> stationaryQuantity){
        this.stationaryQuantity = stationaryQuantity;
    }

    public ArrayList<Integer> getBookQuantity(){
        return bookQuantity;
    }

    public ArrayList<Integer> getStationaryQuantity(){
        return stationaryQuantity;
    }

    public void setTotalPrice(double totalPrice){
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice(){
        return totalPrice;
    }

    public double getTotalPriceWithTax(){
        return Math.round(((totalPrice + getOrderTax()) * (memberDiscount ? 0.9 : 1)) * 100.0) / 100.0;
    }

    public double getOrderTax(){
        return Math.round((totalPrice * StaticStorage.getTax()) * 100.0) / 100.0;
    }

    public void setMemberDiscount(boolean memberDiscount){
        this.memberDiscount = memberDiscount;
    }

    public boolean getMemberDiscount(){
        return memberDiscount;
    }

    public void addBookOrder(String bookID, int quantity){
        if (bookOrder.contains(bookID)) {
            int index = bookOrder.indexOf(bookID);
            bookQuantity.set(index, bookQuantity.get(index) + quantity);
        }else {
            bookOrder.add(bookID);
            bookQuantity.add(quantity);
        }

        this.totalPrice += quantity * Tools.getBookPriceByID(bookID);

        Tools.substractItemStock(bookID, quantity);
    }

    public void removeBookOrder(String bookID){
        int index = bookOrder.indexOf(bookID);

        this.totalPrice -= Tools.getBookPriceByID(bookID) * bookQuantity.get(index);
        Tools.addItemStock(bookID, bookQuantity.get(index));

        bookOrder.remove(index);
        bookQuantity.remove(index);
    }

    public void clearBookOrder(){
        for (String bookID : bookOrder) {
            Tools.addItemStock(bookID, bookQuantity.get(bookOrder.indexOf(bookID)));
        }
        bookOrder.clear();
        bookQuantity.clear();
        totalPrice = 0;
    }

    public void updateBookOrderQuantity(String bookID, int quantity) {
        int index = bookOrder.indexOf(bookID);
        
        if (bookQuantity.get(index) < quantity) {
            this.totalPrice += (quantity - bookQuantity.get(index)) * Tools.getBookPriceByID(bookID);
            Tools.substractItemStock(bookID, quantity);
        }else {
            this.totalPrice -= (bookQuantity.get(index) - quantity) * Tools.getBookPriceByID(bookID);
            Tools.addItemStock(bookID, quantity);
        }
        bookQuantity.set(index, quantity);
    }

    private ArrayList<String> getBookQuantityStrArr() {
        ArrayList<String> intArr = new ArrayList<>();
        for (Integer integer : bookQuantity) {
            intArr.add(String.valueOf(integer));
        }
        return intArr;
    }

    public void addStationaryOrder(String stationaryID, int quantity){
        if (stationaryOrder.contains(stationaryID)) {
            int index = stationaryOrder.indexOf(stationaryID);
            stationaryQuantity.set(index, stationaryQuantity.get(index) + quantity);
        }
        else {
            stationaryOrder.add(stationaryID);
            stationaryQuantity.add(quantity);
        }

        this.totalPrice += quantity * Tools.getStationaryPriceByID(stationaryID);
        Tools.substractItemStock(stationaryID, quantity);
    }

    public void removeStationaryOrder(String stationaryID){
        int index = stationaryOrder.indexOf(stationaryID);

        this.totalPrice -= Tools.getStationaryPriceByID(stationaryID) * stationaryQuantity.get(index);
        Tools.addItemStock(stationaryID, stationaryQuantity.get(index));

        stationaryOrder.remove(index);
        stationaryQuantity.remove(index);
    }

    public void clearStationaryOrder(){
        for (int i = 0; i < stationaryOrder.size(); i++) {
            Tools.addItemStock(stationaryOrder.get(i), stationaryQuantity.get(i));
        }
        stationaryOrder.clear();
        stationaryQuantity.clear();
        totalPrice = 0;
    }

    public void updateStationaryOrderQuantity(String stationaryID, int quantity) {
        int index = stationaryOrder.indexOf(stationaryID);

        if (stationaryQuantity.get(index) < quantity) {
            this.totalPrice += (quantity - stationaryQuantity.get(index)) * Tools.getStationaryPriceByID(stationaryID);
            Tools.substractItemStock(stationaryID, quantity);
        }else {
            this.totalPrice -= (stationaryQuantity.get(index) - quantity) * Tools.getStationaryPriceByID(stationaryID);
            Tools.addItemStock(stationaryID, quantity);
        }
        stationaryQuantity.set(index, quantity);
    }

    private ArrayList<String> getStationaryQuantityStrArr() {
        ArrayList<String> intArr = new ArrayList<>();
        for (Integer integer : stationaryQuantity) {
            intArr.add(String.valueOf(integer));
        }
        return intArr;
    }

    public void clearAllItems() {
        clearBookOrder();
        clearStationaryOrder();
        totalPrice = 0;
    }

    public boolean checkItemIsExist(String itemID) {
        return !bookOrder.contains(itemID) && !stationaryOrder.contains(itemID);
    }

    public void printOrderDetails(){
        if (bookOrder.size() != 0) {
            System.out.printf("%-6s\t%-20s\t%-5s\n", "BookID", "Book", "Quantity");
            for (int i = 0; i < bookOrder.size(); i++) {
                System.out.printf("%-6s\t%-20s\t%-5d\n", bookOrder.get(i), Tools.getBookNameByID(bookOrder.get(i)), bookQuantity.get(i));
            }
            System.out.println();
        }
        if (stationaryOrder.size() != 0) {
            System.out.printf("%-6s\t%-20s\t%-5s\n", "StaID", "Stationary", "Quantity");
            for (int i = 0; i < stationaryOrder.size(); i++) {
                System.out.printf("%-6s\t%-20s\t%-5d\n", stationaryOrder.get(i), Tools.getStationaryNameByID(stationaryOrder.get(i)), stationaryQuantity.get(i));
            }
        }
        System.out.println("\n====================================================");
        System.out.printf("%-30s\t%-5.2f\n","Total Amount:", totalPrice);
    }

    public void saveOrderToDB() {
        String newLine = orderID + "|" + String.join(":", bookOrder) + "|" + String.join(":",getBookQuantityStrArr()) + "|" +
                String.join(":", stationaryOrder) + "|" + String.join(":",getStationaryQuantityStrArr()) + "|" + getTotalPriceWithTax();
        FileHandler.writeFile(FileHandler.ORDER_DB, newLine);
    }


}

