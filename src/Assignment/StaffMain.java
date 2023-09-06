/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;
import static Assignment.Assignment.clearScreen;
import static Assignment.Assignment.RED;
import static Assignment.Assignment.RESET;
import static Assignment.Assignment.input;
import static Assignment.Assignment.CURRENTNAME;

public class StaffMain {
    //staff menu
    public void staffMenu(){
  
        int choice;
        boolean error;
        
        clearScreen();  
        
        do{
            error = false;
            System.out.println("Current Login > " + CURRENTNAME);
            
            System.out.println("=====================================");
            System.out.println("=             STAFF MENU            =");
            System.out.println("=====================================");
            System.out.println("=      1. Add Staff                 =");
            System.out.println("=      2. Remove Staff              =");
            System.out.println("=      3. Edit Info                 =");
            System.out.println("=      4. Search Staff              =");
            System.out.println("=      5. View Staff                =");
            System.out.println("=      6. Staff Sales Performance   =");
            System.out.println("=      7. Exit                      =");
            System.out.println("=====================================");
            
            System.out.print("Enter your choice > ");
            try{
                choice = input.nextInt();
            }catch (Exception e){
                System.out.println("Invalid input!");
                choice = 0;
                error = true;
            }
            
            switch(choice){
                case 1 -> addStaff();
                
                case 2 -> removeStaff();
                
                case 3 -> editStaff();
                
                case 4 -> searchStaff();
                
                case 5 -> viewStaff();
                
                case 6 -> staffSalesPerformance();
                
                case 7 -> {}
                
                default -> System.out.println(RED + "Invalid Input. Please enter again!" + RESET);
                             
            }
            
        }while(choice != 7 || error);
        

    }
    
    public void addStaff(){  

        int choice;
        boolean error;
        
        clearScreen();  
        
        do{
            error = false;
            System.out.println("Current Login > " + CURRENTNAME);
             
            System.out.println("============================================");
            System.out.println("=                 ADD STAFF                =");
            System.out.println("============================================");
            System.out.println("=              1. Add Admin                =");
            System.out.println("=              2. Add Cashier              =");
            System.out.println("=              3. Exit                     =");
            System.out.println("============================================");
            
            System.out.print("Enter your choice >  ");
            try{
                choice = input.nextInt();
            }catch (Exception e){
                System.out.println("Invalid input!");
                choice = 0;
                error = true;
            }
            
            switch(choice){
                case 1 -> new Admin().addAdmin();
                
                case 2 -> new Cashier().addCashier();
                
                case 3 -> {}
                
                default -> System.out.println(RED + "Invalid Input. Please enter again!" + RESET);
                                     
            }
            
        }while(choice != 3 || error);
        
    }
    
    public void removeStaff(){
        
        int choice;
        boolean error;
        
        clearScreen();  
        
        do{
            error = false;
            System.out.println("Current Login > " + CURRENTNAME);
            
            System.out.println("============================================");
            System.out.println("=               DELETE STAFF               =");
            System.out.println("============================================");
            System.out.println("=             1. Delete Admin              =");
            System.out.println("=             2. Delete Cashier            =");
            System.out.println("=             3. Exit                      =");
            System.out.println("============================================");
            
            System.out.print("Enter your choice > ");
            try{
                choice = input.nextInt();
            }catch (Exception e){
                System.out.println("Invalid input!");
                choice = 0;
                error = true;
            }
            
            switch(choice){
                case 1 -> new Admin().deleteAdmin();
                
                case 2 -> new Cashier().deleteCashier();
                
                case 3 -> {}
                
                default -> System.out.println(RED + "Invalid Input. Please enter again!" + RESET);
                               
                            
            }
            
        }while(choice != 3 || error);

    }
    
