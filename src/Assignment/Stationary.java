/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

import static Assignment.Assignment.RED;
import static Assignment.Assignment.RESET;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Character.toUpperCase;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yung
 */
public class Stationary extends Stock{
  
    private String staId;
    static Scanner input = new Scanner(System.in);
    static Scanner inputString = new Scanner(System.in);
  
    Stationary(){}
    
    Stationary(String staId, String name, int stockQuantity, double unitPrice, double soldPrice, boolean stockStatus) {
        super(name, stockQuantity, unitPrice, soldPrice, stockStatus);
        this.staId = staId;
    }
    
    //setter
    public void setStaId(String staId){
        this.staId = staId;
    }
    
    //getter
    public String getStaId(){
        return staId;
    }
    
        
    //File Method
    public static void writeStaToFile(ArrayList<Stationary> staArray) throws IOException{
        try ( FileWriter writeStaFile = new FileWriter("Stationary.txt")) {
            for (Stationary staFromArray : staArray) {
                writeStaFile.write(staFromArray.getStaId()+'|'+staFromArray.getName()+'|'+staFromArray.getStockQuantity()+'|'+staFromArray.getUnitPrice()+'|'+
                                   staFromArray.getSoldPrice()+'|'+staFromArray.isStockStatus()+'\n');
            }
        }
    }
    
