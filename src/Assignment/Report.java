/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

import java.util.Scanner;


/**
 *
 * @author Yung
 */
public class Report {

    public void reportMenu(){
        Scanner input = new Scanner(System.in);
        int choice;
        
        do {
            System.out.println("1. Low Stock Report");
            System.out.println("0. Exit");
            
            System.out.print("Enter your choice : ");
            choice = input.nextInt();
            
            switch(choice){
                case 1->{
                    new LowStockReport().findLowStock();
                }
            }
            
        } while (choice != 0);
    }
    
    
    
}
