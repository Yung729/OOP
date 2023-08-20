/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
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

public class Stock {

    
    static Scanner input = new Scanner(System.in);
    static Scanner inputString = new Scanner(System.in);
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    private final String[] STOCK_TYPE = {"Book","Stationary"};
//2
    //3
    public Stock() {
    }
    
    
    public static void main(String[] args) {
         
      int choice;
       
      do {
        
       clearScreen();
       System.out.println("book Management");
       System.out.println("1. Add Stock");
       System.out.println("2. Adjust Stock");
       System.out.println("3. Remove Stock");
       System.out.println("4. Display Stock");
       System.out.println("5. Stock Report");
       System.out.println("0. Exit");
       
       System.out.print("Enter Your Choice >");
       choice = Validation.getIntegerInput();
       
       switch(choice){
           
           case 1 -> addStock();
              
           case 2 -> adjustStock();
     
           case 3 -> removebook();
   
           case 4 -> displaybook();
        
           case 5 -> new Report().reportMenu();
     
           case 0 -> {}

           default -> {
                    System.out.println(RED + "Invalid Input ! " + RESET);
                    systemPause();
           }
       }
   
      } while (choice != 0);
    
    }
    
    public static void displaybook(){
       
      
    int choice;
    
    do {
        clearScreen();
        System.out.println("Display Stock System");
        System.out.println("====================");
        System.out.println("1. Display All Book");
        System.out.println("2. Display All Stationary");
        System.out.println("0. Exit");
        System.out.print("Enter your choice >");
        choice = Validation.getIntegerInput();
        
        switch(choice){
        
            case 1->{
                    Book.display();
            }
            
            case 2->{
                    Stationary.display();
            }
            
            case 0 ->{}
            
            default -> {
                    System.out.println(RED + "Invalid Input ! " + RESET);
                    systemPause();
           }
        }
                
     } while (choice != 0);
        
        
    }
    
    public static void adjustStock() {
    
        int choice;
        
        do {
            
            clearScreen();
            System.out.println("1. Edit Book");
            System.out.println("2. Edit Stationary");
            System.out.println("0. Exit");
            System.out.print("Enter your choice > ");
            choice = Validation.getIntegerInput();
            
            switch (choice) {
                case 1 -> {
                    Book.adjust();
                }
                
                case 2 -> {
                    Stationary.adjust();
                }
                
                case 0 ->{}
                
                default -> {
                    System.out.println(RED + "Invalid Input ! " + RESET);
                    systemPause();
                }
            }
            
        } while (choice != 0);
         
    }
    
    public static void addStock() {
    
    int choice;   
    
    do {
        
       clearScreen();
       System.out.println("Add Stock ");
       System.out.println("==========");
       System.out.println("1. Add book");
       System.out.println("2. Add Stationary");
       System.out.println("0. Exit");
       
       System.out.print("Enter Your Choice >");
       choice = Validation.getIntegerInput();
       
        switch (choice) {
            case 1 -> {
                new Book().add();
            }
            case 2 -> {
                
                new Stationary().add();
            }
            
            case 0 ->{}
            
            default -> {
                System.out.println(RED + "Invalid Input ! " + RESET);
                systemPause();
            }
        }
        
      } while (choice != 0);
            
     
    }
    
    public static void removebook() {
       
    int choice;
      
    do {
        
       clearScreen();
       System.out.println("Remove Stock ");
       System.out.println("1. Remove book");
       System.out.println("2. Remove Stationary");      
       System.out.println("0. Exit");
       
       System.out.print("Enter Your Choice >");
       choice = Validation.getIntegerInput();
       
        switch (choice) {
            case 1 -> {
                new Book().remove();
            }
            case 2 -> {
                new Stationary().remove();
            }
            
            case 0 -> {}
            
            default -> {
                System.out.println(RED + "Invalid Input ! " + RESET);
                systemPause();
            }
        }
       
     } while (choice != 0);
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
