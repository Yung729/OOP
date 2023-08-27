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
    
    public boolean checkLowStock(Book book){
     
        boolean lowStock = false;
        
        if (book.getStockQuantity() <= lowStockLine) {
            lowStock = true;
        }
        
        return lowStock;
        
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
        
        for (Book bookCheck : bookArray) {
            if (checkLowStock(bookCheck)) {
                System.out.println(bookCheck);              
            }
            
        }
        
        Assignment.systemPause();
    
    }
    
}
