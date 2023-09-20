/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

import static Assignment.Assignment.clearScreen;
import static Assignment.Assignment.logo;

/**
 *
 * @author user
 */
public class SummaryReport extends Report {

    @Override
    public void printReport() {
        clearScreen(); 
        logo();
        System.out.println("====================================================");
        for (Transaction transaction : super.getTransactionList()) {
            transaction.printTransaction();
            System.out.println("====================================================");
        }
        Assignment.systemPause();
    }
}