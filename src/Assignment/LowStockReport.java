 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        
        if (book.getBookBalance() <= lowStockLine) {
            lowStock = true;
        }
        
        return lowStock;
        
    }
    
    public void findLowStock(){
       
        ArrayList<Book> bookArray = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        try {
            Book.readBookFromFile(bookArray);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Stock.clearScreen();
        
        System.out.println("Low Stock Report");
        System.out.println("================");

        System.out.print("Please enter a low line > ");
        lowStockLine = input.nextInt();
        for (Book bookCheck : bookArray) {
            if (checkLowStock(bookCheck)) {
                System.out.println(bookCheck);              
            }
            
        }
        
        Stock.systemPause();
    
    }
    
}
