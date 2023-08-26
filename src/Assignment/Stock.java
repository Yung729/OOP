/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Assignment;


import static Assignment.Assignment.RED;
import static Assignment.Assignment.RESET;
import java.util.Scanner;


/**
 *
 * @author Yung
 */

public class Stock {

    
    static Scanner input = new Scanner(System.in);
    static Scanner inputString = new Scanner(System.in);
  
    public void menu(){
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

    public void stockMenu(String title,Object obj) {
         
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
                    System.out.println("=        0. Exit                          =");
                    System.out.println("===========================================");
                    
                    System.out.print("Enter Your Choice >");
                    choice = Validation.getIntegerInput();
                    
                    switch(choice){
                        
                        case 1 -> book.add();
                        
                        case 2 -> book.adjust();
                        
                        case 3 -> book.remove();
                        
                        case 4 -> book.display();
                        
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
                    System.out.println("=        0. Exit                          =");
                    System.out.println("===========================================");
                    
                    System.out.print("Enter Your Choice >");
                    choice = Validation.getIntegerInput();
                    
                    switch(choice){
                        
                        case 1 -> stationary.add();
                        
                        case 2 -> stationary.adjust();
                        
                        case 3 -> stationary.remove();
                        
                        case 4 -> stationary.display();
                        
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
 

}
