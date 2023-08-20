/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Scanner;

/**
 *
 * @author Yung
 */
public class Assignment {

    public static void main(String[] args) {
        //logo();
        login();
    }
    
    public static void logo(){
        System.out.printf("%s\n", "                      _");
	System.out.printf("%s\n", "      _   _ _ __ ___ | |_");
	System.out.printf("%s\n", "     | | | | '_ ` _ \\| __|");
	System.out.printf("%s\n", "     | |_| | | | | | | |_");
	System.out.printf("%s\n\n", "      \\__,_|_| |_| |_|\\__|");
	
	
    }
    
    public static void login(){
        //login
        Scanner sc = new Scanner(System.in);
        boolean error = false;
        
        String testAdminID = "Ali";
        String testAdminPW = "Abc123";
        
        logo();
        System.out.println("===========================================");
        System.out.println("=                 LOGIN                   =");
        System.out.println("===========================================");
        
        do{
            error = false;
            System.out.printf("Enter ID: ");
            String id = sc.nextLine();
            System.out.printf("Enter Password: ");
            String password = sc.nextLine();
        
            if(id.equals(testAdminID) && password.equals(testAdminPW)){
                error = false;
                menu();
            } else{
                error = true;
                System.out.println("Invalid ID or Password. Please enter again!");
            }
        }while(error == true);
    }
    
    public static void menu(){
        //menu list of main program
        Scanner sc = new Scanner(System.in);
        boolean error = false;
        int choice;

        logo();
        System.out.println("===========================================");
        System.out.println("=                  MENU                   =");
        System.out.println("===========================================");
        System.out.println("=        1. Staff Manage                  =");
        System.out.println("=        2. Display Book Available Menu   =");
        System.out.println("=        3. Sales Order                   =");
        System.out.println("=        4. Membership Register           =");
        System.out.println("=        5. Stock Management              =");
        System.out.println("=        6. Sales Report                  =");
        System.out.println("=        7. Exit                          =");
        System.out.println("===========================================");
        
        do{
            error = false;
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            
            if(choice < 1 || choice > 7){
                error = true;
            }
            else{
                error = false;
            }
            
        }while (error == true);
        
        switch(choice){
            case 1:
                new StaffMain().staffMenu();
                break;
                
            
            case 5:
                new Stock().StockMenu();
                break;
                
            case 7:
        }
    }
    
        public static void systemPause(){
        Scanner buffer = new Scanner(System.in);
        System.out.print("Enter any key to continue....");
        buffer.nextLine(); 
    }
    
    public static void clearScreen() {  
         try {
            Robot robot = new Robot();
            robot.setAutoDelay(10);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_L);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_L);
        } catch (AWTException ex) {
            
        }
        
    }  
    
}
