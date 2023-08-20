/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

import static Assignment.Stock.clearScreen;
import static Assignment.Stock.input;
import static Assignment.Stock.systemPause;
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
    private String name;
    private double unitPrice;
    private double soldPrice;
    private int stockBalance;
    private boolean staStatus;
    
    
    Stationary(){
        staId = "";
        name = "";
        unitPrice = 0.0;
        soldPrice = 0.0;
        stockBalance = 0;
        staStatus = true;
    }
    
    Stationary(String staId,String name,double unitPrice,double soldPrice,int stockBalance,boolean staStatus){
        this.staId = staId;
        this.name = name;
        this.unitPrice = unitPrice;
        this.soldPrice = soldPrice;
        this.stockBalance = stockBalance;
        this.staStatus = staStatus;
    }
    
    
    
    
    //setter
    public void setStaId(String staId){
        this.staId = staId;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setUnitPrice(double unitPrice){
        this.unitPrice = unitPrice;
    }
    
    public void setSoldPrice(double soldPrice){
        this.soldPrice = soldPrice;
    }
    
    public void setStockBalance(int stockBalance){
        this.stockBalance = stockBalance;
    }
   
    public void addStockBalance(int stockBalance){
        this.stockBalance += stockBalance;
    }
    
    public void subStockBalance(int stockBalance){
        this.stockBalance -= stockBalance;
    }
    
    public void setStaStatus(boolean staStatus){
        this.staStatus = staStatus;
    }
    
    //getter
    public String getStaId(){
        return staId;
    }
    
    public String getName(){
        return name;
    }
    
    public double getUnitPrice(){
        return unitPrice;
    }
    
    public double getSoldPrice(){
        return soldPrice;
    }
    
    public int getStockBalance(){
        return stockBalance;
    }
    
    public boolean getStaStatus(){
        return staStatus;
    }
    
    
    //File Method
    public static void writeStaToFile(ArrayList<Stationary> staArray) throws IOException{
        try ( FileWriter writeStaFile = new FileWriter("Stationary.txt")) {
            for (Stationary staFromArray : staArray) {
                writeStaFile.write(staFromArray.getStaId()+'|'+staFromArray.getName()+'|'+staFromArray.getUnitPrice()+'|'+
                                   staFromArray.getSoldPrice()+'|'+staFromArray.getStockBalance()+'|'+staFromArray.getStaStatus()+'\n');
            }
        }
        System.out.println("Successfully wrote to the file.\n");
    }
    
    public static void readStaFromFile(ArrayList<Stationary> staArray) throws FileNotFoundException{
        File readBookFile = new File("Stationary.txt");
        
        if (readBookFile.exists()) {
             Scanner productRead = new Scanner(readBookFile);
            while (productRead.hasNextLine()) {
                String line = productRead.nextLine();
                String[] data = line.split("\\|");
                staArray.add(new Stationary(data[0],data[1],Double.parseDouble(data[2]),Double.parseDouble(data[3]),
                             Integer.parseInt(data[4]),Boolean.parseBoolean(data[5])));
            }
        }else {
            File createProductFile = new File("Stationary.txt");
            try {
                createProductFile.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Stationary.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("File created : " + createProductFile.getName() + "\n");
        }
           
            
        
    }
    
    public String generateStaId(ArrayList<Stationary> StaArray){
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
    public void displayStaDetails(){
            System.out.println("====================================");
            System.out.println("|            Book Detail            |");
            System.out.println("====================================");
            System.out.println("| Stationary ID : " + staId);
            System.out.println("| Stationary Name :  " + name);          
            System.out.println("| Stationary Price :  " + unitPrice);
            System.out.println("| Stationary Sold Price :  " + soldPrice);
            System.out.println("| Stationary Total Added :  " + stockBalance);
            System.out.println("| Stationary Total Added (RM) :  " + (unitPrice * stockBalance));
            System.out.println("|                                  |");
            System.out.println("====================================");

            
    }
    
    public static void displayBookDetails(Stationary sta){
            System.out.println("====================================");
            System.out.println("|            Book Detail            |");
            System.out.println("====================================");
            System.out.println("| Stationary ID : " + sta.getStaId());
            System.out.println("| Stationary Name :  " + sta.getName());          
            System.out.println("| Stationary Price :  " + sta.getUnitPrice());
            System.out.println("| Stationary Sold Price :  " + sta.getSoldPrice());
            System.out.println("| Stationary Total Added :  " + sta.getStockBalance());
            System.out.println("| Stationary Total Added (RM) :  " + (sta.getUnitPrice() * sta.getStockBalance()));
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
        
        return String.format("%s   %s  RM%.2f  RM%.2f  %d ",staId,name,unitPrice,
                            soldPrice,stockBalance);
    }
    
    public static void editSta(ArrayList<Stationary> staArray,String searchId,String newName){
         for (Stationary latestSta: staArray) {
          if (latestSta.getStaId().equals(searchId)) {
                 latestSta.setName(newName);
           }     
            
        }
    }
    
    public static void editSta(ArrayList<Stationary> staArray,String searchId,double newPrice){
         for (Stationary latestSta: staArray) {
          if (latestSta.getStaId().equals(searchId)) {
                 latestSta.setSoldPrice(newPrice);
              
           }     
            
        }
    }
    
    public static void editSta(ArrayList<Stationary> staArray,String searchId,int newQuantity,boolean add,boolean sub){
         for (Stationary latestSta: staArray) {
             if (latestSta.getStaId().equals(searchId)) {
                 if (add) {
                     latestSta.addStockBalance(newQuantity);
                 }else if (sub) {
                     
                     int check = latestSta.getStockBalance() - newQuantity;
                     
                     if (latestSta.getStaStatus() && check >= 0) {
                         latestSta.subStockBalance(newQuantity);
                     }else{
                         System.out.println(Stock.RED+"Book Quantity Is Zero"+Stock.RESET);
                         Stock.systemPause();
                     }
                     
                 }else {
                     latestSta.setStockBalance(newQuantity);
                 }
             }     
        }
    }
    
    public static void checkAvailable(ArrayList<Stationary> staArray){
        for (Stationary sta:staArray) {
            if (sta.stockBalance <= 0) {
                sta.staStatus = false;
            }else if (sta.stockBalance > 0) {
                sta.staStatus = true;
            }
        }
    }
    
    public static void display(){
                    Stock.clearScreen();
            
                    ArrayList<Stationary> staArray = new ArrayList<>();
                    
                    System.out.println("Display All Stationary");
                    
                    try {
                        readStaFromFile(staArray);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    for (Stationary staDisplay : staArray) {
                        System.out.println(staDisplay);
                    }
                    Stock.systemPause();
    }
    
    public static void adjust(){
        String IdSearch ,newBookName,confirm;
        char newBookType;
        int choice , newBookQuantity, currentIndex = 0;
        double newBookPrice;
        boolean notFound = true;
        
                    ArrayList<Stationary> staArray = new ArrayList<>();
                    try {
                        readStaFromFile(staArray);
                    }
                    catch (FileNotFoundException ex) {
                        System.out.println("Failed to read book record");
                    }   
                    
                    do{
                        
                        clearScreen();
                        checkAvailable(staArray);
                        System.out.println("Edit Stationary ");
                        System.out.println("========================");
                        System.out.print("Enter BookID [Q to exit]> ");
                        IdSearch = input.nextLine();
                        
                        for (int i = 0; i < staArray.size(); i++) {
                            
                            if (IdSearch.equals(staArray.get(i).getStaId())) {
                                
                                notFound = false;
                                displayBookDetails(staArray.get(i));
                                break;
                            }
                            
                        }
                        
                        if (notFound && toUpperCase(IdSearch.charAt(0))!='Q') {
                            
                            System.out.println("The Stationary Id Entered Does Not Exist.");
                            systemPause();
                            
                        }else if(!notFound && toUpperCase(IdSearch.charAt(0))!='Q'){
                            
                            System.out.println("1. Stationary Name");
                            System.out.println("2. Sold Price ");
                            System.out.println("3. Stock Balance ");
                            
                            
                            System.out.print("Enter Field to Edit [1-3] >");
                            choice = Validation.getIntegerInput();
                            
                            switch(choice){
                                
                                case 1 -> {
                                    
                                    System.out.println("Edit Stationary Name");
                                    System.out.println("====================");
                                    System.out.print("Enter Stationary Name :");
                                    newBookName = input.nextLine();
                                    
                                    System.out.print("Confirm To Edit Book Name ? [Y/N] >");
                                    confirm = input.next();
                                    
                                    if (toUpperCase(confirm.charAt(0)) == 'Y' && Validation.checkYesNo(confirm.charAt(0))) {
                                        try {
                                            editSta(staArray, IdSearch, newBookName);
                                            writeStaToFile(staArray);
                                        } catch (IOException ex) {
                                            System.out.println("Failed to Edit The Book Name");
                                        }
                                    }
                                }
                                
                                
                                case 2 ->{
                                    
                                    System.out.println("Edit Stationary Price");
                                    System.out.println("=====================");
                                    System.out.print("Enter Stationary Price :");
                                    newBookPrice = input.nextDouble();
                                    
                                    System.out.print("Confirm To Edit Stationary Price ? [Y/N] >");
                                    confirm = input.next();
                                    
                                    if (toUpperCase(confirm.charAt(0)) == 'Y' && Validation.checkYesNo(confirm.charAt(0))) {
                                        
                                        try {
                                            editSta(staArray, IdSearch, newBookPrice);
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
                                    
                                    
                                    System.out.print("Enter Stationary Quantiy >");
                                    newBookQuantity = Validation.getIntegerInput();
                                    
                                    
                                    System.out.println("Confirm To Edit Book Quantity ? [Y/N] >");
                                    confirm = input.next();
                                    
                                    if (toUpperCase(confirm.charAt(0)) == 'Y' && Validation.checkYesNo(confirm.charAt(0))) {
                                        switch (choice) {
                                            case 1 -> {
                                                try {
                                                   editSta(staArray, IdSearch, newBookQuantity,true,false);
                                                   writeStaToFile(staArray);
                                                } catch (IOException ex) {
                                                    System.out.println("Failed to Edit The Book Type");
                                                }
                                            }
                                            case 2 -> {
                                                try {
                                                   editSta(staArray, IdSearch, newBookQuantity,false,true);
                                                   writeStaToFile(staArray);
                                                } catch (IOException ex) {
                                                    System.out.println("Failed to Edit The Book Type");
                                                }
                                            }
                                            case 3 -> {
                                                try {
                                                    editSta(staArray, IdSearch, newBookQuantity,false,false);
                                                    writeStaToFile(staArray);
                                                } catch (IOException ex) {
                                                    System.out.println("Failed to Edit The Book Type");
                                                }
                                            }
                                            default -> {
                                            }
                                        }
                                        
                                    }else {
                                        System.out.println("Edit is denied");
                                    }
                                }
                            }
                        }
                        
                        
                    } while(toUpperCase(IdSearch.charAt(0))!='Q');
    }
    
    public void add(){
                
        char confirmChoice;
        ArrayList <Stationary> staArray= new ArrayList<>();
                
                try {
                    readStaFromFile(staArray);
                } catch (FileNotFoundException ex) {
                    System.out.println("Failed Read File");
                }
                do {
                    clearScreen();
                    
                    staId = generateStaId(staArray);
                    //prompt input
                    System.out.println("Stationary Id : " + staId);
                    
                    System.out.print("Enter Stationary Name : ");
                    name = inputString.nextLine();
                    
                    System.out.print("Enter Quantity :");
                    stockBalance = Validation.getIntegerInput();

                    System.out.print("Enter Stationary price : ");
                    unitPrice = input.nextDouble();
                    
                    System.out.print("Enter Stationary Sold price : ");
                    soldPrice = input.nextDouble();
                    
                    clearScreen();
                    
                    displayStaDetails();
                    
                    System.out.print("Comfirm [Y/N] > ");
                    confirmChoice = inputString.next().charAt(0);
                    
                    if (Validation.checkYesNo(confirmChoice)) {
                        
                        
                        switch (toUpperCase(confirmChoice)) {
                            case 'Y' -> {
                                addSta(staArray, new Stationary(staId,name,unitPrice,
                                        soldPrice,stockBalance,true));
                                
                                try {
                                    writeStaToFile(staArray);  
                                } catch (IOException ex) {
                                    Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public void remove(){
         String IdSearch,confirm;
    
         boolean notFound;
    
         int currentIndex = 0;
         
                ArrayList <Stationary> staArray = new ArrayList<> ();
                try {
                    readStaFromFile(staArray);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
                }do {
                    
                    notFound = true;

                    clearScreen();
                    System.out.println("Remove Stationary System");
                    System.out.println("========================");
                    
                    
                    System.out.print("Enter Stationart ID [Q to exit]> ");
                    IdSearch = inputString.nextLine();

                    for (int i = 0; i < staArray.size(); i++) {

                        if (IdSearch.equals(staArray.get(i).getStaId())) {
                            notFound = false;
                            displayBookDetails(staArray.get(i));
                            currentIndex = i; 
                            break;
                        }
                    }

                    if (notFound && toUpperCase(IdSearch.charAt(0))!='Q') {
                        
                        System.out.println("The Stationary Id Entered Does Not Exist.");
                        systemPause();
                        
                    }else if(!notFound && toUpperCase(IdSearch.charAt(0))!='Q'){

                        System.out.print("Confirm To Remove Stationary  ? [Y/N] >");
                        confirm = inputString.next();

                        if (toUpperCase(confirm.charAt(0)) == 'Y') {
                            removeSta(staArray,staArray.get(currentIndex));
                            try {
                                writeStaToFile(staArray);
                            } catch (IOException ex) {
                                Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            System.out.println("Succcesful Removed");
                            systemPause();
                        }
                    }
                    
                } while (toUpperCase(IdSearch.charAt(0)) != 'Q');
    }
}
