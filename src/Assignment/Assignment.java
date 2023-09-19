/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Yung
 */
public class Assignment {

    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    
    public static final String REDBG = "\u001B[41m";
    public static final String GREENBG = "\u001B[42m";
    public static final String YELLOWBG = "\u001B[43m";
    public static final String BLUEBG = "\u001B[44m";
    public static final String PURPLEBG = "\u001B[45m";
    public static final String CYANBG = "\u001B[46m";
    public static final String RESET = "\u001B[0m";
    
    
    
    static Scanner input = new Scanner(System.in);
    public static String CURRENTNAME;
    
    public static void main(String[] args) {
        
        login();
    }
    
    public static void logo(){
        System.out.printf(PURPLE+"%s\n", "                      _"+RESET);
	System.out.printf(BLUE+"%s\n", "      _   _ _ __ ___ | |_"+RESET);
	System.out.printf(PURPLE+"%s\n", "     | | | | '_ ` _ \\| __|"+RESET);
	System.out.printf(BLUE+"%s\n", "     | |_| | | | | | | |_"+RESET);
	System.out.printf(PURPLE+"%s\n\n", "      \\__,_|_| |_| |_|\\__|"+RESET);
        
	
	
    }
    
   public static void login(){

        ArrayList<Admin> adminArray = new ArrayList();
        ArrayList<Cashier> cashierArray = new ArrayList();
        
        boolean exist = false;
        boolean error = false;
        int currentIndex = 0;
        
        String id;
        String password;
        
        try {
            Admin.readAdminFromFile(adminArray);
        } catch (FileNotFoundException ex) {
            System.out.println(RED + "Cannot read the file!" + RESET);
            return;
        }
        
        try {
            new Cashier().readCashierFromFile(cashierArray);
        } catch (FileNotFoundException ex) {
            System.out.println(RED + "Cannot read the file!" + RESET);
            return;
        }

        do {
            clearScreen();
            logo();
            exist = true;
            
            System.out.println("===========================================");
            System.out.println("=                 LOGIN                   =");
            System.out.println("===========================================");
            
            System.out.println(GREEN+"**Enter x to log out**"+RESET);
            

            System.out.print("Enter ID > ");
            id = input.nextLine();
            if(id.equalsIgnoreCase("x")){
                return;
            }
            
            System.out.print("Enter Password > ");
            password = input.nextLine();
            if(password.equalsIgnoreCase("x")){
                return;
            }
            
     
            if(id.startsWith("AD")){
                exist = checkAdminIDPW(adminArray, id, password);
                if(exist){
                    printAdminLoginName(adminArray, id, password);
                    CURRENTNAME = storeAdminLoginName(adminArray, id, password);
                    systemPause();
                    adminMenu();
                    exist = false;
                    input.nextLine();
                }
                else{
                    System.out.println(RED + "Invalid ID or Password. Please enter again!" + RESET);
                }
            } else if(id.startsWith("CH")){
                exist = checkCashierIDPW(cashierArray, id, password);
                if(exist){
                    printCashierLoginName(cashierArray, id, password);
                    CURRENTNAME = storeCashierLoginName(cashierArray, id, password);
                    systemPause();
                    cashierMenu();
                    exist = false;
                    input.nextLine();
                }
                else{
                    System.out.println(RED + "Invalid ID or Password. Please enter again!" + RESET);
                }
            } else{
                exist = false;
                System.out.println(RED + "Invalid ID or Password. Please enter again!" + RESET);
            }
           
        } while (!exist);

       
    }
 
   public static void adminMenu(){
        //menu list of main program    
        
        int choice;
        boolean error;
        
        do{
            clearScreen(); 
            logo();
            error = false;
            System.out.println("Current Login > " + CURRENTNAME);
            
            System.out.println("===========================================");
            System.out.println("=                  MENU                   =");
            System.out.println("===========================================");
            System.out.println("=        1. Staff Manage                  =");
            System.out.println("=        2. Display Stock Available Menu  =");
            System.out.println("=        3. Sales Order                   =");
            System.out.println("=        4. Membership Register           =");
            System.out.println("=        5. Stock Management              =");
            System.out.println("=        6. Sales Report                  =");
            System.out.println("=        0. Exit                          =");
            System.out.println("===========================================");
        
            System.out.print("Enter your choice > ");
                choice = input.nextInt();
            
            switch(choice){
                case 1 -> {new StaffMain().staffMenu();}
                
                case 2 ->{Stock.stockAvailableMenu();}
                
                case 3->{Menu.salesMenu();}
                
                case 4 ->{new Member().memberMenu();}
                
                case 5 ->{stockMainMenu();}
            
                case 6 -> {Menu.reportMenu();}
                
                case 0 ->{}
                
                default -> System.out.println(RED + "Invalid input. Please enter again!" + RESET);
                           
            }
        }while (choice != 0 || error);
        
       
    }
   
