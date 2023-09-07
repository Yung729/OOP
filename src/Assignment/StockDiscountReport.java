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
public class StockDiscountReport extends Report{
    
    StockDiscountReport() {
    }
    

    public void discountReport(){
        
        ArrayList<Book> bookArr = new ArrayList<>();
        
        try {
            Book.readBookFromFile(bookArr);
        } catch (FileNotFoundException ex) {
        }
        Assignment.clearScreen();
        System.out.println("Stock Discount Available Report");
        System.out.println("===============================");
        System.out.println("Book ID" + " Original Sold Price" + " Sold Price Dicounted");
        System.out.println("======================================================");
        for (Book book:bookArr) {
            
            
            if (!book.author.checkArrive()) {
                double oriPrice = (book.getSoldPrice() / Author.DISCOUNT_RATE);
                System.out.println(book.getBookId() + "          RM" + oriPrice + "                RM" + book.getSoldPrice() );
            }
                
            
        }
        Assignment.systemPause();
    }
}
