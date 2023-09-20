/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

import static Assignment.Assignment.RED;
import static Assignment.Assignment.RESET;
import static Assignment.Assignment.clearScreen;
import static Assignment.Assignment.input;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @Jesmine
 */
public class Employees {
        private String id;
        private String password;
        private String name;
        private String ic;
        private String position;
        private String phoneNumber;
        private String email;
        private String address;      
        private double basicSalary;
        
        public static String CURRENTNAME;
        
        //constructor
        public Employees(){
            this("","","","","","","", "", 0.0);
        }
        
        public Employees(String id, String password, String name, String ic, String position, String phoneNumber, String email, String address, double basicSalary){
            this.id = id;
            this.password = password;
            this.name = name;
            this.ic = ic;
            this.position = position;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.address = address;
            this.basicSalary = basicSalary;

        }
        
        //getter
        public String getId(){
            return id;
        }
        
        public String getPassword(){
            return password;
        }
        
        public String getName(){
            return name;
        }
        
        public String getIc(){
            return ic;
        }
        
        public String getPosition(){
            return position;
        }
        
        public String getPhoneNumber(){
            return phoneNumber;
        }
        
        public String getEmail(){
            return email;
        }
        
        public String getAddress(){
            return address;
        }
        
        public double getBasicSalary(){
            return basicSalary;
        }
        
        //setter
        public void setId(String id){
            this.id = id;
        }
        
        public void setPassword(String password){
            this.password = password;
        }
        
        public void setName(String name){
            this.name = name;
        }
        
        public void setIc(String ic){
            this.ic = ic;
        }
        
        public void setPosition(String position){
            this.position = position;
        }
        
        public void setPhoneNumber(String phoneNumber){
            this.phoneNumber = phoneNumber;
        }
        
        public void setEmail(String email){
            this.email = email;
        }
        
        public void setAddress(String address){
            this.address = address;
        }
        
        public void setBasicSalary(double basicSalary){
            this.basicSalary = basicSalary;
        }
      
        //toString
        @Override
        public String toString(){
            return "\nID: " + id + 
                   "\nPassword: " + password +
                   "\nName: " + name + 
                   "\nIC: " + ic + 
                   "\nPosition: " + position + 
                   "\nPhone Number: " + phoneNumber + 
                   "\nEmail: " + email + 
                   "\nAddress: " + address +
                   "\nBasic Salary: " + basicSalary;
        }
        
        //Validation
        public boolean checkPasswordFormat(String checkPassword){
        boolean error = false;
        int countDigit = 0;
        int countLetter = 0;
        
        for(int i = 0; i < checkPassword.length() ; i++){
            char same = checkPassword.charAt(i);
            if(Character.isLetter(same)){
                countLetter++;
            } else if(Character.isDigit(same)){
                countDigit++;
            }
        }
        
        if(countLetter == 0 || countDigit == 0){
            error = true;
        }
        
        if(checkPassword.length() < 8 || checkPassword.length() > 12){
            error = true;
        }
        
        return error;
    }
        
