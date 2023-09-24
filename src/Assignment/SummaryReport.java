/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

import static Assignment.Assignment.clearScreen;
import static Assignment.Assignment.logo;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class SummaryReport extends Report {

    private final ArrayList<Order> orderList;
    Transaction transaction;

    public SummaryReport() {
        super();
        orderList = new ArrayList<>();
        initOrderList();
    }

    private void initOrderList(){
        for (Transaction summary : super.getTransactionList()) {
            orderList.add(summary.getOrder());
        }
    }
    
    @Override
    public void printReport() {
        clearScreen(); 
        logo();
        System.out.println("====================================================");
        System.out.println("                    Summary Report                  ");
        System.out.println("====================================================");
        Transaction summary = super.getTransactionList().get(0);
        
        summary.printSummaryTransaction(orderList);
   
        Assignment.systemPause();
    }
}