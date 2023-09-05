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
    
    public double calculateBookPrice(double soldPrice,double discount){
        return soldPrice - (soldPrice * discount);
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
        for (Book book:bookArr) {

            double bookPrice = calculateBookPrice(book.getSoldPrice(),book.author.memoryDiscount(book)) ;
            
            if (bookPrice != book.getSoldPrice() ) {
                System.out.println(book.getBookId() + " RM" + book.getSoldPrice() + " RM" + bookPrice );
            }
        }
        Assignment.systemPause();
    }
}