   public static void cashierMenu(){
        //menu list of main program
        
        int choice;
        boolean error;

        do{
            clearScreen(); 
            error = false;
            System.out.println("Current Login > " + CURRENTNAME);
            
            System.out.println("===========================================");
            System.out.println("=                  MENU                   =");
            System.out.println("===========================================");
            System.out.println("=        1. Display Book Available Menu   =");
            System.out.println("=        2. Sales Order                   =");
            System.out.println("=        3. Membership Register           =");
            System.out.println("=        0. Exit                          =");
            System.out.println("===========================================");
            
            System.out.print("Enter your choice > ");
            try{
                choice = input.nextInt();
            }catch (Exception e){
                System.out.println("Invalid input!");
                choice = 0;
                error = true;
            }
            
            switch(choice){
                
                case 1-> {
                    Stock.stockAvailableMenu();
                }
                
                case 2-> {
                    Menu.salesMenu();
                }
                
                case 3-> {
                    new Member().memberRegistration();
                }
                
                case 0 -> {}
                
                default -> System.out.println("Invalid input. Please enter again!");
                           
            }
        }while (choice != 0 || error);
        
        
    }
   
    public static void stockMainMenu(){
         int choice;
      
      do {
        
       clearScreen();

        logo();
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
           
           case 3 -> new Report().stockReportMenu();
           
           case 0 -> {}
           
           
           default -> {
                    System.out.println(RED + "Invalid Input ! " + RESET);
                    systemPause();
           }
       }
   
      } while (choice != 0); 
    }

    public static void stockMenu(String title,Object obj) {
         
      int choice;

        switch (obj) {
            case Book book -> {
                
                do {
                    
                    clearScreen();
                    
                    logo();
                    System.out.println("===========================================");
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
                            systemPause();
                        }
                    }
                    
                } while (choice != 0);
                
            }       
            
            case Stationary stationary -> {
                
                
                do {
                    
                    clearScreen();
                    
                    logo();
                    System.out.println("===========================================");
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
                            systemPause();
                        }
                    }
                    
                } while (choice != 0);
            }
            default -> {
            }
        } 
    
    }
   
    public static boolean checkAdminIDPW(ArrayList<Admin> adminArray, String searchID,String searchPassword){
        boolean exist = false;
        for(Admin ad : adminArray){
            if(ad.getId().equals(searchID) && ad.getPassword().equals(searchPassword))
                exist = true;
        }
        
        return exist;
    }
    
    public static boolean checkCashierIDPW(ArrayList<Cashier> cashierArray, String searchID, String searchPassword){
        boolean exist = false;
        for(Cashier ch : cashierArray){
            if(ch.getId().equals(searchID) && ch.getPassword().equals(searchPassword))
                exist = true;
        }
        
        return exist;
    }
    
    public static int checkAdminIndexNumber(ArrayList<Admin> adminArray, String searchID, String password){
        int currentIndex = 0;
        for(int i = 0; i < adminArray.size();i++){
            if(searchID.equals(adminArray.get(i).getId())){
                currentIndex = i;
            }
        }
        
        return currentIndex;
    }
    
    public static int checkCashierIndexNumber(ArrayList<Cashier> cashierArray, String searchID, String password){
        int currentIndex = 0;
        for(int i = 0; i < cashierArray.size();i++){
            if(searchID.equals(cashierArray.get(i).getId())){
                currentIndex = i;
            }
        }
        
        return currentIndex;
    }
    
    public static void printAdminLoginName(ArrayList<Admin> adminArray, String id, String password){
        int currentIndex = 0;
        
        currentIndex = checkAdminIndexNumber(adminArray, id, password);
        System.out.println("\nWelcome, " + adminArray.get(currentIndex).getName() + "!");
    }
    
    public static void printCashierLoginName(ArrayList<Cashier> cashierArray, String id, String password){
        int currentIndex = 0;
        
        currentIndex = checkCashierIndexNumber(cashierArray, id, password);
        System.out.println("\nWelcome, " + cashierArray.get(currentIndex).getName() + "!");
    }
    
    public static String storeAdminLoginName(ArrayList<Admin> adminArray, String id, String password){
        int currentIndex = 0;
        
        currentIndex = checkAdminIndexNumber(adminArray, id, password);
        return adminArray.get(currentIndex).getName();
    }
    
    public static String storeCashierLoginName(ArrayList<Cashier> cashierArray, String id, String password){
        int currentIndex = 0;
        
        currentIndex = checkCashierIndexNumber(cashierArray, id, password);
        return cashierArray.get(currentIndex).getName();
    }
    
    //common function
    public static void systemPause(){
        Scanner buffer = new Scanner(System.in);
        System.out.print(GREEN+"Enter any key to continue...."+RESET);
        buffer.nextLine(); 
    }
    
    public static void systemPause(String sentence){
        Scanner buffer = new Scanner(System.in);
        System.out.print(sentence);
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
