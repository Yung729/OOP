 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author Yung
 */
public class LowStockReport extends Report{
    
    private int lowStockLine;
    
    LowStockReport(){
        lowStockLine = 0;
    }
  
    public void setLowStockLine(int lowStockLine){
        this.lowStockLine = lowStockLine;
    }
    
    public boolean checkLowStock(Stock book){
 
        return book.getStockQuantity() <= lowStockLine;
        
    }
    
    public void findLowStock(){
       
        ArrayList<Book> bookArray = new ArrayList<>();
        
        try {
            Book.readBookFromFile(bookArray);
        } catch (FileNotFoundException ex) {
            System.out.println("Failed to read file");
        }
        
        Assignment.clearScreen();
        
        System.out.println("Low Stock Report");
        System.out.println("================");

        lowStockLine = Validation.getIntegerInput("Please enter a low line > ");
        System.out.println("Low Book Report");
        System.out.println("================");
        for (Book bookCheck : bookArray) {
            if (checkLowStock(bookCheck)) {
                System.out.println(bookCheck);              
            }
            
        }
        
        ArrayList<Stationary> staArray = new ArrayList<>();
        
        try {
            Stationary.readStaFromFile(staArray);
        } catch (FileNotFoundException ex) {
            System.out.println("Failed to read file");
        }
 
        System.out.println("Low Stationary Report");
        System.out.println("================");
        for (Stationary staCheck : staArray) {
            if (checkLowStock(staCheck)) {
                System.out.println(staCheck);              
            }
            
        }
        
        Assignment.systemPause();
    
    }
    
}
