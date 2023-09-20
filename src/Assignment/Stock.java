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
  
    protected String name;
    protected  int stockQuantity;
    protected double unitPrice;
    protected double soldPrice;
    protected boolean stockStatus;
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
    
    public String convertBooleanToString(boolean arrive){
        if (arrive) {
            return Assignment.GREEN +"Enough"+Assignment.RESET;
        }else{
            return Assignment.RED+"Out Of Stock"+Assignment.RESET;
        }
    }
    
    @Override
    public String toString() {
        return String.format("%-30s  %-10d  RM%-12.2f RM%-12.2f %-25s",name,stockQuantity,unitPrice,soldPrice,convertBooleanToString(stockStatus));
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
            Book.readFromFile(bookArray);
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
        
        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tTotal Book :" + count 
                            +"\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tTotal Quantity :" + quantity +
                            "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tTotal UnitPrice :" +totalUnitPrice +
                            "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tTotal SoldPrice:" + totalSoldPrice +
                            "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tTotal Estimate Sold:" + (totalSoldPrice - totalUnitPrice));
        Assignment.systemPause(Assignment.GREEN+"Press Any Key To Show Stationary List...."+Assignment.RESET);
        Assignment.clearScreen();
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
        
        System.out.println("\n\t\t\t\t\t\t\t\t\t\tTotal Book :" + count 
                            +"\n\t\t\t\t\t\t\t\t\t\tTotal Quantity :" + quantity +
                            "\n\t\t\t\t\t\t\t\t\t\tTotal UnitPrice :" +totalUnitPrice +
                            "\n\t\t\t\t\t\t\t\t\t\tTotal SoldPrice:" + totalSoldPrice +
                            "\n\t\t\t\t\t\t\t\t\t\tTotal Estimate Sold:" + (totalSoldPrice - totalUnitPrice));
        
        
        Assignment.systemPause();
    }
    
       
    public static void stockMainMenu(){
         int choice;
      
      do {
        
       Assignment.clearScreen();

        Assignment.logo();
        System.out.println("===========================================");
        System.out.println("=           Stock Management              =");
        System.out.println("===========================================");
        System.out.println("=        1. Book Management               =");
        System.out.println("=        2. Stationary Management         =");
        System.out.println("=        3. Stock Report                  =");
        System.out.println("=        0. Exit                          =");
        System.out.println("===========================================");
        
       System.out.print("Enter Your Choice >");
       choice = Validation.getIntegerInput();
       
       switch(choice){
           
           case 1 -> stockMenu("Book Management",new Book() );
              
           case 2 -> stockMenu("Stationary Management",new Stationary());
           
           case 3 -> new Report().stockReportMenu();
           
           case 0 -> {}
           
           
           default -> {
                    System.out.println(Assignment.RED + "Invalid Input ! " + Assignment.RESET);
                    Assignment.systemPause();
           }
       }
   
      } while (choice != 0); 
    }

    public static void stockMenu(String title,Stock obj) {
         
        int choice;
        Stock stock = obj;
      
        do {

            Assignment.clearScreen();

            Assignment.logo();
            System.out.println("===========================================");
            System.out.printf("=           %s               =\n",title);
            System.out.println("===========================================");
            System.out.println("=        1. Add Stock                     =");
            System.out.println("=        2. Adjust Stock                  =");
            System.out.println("=        3. Remove Stock                  =");
            System.out.println("=        4. Display Stock                 =");
            System.out.println("=        5. Search Stock                  =");
            System.out.println("=        0. Exit                          =");
            System.out.println("===========================================");

            System.out.print("Enter Your Choice >");
            choice = Validation.getIntegerInput();

            switch(choice){

                case 1 -> stock.add();

                case 2 -> stock.adjust();

                case 3 -> stock.remove();

                case 4 -> stock.display();

                case 5 -> stock.search();

                case 0 -> {}

                default -> {
                    System.out.println(Assignment.RED + "Invalid Input ! " + Assignment.RESET);
                    Assignment.systemPause();
                }
            }

        } while (choice != 0);
                
      
    }
}
