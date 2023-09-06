/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sales;

/**
 *
 * @author user
 */
public class StaticStorage {

    private static double TAX = 0.06;

    public static Order currentOrder = null;
    public static Cart currentCart = null;
    public static Transaction currentTransaction = null;
    public static Book currentBook = null;
    public static Stationary currentStationary = null;

    public static double getTax(){
        return TAX;
    }

    public static void setTax(double tax){
        TAX = tax;
    }

    public static void setCurrentOrder(Order order){
        currentOrder = order;
    }

    public static Order getCurrentOrder(){
        return currentOrder;
    }

    public static void setCurrentCart(Cart cart){
        currentCart = cart;
    }

    public static Cart getCurrentCart(){
        return currentCart;
    }

    public static void setCurrentBook(Book book){
        currentBook = book;
    }

    public static Book getCurrentBook(){
        return currentBook;
    }

    public static void setCurrentStationary(Stationary stationary){
        currentStationary = stationary;
    }

    public static Stationary getCurrentStationary(){
        return currentStationary;
    }
}

