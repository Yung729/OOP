/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static Assignment.Assignment.clearScreen;
import static Assignment.Assignment.systemPause;
import static Assignment.Assignment.RED;
import static Assignment.Assignment.RESET;
import static Assignment.Assignment.input;
import static Assignment.Assignment.CURRENTNAME;

/**
 *
 * @author User
 */
public class Cashier extends Employees{
    private double sales;
    private double commissionRate;
    private double totalSalary;
    private static int noOfCashier = 0;
    
    
    //constructor
    public Cashier(){
        super("", "", "", "", "", "", "", "", 0.0);
        this.sales = sales;
        this.commissionRate = commissionRate;
        this.totalSalary = totalSalary;
    }
    
    public Cashier(String id, String password, String name, String ic, String position, String phoneNumber, String email, String address, double basicSalary, double sales, double commissionRate, double totalSalary){
        super(id, password, name, ic, position, phoneNumber, email, address, basicSalary);
        this.sales = sales;
        this.commissionRate = commissionRate;
        this.totalSalary = totalSalary;
        noOfCashier++;
    }
    
    //getter
    public double getSales(){
        return sales;
    }
    
    public double getCommissionRate(){
        return commissionRate;
    }
    
    public double getTotalSalary(){
        return totalSalary;
    }
    
    public static int getNoOfCashier(){
        return noOfCashier;
    }
    
    //setter
    public void setSales(double sales){
        this.sales = sales;
    }
    
    public void setCommissionRate(double commissionRate){
        this.commissionRate = commissionRate;
    }
    
    public void setTotalSalary(double totalSalary){
        this.totalSalary = totalSalary;
    }
    
    //other methods
    @Override
    public String toString(){
        return super.toString() +
                "\nSales: " + sales + 
                "\nCommission Rate: " + commissionRate;
    }
    
    public double calculateTotalSalary(){
        return super.getBasicSalary() + (sales * commissionRate);
    }
    
    //methods update
    public void addCh(ArrayList<Cashier> cashierArray, Cashier ch) {
        cashierArray.add(ch);
    }
    
    public void deleteCh(ArrayList<Cashier> cashierArray, Cashier ch){
        cashierArray.remove(ch);
    }
    
    public void updateSales(ArrayList<Cashier> cashierArray, String id, double s){
        double newSales = 0;
        
        for(Cashier cashier : cashierArray){
            if(cashier.getId().equals(id)){
                newSales = sales + s;
                sales = newSales;
            }
        }
    }
    
    public void updateTotalSalary(ArrayList<Cashier> cashierArray, String id, double s, double salary){
        double newSalary = 0;
        
        for(Cashier cashier : cashierArray){
            if(cashier.getId().equals(id)){
                newSalary = calculateTotalSalary();
            }
        }
        
    }
    
    //File
    public void writeCashier(ArrayList<Cashier> cashierArray) throws IOException{
        try(FileWriter writeCashierFile = new FileWriter("Cashier.txt")){
            for(Cashier cashier : cashierArray){
                writeCashierFile.write(cashier.getId() + "|" +
                                     cashier.getPassword() + "|" +
                                     cashier.getName() + "|" + 
                                     cashier.getIc() + "|" +
                                     cashier.getPosition() + "|" +
                                     cashier.getPhoneNumber() + "|" +
                                     cashier.getEmail() + "|" +
                                     cashier.getAddress() + "|" + 
                                     cashier.getBasicSalary() + "|" +
                                     cashier.getCommissionRate() + "|" +
                                     cashier.getSales() + "|" +
                                     cashier.getTotalSalary() + "|"
                                    );
            }
        }
        
    }
    
