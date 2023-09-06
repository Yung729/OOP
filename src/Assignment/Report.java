
package Assignment;

import java.util.ArrayList;



/**
 *
 * @author Yung
 */
public class Report {
    private final ArrayList<Transaction> transactionList;
    
     public Report(){
        transactionList = new ArrayList<>();
        initTransactionList();
    }
     
    private void initTransactionList(){
        ArrayList<String> transactionIDList = FileHandler.getColumnByType(FileHandler.TRANSACTION_DB, "transactionID");
        for (String transactionID : transactionIDList) {
            transactionList.add(new Transaction(transactionID));
        }
    }
    
    public void printReport(){
        System.out.println("Display Report");
    }
    
    protected ArrayList<Transaction> getTransactionList(){
        return transactionList;
    }
     
    public void reportMenu(){      
        int choice;
        Report report = new Report();
         
         
        do {
            Assignment.clearScreen();
            Assignment.logo();
            System.out.println("1. Low Stock Report");
            System.out.println("2. Stock Discount Report");
            System.out.println("3. Stock Flow Report");
            System.out.println("0. Exit");

            choice = Validation.getIntegerInput("Enter your choice : ");
            
            switch(choice){
                case 1->{
                    new LowStockReport().findLowStock();
                }
                
                case 2 ->{
                    new StockDiscountReport().discountReport();
                }
                
                case 3 ->{
                    new StockFlowReport().report();
                }
            }
            
        } while (choice != 0);
    }
    
    
    
}
