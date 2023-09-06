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
import static Assignment.Assignment.input;
import static Assignment.Stationary.inputString;

/**
 *
 * @author User
 */
public class Member {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    
    private String memberID;
    private String name;
    private String phoneNumber;
    private String email;
    private String dateOfBirth;
    private double discountRate;
    private double memberPoints;
    
    
    public Member(){
        this("","","","","",0.0,0.0);
    }
    
    public Member(String memberID, String name, String phoneNumber, String email, String dateOfBirth, double discountRate, double memberPoints){
        this.memberID = memberID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.discountRate = discountRate;
        this.memberPoints = memberPoints;
    }
    
    //getter
    public String getMemberID(){
        return memberID;
    }
    
    public String getName(){
        return name;
    }
    
    public String getPhoneNumber(){
        return phoneNumber;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getDateOfBirth(){
        return dateOfBirth;
    }
    
    public double getDiscountRate(){
        return discountRate;
    }
    
    public double getMemberPoints(){
        return memberPoints;
    }
    
    //setter
    public void setMemberID(String memberID){
        this.memberID = memberID;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public void setDateOfBirth(String dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
    
    public void setDiscountRate(double discountRate){
        this.discountRate = discountRate;
    }
    
    public void setMemberPoints(double memberPoints){
        this.memberPoints = memberPoints;
    }
    
    public void rmtoPoints(double rm){
        double point = rm /100;
        this.memberPoints = point;
    }
    
    //other methods
    @Override
    public String toString(){
        return "Member ID: " + memberID + "Name: " + name + "Phone Number: " + phoneNumber + "Email: " + email + "Date of Birth" + dateOfBirth + "Discount Rate: " + discountRate;
    }
    
    public void addMember(ArrayList<Member> memberArray, Member member){
        memberArray.add(member);
    }
    
    public void deleteMember(ArrayList<Member> memberArray, Member member){
        memberArray.remove(member);
    }
    //File
    public static void writeMember(ArrayList<Member> memberArray) throws IOException{
        try(FileWriter writeMemberFile = new FileWriter("Member.txt")){
            for(Member member : memberArray){
                writeMemberFile.write(member.getMemberID() + "|" +                                     
                                     member.getName() + "|" + 
                                     member.getPhoneNumber() + "|" +
                                     member.getEmail() + "|" +
                                     member.getDateOfBirth() + "|" +
                                     member.getDiscountRate() + "|" +
                                     member.getMemberPoints() + "\n"
                                    );
            }
        }
        
    }
    
    public static void readMemberFromFile(ArrayList<Member> memberArray) throws FileNotFoundException{
        File readMemberFile = new File("Member.txt");
        
        if(readMemberFile.exists()){
            Scanner memberRead = new Scanner(readMemberFile);
            while(memberRead.hasNextLine()){
                String line = memberRead.nextLine();
                String[] info = line.split("\\|");
                memberArray.add(new Member(info[0], info[1], info[2], info[3], info[4], Double.parseDouble(info[5]), Double.parseDouble(info[6])));
            }
        } else {
            File createMemberFile = new File("Member.txt");
            try{
                createMemberFile.createNewFile();
            } catch(IOException ex){
                Logger.getLogger(Member.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("File created : " + createMemberFile.getName() + "\n");
        }
    }
    
    //main function
    public void memberMenu(){
        int choice;
        
        do{
            Assignment.clearScreen();
            System.out.println("========================================");
            System.out.println("=                MEMBER                =");
            System.out.println("========================================");
            System.out.println("=       1. Register Member             =");
            System.out.println("=       2. View Member                 =");
            System.out.println("=       3. Delete Member               =");
            System.out.println("=       4. View Member Discount        =");
            System.out.println("=       0. Exit                        =");
            System.out.println("========================================");
            
            System.out.println("Enter your choice > ");
            choice = input.nextInt();
            
            switch(choice){
                case 1 -> memberRegistration();
   
                case 2 -> displayMember();
                    
                case 3 ->{DeleteMember();}
                    
                case 4 -> {}
                 
                case 0 -> {}
                   
                default -> {
                          System.out.println("Invalid Input. Please enter again!");
                         }
            }
        }while(choice != 0);
    }
    
    public void memberRegistration(){
        
        Assignment.clearScreen();

        Member member = new Member();
        ArrayList<Member> memberArray = new ArrayList();
        
        
        String validatedName;
        String validatedPhoneNumber;
        String validatedEmail;
        String validatedDateOfBirth = null;
        double validatedPoints = 0.0;
        
        int choice;
        char confirm;
        boolean error ;
        
        try{
            readMemberFromFile(memberArray);
        } catch (FileNotFoundException ex) {
            System.out.println(RED + "Cannot read the file!" + RESET);
        }
        
        do{
        System.out.println("=======================================");
        System.out.println("=              Add Member             =");
        System.out.println("=======================================");
        
        setMemberID(generateMemberID(memberArray));
        System.out.println("ID: " + getMemberID());
        
        
        do{
            System.out.print("Enter Name: ");
            validatedName = inputString.nextLine();
            
            error = checkNameFormat(validatedName);
            
            if(error){
                System.out.println(RED + "Invalid Name Format!" + RESET);
            }else if(!error){
                setName(validatedName);
            }
            
        }while(error);
       
        inputString.nextLine();
        
        do{
            System.out.print("Enter Phone Number: ");
            validatedPhoneNumber = inputString.nextLine();
            
            error = checkPhoneNumberFormat(validatedPhoneNumber);
            if(error){
                
                System.out.println(RED + "Invalid format. Please enter again!" + RESET);
                System.out.println(RED + "Example: 012-3456789" + RESET);
                
            }else if(!error){
                setPhoneNumber(validatedPhoneNumber);
            }
            
        }while(error);
        
        do{
            System.out.print("Enter Email: ");
            validatedEmail = inputString.nextLine();
            
            error = checkEmailFormat(validatedEmail);
            if(error){
                System.out.println(RED + "Invalid format. Please enter again!" + RESET);
                System.out.println(RED + "Example: abc123@gmail.com" + RESET);
            }else if(!error){
                setEmail(validatedEmail);
            }
            
        }while(error);
        
       
        
        do{
            error = false;
            System.out.print("Confirm Member ? (Y/N): ");
            confirm = inputString.next().charAt(0);
            confirm = Character.toUpperCase(confirm);
            inputString.nextLine();
            
            if(confirm != 'Y' && confirm != 'N'){
                error = true;
            }
            
        }while(error);
        
        if (confirm == 'Y') {
            member.addMember(memberArray, new Member(getMemberID(),validatedName,validatedPhoneNumber,validatedEmail,
                    validatedDateOfBirth,0,validatedPoints));
            
            try {
                writeMember(memberArray);
            } catch (IOException ex) {
                Logger.getLogger(Member.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        do{
            error = false;
            System.out.print("Add more Member? (Y/N): ");
            confirm = inputString.next().charAt(0);
            confirm = Character.toUpperCase(confirm);
            inputString.nextLine();
            
            if(confirm != 'Y' && confirm != 'N'){
                error = true;
            }
            
        }while(error);
        
        }while(confirm == 'Y');
        
        Assignment.systemPause();
    }

    public void displayMember(){
        ArrayList<Member> memberArray = new ArrayList<>();
        
        Assignment.clearScreen();
        System.out.println("Member Record");
        System.out.println("========================");
        
        try {
            readMemberFromFile(memberArray);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Member.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (Member member:memberArray) {
            System.out.println(member);
        }
    }
    
    public void viewMember(){
        Assignment.clearScreen();
        Scanner sc = new Scanner(System.in);    //create a scanner object
        System.out.println("Enter Member that you want to view: ");
        String MemberID = sc.nextLine();
        
        System.out.println("member that you search is: " + MemberID); // output user input
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Contact Number: " + phoneNumber);
        System.out.println("Date Of Birth: " + dateOfBirth);
        System.out.println("Member's Points: " + memberPoints);
        
        Assignment.systemPause();
    }
    
    public void DeleteMember(){
        Assignment.clearScreen();
        
        Member member = new Member();
        ArrayList<Member> memberArray = new ArrayList();

        String deleteID;
        boolean exist = true;
        boolean error = false;
        char confirm;
        char confirmDelete;
        boolean check;
        int currentIndex = 0;
        
        try{
            readMemberFromFile(memberArray);
        } catch (FileNotFoundException ex) {
            System.out.println(RED + "Cannot read the file!");
        }
        
        
        
        if(memberArray.isEmpty()){
            System.out.println("None Member Record!");
        }
        else{
            do{
                confirm = 'N';
                Assignment.clearScreen();
                System.out.println("=======================================");
                System.out.println("=          Delete Member              =");
                System.out.println("=======================================");
                
                do{
                    exist = true;
                    System.out.println("Enter Member ID to delete(Press X to exit): ");
                    deleteID = inputString.nextLine();
                    
                    deleteID = deleteID.trim();
                    
                    if((deleteID.equalsIgnoreCase("X"))) {
                        return;
                    }
                    
                    for (int i = 0; i < memberArray.size(); i++) {

                        if (deleteID.equals(memberArray.get(i).getMemberID())) {
                            exist = false;
                            currentIndex = i; 
                            break;
                        }
                    }
                    
                    if(!exist){
                        System.out.println("Member not found!");
                    }
                    
                }while(!exist);
                
                
                
                viewMemberInformation();
                
                do{
                    error = false;
                    System.out.println("Confirm to Delete? (Y/N): ");
                    confirmDelete = inputString.next().charAt(0);
                    confirmDelete = Character.toUpperCase(confirmDelete);
                    
                    check = checkYesNo(confirmDelete);
                    if(!check){
                        error = true;
                        System.out.println(RED + "Invalid input. Please enter again!" + RESET);
                    }
                }while(error);
                
                if(confirmDelete == 'Y'){
                    deleteMember(memberArray, memberArray.get(currentIndex));
                    try{
                        writeMember(memberArray);
                    }catch(IOException ex){
                        Logger.getLogger(Member.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("Successful Delete");
                }
                
                do{
                    error = false;
                    
                    System.out.print("Delete more member? (Y/N) > ");
                    confirm = inputString.next().charAt(0);
                    confirm = Character.toUpperCase(confirm);
                    
                    check = checkYesNo(confirm);
                    
                    if(!check){
                        error = true;
                        System.out.println(RED + "Invalid input. Please enter again!" + RESET);
                    }
                    
                }while(error);
                
                inputString.nextLine();
            }while(confirm == 'Y');
        }
        
        Assignment.systemPause();
    }
//    
//    public void viewMemberDiscount(){
//        double MemberDiscount = 0.75;
//        int choice;
//        
//        clearScreen();
//        
//        System.out.println("====================================");
//        System.out.println("=         Member Discount          =");
//        System.out.println("=         1. View Only             =");
//        System.out.println("=         2. Modify Discount       =");
//        System.out.println("=         3. Exit                  =");
//        System.out.println("====================================");
//        
//        System.out.println("Enter your choice > ");
//            choice = sc.nextInt();
//            
//            switch(choice){
//                case 1 -> System.out.println("MemberDiscount: " + MemberDiscount);
//   
//                case 2 -> ModifyMemberDiscount();
//                
//                case 3 -> {}
//                
//                default -> {
//                    System.out.println("Error!!!!Please Choose a correct number.");
//                }
//            }
//        systemPause();
//    }
//    
//    public void memberPointEarned(){
//        double MemberPoints;
//        int choice;
//        
//        
//        clearScreen();
//        
//        Scanner MemberID = new Scanner(System.in);
//        System.out.println("Please enter memberID that want to view: ");
//        MemberPoints = sc.nextDouble();
//        
//        
//        System.out.println("===================================");
//        System.out.println("=           Total Points          =");
//        System.out.println(  "=        Member Points:" + MemberPoints +"       =");
//        System.out.println("===================================");
//        
//        System.out.println("Do you want to continue?(YES/NO): ");
//        choice = sc.nextInt();
//        if(choice == 'YES'){
//            memberPointEarned();
//        }
//        else(choice == 'NO'){
//        systemPause();
//    }
//    }
     
     public String generateMemberID(ArrayList<Member> memberArray){
        String generatedMemberID;
        
        if(memberArray.isEmpty()){
            generatedMemberID = "MID0001";
        } else{
            generatedMemberID = memberArray.get(memberArray.size() - 1).getMemberID();
            int bufferMemberIDNum = Integer.parseInt(generatedMemberID.replaceAll("\\D+", ""));
            generatedMemberID = String.format("MID%04d", bufferMemberIDNum + 1);
        }
        
        return generatedMemberID;
    }
     
     public boolean checkMember(String checkMemeber){
         boolean error = false;
         String IDFormat = "M";
         int number;
         int bufferMemberIDNUm;
         
         if(IDFormat == "M"){
             error = false;
         }
         else{
             System.out.println("Invalid memberID!");
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
        
        return error;
    }
     
    public boolean checkPointsFormat(double checkPoints){
         boolean error = false;
         int countPositive = 0;
         int countNegative = 0;
         char positive = '+';
         char negative ='-';
         
         if(memberPoints >0){
             error = true;
         }
         
         return error;
    }
     
    public static void ModifyMemberDiscount(){
         String confirm;
         double newMemberDiscount;
        
         boolean error;
         Scanner sc = new Scanner(System.in);
         
        do{
         System.out.println("Please enter new Member Discount: ");
         newMemberDiscount = sc.nextDouble();
         
            do{
                error = false;
                System.out.print("Add more Member? (yes/no): ");
                confirm = sc.nextLine();
            
                if(confirm != "yes" && confirm != "no"){
                    error = true;
                }
            
            }while(error);
        
        }while(confirm == "yes");
    }
    
    public boolean viewMemberInformation(){
        System.out.println("member ID: " + memberID);
        System.out.println("member Name: " + name);
        System.out.println("phone number: " + phoneNumber);
        System.out.println("email: " + email);
        System.out.println("date of birth: " + dateOfBirth);
        System.out.println("points:  " + memberPoints);
        
        return viewMemberInformation();
    }
    
    public boolean checkYesNo(char check){
        boolean checkYN = true;
        
        if(check != 'Y' && check != 'N'){
            checkYN = false;
        }
        
        return checkYN;
    }

    
    }