        public boolean checkIcFormat(String checkIC){
        boolean error = false;
        int countDigit = 0;
        int countLetter = 0;
        int countDash = 0;
        char dash = '-';
        
        for(int i = 0; i < checkIC.length(); i++){
            char check = checkIC.charAt(i);
            if(Character.isLetter(check)){
                countLetter++;
            } else if(Character.isDigit(check)){
                countDigit++;
            } else if(check == dash){
                countDash++;
            }
        }
        
        if(checkIC.length() == 14){

            if(checkIC.charAt(2) == '0'){
                if(checkIC.charAt(3) == '0'){
                    error = true;
                }
            } else if(checkIC. charAt(2) == '1'){
                if(checkIC.charAt(3) != '0' && checkIC.charAt(3) != '1' && checkIC.charAt(3) != '2'){
                    error = true;
                    }
            }
        
            if(checkIC.charAt(4) != '0' && checkIC.charAt(4) != '1' && checkIC.charAt(4) != '2' && checkIC. charAt(4) != '3'){
                error = true;
            } 
        
            if((checkIC.charAt(2) == '0' && checkIC.charAt(3) == '1') || (checkIC.charAt(3) == '3') || (checkIC.charAt(3) == '5') || (checkIC.charAt(3) == '7') || (checkIC.charAt(3) == '8') || (checkIC.charAt(3) == '0') || (checkIC.charAt(2) == '1' && checkIC.charAt(3) == '2')){
                if(checkIC.charAt(4) == '3'){
                    if(checkIC.charAt(5) != '1' && checkIC.charAt(5) != '0'){
                        error = true;
                    }
            }
            } else if((checkIC.charAt(2) == '1' && checkIC.charAt(2) == '3') || checkIC.charAt(3) == '4' || checkIC.charAt(3) == '6' && checkIC.charAt(3) == '9'){
                if(checkIC.charAt(4) == '3'){
                    if(checkIC.charAt(5) != '0'){
                        error = true;
                    }
                }
            }

            if(checkIC.charAt(7) != '0' && ((checkIC.charAt(7) == '1') && (checkIC.charAt(8) != '0')) && ((checkIC.charAt(7) == '1') && (checkIC.charAt(8) != '1')) && ((checkIC.charAt(7) == '1') && (checkIC.charAt(8) != '2')) && ((checkIC.charAt(7) == '1') && (checkIC.charAt(8) != '3')) && ((checkIC.charAt(7) == '1') && (checkIC.charAt(8) != '4'))){
                error = true;
            }

            if(countLetter > 0 || checkIC.charAt(6)!= dash || checkIC.charAt(9) != dash || countDash > 2 || countDash < 2){
                error = true;
            }
        
            } else {
                error = true;
            }
        
        return error;
    }
        
        public boolean checkNameFormat(String checkName){
        boolean error = false;
        int countDigit = 0;
        
        for(int i = 0; i < checkName.length(); i++){
            char check = checkName.charAt(i);
            if(Character.isDigit(check)){
                countDigit++;
            }
        }
        
        if(countDigit > 0){
            error = true;
        }
        
        return error;
    }
    
        public boolean checkPhoneNumberFormat(String checkPhoneNumber){
        boolean error = false;
        int countDigit = 0;
        int countLetter = 0;
        int countDash = 0;
        char dash = '-';
        
        for(int i = 0; i < checkPhoneNumber.length(); i++){
            char check = checkPhoneNumber.charAt(i);
            if(Character.isLetter(check)){
                countLetter++;
            } else if(Character.isDigit(check)){
                countDigit++;
            } else if(check == dash){
                countDash++;
            }
        }
        
        if(checkPhoneNumber.length() < 11 || checkPhoneNumber.length() > 12 || countLetter > 0 || countDash > 1 || countDash < 0 || checkPhoneNumber.charAt(3) != dash){
            error = true;
        }
        
        return error;
    }
    
        public boolean checkEmailFormat(String checkEmail){
        boolean error = false;
        int countAlian = 0;
        int countDot = 0;
        char alian = '@';
        char dot = '.';
        
        if(Character.isDigit(checkEmail.charAt(0))){
            error = true;
        }
        
        if(checkEmail.charAt(0) == alian || checkEmail.charAt(0) == dot){
            error = true;
        }
        
        for(int i = 0; i < checkEmail.length(); i++){
            char check = checkEmail.charAt(i);
            
            if(check == alian){
                countAlian++;
            }else if(check == dot){
                countDot++;
            }
        }     
        
        if(countAlian < 1 || countAlian > 1 || countDot < 1 || countDot > 1){
            error = true;
        }
        
        return error;
    }
    
        public boolean checkAddressFormat(String checkAddress){
        boolean error = false;
        int countDigit = 0;
        int countLetter = 0;
        
        for(int i = 0; i < checkAddress.length(); i++){
            char check = checkAddress.charAt(i);
            if(Character.isLetter(check)){
                countLetter++;
            } else if(Character.isDigit(check)){
                countDigit++;
            }
        }
        
        if(countDigit == 0 || countLetter == 0){
            error = true;
        }
        
        return error;
        
    }
        
