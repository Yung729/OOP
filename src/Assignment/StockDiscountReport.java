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
    private boolean discountActive;

    StockDiscountReport() {
    }

    StockDiscountReport(boolean discountActive) {
        this.discountActive = discountActive;
    }
    
    public boolean checkStockDiscount(Book book){

        return !book.author.checkArrive();
    }
    
    public void discountReport(){
        
        ArrayList<Book> bookArr = new ArrayList<>();
        
        try {
            Book.readBookFromFile(bookArr);
        } catch (FileNotFoundException ex) {
        }

        System.out.println("Stock Discount Available Report");
        System.out.println("===============================");
        for (Book book:bookArr) {

            if (checkStockDiscount(book)) {
                System.out.println(book);
            }
        }
        Assignment.systemPause();
    }
}
