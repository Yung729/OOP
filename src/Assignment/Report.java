/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;



/**
 *
 * @author Yung
 */
public class Report {

    public void reportMenu(){
        
        int choice;
        
        do {
            Assignment.clearScreen();
            Assignment.logo();
            System.out.println("1. Low Stock Report");
            System.out.println("2. Stock Discount Report");
            System.out.println("0. Exit");

            choice = Validation.getIntegerInput("Enter your choice : ");
            
            switch(choice){
                case 1->{
                    new LowStockReport().findLowStock();
                }
                
                case 2 ->{
                    new StockDiscountReport().discountReport();
                }
            }
            
        } while (choice != 0);
    }
    
    
    
}