        public static void login(){

        ArrayList<Admin> adminArray = new ArrayList();
        ArrayList<Cashier> cashierArray = new ArrayList();
        Employees emp;
        
        boolean exist = false;
        boolean error = false;
        int currentIndex = 0;
        
        String id;
        String password;
        
        try {
            Admin.readAdminFromFile(adminArray);
        } catch (FileNotFoundException ex) {
            System.out.println(Assignment.RED + "Cannot read the file!" + Assignment.RESET);
            return;
        }
        
        try {
            new Cashier().readCashierFromFile(cashierArray);
        } catch (FileNotFoundException ex) {
            System.out.println(Assignment.RED + "Cannot read the file!" + Assignment.RESET);
            return;
        }

        do {
            Assignment.clearScreen();
            Assignment.logo();
            exist = true;
            
            System.out.println("===========================================");
            System.out.println("=                 LOGIN                   =");
            System.out.println("===========================================");
            
            System.out.println(Assignment.GREEN+"**Enter x to log out**"+Assignment.RESET);
            

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
                exist = Admin.checkAdminIDPW(adminArray, id, password);
                if(exist){
                    Admin.printAdminLoginName(adminArray, id, password);
                    CURRENTNAME = Admin.storeAdminLoginName(adminArray, id, password);
                    Assignment.systemPause();
                    Admin.adminMenu();
                    exist = false;
                }
                else{
                    System.out.println(Assignment.RED + "Invalid ID or Password. Please enter again!" + Assignment.RESET);
                }
            } else if(id.startsWith("CH")){
                exist = Cashier.checkCashierIDPW(cashierArray, id, password);
                if(exist){
                    Cashier.printCashierLoginName(cashierArray, id, password);
                    CURRENTNAME = Cashier.storeCashierLoginName(cashierArray, id, password);
                    Assignment.systemPause();
                    Cashier.cashierMenu();
                    exist = false;
                }
                else{
                    System.out.println(Assignment.RED + "Invalid ID or Password. Please enter again!" + Assignment.RESET);
                }
            } else{
                exist = false;
                System.out.println(Assignment.RED + "Invalid ID or Password. Please enter again!" + Assignment.RESET);
            }
           
        } while (!exist);

       
    }
        
        public static void staffMenu(){
  
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
            System.out.println("=      0. Exit                      =");
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
                
                case 0 -> {}
                
                default -> System.out.println(RED + "Invalid Input. Please enter again!" + RESET);
                             
            }
            
        }while(choice != 0 || error);
        

    }
    
    public static void addStaff(){  

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
            System.out.println("=              0. Exit                     =");
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
                
                case 0 -> {}
                
                default -> System.out.println(RED + "Invalid Input. Please enter again!" + RESET);
                                     
            }
            
        }while(choice != 0 || error);
        
    }
    
    public static void removeStaff(){
        
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
            System.out.println("=             0. Exit                      =");
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
                
                case 0 -> {}
                
                default -> System.out.println(RED + "Invalid Input. Please enter again!" + RESET);
                               
                            
            }
            
        }while(choice != 0 || error);

    }
    
    public static void editStaff(){
        
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
            System.out.println("=             0. Exit                      =");
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
            
        }while(choice != 0 || error);
        
       

    }
    
    public static void searchStaff(){

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
            System.out.println("=             0. Exit                      =");
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
        } while(choice !=0 || error);
    }
    
    public static void viewStaff(){
        
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
            System.out.println("=             0. Exit                      =");
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
                
                case 0 -> {}
                
                default -> System.out.println(RED + "Invalid Input. Please enter again!" + RESET);
                                
            }
        } while(choice !=0 || error);
        
        
    }
    
    public static void staffSalesPerformance(){
        
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
            System.out.println("=             0. Exit                      =");
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
                
                case 0 -> {}
                
                default -> System.out.println(RED + "Invalid Input. Please enter again!" + RESET);
                               
                            
            }
            
        }while(choice != 0 || error);
        
    }   
        
        
    
}

