/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Assignment;
/**
 *
 * @author Yung
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Stock {
  
    private String name;
    private int stockQuantity;
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
    
    public void addStockQuantity(int stockQuantity){
        this.stockQuantity += stockQuantity;
    }
    
    public void subStockQuantity(int stockQuantity){
        
        this.stockQuantity -= stockQuantity;
    }

    public LocalDate getStockAddDate() {
        return stockAddDate;
    }

    public void setStockAddDate(LocalDate stockAddDate) {
        this.stockAddDate = stockAddDate;
    }
        
    public static void writeStockToFile(String Id,LocalDate date) throws IOException{
        try ( FileWriter writeBookFile = new FileWriter("StockFlow.txt",true)) {
            
                writeBookFile.write(Id + '|' + date +'\n');
            
            
        }
        System.out.println("Successfully wrote to the file.\n");
    }
    
    
    
    @Override
    public String toString() {
        return String.format("%-30s  %-10d  RM%-10.2f RM%-10.2f %-10s",name,stockQuantity,unitPrice,soldPrice,String.valueOf(stockStatus));
    }
 

}
