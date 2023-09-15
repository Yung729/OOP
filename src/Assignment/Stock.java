/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Assignment;
/**
 *
 * @author Yung
 */

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;


public abstract class Stock {
  
    private String name;
    private  int stockQuantity;
    private double unitPrice;
    private double soldPrice;
    private boolean stockStatus;
    LocalDate stockAddDate = LocalDate.now();

    public abstract void add();
    public abstract void adjust();
    public abstract void remove();
    public abstract void display();
    public abstract void search();
    
    Stock(){}
    
    public Stock(String name, int stockQuantity, double unitPrice, double soldPrice, boolean stockStatus) {
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.unitPrice = unitPrice;
        this.soldPrice = soldPrice;
        this.stockStatus = stockStatus;
    }
     
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
    
    public  void addStockQuantity(int stockQuantity){
        this.stockQuantity += stockQuantity;
    }
    
    public  void subStockQuantity(int stockQuantity){       
        this.stockQuantity -= stockQuantity;
    }

    public  LocalDate getStockAddDate() {
        return stockAddDate;
    }

    public  void setStockAddDate(LocalDate stockAddDate) {
        this.stockAddDate = stockAddDate;
    }

    public Boolean validQuantity(int Quantity){
        return Quantity > 0;
    }
    
    public Boolean validUnitPrice(double unitPrice){

        return unitPrice > 0;
    }
    
    public Boolean validSoldPrice(double soldPrice,double unitPrice){

        return soldPrice > unitPrice;
    }
    
    
    @Override
    public String toString() {
        return String.format("%-30s  %-10d  RM%-13.2f RM%-13.2f %-12s",name,stockQuantity,unitPrice,soldPrice,String.valueOf(stockStatus));
    }
 
    public static void stockAvailableMenu(){
        double totalUnitPrice=0.0 ,totalSoldPrice=0.0 ;  
        int quantity = 0,count=0;
        Assignment.clearScreen();
        ArrayList<Book> bookArray = new ArrayList<>();
        ArrayList<Stationary> staArray = new ArrayList<>();

         System.out.println("Display Available Book");
        System.out.printf("%-11s %-28s    %-8s    %-6s    %-9s    %-15s    %-13s    %-10s    %-10s    %-10s\n","Book Id","BookName","Quantity","Unit Price","Sold Price","Stock Status","Type",
                "Author Name","YearOfBirth","status");
        System.out.println("================================================================================================================================================================");
        
        try {
            Book.readBookFromFile(bookArray);
        } catch (FileNotFoundException ex) {
            System.out.println("Failed to Read File.");
        }

        for (Book bookDisplay: bookArray) {
            if (bookDisplay.isStockStatus()) {
                System.out.println(bookDisplay);   
            }
              
            quantity += bookDisplay.getStockQuantity();
            totalUnitPrice += (bookDisplay.getStockQuantity() * bookDisplay.getUnitPrice());
            totalSoldPrice +=  (bookDisplay.getStockQuantity() * bookDisplay.getSoldPrice());
            count++;
        }
        
        System.out.println("\nTotal Book :" + count +"\nTotal Quantity :" + quantity +"\nTotal UnitPrice :" +totalUnitPrice +"\nTotal SoldPrice:" + totalSoldPrice );
        Assignment.systemPause();
        
        System.out.println("Display Available Stationary");
        System.out.printf("%-15s %-28s    %-8s    %-6s    %-9s    %-15s    \n","Stationary Id","Stationary Name","Quantity",
                "Unit Price","Sold Price","Stock Status");
        System.out.println("==========================================================================================================");
        
        try {
            Stationary.readStaFromFile(staArray);
        } catch (FileNotFoundException ex) {
            System.out.println("Failed to Read File.");
        }

        quantity = 0;
        totalUnitPrice = 0;
        totalSoldPrice = 0;
        count = 0;
        
        for (Stationary staDisplay: staArray) {
            if (staDisplay.isStockStatus()) {
                System.out.println(staDisplay);   
            }
              
            quantity += staDisplay.getStockQuantity();
            totalUnitPrice += (staDisplay.getStockQuantity() * staDisplay.getUnitPrice());
            totalSoldPrice +=  (staDisplay.getStockQuantity() * staDisplay.getSoldPrice());
            count++;
        }
        
        System.out.println("\nTotal Book :" + count +"\nTotal Quantity :" + quantity +"\nTotal UnitPrice :" +totalUnitPrice +"\nTotal SoldPrice:" + totalSoldPrice );
        Assignment.systemPause();
    }
}