    public static void readStaFromFile(ArrayList<Stationary> staArray) throws FileNotFoundException{
        File readStaFile = new File("Stationary.txt");
        
        if (readStaFile.exists()) {
             Scanner productRead = new Scanner(readStaFile);
            while (productRead.hasNextLine()) {
                String line = productRead.nextLine();
                String[] data = line.split("\\|");
                staArray.add(new Stationary(data[0],data[1],Integer.parseInt(data[2]),Double.parseDouble(data[3]),Double.parseDouble(data[4]),
                             Boolean.parseBoolean(data[5])));
            }
        }else {
            File createStaFile = new File("Stationary.txt");
            try {
                createStaFile.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Stationary.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
           
            
        
    }
    
    public String generateId(ArrayList<Stationary> StaArray){
        String staIdGenerated;
       
        if  (StaArray.isEmpty()) {
                staIdGenerated = "S0001";
        }else{
            staIdGenerated = StaArray.get(StaArray.size() - 1).getStaId();

            int bufferBookIdNum = Integer.parseInt(staIdGenerated.replaceAll("\\D+", ""));

            staIdGenerated = String.format("S%04d", bufferBookIdNum + 1);
        }
        
        return staIdGenerated;
    }
    
    //overloading display method

    
    public static void displayStaDetails(Stationary sta){
            System.out.println("====================================");
            System.out.println("|            Book Detail            |");
            System.out.println("====================================");
            System.out.println("| Stationary ID : " + sta.getStaId());
            System.out.println("| Stationary Name :  " + sta.getName());          
            System.out.println("| Stationary Price :  " + sta.getUnitPrice());
            System.out.println("| Stationary Sold Price :  " + sta.getSoldPrice());
            System.out.println("| Stationary Total Added :  " + sta.getStockQuantity());
            System.out.println("| Stationary Total Added (RM) :  " + (sta.getUnitPrice() * sta.getStockQuantity()));
            System.out.println("|                                  |");
            System.out.println("====================================");
            
    }
    
    void addSta(ArrayList<Stationary> staArray, Stationary sta) {
        staArray.add(sta);
    }
    
    public void removeSta(ArrayList<Stationary> staArray,Stationary sta){
        staArray.remove(sta);
    }

    @Override
    public String toString( ){
        return String.format("%-15s   %s  ",staId,super.toString());
    }
    
    //edit name
    public static void editSta(ArrayList<Stationary> staArray,String searchId,String newName){
         for (Stationary latestSta: staArray) {
          if (latestSta.getStaId().equals(searchId)) {
                 latestSta.setName(newName);
           }     
            
        }
    }
    
    // edit price
    public static void editSta(ArrayList<Stationary> staArray,String searchId,double newPrice){
         for (Stationary latestSta: staArray) {
          if (latestSta.getStaId().equals(searchId)) {
                 latestSta.setSoldPrice(newPrice);
              
           }     
            
        }
    }
    
    //edit quantity
    public static void editSta(ArrayList<Stationary> staArray,String searchId,int newQuantity,boolean add,boolean sub){
         for (Stationary latestSta: staArray) {
             if (latestSta.getStaId().equals(searchId)) {
                 if (add) {
                     latestSta.addStockQuantity(newQuantity);
                 }else if (sub) {
                     
                     int check = latestSta.getStockQuantity() - newQuantity;
                     
                     if (latestSta.isStockStatus() && check >= 0) {
                         latestSta.subStockQuantity(newQuantity);
                     }else{
                         System.out.println(RED+"Book Quantity Is Zero"+RESET);
                         Assignment.systemPause();
                     }
                     
                 }else {
                     latestSta.setStockQuantity(newQuantity);
                 }
             }     
        }
    }
    
    public static void checkAvailable(ArrayList<Stationary> staArray){
        for (Stationary sta:staArray) {
            if (sta.getStockQuantity() <= 0) {
                sta.setStockStatus(false); 
            }else if (sta.getStockQuantity() > 0) {
                sta.setStockStatus(true); 
            }
        }
    }
    
    @Override
    public void display(){
                    Assignment.clearScreen();
            
                    ArrayList<Stationary> staArray = new ArrayList<>();
                    
                    System.out.println("Display All Stationary");
                    System.out.printf("%-15s %-28s    %-8s    %-6s    %-9s    %-15s    \n","Stationary Id","Stationary Name","Quantity",
                "Unit Price","Sold Price","Stock Status");
                    System.out.println("==========================================================================================================");
                    
                    try {
                        readStaFromFile(staArray);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    for (Stationary staDisplay : staArray) {
                        System.out.println(staDisplay);
                    }
                    Assignment.systemPause();
    }
    
    @Override
    public void adjust(){
        String idSearch,confirm;
        int choice , newBookQuantity,previousQuantity, currentIndex = 0;    
        boolean notFound,valid ;
        
                    ArrayList<Stationary> staArray = new ArrayList<>();
                    ArrayList<Stationary> checkArray = new ArrayList<>();
                    try {
                        readStaFromFile(staArray);
                        readStaFromFile(checkArray);
                    }
                    catch (FileNotFoundException ex) {
                        System.out.println("Failed to read book record");
                    }   
                    
                    do{
                        notFound = true;
                        Assignment.clearScreen();
                        
                        System.out.println("Edit Stationary ");
                        System.out.println("========================");
                        
                        
                        do {
                            idSearch = Validation.getStringInput("Enter Stationary ID [Q to exit]> ");

                             if ("".equals(idSearch)) {
                                continue;
                            }
                             
                            if (toUpperCase(idSearch.charAt(0)) == 'Q') {
                                break;
                            }

                            if (!validID(idSearch)) {
                               System.out.println(RED +"Stationary ID Must in S0001 format" + RESET);
                            }

                        } while (!validID(idSearch));
            
                            for (int i = 0; i < staArray.size(); i++) {
                            
                            if (idSearch.equals(staArray.get(i).getStaId())) {
                                
                                notFound = false;
                                displayStaDetails(staArray.get(i));
                                currentIndex = i;
                                break;
                            }
   
                            }
                        
                        if (notFound && toUpperCase(idSearch.charAt(0))!='Q') {
                            
                            System.out.println("The Stationary Id Entered Does Not Exist.");
                            Assignment.systemPause();
                            
                        }else if(!notFound && toUpperCase(idSearch.charAt(0))!='Q'){
                            
                            System.out.println("1. Stationary Name");
                            System.out.println("2. Sold Price ");
                            System.out.println("3. Stock Balance ");
                            
                            
                            System.out.print("Enter Field to Edit [1-3] >");
                            choice = Validation.getIntegerInput();
                            
                            switch(choice){
                                
                                case 1 -> {
                                    
                                    System.out.println("Edit Stationary Name");
                                    System.out.println("====================");
                                    
                                    do {
                                        System.out.print("Enter Stationary Name :");
                                        staArray.get(currentIndex).setName(Validation.getStringInput());

                                        if (!validName(checkArray,staArray.get(currentIndex).getName())) {
                                            System.out.println(RED +"Book Name Repeated or Too Short" + RESET);
                                        }

                                    } while (!validName(checkArray,staArray.get(currentIndex).getName()));
                        
                                    
                                    
                                    
                                    System.out.print("Confirm To Edit Stationary Name ? [Y/N] >");
                                    confirm = input.next();
                                    
                                    if (toUpperCase(confirm.charAt(0)) == 'Y' && Validation.checkYesNo(confirm.charAt(0))) {
                                        try {
                                            editSta(staArray, idSearch, staArray.get(currentIndex).getName());
                                            writeStaToFile(staArray);
                                        } catch (IOException ex) {
                                            System.out.println("Failed to Edit The Book Name");
                                        }
                                    }
                                }
                                
                                
                                case 2 ->{
                                    
                                    System.out.println("Edit Stationary Price");
                                    System.out.println("=====================");
                                    do {
                                        System.out.print("Enter Stationary Price :");
                                        staArray.get(currentIndex).setSoldPrice(input.nextDouble());

                                        if (!super.validSoldPrice(staArray.get(currentIndex).getSoldPrice(),staArray.get(currentIndex).getUnitPrice())) {
                                            System.out.println(RED +"Stationary Price Less Than Unit Price " + RESET);
                                        }

                                    } while (!super.validSoldPrice(staArray.get(currentIndex).getSoldPrice(),staArray.get(currentIndex).getUnitPrice()));
                                    
                                    
                                    
                                    System.out.print("Confirm To Edit Stationary Price ? [Y/N] >");
                                    confirm = input.next();
                                    
                                    if (toUpperCase(confirm.charAt(0)) == 'Y' && Validation.checkYesNo(confirm.charAt(0))) {
                                        
                                        try {
                                            editSta(staArray, idSearch, staArray.get(currentIndex).getSoldPrice());
                                            writeStaToFile(staArray);
                                            
                                        } catch (IOException ex) {
                                            System.out.println("Failed to Edit The Book Type");
                                        }
                                        
                                    }
                                }
                                
                                case 3-> {
                                    System.out.println("Edit Stationary Quantity");
                                    System.out.println("========================");
                                    System.out.println("1. Add Stationary Quantity");
                                    System.out.println("2. Sub Stationary Quantity");
                                    System.out.println("3. Set Stationary Quantity");
                                    
                                    System.out.print("Enter your Choice > ");
                                    choice = Validation.getIntegerInput();
                                    
                                    if (choice > 0 && choice <=3) {
                                        
                                    
                                    do {
                                        valid = true;
                                        System.out.print("Enter Stationary Quantiy >");
                                        newBookQuantity = Validation.getIntegerInput();
                                       if (choice == 2) {

                                        if (newBookQuantity > staArray.get(currentIndex).getStockQuantity()) {
                                            valid = false;
                                            System.out.println(RED+ "Quantity Sub Exceed" + RESET);
                                        }

                                        } 
                                    } while (!valid);
                                    
                                    System.out.print("Confirm To Edit Stationary Quantity ? [Y/N] >");
                                    confirm = input.next();
                                    
                                    if (toUpperCase(confirm.charAt(0)) == 'Y' && Validation.checkYesNo(confirm.charAt(0))) {
                                        switch (choice) {
                                            case 1 -> {
                                                try {
                                                   editSta(staArray, idSearch, newBookQuantity,true,false);
                                                   checkAvailable(staArray);
                                                   writeStaToFile(staArray);
                                                } catch (IOException ex) {
                                                    System.out.println("Failed to Edit The Book Type");
                                                }
                                                
                                                try {
                                                    StockFlowReport.writeStockToFile(staArray.get(currentIndex).getStaId(),newBookQuantity,
                                                            staArray.get(currentIndex).getStockAddDate());
                                                } catch (IOException ex) {
                                                    Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                                
                                            }
                                            case 2 -> {
                                                try {
                                                   editSta(staArray, idSearch, newBookQuantity,false,true);
                                                   checkAvailable(staArray);
                                                   writeStaToFile(staArray);
                                                } catch (IOException ex) {
                                                    System.out.println("Failed to Edit The Book Type");
                                                }
                                                
                                                try {
                                                    StockFlowReport.writeStockToFile(staArray.get(currentIndex).getStaId(),-(newBookQuantity),
                                                            staArray.get(currentIndex).getStockAddDate());
                                                } catch (IOException ex) {
                                                    Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                            }
                                            case 3 -> {
                                                previousQuantity =staArray.get(currentIndex).getStockQuantity();
                                                try {
                                                    editSta(staArray, idSearch, newBookQuantity,false,false);
                                                    checkAvailable(staArray);
                                                    writeStaToFile(staArray);
                                                } catch (IOException ex) {
                                                    System.out.println("Failed to Edit The Book Type");
                                                }
                                                
                                                try {
                                                    StockFlowReport.writeStockToFile(staArray.get(currentIndex).getStaId(),
                                                            -( previousQuantity - newBookQuantity),
                                                            staArray.get(currentIndex).getStockAddDate());
                                                } catch (IOException ex) {
                                                    Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                            }
                                            default -> {
                                            }
                                        }
                                        
                                    }else {
                                        System.out.println("Edit is denied");
                                    } 
                                    }else
                                        System.out.println(RED +"Invalid Input" + RESET);
                                    
                                }
                            }
                        }
                  
                    } while(toUpperCase(idSearch.charAt(0))!='Q');
    }
    
    @Override
    public void add(){
                
        char confirmChoice;
        ArrayList <Stationary> staArray= new ArrayList<>();
        
        Stationary sta = new Stationary();
        
                try {
                    readStaFromFile(staArray);
                } catch (FileNotFoundException ex) {
                    System.out.println("Failed Read File");
                }
                
                do {
                    Assignment.clearScreen();
                    sta.setStaId(generateId(staArray));
                    System.out.println("Stationary Id : " + sta.getStaId());
                    
                    do {
                        System.out.print("Enter Stationary Name : ");
                        sta.setName(Validation.getStringInput());
                        
                        if (!validName(staArray,sta.getName())) {
                            System.out.println(RED + "The Stationary Name Already Exits" + RESET);
                        }
                        
                    } while (!validName(staArray,sta.getName()));
                    
                    
                    do {
                        System.out.print("Enter Quantity :");
                    
                        sta.setStockQuantity(Validation.getIntegerInput());
                        
                        if (!super.validQuantity(sta.getStockQuantity())) {
                            System.out.println(RED +"Stationary Quantity Must At Least one" + RESET);
                        }
                        
                    } while (!super.validQuantity(sta.getStockQuantity()));
                    
                    
                    do {
                        System.out.print("Enter Stationary price : ");
                    
                        sta.setUnitPrice(input.nextDouble());
                        
                        if (!super.validUnitPrice(sta.getUnitPrice())) {
                            System.out.println(RED +"Stationary Price Must more than RM 0 " + RESET);
                        }
                        
                    } while (!super.validUnitPrice(sta.getUnitPrice()));

                    
                    do {
                        System.out.print("Enter Stationary Sold price : ");
                    
                        sta.setSoldPrice(input.nextDouble());
                        
                        if (!super.validSoldPrice(sta.getSoldPrice(),sta.getUnitPrice())) {
                            System.out.println(RED +"Stationary Price Less Than Unit Price " + RESET);
                        }
                        
                    } while (!super.validSoldPrice(sta.getSoldPrice(),sta.getUnitPrice()));
                    
                           
                    Assignment.clearScreen();
                    
                    displayStaDetails(sta);
                    
                    System.out.print("Comfirm [Y/N] > ");
                    confirmChoice = inputString.next().charAt(0);
                    
                    if (Validation.checkYesNo(confirmChoice)) {
                        
                        
                        switch (toUpperCase(confirmChoice)) {
                            case 'Y' -> {
                                addSta(staArray, new Stationary(sta.getStaId(),sta.getName(),sta.getStockQuantity(),sta.getUnitPrice(),sta.getSoldPrice(),true));
                                
                                try {
                                    writeStaToFile(staArray);  
                                } catch (IOException ex) {
                                    Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                                try {
                                    StockFlowReport.writeStockToFile(sta.getStaId(),
                                            sta.getStockQuantity() ,
                                            sta.getStockAddDate());
                                } catch (IOException ex) {
                                    Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                
                            }
                            case 'N' -> System.out.println("Thank you...");
                            default -> System.out.println("Wrong input");
                        }
                        
                        System.out.print("Any More Book ? [Y/N] > ");
                        confirmChoice = inputString.next().charAt(0);
                        
                    }else{
                        System.out.println("Wrong Input");
                    }
                    
                } while (toUpperCase(confirmChoice) != 'N');
    }
    
    @Override
    public void remove(){
         String idSearch,confirm;
    
         boolean notFound;
    
         int currentIndex = 0;
         
                ArrayList <Stationary> staArray = new ArrayList<> ();
                try {
                    readStaFromFile(staArray);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                do {
                    
                    if (!staArray.isEmpty()) {
                        
                    notFound = true;

                    Assignment.clearScreen();
                    System.out.println("Remove Stationary System");
                    System.out.println("========================");
                    
                    do {
                        idSearch = Validation.getStringInput("Enter Stationary ID [Q to exit]> ");

                         if ("".equals(idSearch)) {
                            continue;
                        }

                        if (toUpperCase(idSearch.charAt(0)) == 'Q') {
                            break;
                        }

                        if (!validID(idSearch)) {
                           System.out.println(RED +"Stationary ID Must in S0001 format" + RESET);
                        }

                    } while (!validID(idSearch));
                    

                    for (int i = 0; i < staArray.size(); i++) {

                        if (idSearch.equals(staArray.get(i).getStaId())) {
                            notFound = false;
                            displayStaDetails(staArray.get(i));
                            currentIndex = i; 
                            break;
                        }
                    }

                    if (notFound && toUpperCase(idSearch.charAt(0))!='Q') {
                        
                        System.out.println("The Stationary Id Entered Does Not Exist.");
                        Assignment.systemPause();
                        
                    }else if(!notFound && toUpperCase(idSearch.charAt(0))!='Q'){

                        System.out.print("Confirm To Remove Stationary  ? [Y/N] >");
                        confirm = inputString.next();

                        if (toUpperCase(confirm.charAt(0)) == 'Y') {
                            try {
                                StockFlowReport.writeStockToFile(staArray.get(currentIndex).getStaId(),
                                        -(staArray.get(currentIndex).getStockQuantity()) ,
                                        staArray.get(currentIndex).getStockAddDate());
                            } catch (IOException ex) {
                                Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            removeSta(staArray,staArray.get(currentIndex));
                            try {
                                writeStaToFile(staArray);
                            } catch (IOException ex) {
                                Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            System.out.println("Succcesful Removed");
                            Assignment.systemPause();
                        }
                    }
                    
                    }else{
                        System.out.println("Stationary List Is Empty");
                        idSearch = "Q";
                    }
                    
                } while (toUpperCase(idSearch.charAt(0)) != 'Q');
    }
    
    @Override
    public void search(){
        String search;
    
         boolean notFound = true;
    
         int choice;
         
        ArrayList <Stationary> staArray = new ArrayList<> ();
        try {
            readStaFromFile(staArray);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        do {
            Assignment.clearScreen();
            System.out.println("1. Search By Id");
            System.out.println("2. Search By Name");
            System.out.println("0. Exit");
            System.out.print("Enter your Choice > ");
            choice = Validation.getIntegerInput();
            
            switch(choice){
                case 1 ->{
                    
                    
                    do {
                            System.out.print("Enter Stationary Id > ");
                            search = Validation.getStringInput();
                         
                            if (!validID(search)) {
                               System.out.println(RED +"Stationary ID Must in S0001 format" + RESET);
                            }

                    } while (!validID(search));
                    
                    for (Stationary sta:staArray) {
                        if (sta.getStaId().equals(search)) {
                            System.out.println(sta);
                            notFound = false;
                        }
                    }
                    
                    if (notFound) {
                        System.out.println(RED +"Stationary Does Not Exist" + RESET);
                    }
                }
                
                case 2 ->{
                    System.out.print("Enter Stationary Name > ");
                    search = Validation.getStringInput();
                    
                    for (Stationary sta:staArray) {
                        if (sta.getName().equals(search)) {
                            System.out.println(sta);
                            notFound = false;
                        }
                    }
                    
                    if (notFound) {
                        System.out.println(RED +"Stationary Does Not Exist" + RESET);
                    }
                    
                }
            }
            Assignment.systemPause();
        } while (choice != 0);
    }
    
       //validation
    public Boolean validID(String id){

        if (id.length()< 5) {
            return false;
        }else if(id.charAt(0) != 'B'){
            return false;
        }
        
        for (int i = 1; i < 5; i++) {
            if (!Character.isDigit(id.charAt(i))) {
                return false;
            }
        }

        return true;
    }
    
    public Boolean validName(ArrayList<Stationary> staArray,String name){
        
        for (Stationary sta: staArray) {
            if (sta.getName().equals(name)) {
                return false;
            }
        }  
        return true;
    }

}
