/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;
import java.util.Scanner;
import java.util.Date;

/**
 *
 * @author Yung
 */
public class StaffMain {
    //staff menu
    public void staffMenu(){
        
        Scanner sc = new Scanner(System.in);
        
        boolean error = false;
        int choice;
        
        System.out.println("=====================================");
        System.out.println("=             STAFF MENU            =");
        System.out.println("=====================================");
        System.out.println("=       1. Add Staff                =");
        System.out.println("=       2. Remove Staff             =");
        System.out.println("=       3. Edit Info                =");
        System.out.println("=       4. Staff Sales Performance  =");
        System.out.println("=       5. Exit                     =");
        System.out.println("=====================================");
        
        do{
            error = false;
            System.out.println("Enter your chhoice: ");
            choice = sc.nextInt();
            
            if(choice < 1 || choice > 5){
                error = true;
            }else{
                error = false;
            }
        }while(error == true);
        
        switch(choice){
            case 1:
                //addStaff();
                break;
            case 2:
                //removeStaff();
                break;
            case 3:
                //editInfo();
                break;
            case 4:
                //staffSalesPerformance();
                break;
            case 5:
                return;
        }
    }
    
    public void addStaff(){
        Scanner sc = new Scanner(System.in);
        
        String id;
        String password;
        String name;
        String ic;
        String position;
        String phoneNumber;
        String email;
        String address;
        double salary;
        Date hireDate;
        
        boolean error = false;
        boolean cont = false; //continue looping
        int choice;
        
        System.out.println("============================================");
        System.out.println("=               ADD STAFF                  =");
        System.out.println("============================================");
        
        do{
            cont = false;
            
            do{
                error = false;
                System.out.println("Enter your id: ");
                id = sc.nextLine();
                
            }while(error == true);
            
            do{
                error = false;
                System.out.print("Enter your password: ");
                password = sc.nextLine();
            }while(error == true);
            
            do{
                error = false;
                System.out.print("\nEnter your name: ");
                name = sc.nextLine();
            }while(error == true);
            
            do{
                error = false;
                System.out.print("\nEnter your IC:" );
                ic = sc.nextLine();
            }while(error == true);
            
            System.out.println("Position: ");
            System.out.println("1. Manager");
            System.out.println("2. Department Leader");
            System.out.println("3. Cashier");
            do{
                error = false;
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                
                if(choice < 1 || choice > 3){
                    error = true;
                }
                
            }while(error == true);
            
            if(choice == 1){
                position = "Manager";
            } else if(choice == 2){
                position = "Department Leader";
            } else{
                position = "Cashier";
            }
            
            do{
                error = false;
                System.out.print("Enter your phone number: ");
                phoneNumber = sc.nextLine();
            }while(error == true);
            
            do{
                error = false;
                System.out.print("Enter your email: ");
                email = sc.nextLine();
            }while(error == true);
            
            do{
                error = false;
                System.out.print("Enter your address: ");
                address = sc.nextLine();
            }while(error == true);
            
            do{
                error = false;
                System.out.print("Enter your salary: ");
                salary = sc.nextDouble();
                
                if(salary < 1000 || salary > 100000){
                    error = true;
                }
                
            }while(error == true);
            
            do{
                error = false;
                System.out.print("Enter hire date: ");
                //hireDate = sc.nextLine(); - convert to date
            }while(error == true);
        
            
        }while(cont == true);
    }
    
    public void removeStaff(){
        
        Scanner sc = new Scanner(System.in);
        
        String deleteID;
        String deletePW;
        char yesNo = 'N'; //TEMPORARILY
        boolean error = false;
        
        System.out.println("============================================");
        System.out.println("=               DELETE STAFF               =");
        System.out.println("============================================");
        
        System.out.println("\nPlease enter an ID to edit: ");
        System.out.println("Enter Password: ");
        
        //check both correct?
        
        //check file
        //show info list
        
        do{
            error = false;
            System.out.println("Confirm delete? (Y/N)");
            //yesNo = sc.next().toUpperCase();
            
            if(yesNo != 'Y' && yesNo != 'N'){
                error = true;
                System.out.println("Invalid option. Please enter again!");
            } else{
                error = false;
            }
        }while(error == true);
        
    }
    
    public void editInfo(){
        
    }
    
    public void staffSalesPerformance(){
        
    }
}