    public void editStaff(){
        
        int choice;
        boolean error;
     
        clearScreen();  
        
        do{
            error = false;
            System.out.println("Current Login > " + CURRENTNAME);
            
            System.out.println("============================================");
            System.out.println("=                EDIT STAFF                =");
            System.out.println("============================================");
            System.out.println("=             1. Edit Admin                =");
            System.out.println("=             2. Edit Cashier              =");
            System.out.println("=             3. Exit                      =");
            System.out.println("============================================");

            System.out.print("Enter your choice > ");
            try{
                choice = input.nextInt();
            }catch (Exception e){
                System.out.println("Invalid input!");
                choice = 0;
                error = true;
            }
            
            switch(choice){
                case 1 -> new Admin().editAdmin();
                
                case 2 -> new Cashier().editCashier();
                
                case 3 -> {}
                
                default -> System.out.println(RED + "Invalid Input. Please enter again!" + RESET);
                            
            }
            
        }while(choice != 3 || error);
        
       

    }
    
    public void searchStaff(){

        int choice;
        boolean error;
        
        clearScreen();  
        
        do{
            error = false;
            System.out.println("Current Login > " + CURRENTNAME);
           
            System.out.println("============================================");
            System.out.println("=              SEARCH STAFF                =");
            System.out.println("============================================");
            System.out.println("=             1. Search Admin              =");
            System.out.println("=             2. Search Cashier            =");
            System.out.println("=             3. Exit                      =");
            System.out.println("============================================");
            
            System.out.print("Enter your choice > ");
            try{
                choice = input.nextInt();
            }catch (Exception e){
                System.out.println("Invalid input!");
                choice = 0;
                error = true;
            }
            
            switch(choice){
                case 1 -> new Admin().searchAdmin();
                
                case 2 -> new Cashier().searchCashier();
                
                case 3 -> {}
                
                default -> System.out.println(RED + "Invalid Input. Please enter again!" + RESET);
                               
                            
            }
        } while(choice !=3 || error);
    }
    
    public void viewStaff(){
        
        int choice;
        boolean error;
        
        clearScreen();  
        
        do{
            error = false;
            System.out.println("Current Login > " + CURRENTNAME);
            
            System.out.println("============================================");
            System.out.println("=                VIEW STAFF                =");
            System.out.println("============================================");
            System.out.println("=             1. View Admin                =");
            System.out.println("=             2. View Cashier              =");
            System.out.println("=             3. Exit                      =");
            System.out.println("============================================");
            
            System.out.print("Enter your choice > ");
            try{
                choice = input.nextInt();
            }catch (Exception e){
                System.out.println("Invalid input!");
                choice = 0;
                error = true;
            }
            
            switch(choice){
                case 1 -> new Admin().viewAdminInformation();
                
                case 2 -> new Cashier().viewCashierInformation();
                
                case 3 -> {}
                
                default -> System.out.println(RED + "Invalid Input. Please enter again!" + RESET);
                                
            }
        } while(choice !=3 || error);
        
        
    }
    
    public void staffSalesPerformance(){
        
        int choice;
        boolean error;
        
        clearScreen();  
        
        do{
            error = false;
            System.out.println("Current Login > " + CURRENTNAME);
           
            System.out.println("============================================");
            System.out.println("=           STAFF SALES PERFORMANCE        =");
            System.out.println("============================================");
            System.out.println("=             1. View Admin                =");
            System.out.println("=             2. View Cashier              =");
            System.out.println("=             3. Exit                      =");
            System.out.println("============================================");
            
            System.out.print("Enter your choice > ");
            try{
                choice = input.nextInt();
            }catch (Exception e){
                System.out.println("Invalid input!");
                choice = 0;
                error = true;
            }
            
            switch(choice){
                case 1 -> new Admin().adminSalesPerformance();
                
                case 2 -> new Cashier().cashierSalesPerformance();
                
                case 3 -> {}
                
                default -> System.out.println(RED + "Invalid Input. Please enter again!" + RESET);
                               
                            
            }
            
        }while(choice != 3 || error);
        
    }
    
}
