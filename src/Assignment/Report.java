/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
        ArrayList<String> transactionIDList = FileHandler.getColumnByType(FileHandler.TRANSACTION_DB, 0);
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
    
    public void stockReportMenu(){      
        int choice;
        
        do {
            Assignment.clearScreen();
            Assignment.logo();
            System.out.println("Stock Report List  ");
            System.out.println("=========================");
            System.out.println("1. Low Stock Report");
            System.out.println("2. Stock Discount Report");
            System.out.println("3. Stock Flow Report");
            System.out.println("0. Exit");
            System.out.println("=========================");
            
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
