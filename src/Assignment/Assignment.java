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

    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    
    public static void main(String[] args) {
        login();
    }
    
    public static void logo(){
        System.out.printf("%s\n", "                      _");
	System.out.printf("%s\n", "      _   _ _ __ ___ | |_");
	System.out.printf("%s\n", "     | | | | '_ ` _ \\| __|");
	System.out.printf("%s\n", "     | |_| | | | | | | |_");
	System.out.printf("%s\n\n", "      \\__,_|_| |_| |_|\\__|");
        System.out.println("===========================================");
	
	
    }
    
    public static void login(){
        //login
        Scanner sc = new Scanner(System.in);
        boolean error = false;
        
        String testAdminID = "Ali";
        String testAdminPW = "Abc123";
        
        logo();
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

    do{
        clearScreen();
        logo();
        System.out.println("=                  MENU                   =");
        System.out.println("===========================================");
        System.out.println("=        1. Staff Manage                  =");
        System.out.println("=        2. Display Book Available Menu   =");
        System.out.println("=        3. Sales Order                   =");
        System.out.println("=        4. Membership Register           =");
        System.out.println("=        5. Stock Management              =");
        System.out.println("=        6. Sales Report                  =");
        System.out.println("=        0. Exit                          =");
        System.out.println("===========================================");
        
        do{
            error = false;
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            
            if(choice <= 6 && choice >= 0){
                error = false;
            }
            else{
                error = true;
            }
            
        }while (error == true);
        
        switch(choice){
            case 1:
                new StaffMain().staffMenu();
                break;
              
            case 3:
                break;
            
            case 5:
                stockMainMenu();
                break;
                
            case 0:
        }
    
    }while(choice != 0);
    }
    
    public static void stockMainMenu(){
         int choice;
      
      do {
        
       Assignment.clearScreen();

        Assignment.logo();
        System.out.println("===========================================");
        System.out.println("=           Stock Management              =");
        System.out.println("===========================================");
        System.out.println("=        1. Book Management               =");
        System.out.println("=        2. Stationary Management         =");
        System.out.println("=        3. Stock Report                  =");
        System.out.println("=        0. Exit                          =");
        System.out.println("===========================================");
        
       System.out.print("Enter Your Choice >");
       choice = Validation.getIntegerInput();
       
       switch(choice){
           
           case 1 -> stockMenu("Book Management",new Book() );
              
           case 2 -> stockMenu("Stationary Management",new Stationary());
     
           case 0 -> {}

           case 3 -> new Report().reportMenu();
           
           default -> {
                    System.out.println(RED + "Invalid Input ! " + RESET);
                    Assignment.systemPause();
           }
       }
   
      } while (choice != 0); 
    }

    public static void stockMenu(String title,Object obj) {
         
      int choice;

        switch (obj) {
            case Book book -> {
                
                do {
                    
                    Assignment.clearScreen();
                    
                    Assignment.logo();
                    System.out.printf("=           %s               =\n",title);
                    System.out.println("===========================================");
                    System.out.println("=        1. Add Stock                     =");
                    System.out.println("=        2. Adjust Stock                  =");
                    System.out.println("=        3. Remove Stock                  =");
                    System.out.println("=        4. Display Stock                 =");
                    System.out.println("=        5. Search Stock                  =");
                    System.out.println("=        0. Exit                          =");
                    System.out.println("===========================================");
                    
                    System.out.print("Enter Your Choice >");
                    choice = Validation.getIntegerInput();
                    
                    switch(choice){
                        
                        case 1 -> book.add();
                        
                        case 2 -> book.adjust();
                        
                        case 3 -> book.remove();
                        
                        case 4 -> book.display();
                        
                        
                        case 5 -> book.search();
                        
                        case 0 -> {}
                        
                        default -> {
                            System.out.println(RED + "Invalid Input ! " + RESET);
                            Assignment.systemPause();
                        }
                    }
                    
                } while (choice != 0);
                
            }       
            
            case Stationary stationary -> {
                
                
                do {
                    
                    Assignment.clearScreen();
                    
                    Assignment.logo();
                    
                    System.out.printf("=           %s         =\n",title);
                    System.out.println("===========================================");
                    System.out.println("=        1. Add Stock                     =");
                    System.out.println("=        2. Adjust Stock                  =");
                    System.out.println("=        3. Remove Stock                  =");
                    System.out.println("=        4. Display Stock                 =");
                    System.out.println("=        5. Search Stock                 =");
                    System.out.println("=        0. Exit                          =");
                    System.out.println("===========================================");
                    
                    System.out.print("Enter Your Choice >");
                    choice = Validation.getIntegerInput();
                    
                    switch(choice){
                        
                        case 1 -> stationary.add();
                        
                        case 2 -> stationary.adjust();
                        
                        case 3 -> stationary.remove();
                        
                        case 4 -> stationary.display();
                        
                        case 5 -> stationary.search();
                        
                        case 0 -> {}
                        
                        default -> {
                            System.out.println(RED + "Invalid Input ! " + RESET);
                            Assignment.systemPause();
                        }
                    }
                    
                } while (choice != 0);
            }
            default -> {
            }
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
