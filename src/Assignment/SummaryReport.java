/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

import static Assignment.Assignment.systemPause;

/**
 *
 * @author user
 */
public class SummaryReport extends Report {

    @Override
    public void printReport() {
        System.out.println("Total transaction: " + super.getTransactionList().size());

        System.out.println("====================================================");
        for (Transaction transaction : super.getTransactionList()) {
            
             transaction.printTransaction();
            
            System.out.println("====================================================");

        }
        systemPause();
    }
}