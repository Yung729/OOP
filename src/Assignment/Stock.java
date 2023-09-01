/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Assignment;


import static Assignment.Assignment.RED;
import static Assignment.Assignment.RESET;
import java.util.Scanner;


/**
 *
 * @author Yung
 */

public abstract class Stock {

    
    static Scanner input = new Scanner(System.in);
    static Scanner inputString = new Scanner(System.in);
  
    private String name;
    private int stockQuantity;
    private double unitPrice;
    private double soldPrice;
    private boolean stockStatus;

    public abstract void add();
    public abstract void adjust();
    public abstract void remove();
    public abstract void display();
    public abstract void search();
    
    public Stock(String name, int stockQuantity, double unitPrice, double soldPrice, boolean stockStatus) {
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.unitPrice = unitPrice;
        this.soldPrice = soldPrice;
        this.stockStatus = stockStatus;
    }
    
    Stock(){}
   
    //getter
    public String getName() {
        return name;
    }

    public boolean isStockStatus() {
        return stockStatus;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }


    public double getUnitPrice() {
        return unitPrice;
    }

    public double getSoldPrice() {
        return soldPrice;
    }
    
    //setter
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setSoldPrice(double soldPrice) {
        this.soldPrice = soldPrice;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setStockStatus(boolean stockStatus) {
        this.stockStatus = stockStatus;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void addStockQuantity(int stockQuantity){
        this.stockQuantity += stockQuantity;
    }
    
    public void subStockQuantity(int stockQuantity){
        
        this.stockQuantity -= stockQuantity;
    }

    @Override
    public String toString() {
        return String.format("%-30s  %-10d  RM%-10.2f RM%-10.2f %-10s",name,stockQuantity,unitPrice,soldPrice,String.valueOf(stockStatus));
    }
 

}