    public void readCashierFromFile(ArrayList<Cashier> cashierArray) throws FileNotFoundException{
        File readCashierFile = new File("Cashier.txt");
        
        if(readCashierFile.exists()){
            Scanner cashierRead = new Scanner(readCashierFile);
            while(cashierRead.hasNextLine()){
                String line = cashierRead.nextLine();
                String[] info = line.split("\\|");
                cashierArray.add(new Cashier(info[0], info[1], info[2], info[3], info[4], info[5], info[6], info[7], Double.parseDouble(info[8]), Double.parseDouble(info[9]), Double.parseDouble(info[10]), Double.parseDouble(info[11])));
            }
        } else {
            File createCashierFile = new File("Cashier.txt");
            try{
                createCashierFile.createNewFile();
            } catch(IOException ex){
                Logger.getLogger(Cashier.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("File created : " + createCashierFile.getName() + "\n");
        }
    }
    
    //Main method
    
    //done checking
    public void addCashier(){
        
        Admin admin = new Admin();
        ArrayList<Admin> adminArray = new ArrayList();
        Cashier cashier = new Cashier();
        ArrayList<Cashier> cashierArray = new ArrayList();
        
        String validatedPassword;
        String validatedName;
        String validatedIC;
        String validatedPhoneNumber;
        String validatedEmail;
        String validatedAddress;
        double bSalary;
        double commRate;
        int choice;
        char confirm;
        boolean error = false;
        boolean duplicate = false;
        boolean check = true;
        
        try{
            new Admin().readAdminFromFile(adminArray);
        } catch (FileNotFoundException ex) {
            System.out.println(RED + "Cannot read the file!" + RESET);
        }
        
        try{
            readCashierFromFile(cashierArray);
        } catch (FileNotFoundException ex) {
            System.out.println(RED + "Cannot read the file!" + RESET);
        }
        
        input.nextLine();
        
        do{
        clearScreen();
        
        System.out.println("Current Login > " + CURRENTNAME);
        
        System.out.println("=======================================");
        System.out.println("=              Add Cashier            =");
        System.out.println("=======================================");
        
        super.setId(generateCashierID(cashierArray));
        System.out.println("ID: " + super.getId());
        
        do{
            error = false;
            System.out.println("**Press x to exit**");
            System.out.print("Enter Password (8 to 12 characters and numbers) > ");
            validatedPassword = input.nextLine();
            validatedPassword = validatedPassword.replace(" ","");
            
            if(validatedPassword.equalsIgnoreCase("x")){
                System.out.println("Thanks!");

                return;
            }
            
            error = super.checkPasswordFormat(validatedPassword);
            
            if(error){
                System.out.println(RED + "Password must be 8 to 12 combination of characters and numbers!" + RESET);
                System.out.println(RED + "Example: Abc12345" + RESET);
            }
            
            if(!error){
                super.setPassword(validatedPassword);
            }
            
            
        }while(error);
        
        do{
            error = false;
            
            System.out.print("Enter Name > ");
            validatedName = input.nextLine();
            
            if(validatedName.equalsIgnoreCase("x")){
                System.out.println("Thanks!");

                return;
            }
            
            error = super.checkNameFormat(validatedName);
            
            if(error){
                System.out.println(RED + "Invalid Name Format!" + RESET);
            }
            
            if(!error){
                super.setName(validatedName);
            }
            
            if(validatedPassword.equalsIgnoreCase("x")){
                System.out.println("Thanks!");

                return;
            }
            
        }while(error);
        
        do{
            error = false;
            duplicate = false;
            
            System.out.print("Enter IC (040420-01-0110) > ");
            validatedIC = input.nextLine();
            validatedIC = validatedIC.replace(" ","");
            
            if(validatedIC.equalsIgnoreCase("x")){
                System.out.println("Thanks!");

                return;
            }
            
            error = super.checkIcFormat(validatedIC);
            duplicate = checkCashierDuplicateIc(cashierArray, adminArray, validatedIC);
            
            if(error){
                System.out.println(RED + "Invalid IC Format!" + RESET);
                System.out.println(RED + "Example: 040420-01-0110" + RESET);
            }
            
            if(duplicate){
                System.out.println(RED + "RECORD ALREADY EXIST!" + RESET);
            }
            
            if(!error && !duplicate){
                super.setIc(validatedIC);
            }
            
        }while(error || duplicate);
        
        System.out.println("============================================");
        System.out.println("=                 Position                 =");
        System.out.println("============================================");
        System.out.println("=         1. Full Time Cashier             =");
        System.out.println("=         2. Part Time Cashier             =");
        System.out.println("============================================");
        do{
            error = false;
            System.out.print("Enter your choice > ");
            choice = input.nextInt();
            
            switch(choice){
                case 1 -> super.setPosition("Full Time Cashier");
                
                case 2 -> super.setPosition("Part Time Cashier");
                
                default -> { error = true;
                            System.out.println(RED + "Invalid input. Please enter again!" + RESET);
                }
            }
            
        }while(error);
        input.nextLine();
        
        do{
            error = false;
            
            System.out.print("Enter Phone Number > ");
            validatedPhoneNumber = input.nextLine();
            validatedPhoneNumber = validatedPhoneNumber.trim();
            
            if(validatedPhoneNumber.equalsIgnoreCase("x")){
                System.out.println("Thanks!");

                return;
            }
            
            error = super.checkPhoneNumberFormat(validatedPhoneNumber);
            duplicate = checkCashierPhoneNumberDuplicate(cashierArray, adminArray, validatedPhoneNumber);
            
            if(error){
                System.out.println(RED + "Invalid format. Please enter again!" + RESET);
                System.out.println(RED + "Example: 012-3456789" + RESET);
            }
            
            if(duplicate){
                System.out.println(RED + "RECORD ALREADY EXIST!" + RESET);
            }
            
            if(!error){
                super.setPhoneNumber(validatedPhoneNumber);
            }
            
        }while(error || duplicate);
        
        do{
            error = false;
            duplicate = false;
            
            System.out.print("Enter Email > ");
            validatedEmail = input.nextLine();
            validatedEmail = validatedEmail.trim();
            
            if(validatedEmail.equalsIgnoreCase("x")){
                System.out.println("Thanks!");
                return;
            }
            
            error = super.checkEmailFormat(validatedEmail);
            duplicate = checkCashierEmailDuplicate(cashierArray, adminArray, validatedEmail);
            
            if(error){
                System.out.println(RED + "Invalid format. Please enter again!" + RESET);
                System.out.println(RED + "Example: abc123@gmail.com" + RESET);
            }
            
            if(!error){
                super.setEmail(validatedEmail);
            }
            
            if(duplicate){
               System.out.println(RED + "RECORD ALREADY EXIST!" + RESET);
            }
            
        }while(error || duplicate);
        
        do{
            error = false;
            
            System.out.print("Enter Address > ");
            validatedAddress = input.nextLine();
            validatedAddress = validatedAddress.trim();
            
            if(validatedAddress.equalsIgnoreCase("x")){
                System.out.println("Thanks!");
                return;
            }
            
            error = super.checkAddressFormat(validatedAddress);
            
            if(error){
                System.out.println(RED + "Invalid format. Please enter again!" + RESET);
                System.out.println(RED + "Example: 12, Jalan PV15, Setapak" + RESET);
            }
            
            if(!error){
                super.setAddress(validatedAddress);
            }
            
        }while(error);
        
        do{
            error = false;
            
            System.out.print("Enter Basic Salary > ");
            bSalary = input.nextDouble();
           
            
            if(bSalary <= 1000 || bSalary >= 3000){
                error = true;
                System.out.println(RED + "Invalid input. Please enter again!" + RESET);
            }
            
            if(!error){
                super.setBasicSalary(bSalary);
            }
            
        }while(error);
        
        do{
            error = false;
            
            System.out.print("Enter Commission Rate > ");
            commRate = input.nextDouble();
            
            if(commRate > 1.00 || commRate <= 0){
                error = true;
                System.out.println(RED + "Invalid input. Please enter again!" + RESET);
            }
            
            if(!error){
                setCommissionRate(commRate);
            }
        }while(error);
        
        setSales(0);
        setTotalSalary(super.getBasicSalary());
        
        displayCashierInformation();
        
        do{
            error = false;
            
            System.out.print("Confirm Add (Y/N) > ");
            confirm = input.next().charAt(0);
            confirm = Character.toUpperCase(confirm);
            
            check = checkYesNo(confirm);
            
            if(!check){
                error = true;
                System.out.println(RED + "Invalid input. Please enter again!" + RESET);
            }
            
            if(confirm == 'Y'){
                addCh(cashierArray, new Cashier(super.getId(), super.getPassword(), super.getName(), super.getIc(), super.getPosition(), super.getPhoneNumber(), super.getEmail(), super.getAddress(), getBasicSalary(), getCommissionRate(), getSales(), getTotalSalary()));
            
                try {
                    writeCashier(cashierArray);
                } catch (IOException ex) {
                    Logger.getLogger(Assignment.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }
        
                 
        }while(error);
       
        input.nextLine();
            do{
                error = false;
                System.out.print("Add more Cashier? (Y/N) > ");
                confirm = input.next().charAt(0);
                confirm = Character.toUpperCase(confirm);
                input.nextLine();
            
                check = checkYesNo(confirm);
            
                if(!check){
                    error = true;
                    System.out.println(RED + "Invalid input. Please enter again!" + RESET);
                }
            
            }while(error);
        
        }while(confirm == 'Y');
        
        systemPause();
        
    } 
    
    public void deleteCashier(){
        clearScreen();
        
        System.out.println("Current Login > " + CURRENTNAME);
        
        Cashier cashier = new Cashier();
        ArrayList<Cashier> cashierArray = new ArrayList();
        
        String deleteId;
        String deletePassword;
        char confirm;
        char confirmDelete;
        int currentIndex = 0;
        boolean exist = true;
        boolean error = false;
        boolean check = false;
        
        try{
            readCashierFromFile(cashierArray);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Assignment.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("=======================================");
        System.out.println("=            DELETE Cashier           =");
        System.out.println("=======================================");
        
        input.nextLine();
        
        if(cashierArray.isEmpty()){
            System.out.println("========================================");
            System.out.println("=            No Cashier Record!        =");
            System.out.println("========================================");
        }
        else{
            do{
                confirm = 'N';
                clearScreen();
                
                do{
                    exist = true;
                    System.out.print("Enter Cashier ID to delete (Press X to exit) > ");
                    deleteId = input.nextLine();
                    
                    deleteId = deleteId.trim(); // Trim whitespace

                    if ((deleteId.equalsIgnoreCase("X"))) {
                        return; // Exit the loop if 'X' or 'x' is entered
                    }
                    
                    exist = checkCashier(cashierArray, deleteId);
                    
                    if (!exist) {
                        System.out.println("======================================");
                        System.out.println("=        Cashier DOES NOT FOUND      =");
                        System.out.println("======================================");
                    } 
                    
                }while(!exist);
                
                do{
                    exist = true;
                    System.out.print("Enter Cashier Password (Press X to exit) > ");
                    deletePassword = input.nextLine();
                    
                    exist = checkCashierPassword(cashierArray, deletePassword);
                    
                    if(!exist){
                        System.out.println(RED + "Invalid password!" + RESET);
                    }
                    
                    if((deletePassword.equalsIgnoreCase("X"))){
                        return;
                    }
                    
                }while(!exist);
                
                displayCashierInformation(cashierArray,deleteId, deletePassword);
                currentIndex = checkCashierIndexNumber(cashierArray, deleteId, deletePassword);
                
                do{
                    System.out.print("Confirm Delete? (Y/N) > ");
                    confirmDelete = input.next().charAt(0);
                    confirmDelete = Character.toUpperCase(confirmDelete);
                    
                    check = checkYesNo(confirmDelete);
                    
                    if(!check){
                        error = true;
                        System.out.println(RED + "Invalid input. Please enter again!" + RESET);
                    }
                }while(error);
                
                if(confirmDelete == 'Y'){
                    deleteCh(cashierArray, cashierArray.get(currentIndex));
                    try{
                        writeCashier(cashierArray);
                    }catch(IOException ex){
                        Logger.getLogger(Cashier.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("Successful Delete");
                }
                
                do{
                    
                    error = false;
                    
                    System.out.print("Delete more Cashier? (Y/N) > ");
                    confirm = input.next().charAt(0);
                    confirm = Character.toUpperCase(confirm);
                    
                    check = checkYesNo(confirm);
                    
                    if(!check){
                        error = true;
                        System.out.println(RED + "Invalid input. Please enter again!" + RESET);
                    }
                    
                }while(error);
                
                input.nextLine();
                
            }while(confirm == 'Y');
        }
        
        systemPause();
    }  
     
    public void editCashier(){
        clearScreen();
        
        System.out.println("Current Login > " + CURRENTNAME);
    
        Admin admin = new Admin();
        ArrayList<Admin> adminArray = new ArrayList();
        Cashier Cashier = new Cashier();
        ArrayList<Cashier> cashierArray = new ArrayList();
        
        String editID;
        String editPassword;
        String oldPassword;
        String confirmPassword;
        String newPosition;
        String latest;
        double latestNum;
        double latestNum2;
        int choice;
        char confirm;
        int currentIndex = 0;
        boolean error = false;
        boolean exist = true;
        boolean check = true;
        boolean duplicate = false;
        
        try{
            new Admin().readAdminFromFile(adminArray);
        } catch (FileNotFoundException ex) {
            System.out.println(RED + "Cannot read the file!" + RESET);
        }
        
        try{
            readCashierFromFile(cashierArray);
        } catch (FileNotFoundException ex) {
            System.out.println(RED + "Cannot read the file!" + RESET);
        }
        
        if(cashierArray.isEmpty()){
            System.out.println("========================================");
            System.out.println("=             No Cashier Record!       =");
            System.out.println("========================================");
        }
        else{
            do{
                
                clearScreen();
                
                System.out.println("========================================");
                System.out.println("=              EDIT Cashier            =");
                System.out.println("========================================");
                
                do{
                    exist = true;
                    
                    System.out.print("Enter Cashier ID to edit (Press X to exit) > ");
                    editID = input.nextLine();
                    
                    editID = editID.trim(); // Trim whitespace

                    if ((editID.equalsIgnoreCase("X"))) {
                        return; // Exit the loop if 'X' or 'x' is entered
                    }
                    
                    exist = checkCashier(cashierArray, editID);

                    if (!exist) {
                        
                        System.out.println("========================================");
                        System.out.println("=            RECORD NOT FOUND!         =");
                        System.out.println("========================================");
                    }
                    
                }while(!exist);
                
                do{
                    exist = true;
                    System.out.print("Enter Cashier Password (Press X to exit) > ");
                    editPassword = input.nextLine();
                    exist = checkCashierPassword(cashierArray, editPassword);
                    
                    if((editPassword.equalsIgnoreCase("X"))){
                        return;
                    }
                    
                    if(!exist){
                        System.out.println(RED + "Invalid password!" + RESET);
                    } else{
                        break;
                    }
                    
                }while(!exist);
                
                currentIndex = checkCashierIndexNumber(cashierArray, editID, editPassword);
                
                
                do{
                    error = false;
                    clearScreen();
                    
                    editCashierInformation(cashierArray, editID, editPassword);
                    
                    System.out.print("\nEnter your choice > ");
                    choice = input.nextInt();
                    
                    if(choice < 1 || choice > 8){
                        error = true;
                        System.out.println(RED + "Invalid input. Please enter again!" + RESET);
                    } else if(choice == 8){
                        return;
                    }
                   
                    
                }while(error);
                    
                    switch(choice){
                        case 1:
                            clearScreen();
                            input.nextLine();
                            System.out.println("======================================");
                            System.out.println("=             EDIT PASSWORD          =");
                            System.out.println("======================================");
                            
                            input.nextLine();
                            do{
                                error = false;
                                System.out.print("Enter Old Password > ");
                                oldPassword = input.nextLine();

                                exist = checkCashierPassword(cashierArray, oldPassword);
                                
                                if (!exist) { // Use equality operator for comparison
                                    System.out.println(RED + "Invalid Password!" + RESET);
                                    error = true;
                                }
                                
                            }while(error);
                            
                            do{
                                error = false;
                                System.out.print("Enter New Password > ");
                                latest = input.nextLine();
                                
                                error = super.checkPasswordFormat(latest);
                                
                                if(error){
                                    System.out.println(RED + "Password must be 8 to 12 combination of characters and numbers!" + RESET);
                                    System.out.println(RED + "Example: Abc12345" + RESET);
                                }
                                
                            }while(error);
                            
                            do{
                                error = false;
                                System.out.print("Confirm New Password > ");
                                confirmPassword = input.nextLine();
                                
                                if(confirmPassword.equals(latest)){
                                    error = false;
                                } else{
                                    error = true;
                                    System.out.println(RED + "Passwords entered are different!" + RESET);
                                }
                            }while(error);
                            
                            
                            System.out.println("-------------------------------------------");
                            System.out.println("                RESET PASSWORD             ");
                            System.out.println("-------------------------------------------");
                            System.out.println("Original | " + cashierArray.get(currentIndex).getPassword());
                            System.out.println("New      | " + latest);
                            System.out.println("-------------------------------------------");
                            
                            do{
                                error = false;
                                check = true;
                                System.out.print("Confirm to edit? (Y/N) > ");
                                confirm = input.next().charAt(0);
                                confirm = Character.toUpperCase(confirm);
                                check = checkYesNo(confirm);
                                
                                if(!check){
                                    error = true;
                                    System.out.println(RED + "Invalid input. Please enter again!" + RESET);
                                }
                            }while(error);
                            
                            if(confirm == 'Y'){
                                cashierArray.get(currentIndex).setPassword(latest);
                                try{
                                    writeCashier(cashierArray);
                                }catch(IOException ex){
                                    Logger.getLogger(Cashier.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                System.out.println("Successful edit");
                                
                            }
                            else{
                                break;
                            }    
                            break;
                            
                        case 2:
                            input.nextLine();
                            System.out.println("==================================");
                            System.out.println("=            Position            =");
                            System.out.println("==================================");
                            System.out.println("=      1. Full Time Cashier      =");
                            System.out.println("=      2. Part Time Cashier      =");
                            System.out.println("==================================");
                            
                            do{
                                error = false;
                                System.out.print("Enter your choice > ");
                                choice = input.nextInt();
                                    if(choice != 1 && choice != 2){
                                    error = true;
                                    System.out.println(RED + "Invalid input. Please enter again!");
                                }
            
                            }while(error);
        
                            if(choice == 1){
                                newPosition = "Full Time Cashier";
                            } else {
                                newPosition = "Part Time Cashier";
                            }
                            
                            System.out.println("-------------------------------------------");
                            System.out.println("                RESET POSITION             ");
                            System.out.println("-------------------------------------------");
                            System.out.println("Original | " + cashierArray.get(currentIndex).getPosition());
                            System.out.println("New      | " + newPosition);
                            System.out.println("-------------------------------------------");
                            
                            do{
                                error = false;
                                System.out.print("Confirm to edit? (Y/N) > ");
                                confirm = input.next().charAt(0);
                                confirm = Character.toUpperCase(confirm);
                                
                                check = checkYesNo(confirm);
                                
                                if(!check){
                                    error = true;
                                    System.out.println(RED + "Invalid input. Please enter again!" + RESET);
                                }
                            }while(error);
                            
                            if(confirm == 'Y'){
                                cashierArray.get(currentIndex).setPosition(newPosition);
                                try{
                                    writeCashier(cashierArray);
                                }catch(IOException ex){
                                    Logger.getLogger(Cashier.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                System.out.println("Successful edit");
                                
                            }
                            else{
                                break;
                            }    
                            break;
                         
                        case 3: 
                            input.nextLine();
                            System.out.println("======================================");
                            System.out.println("=          EDIT PHONE NUMBER         =");
                            System.out.println("======================================");
                            
                            do{
                                error = false;
                                duplicate = false;
                                
                                System.out.print("Enter New Phone Number > ");
                                latest = input.nextLine();
                                
                                error = super.checkPhoneNumberFormat(latest);
                                duplicate = checkCashierPhoneNumberDuplicate(cashierArray, adminArray, latest);
                                
                                if(error){
                                    System.out.println(RED + "Invalid format. Please enter again!" + RESET);
                                    System.out.println(RED + "Example: 012-3456789" + RESET);
                                }
                                
                                if(duplicate){
                                    System.out.println(RED + "RECORD ALREADY EXIST!" + RESET);
                                }
                                
                            }while(error || duplicate);
                            
                            System.out.println("-------------------------------------------");
                            System.out.println("            RESET PHONE NUMBER             ");
                            System.out.println("-------------------------------------------");
                            System.out.println("Original | " + cashierArray.get(currentIndex).getPhoneNumber());
                            System.out.println("New      | " + latest);
                            System.out.println("-------------------------------------------");
                            
                            do{
                                error = false;
                                System.out.print("Confirm to edit? (Y/N) > ");
                                confirm = input.next().charAt(0);
                                confirm = Character.toUpperCase(confirm);
                                
                                check = checkYesNo(confirm);
                                
                                if(!check){
                                    error = true;
                                    System.out.println(RED + "Invalid input. Please enter again!" + RESET);
                                }
                            }while(error);
                            
                            if(confirm == 'Y'){
                                cashierArray.get(currentIndex).setPhoneNumber(latest);
                                try{
                                    writeCashier(cashierArray);
                                }catch(IOException ex){
                                    Logger.getLogger(Cashier.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                System.out.println("Successful edit");
                                
                            }
                            else{
                                break;
                            }    
                            break;
                            
                        case 4:
                            input.nextLine();
                            System.out.println("======================================");
                            System.out.println("=             EDIT EMAIL             =");
                            System.out.println("======================================");
                            
                            do{
                                error = false;
                                duplicate = false;
                                
                                System.out.print("Enter New Email > ");
                                latest = input.nextLine();
                                
                                error = super.checkEmailFormat(latest);
                                duplicate = checkCashierEmailDuplicate(cashierArray, adminArray, latest);
                                
                                if(error){
                                    System.out.println(RED + "Invalid Format. Please enter again!" + RESET);
                                    System.out.println(RED + "Example: abc123@gmail.com" + RESET);
                                }
                                
                                if(duplicate){
                                    System.out.println(RED + "RECORD ALREADY EXIST!" + RESET);
                                }
                                
                            }while(error || duplicate);
                            
                            System.out.println("-------------------------------------------");
                            System.out.println("                RESET EMAIL                ");
                            System.out.println("-------------------------------------------");
                            System.out.println("Original | " + cashierArray.get(currentIndex).getEmail());
                            System.out.println("New      | " + latest);
                            System.out.println("-------------------------------------------");
                            
                            do{
                                error = false;
                                System.out.print("Confirm to edit? (Y/N) > ");
                                confirm = input.next().charAt(0);
                                confirm = Character.toUpperCase(confirm);
                                
                                check = checkYesNo(confirm);
                                
                                if(!check){
                                    error = true;
                                    System.out.println(RED + "Invalid input. Please enter again!" + RESET);
                                }
                                
                            }while(error);
                            
                            if(confirm == 'Y'){
                                cashierArray.get(currentIndex).setEmail(latest);
                                try{
                                    writeCashier(cashierArray);
                                }catch(IOException ex){
                                    Logger.getLogger(Cashier.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                System.out.println("Successful edit");
                               
                            }
                            else{
                                break;
                            }    
                            break;
                        
                        case 5:
                            input.nextLine();
                            System.out.println("======================================");
                            System.out.println("=           EDIT ADDRESS             =");
                            System.out.println("======================================");
                            
                            do{
                                error = false;
                                System.out.print("Enter New Address > ");
                                latest = input.nextLine();
                                
                                error = super.checkAddressFormat(latest);
                                
                                if(error){
                                    System.out.println(RED + "Invalid Format. Please enter again!" + RESET);
                                    System.out.println(RED + "Example: 12, Jalan PV15, Setapak" + RESET);
                                }
                                
                            }while(error);
                            
                            System.out.println("-------------------------------------------");
                            System.out.println("                RESET ADDRESS             ");
                            System.out.println("-------------------------------------------");
                            System.out.println("Original | " + cashierArray.get(currentIndex).getAddress());
                            System.out.println("New      | " + latest);
                            System.out.println("-------------------------------------------");
                            
                            do{
                                error = false;
                                System.out.print("Confirm to edit? (Y/N) > ");
                                confirm = input.next().charAt(0);
                                confirm = Character.toUpperCase(confirm);
                                
                                check = checkYesNo(confirm);
                                
                                if(!check){
                                    error = true;
                                    System.out.println(RED + "Invalid input. Please enter again!" + RESET);
                                }
                            }while(error);
                            
                            if(confirm == 'Y'){
                                cashierArray.get(currentIndex).setAddress(latest);
                                try{
                                    writeCashier(cashierArray);
                                }catch(IOException ex){
                                    Logger.getLogger(Cashier.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                System.out.println("Successful edit");
                                
                            }
                            else{
                                break;
                            }    
                            break;
                            
                        case 6:
                            input.nextLine();
                            System.out.println("======================================");
                            System.out.println("=          EDIT BASIC SALARY         =");
                            System.out.println("======================================");
                            
                            do{
                                error = false;
                                System.out.print("Enter New Basic Salary > ");
                                latestNum = input.nextDouble();
                                
                                if(latestNum < 1000 || latestNum > 15000){
                                    error = true;
                                }
                                
                                if(error){
                                    System.out.println(RED + "Invalid Input. Please enter again!" + RESET);
                                }
                                
                            }while(error);
                            
                            System.out.println("-------------------------------------------");
                            System.out.println("             RESET BASIC SALARY            ");
                            System.out.println("-------------------------------------------");
                            System.out.println("Original | " + cashierArray.get(currentIndex).getBasicSalary());
                            System.out.println("New      | " + latestNum);
                            System.out.println("-------------------------------------------");
                            
                            do{
                                error = false;
                                System.out.print("Confirm to edit? (Y/N) > ");
                                confirm = input.next().charAt(0);
                                confirm = Character.toUpperCase(confirm);
                                
                                check = checkYesNo(confirm);
                                
                                if(!check){
                                    error = true;
                                    System.out.println(RED + "Invalid input. Please enter again!" + RESET);
                                }
                                
                            }while(error);
                            
                            if(confirm == 'Y'){
                                cashierArray.get(currentIndex).setBasicSalary(latestNum);
                                try{
                                    writeCashier(cashierArray);
                                }catch(IOException ex){
                                    Logger.getLogger(Cashier.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                System.out.println("Successful edit");
                               
                            }
                            else{
                                break;
                            }    
                            break;
                        case 7:
                            input.nextLine();
                            System.out.println("======================================");
                            System.out.println("=        EDIT COMMISSION RATE        =");
                            System.out.println("======================================");
                            
                            do{
                                error = false;
                                System.out.print("Enter New Basic Salary > ");
                                latestNum = input.nextDouble();
                                
                                if(latestNum < 1000 || latestNum > 15000){
                                    error = true;
                                }
                                
                                if(error){
                                    System.out.println(RED + "Invalid Input. Please enter again!" + RESET);
                                }
                                
                            }while(error);
                            
                            do{
                                error = false;
                                System.out.print("Enter New Commission Rate: ");
                                latestNum2 = input.nextDouble();
                                if(latestNum2> 1.00 || latestNum2 < 0.00){
                                    error = true;
                                }
                                
                                if(error){
                                    System.out.println(RED + "Invalid Input. Please enter again!" + RESET);
                                    
                                }
                                
                            }while(error);
                            
                            System.out.println("-------------------------------------------");
                            System.out.println("            RESET BASIC SALARY             ");
                            System.out.println("-------------------------------------------");
                            System.out.println("Original | " + cashierArray.get(currentIndex).getBasicSalary());
                            System.out.println("New      | " + latestNum);
                            System.out.println("-------------------------------------------");
                            System.out.println("            RESET COMMISSION RATE");
                            System.out.println("-------------------------------------------");
                            System.out.println("Original | " + cashierArray.get(currentIndex).getCommissionRate());
                            System.out.println("New      | " + latestNum2);
                            System.out.println("-------------------------------------------");
                            
                            do{
                                error = false;
                                System.out.print("Confirm to edit? (Y/N) > ");
                                confirm = input.next().charAt(0);
                                confirm = Character.toUpperCase(confirm);
                                
                                check = checkYesNo(confirm);
                                
                                if(!check){
                                    error = true;
                                    System.out.println(RED + "Invalid input. Please enter again!" + RESET);
                                }
                                
                            }while(error);
                            
                            if(confirm == 'Y'){
                                cashierArray.get(currentIndex).setBasicSalary(latestNum);
                                cashierArray.get(currentIndex).setCommissionRate(latestNum2);
                                try{
                                    writeCashier(cashierArray);
                                }catch(IOException ex){
                                    Logger.getLogger(Cashier.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                System.out.println("Successful edit");
                                
                            }
                            else{
                                break;
                            }    
                            break;
                        case 8:
                            return;
                            
                    }
                    
                    do{
                        error = false;
                        System.out.print("Edit more? (Y/N) >  ");
                        confirm = input.next().charAt(0);
                        confirm = Character.toUpperCase(confirm);
                        
                        check = checkYesNo(confirm);
                                
                        if(!check){
                           error = true;
                           System.out.println(RED + "Invalid input. Please enter again!" + RESET);
                        }
                    }while(error);
                    
                    input.nextLine();
                
            }while(confirm == 'Y');
            
           systemPause();
        
    }
       
}  
    
    public void searchCashier(){
        Cashier Cashier = new Cashier();
        ArrayList<Cashier> CashierArray = new ArrayList();
        
        Scanner sc = new Scanner(System.in);
        
        String searchId;
        String searchName;
        String searchPosition;
        int choice;
        boolean error = false;
        boolean exist = false;
        
        try{
            readCashierFromFile(CashierArray);
        } catch (FileNotFoundException ex) {
            System.out.println(RED + "Cannot read the file!" + RESET);
        }
        
        
        if(CashierArray.isEmpty()){
            System.out.println("========================================");
            System.out.println("=            No Cashier Record!        =");
            System.out.println("========================================");
           
        } else{

        
        do{
            clearScreen();
            error = false;
            System.out.println("Current Login > " + CURRENTNAME);
            
            System.out.println("=========================================");
            System.out.println("=              SEARCH Cashier           =");
            System.out.println("=========================================");
            System.out.println("=          1. Search by ID              =");
            System.out.println("=          2. Search by Name            =");
            System.out.println("=          3. Search by Position        =");
            System.out.println("=          4. Exit                      =");
            System.out.println("=========================================");
            
            System.out.print("Enter your choice > ");
            choice = input.nextInt();
            input.nextLine();
            
                switch(choice){
                    case 1:
                        clearScreen();
                        System.out.println("=====================================");
                        System.out.println("=             Search by ID          =");
                        System.out.println("=====================================");
                        System.out.print("Enter ID to search > ");
                        searchId = input.nextLine();
                        
                        exist = checkCashier(CashierArray,searchId);
                        
                        if(exist == true){
                            searchCashierId(CashierArray, searchId);
                            systemPause();
                        } else{
                            System.out.println("===================================");
                            System.out.println("=          No Record Found!       =");
                            System.out.println("===================================");
                            systemPause();
                        }
                        break;
                        
                    
                        
                    case 2:
                        clearScreen();
                        System.out.println("=======================================");
                        System.out.println("=            Search by Name           =");
                        System.out.println("=======================================");
                        System.out.print("Enter Name to search > ");
                        searchName = input.nextLine();
                        
                        exist = checkCashierName(CashierArray,searchName);
                        
                        if(exist ==  true){
                            searchCashierName(CashierArray, searchName);
                            systemPause();
                        } else{
                            System.out.println("===================================");
                            System.out.println("=          No Record Found!       =");
                            System.out.println("===================================");
                            systemPause();
                        }
                        break;
                        
                   
                        
                    case 3:
                        clearScreen();
                        do{
                            error = false;
                            System.out.println("=======================================");
                            System.out.println("=          Search by Position         =");
                            System.out.println("=======================================");
                            System.out.print("\n");
                            System.out.println("==================================");
                            System.out.println("=            Position            =");
                            System.out.println("==================================");
                            System.out.println("=      1. Full Time Cashier      =");
                            System.out.println("=      2. Part Time Cashier      =");
                            System.out.println("==================================");
                            System.out.print("Enter your choice > ");
                            choice = input.nextInt();
                            
                            switch(choice){
                                case 1:
                                    searchPosition = "Full Time Cashier";
                                    searchCashierPosition(CashierArray, searchPosition);
                                    
                                    break;
                                case 2:
                                    searchPosition = "Part Time Cashier";
                                    searchCashierPosition(CashierArray, searchPosition);
                                    
                                    break;
                                default:
                                    System.out.println("Invalid input. Please enter again!");
                                    error = true;
                            }
                            
                        }while(error);
                        systemPause();
                        break;
                        
                    case 4:
                        return;
                        
                    default:
                        error = true;
                        System.out.println("Invalid input. Please enter again!");
                        
                        
                }
            
            
        }while(error);
        
        }
        
    }
    
    public void editCashierInformation(ArrayList<Cashier> cashierArray, String id, String password){
        Cashier cashier = new Cashier();
        
        for(Cashier ch : cashierArray){
            if(ch.getId().equals(id)){
                System.out.println("======================================================================================================");
                System.out.println("=                                              ADMIN                                                 =");
                System.out.println("======================================================================================================");
                System.out.printf("   ID                : %-25s |                      Edit                    \n" , ch.getId());
                System.out.printf("   Password          : %-25s | ===================================================\n" , ch.getPassword());
                System.out.printf("   Name              : %-25s |                 1. Password                  \n" , ch.getName());
                System.out.printf("   IC                : %-25s |                 2. Position                  \n" , ch.getIc());
                System.out.printf("   Position          : %-25s |                 3. Phone Number              \n" , ch.getPosition());
                System.out.printf("   Phone Number      : %-25s |                 4. Email                     \n" , ch.getPhoneNumber());
                System.out.printf("   Email             : %-25s |                 5. Address                   \n" , ch.getEmail());
                System.out.printf("   Address           : %-25s |                 6. Basic Salary              \n" , ch.getAddress());
                System.out.printf("   Basic Salary      : %-25.2f |                 7. Commission Rate                \n" , ch.getBasicSalary());
                System.out.printf("   CommissionRate    : %-25.2f |                 8. Exit                      \n" , ch.getCommissionRate());
                System.out.printf("   Sales             : %-25.2f |                                            \n" , ch.getSales());
                System.out.printf("   Total Salary      : %-25.2f |                                            \n" , ch.getTotalSalary());
                System.out.printf("=======================================================================================================");
                        
                 
            }
        }
        
    }
    
    public void searchCashierId(ArrayList<Cashier> CashierArray, String id){
        Cashier Cashier = new Cashier();
     
        for(Cashier ad : CashierArray){
            if(ad.getId().equals(id)){
                System.out.println("======================================================");
                System.out.println("=                       Cashier                      =");
                System.out.println("======================================================");
                System.out.println("   ID                : " + ad.getId());
                System.out.println("   Password          : " + ad.getPassword());
                System.out.println("   Name              : " + ad.getName());
                System.out.println("   IC                : " + ad.getIc());
                System.out.println("   Position          : " + ad.getPosition());
                System.out.println("   Phone Number      : " + ad.getPhoneNumber());
                System.out.println("   Email             : " + ad.getEmail());
                System.out.println("   Address           : " + ad.getAddress());
                System.out.println("   Basic Salary      : " + ad.getBasicSalary());
                System.out.println("   Commission Rate   : " + ad.getCommissionRate());
                System.out.println("   Sales             : " + ad.getSales());
                System.out.println("   Total Salary      : " + ad.getTotalSalary());
                System.out.println("======================================================");
            }
        }
    }
    
    public void searchCashierName(ArrayList<Cashier> CashierArray, String name){
        Cashier Cashier = new Cashier();
     
        for(Cashier ad : CashierArray){
            if(ad.getName().equals(name)){
                System.out.println("======================================================");
                System.out.println("=                       Cashier                      =");
                System.out.println("======================================================");
                System.out.println("   ID                : " + ad.getId());
                System.out.println("   Password          : " + ad.getPassword());
                System.out.println("   Name              : " + ad.getName());
                System.out.println("   IC                : " + ad.getIc());
                System.out.println("   Position          : " + ad.getPosition());
                System.out.println("   Phone Number      : " + ad.getPhoneNumber());
                System.out.println("   Email             : " + ad.getEmail());
                System.out.println("   Address           : " + ad.getAddress());
                System.out.println("   Basic Salary      : " + ad.getBasicSalary());
                System.out.println("   Commission Rate   : " + ad.getCommissionRate());
                System.out.println("   Sales             : " + ad.getSales());
                System.out.println("   Total Salary      : " + ad.getTotalSalary());
                System.out.println("======================================================");
            }
        }
        
    }
    
    public void searchCashierPosition(ArrayList<Cashier> CashierArray, String position){
        Cashier Cashier = new Cashier();
        int count = 0;
        
        System.out.println("=====================================================================================================");
        System.out.printf("%-8s %-25s %-20s %-15s %-20s\n", "ID", "NAME", "POSITION", "PHONE NUMBER", "EMAIL");
        System.out.println("=====================================================================================================");
        
        for(Cashier ad : CashierArray){
            if(ad.getPosition().equals(position)){
                System.out.printf("%-8s %-25s %-20s %-15s %-20s\n", ad.getId(), ad.getName(), ad.getPosition(), ad.getPhoneNumber(), ad.getEmail());
                count++;
            }
        }
        
        if(count == 0){
            System.out.println("                               NO RECORD FOUND!");
        }
        
        System.out.println("=====================================================================================================");
        
        
    }
    
    public void displayCashierInformation(){
        clearScreen();
        
        Cashier Cashier = new Cashier();
        ArrayList<Cashier> CashierArray = new ArrayList();
        
        System.out.println("======================================================");
        System.out.println("=                       Cashier                      =");
        System.out.println("======================================================");
        System.out.println("   ID                : " + super.getId());
        System.out.println("   Password          : " + super.getPassword());
        System.out.println("   Name              : " + super.getName());
        System.out.println("   IC                : " + super.getIc());
        System.out.println("   Position          : " + super.getPosition());
        System.out.println("   Phone Number      : " + super.getPhoneNumber());
        System.out.println("   Email             : " + super.getEmail());
        System.out.println("   Address           : " + super.getAddress());
        System.out.println("   Basic Salary      : " + getBasicSalary());
        System.out.println("   Commission Rate   : " + getCommissionRate());
        System.out.println("   Sales             : " + getSales());
        System.out.println("   Total Salary      : " + getTotalSalary());
        System.out.println("======================================================");
       
        
    }
    
    public void displayCashierInformation(ArrayList<Cashier> CashierArray, String id, String password){
        clearScreen();
        
        Cashier Cashier = new Cashier();
     
        for(Cashier ad : CashierArray){
            if(ad.getId().equals(id) && ad.getPassword().equals(password)){
                System.out.println("======================================================");
                System.out.println("=                       Cashier                      =");
                System.out.println("======================================================");
                System.out.println("   ID                : " + ad.getId());
                System.out.println("   Password          : " + ad.getPassword());
                System.out.println("   Name              : " + ad.getName());
                System.out.println("   IC                : " + ad.getIc());
                System.out.println("   Position          : " + ad.getPosition());
                System.out.println("   Phone Number      : " + ad.getPhoneNumber());
                System.out.println("   Email             : " + ad.getEmail());
                System.out.println("   Address           : " + ad.getAddress());
                System.out.println("   Basic Salary      : " + ad.getBasicSalary());
                System.out.println("   Commission Rate   : " + ad.getCommissionRate());
                System.out.println("   Sales             : " + ad.getSales());
                System.out.println("   Total Salary      : " + ad.getTotalSalary());
                System.out.println("======================================================");
            }
        }
       
        systemPause();
        
    }
     
    public void viewCashierInformation(){
        clearScreen();
        
        int count = 0;
        
        Scanner sc = new Scanner(System.in);
    
        ArrayList<Cashier> CashierArray = new ArrayList<>();
    
        try {
            readCashierFromFile(CashierArray);
        } catch (FileNotFoundException ex) {
            System.out.println(RED + "Cannot read the file!");
            return;
        }
        
        System.out.println("Current Login > " + CURRENTNAME);
    
        if(CashierArray.isEmpty()){
            System.out.println("========================================");
            System.out.println("=           No Cashier Record!         =");
            System.out.println("========================================");

        } else{

        System.out.println("                                =============================================================================");
        System.out.println("                                =                            View Cashier Information                       =");
        System.out.println("                                =============================================================================");
    
        System.out.printf("%-8s %-25s %-20s %-15s %-25s %-13s %-8s %-15s %-15s\n", "ID", "Name", "Position", "Phone Number", "Email", "Basic Salary", "Sales", "Commission Rate", "Total Salary");
        System.out.println("=====================================================================================================================================================");
        
        for (Cashier Cashier : CashierArray) {
            count++;
            System.out.printf("%-8s %-25s %-20s %-15s %-25s %-13.2f %-8.2f %-15.2f %-15.2f\n",
                          Cashier.getId(), Cashier.getName(), Cashier.getPosition(), Cashier.getPhoneNumber(), Cashier.getEmail(),Cashier.getBasicSalary(),
                          Cashier.getSales(), Cashier.getCommissionRate(), Cashier.getTotalSalary());
        }
        
        System.out.println("\nTotal Number of Cashier > " + count);
        }
        
        systemPause();
        
    }
    
    public void cashierSalesPerformance(){
      
        clearScreen();
    
        ArrayList<Cashier> CashierArray = new ArrayList<>();
    
        try {
            readCashierFromFile(CashierArray);
        } catch (FileNotFoundException ex) {
            System.out.println(RED + "Cannot read the file!");
            return;
        }
        
        System.out.println("Current Login > " + CURRENTNAME);
    
        if(CashierArray.isEmpty()){
            System.out.println("========================================");
            System.out.println("=           No Cashier Record!         =");
            System.out.println("========================================");
            
        } else {

        System.out.println("                                             ===============================================");
        System.out.println("                                             =          Cashier SALES PERFORMANCE          =");
        System.out.println("                                             ===============================================");
        System.out.println("=============================================================================================================================");;
    
        System.out.printf("%-8s %-25s %-13s %-8s %-18s %-15s\n", "ID", "Name", "Basic Salary", "Sales", "Commission Rate", "Total Salary");
    
        for (Cashier Cashier : CashierArray) {
            System.out.printf("%-8s %-25s %-13.2f %-8.2f %-18.2f %-15.2f\n",
                          Cashier.getId(), Cashier.getName(), Cashier.getBasicSalary(),
                          Cashier.getSales(), Cashier.getCommissionRate(), Cashier.getTotalSalary());
        }
                
                /* get sales from Sales Order
                maybe this can directly update from sales order? just move this code to the sales order
                
                try {
                if(new Validation().checkCashier(CashierArray, searchID) == true){
                    updateSales(arrayCashier, searchID, sales);
                    updateTotalSales(arrayCashier, searchID, sales, salary);
                    writeCashier(CashierArray);
                    } catch(IOException ex){
                    System.out.println("Unable to edit the password!");
                    }
                           
                */
        }
                
        systemPause();
        
    }
    
    //Other methods
    public String generateCashierID(ArrayList<Cashier> cashierArray){
        String generatedCashierID;
        
        if(cashierArray.isEmpty()){
            generatedCashierID = "CH0001";
        } else{
            generatedCashierID = cashierArray.get(cashierArray.size() - 1).getId();
            int bufferCashierIDNum = Integer.parseInt(generatedCashierID.replaceAll("\\D+", ""));
            generatedCashierID = String.format("CH%04d", bufferCashierIDNum + 1);
        }
        
        return generatedCashierID;
    }
    
    //Validation
    public boolean checkCashier(ArrayList<Cashier> cashierArray, String searchId){
        boolean exist = false;
        for(Cashier ch : cashierArray){
            if(ch.getId().equals(searchId)){
                exist = true;
                break;
            }
        }
        
        return exist;
    }
    
    public boolean checkCashierName(ArrayList<Cashier> cashierArray, String searchName){
        boolean exist = false;
        for(Cashier ch : cashierArray){
            if(ch.getName().equals(searchName)){
                exist = true;
            }
        }
        
        return exist;
    }
    
    public boolean checkCashierPassword(ArrayList<Cashier> cashierArray, String searchPassword){
        boolean exist = false;
        for(Cashier cashier : cashierArray){
            if(cashier.getPassword().equals(searchPassword)){
                exist = true;
                break;
            }
        }
        
        return exist;
    }
    
    public boolean checkCashierDuplicateIc(ArrayList<Cashier> cashierArray, ArrayList<Admin> adminArray, String checkIC){
        boolean duplicate = false;
        
        for(Cashier ch :cashierArray){
            if(ch.getIc().equals(checkIC)){
                duplicate = true;
                break;
            }
        }
        
        for(Admin ad : adminArray){
            if(ad.getIc().equals(checkIC)){
                duplicate = true;
                break;
            }
        }
        
        return duplicate;
        
    }
    
    public boolean checkCashierPhoneNumberDuplicate(ArrayList<Cashier> cashierArray, ArrayList<Admin> adminArray, String checkPhoneNumber){
        boolean duplicate = false;
        
        for(Cashier cashier : cashierArray){
            if(cashier.getPhoneNumber().equals(checkPhoneNumber)){
                duplicate = true;
            }
        }
        
        for(Admin ad : adminArray){
            if(ad.getPhoneNumber().equals(checkPhoneNumber)){
                duplicate = true;
                break;
            }
        }
        
        return duplicate;
    }
    
    public boolean checkCashierEmailDuplicate(ArrayList<Cashier> cashierArray, ArrayList<Admin> adminArray, String checkEmail){
        boolean duplicate = false;
        
        for(Admin admin : adminArray){
            if(admin.getPhoneNumber().equals(checkEmail)){
                duplicate = true;
                break;
            }
        }
        
        for(Cashier cashier : cashierArray){
            if(cashier.getPhoneNumber().equals(checkEmail)){
                duplicate = true;
                break;
            }
        }
        
        return duplicate;
    }
    
    public int checkCashierIndexNumber(ArrayList<Cashier> cashierArray, String searchID, String password){
        int currentIndex = 0;
        for(int i = 0; i < cashierArray.size();i++){
            if(searchID.equals(cashierArray.get(i).getId())){
                currentIndex = i;
            }
        }
        
        return currentIndex;
    }
    
    public boolean checkYesNo(char check){
        boolean checkYN = true;
        
        if(check != 'Y' && check != 'N'){
            checkYN = false;
        }
        
        return checkYN;
    }
    
}
