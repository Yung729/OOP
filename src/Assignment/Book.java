/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

import static Assignment.Assignment.RED;
import static Assignment.Assignment.RESET;
import static Assignment.Stock.input;
import static Assignment.Stock.inputString;
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

public class Book extends Stock {
     
    Author author;
    private  String bookId;
    private  String bookName;
    private  char bookType;
    private  int bookBalance;
    private  double unitPrice;
    private  double soldPrice;
    private boolean bookStatus;
    
    //private String startSellDate ;
    // language - can do report
      
    Book(Author author,String bookId, String bookName, char bookType, int bookBalance, double unitPrice,double soldPrice,boolean bookStatus){
       
        this.author = author;
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookType = bookType;
        this.bookBalance = bookBalance;
        this.unitPrice = unitPrice;
        this.soldPrice = soldPrice;
        this.bookStatus = bookStatus;
        
        
        //startSellDate = formatter.format(java.time.LocalDate.now());
    }

    Book(){
        author = null;
        bookId = "";
        bookName = "";
        bookType= 'N';
        bookBalance = 0;
        unitPrice =0.0;
        soldPrice =0.0;
        bookStatus = true; 
    }
    
    //setter
    public void setBookId(String bookId){
        this.bookId = bookId;
    }
    
    public void setBookName(String bookName){
        this.bookName = bookName;
    }
    
    public void setBookType(char bookType){
        this.bookType = bookType;
    }
    
    public void setBookBalance(int bookBalance){
        this.bookBalance = bookBalance;
    }
    
 
    public void setUnitPrice(double unitPrice){
        this.unitPrice = unitPrice;
    }
    
    public void setSoldPrice(double soldPrice){
        this.soldPrice = soldPrice;
    }
    
    public void setBookStatus(boolean bookStatus){
        this.bookStatus = bookStatus;
    }
 
    //getter
    public  String getBookName(){
        return bookName;
    }
    
    public  char getBookType(){
        return bookType;
    }
    
    public  int getBookBalance(){
        return bookBalance;
    }
    
    public double getUnitPrice(){
        return unitPrice;
    }
    
    public double getSoldPrice(){
        return soldPrice;
    }
    
    public String getBookId(){
        return bookId;
    }
    
    public boolean getBookStatus(){
        return bookStatus;
    }
    

    
    //File Method
    public static void writeBookToFile(ArrayList<Book> bookArray) throws IOException{
        try ( FileWriter writeBookFile = new FileWriter("Book.txt")) {
            for (Book bookFromArray : bookArray) {
               
                writeBookFile.write(bookFromArray.author.getName() +'|'+
                                    bookFromArray.author.getAge() + '|'+ bookFromArray.author.checkArrive()+'|'+ 
                                    bookFromArray.getBookId()+'|'+bookFromArray.getBookName()+'|'+ bookFromArray.getBookType() + '|'+ 
                                    bookFromArray.getBookBalance() + '|' + bookFromArray.getUnitPrice() +'|' + bookFromArray.getSoldPrice()+'|'+
                                    bookFromArray.getBookStatus()+'\n');
            }
        }
        System.out.println("Successfully wrote to the file.\n");
    }
    
    public static void readBookFromFile(ArrayList<Book> bookArray) throws FileNotFoundException{
        File readBookFile = new File("Book.txt");
        
        if (readBookFile.exists()) {
           Scanner productRead = new Scanner(readBookFile);
            while (productRead.hasNextLine()) {
                String line = productRead.nextLine();
                String[] data = line.split("\\|");
                bookArray.add(new Book(new Author(data[0],Integer.parseInt(data[1]),Boolean.parseBoolean(data[2])),
                        data[3],data[4],data[5].charAt(0),Integer.parseInt(data[6]),
                        Double.parseDouble(data[7]),Double.parseDouble(data[8]),
                                Boolean.parseBoolean(data[9])));
            } 
        }else {
            File createProductFile = new File("Book.txt");
            try {
                createProductFile.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Stationary.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("File created : " + createProductFile.getName() + "\n");
        }
            
          
        
    }
         
    //other method
    public void addBookBalance(int bookQuantity){
        this.bookBalance += bookQuantity;
    }
    
    public void subBookBalance(int bookQuantity){
        
        this.bookBalance -= bookQuantity;
    }
    
    public String generateBookId(ArrayList<Book> bookArray){
        String bookIdGenerated;
       
        if  (bookArray.isEmpty()) {
                bookIdGenerated = "B0001";
        }else{
            bookIdGenerated = bookArray.get(bookArray.size() - 1).getBookId();

            int bufferBookIdNum = Integer.parseInt(bookIdGenerated.replaceAll("\\D+", ""));

            bookIdGenerated = String.format("B%04d", bufferBookIdNum + 1);
        }
        
        return bookIdGenerated;
    }
    
    public void displayAllRecord(ArrayList<Book> bookArray){
      String specificBookType = "Non Type";
       
        for (Book allBook: bookArray) {
           
            
            switch(allBook.getBookType()){
                case 'R'->{
                    specificBookType = "Romantic";
                }
                case 'H'->{
                    specificBookType = "Horror";
                }
                case 'T'->{
                    specificBookType = "Historical fiction";
                }
                case 'F'->{
                    specificBookType = "Fiction";
                }
                case 'E'->{
                    specificBookType = "Education";
                }
                
                         
            }
            
            System.out.printf("%s   %s  %s  %d  RM%.2f  RM%.2f\n",allBook.getBookId(),allBook.getBookName(),specificBookType,
                              allBook.getBookBalance(),allBook.getUnitPrice(),allBook.getSoldPrice());
        }
    }
    
    public String convertBookType(char bookType){
    
        String specificBookType = "Non Type";
        switch(bookType){
                case 'R'->{
                    specificBookType = "Romantic";
                }
                case 'H'->{
                    specificBookType = "Horror";
                }
                case 'T'->{
                    specificBookType = "Historical fiction";
                }
                case 'F'->{
                    specificBookType = "Fiction";
                }
                case 'E'->{
                    specificBookType = "Education";
                }
                
                         
        }
        
        return specificBookType;
    
    }
    
    public static void addBook(ArrayList<Book> bookArray, Book Book) {
        bookArray.add(Book);
    }
    
    public void removeBook(ArrayList<Book> bookArray,Book Book){
        bookArray.remove(Book);
    }

    // overloading method of editBook 
    public static void editBook(ArrayList<Book> bookArray,String searchBookId,String newBookName){
         for (Book latestBook: bookArray) {
          if (latestBook.getBookId().equals(searchBookId)) {
                 latestBook.setBookName(newBookName);
           }     
            
        }
    }
    
    
    public static void editBook(ArrayList<Book> bookArray,String searchBookId,char newBookType){
         for (Book latestBook: bookArray) {
             if (latestBook.getBookId().equals(searchBookId)) {
                 latestBook.setBookType(newBookType);
             }     
        }
    }

    public static void editBook(ArrayList<Book> bookArray,String searchBookId,double newBookPrice){
         for (Book latestBook: bookArray) {
             if (latestBook.getBookId().equals(searchBookId)) {
                 latestBook.setSoldPrice(newBookPrice);
             }     
        }
    }

    public static void editBook(ArrayList<Book> bookArray,String searchBookId,int newBookQuantity,boolean add,boolean sub){
         for (Book latestBook: bookArray) {
             if (latestBook.getBookId().equals(searchBookId)) {
                 if (add) {
                     latestBook.addBookBalance(newBookQuantity);
                 }else if (sub) {
                     
                     int check = latestBook.getBookBalance() - newBookQuantity;
                     
                     if (latestBook.getBookStatus() && check >= 0) {
                         latestBook.subBookBalance(newBookQuantity);
                     }else{
                         System.out.println(RED+"Book Quantity Is Zero"+RESET);
                         Assignment.systemPause();
                     }
                     
                 }else {
                     latestBook.setBookBalance(newBookQuantity);
                 }
             }     
        }
    }
    
    public void displayBookDetails(){
            System.out.println("====================================");
            System.out.println("|            Book Detail            |");
            System.out.println("====================================");
            System.out.println("| Book ID : " + bookId);
            System.out.println("| Book Name :  " + bookName);
            System.out.println("| Book Type :  " + bookType);
            System.out.println("| Book Price :  " + unitPrice);
            System.out.println("| Book Sold Price :  " + soldPrice);
            System.out.println("| Book Total Added :  " + bookBalance);
            System.out.println("| Book Total Added (RM) :  " + (unitPrice * bookBalance));

            
    }
 
    public static void displayBookDetails(Book book){
            System.out.println("====================================");
            System.out.println("|            Book Detail            |");
            System.out.println("====================================");
            System.out.println("| Book ID : " + book.getBookId());
            System.out.println("| Book Name :  " + book.getBookName());
            System.out.println("| Book Type :  " + book.getBookType());
            System.out.println("| Book Price :  " + book.getUnitPrice());
            System.out.println("| Book Sold Price :  " + book.getSoldPrice());
            System.out.println("| Book Total Added :  " + book.getBookBalance());
            System.out.println("| Book Total Added (RM) :  " + (book.getUnitPrice() * book.getBookBalance()));
            
    }
    
    public void display(){
                    
        Assignment.clearScreen();
        ArrayList<Book> bookArray = new ArrayList<>();

        System.out.println("Display All Book");
        System.out.println("Author Name Age Status  BookId  BookName    Type    Quantity    Unit Price  Sold Price  Book Status");
        System.out.println("===================================================================================================");
        try {
            readBookFromFile(bookArray);
        } catch (FileNotFoundException ex) {
            System.out.println("Failed to Read File.");
        }

        for (Book bookDisplay: bookArray) {
            System.out.println(bookDisplay);         
        }
        
        Assignment.systemPause();
    }
    
    public void adjust(){
        
        String IdSearch ,confirm;
        int choice , newBookQuantity, currentIndex = 0;
        boolean notFound ;
        
                    ArrayList<Book> bookArray = new ArrayList<>();
                    try {
                        readBookFromFile(bookArray);
                    }
                    catch (FileNotFoundException ex) {
                        System.out.println("Failed to read book record");
                    }  
                    
                    do{
                        notFound = true;
                        Assignment.clearScreen();
                                
                        checkAvailable(bookArray);
                        System.out.println("Edit Book Name");
                        System.out.println("==============");
                        System.out.print("Enter BookID [Q to exit]> ");
                        IdSearch = input.nextLine();
                        
                        // find book pick
                        for (int i = 0; i < bookArray.size(); i++) {
                            
                            if (IdSearch.equals(bookArray.get(i).getBookId())) {
                                
                                notFound = false;
                                displayBookDetails(bookArray.get(i));
                                Author.displayAuthorDetail(bookArray.get(i));
                                currentIndex = i;
                                break;
                            }
                            
                        }
                        
                        
                        
                        if (notFound && toUpperCase(IdSearch.charAt(0))!='Q') {
                            
                            System.out.println("The BookId Entered Does Not Exist.");
                            Assignment.systemPause();
                            
                        }else if(!notFound && toUpperCase(IdSearch.charAt(0))!='Q'){
                            
                            System.out.println("Option");
                            System.out.println("=========================");
                            System.out.println("1. Book Name");
                            System.out.println("2. Book Type");
                            System.out.println("3. Book Price");
                            System.out.println("4. Book Quantity");
                            System.out.println("5. Author Name");
                            System.out.println("6. Author Year Of Birth");
                            System.out.println("7. Author Status");
                            System.out.println("0. Stop Edit");
                            System.out.println("=========================");
                            
                            System.out.print("Enter Field to Edit [1-4] >");
                            choice = Validation.getIntegerInput();
                            
                            switch(choice){
                                
                                case 1 -> {
                                    System.out.println("Edit Book Name");
                                    System.out.println("==============");
                                    System.out.print("Enter Book Name :");
                                    bookArray.get(currentIndex).setBookName(inputString.nextLine());
                                    
                                    System.out.print("Confirm To Edit Book Name ? [Y/N] >");
                                    confirm = inputString.next();
                                    
                                    if (toUpperCase(confirm.charAt(0)) == 'Y' && Validation.checkYesNo(confirm.charAt(0))) {
                                        try {
                                            editBook(bookArray, IdSearch, bookArray.get(currentIndex).getBookName());
                                            writeBookToFile(bookArray);
                                        } catch (IOException ex) {
                                            System.out.println("Failed to Edit The Book Name");
                                        }
                                    }else {
                                        System.out.println(RED + "Edit is denied");
                                    }
                                }
                                
                                case 2 -> {
                                    System.out.println("Edit Book Type");
                                    System.out.println("==============");
                                    
                                    System.out.print("Enter Book Type :");
                                    bookArray.get(currentIndex).setBookType(inputString.next().charAt(0));
                                    
                                    System.out.print("Confirm To Edit Book Type ? [Y/N] >");
                                    confirm = inputString.next();
                                    
                                    if (toUpperCase(confirm.charAt(0)) == 'Y' && Validation.checkYesNo(confirm.charAt(0))) {
                                        try {
                                            editBook(bookArray, IdSearch, bookArray.get(currentIndex).getBookType());
                                            writeBookToFile(bookArray);
                                        } catch (IOException ex) {
                                            System.out.println("Failed to Edit The Book Type");
                                        }
                                    }else {
                                        System.out.println(RED + "Edit is denied");
                                    }
                                }
                                
                                case 3 ->{
                                    System.out.println("Edit Book Price");
                                    System.out.println("==============");
                                    System.out.print("Enter Book Price :");
                                    bookArray.get(currentIndex).setSoldPrice(input.nextDouble());
                                    
                                    System.out.print("Confirm To Edit Book Price ? [Y/N] >");
                                    confirm = inputString.next();
                                    
                                    if (toUpperCase(confirm.charAt(0)) == 'Y' && Validation.checkYesNo(confirm.charAt(0))) {
                                        try {
                                            editBook(bookArray, IdSearch, bookArray.get(currentIndex).getSoldPrice());
                                            writeBookToFile(bookArray);
                                        } catch (IOException ex) {
                                            System.out.println("Failed to Edit The Book Type");
                                        }         
                                    }else {
                                        System.out.println(RED + "Edit is denied");
                                    }
                                }
                                
                                case 4 -> {
                                    System.out.println("Edit Book Quantity");
                                    System.out.println("==================");
                                    System.out.println("1. Add Book Quantity");
                                    System.out.println("2. Sub Book Quantity");
                                    System.out.println("3. Set Book Quantity");
                                    
                                    System.out.print("Enter your Choice > ");
                                    choice = Validation.getIntegerInput();
                                    
                                    
                                    System.out.print("Enter Book Quantiy >");
                                    newBookQuantity = Validation.getIntegerInput();
                                    
                                    
                                    System.out.print("Confirm To Edit Book Quantity ? [Y/N] >");
                                    confirm = inputString.next();
                                    
                                    if (toUpperCase(confirm.charAt(0)) == 'Y' && Validation.checkYesNo(confirm.charAt(0))) {
                                        switch (choice) {
                                            case 1 -> {
                                                try {
                                                    editBook(bookArray, IdSearch, newBookQuantity,true,false);
                                                    writeBookToFile(bookArray);
                                                } catch (IOException ex) {
                                                    System.out.println(RED + "Failed to Edit The Book Type" +RESET);
                                                }
                                               
                                            }
                                            case 2 -> {
                                                try {
                                                    editBook(bookArray, IdSearch, newBookQuantity,false,true);
                                                    writeBookToFile(bookArray);
                                                } catch (IOException ex) {
                                                    System.out.println(RED + "Failed to Edit The Book Type" + RESET);
                                                }
                                                
                                            }
                                            case 3 -> {
                                                try {
                                                    editBook(bookArray, IdSearch, newBookQuantity,false,false);
                                                    writeBookToFile(bookArray);
                                                } catch (IOException ex) {
                                                    System.out.println(RED + "Failed to Edit The Book Type" + RESET);
                                                }
                                            }
                                            default -> {
                                            }
                                        }
                                        
                                    }else {
                                        System.out.println(RED +"Edit is denied" + RESET);
                                    }
                                }
                                
                                case 5->{
                                    System.out.println("Edit Author Name");
                                    System.out.println("==================");
                                    System.out.print("Enter Book Name :");
                                    bookArray.get(currentIndex).author.setName(inputString.nextLine());
                                    
                                    System.out.print("Confirm To Edit Author Name ? [Y/N] >");
                                    confirm = inputString.next();
                                    
                                    if (toUpperCase(confirm.charAt(0)) == 'Y' && Validation.checkYesNo(confirm.charAt(0))) {
                                        try {
                                            Author.editAuthor(bookArray, IdSearch, bookArray.get(currentIndex).author.getName());
                                            writeBookToFile(bookArray);
                                        } catch (IOException ex) {
                                            System.out.println("Failed to Edit The Book Name");
                                        }
                                    }else {
                                        System.out.println(RED + "Edit is denied");
                                    }
                                }
                                case 6->{
                                    System.out.println("Edit Author Birth");
                                    System.out.println("==================");
                                    System.out.print("Enter Year Of Birth :");
                                    
                                    bookArray.get(currentIndex).author.setAge(inputString.nextInt());
                                    
                                    System.out.print("Confirm To Edit Author Birth ? [Y/N] >");
                                    confirm = inputString.next();
                                    
                                    if (toUpperCase(confirm.charAt(0)) == 'Y' && Validation.checkYesNo(confirm.charAt(0))) {
                                        try {
                                            Author.editAuthor(bookArray, IdSearch, bookArray.get(currentIndex).author.getAge());
                                            writeBookToFile(bookArray);
                                        } catch (IOException ex) {
                                            System.out.println("Failed to Edit The Book Name");
                                        }
                                    }else {
                                        System.out.println(RED + "Edit is denied");
                                    }
                                }
                                case 7->{
                                    System.out.println("Edit Author Status");
                                    System.out.println("==================");
                                    System.out.println("Current status : " + bookArray.get(currentIndex).author.checkArrive());
                                    
                                    System.out.print("Confirm To Edit Book Name ? [Y/N] >");
                                    confirm = inputString.next();
                                    
                                    if (toUpperCase(confirm.charAt(0)) == 'Y' && Validation.checkYesNo(confirm.charAt(0))) {
                                        try {
                                            if (bookArray.get(currentIndex).author.checkArrive()) {
                                               Author.editAuthor(bookArray, IdSearch, false); 
                                            }else{
                                              Author.editAuthor(bookArray, IdSearch, false); 
                                            }
                                            writeBookToFile(bookArray);
                                        } catch (IOException ex) {
                                            System.out.println("Failed to Edit The Book Name");
                                        }
                                    }else {
                                        System.out.println(RED + "Edit is denied");
                                    }
                                }
                                
                                case 0->{}
                                
                                
                                default -> {
                                    System.out.println(RED + "INVALID INPUT" + RESET);
                                }
                            }
                        }
                        
                        
                    } while(toUpperCase(IdSearch.charAt(0))!='Q');
    }
    
    public void add(){
       
        
        char confirmChoice;
        
                ArrayList<Book> bookArray = new ArrayList<>();
                try {
                    readBookFromFile(bookArray);
                } catch (FileNotFoundException ex) {
                    System.out.println("File Error");
                }
        
        
                do {
                    Assignment.clearScreen();
                    bookId = generateBookId(bookArray);
                    
                    //prompt input
                    System.out.println("Book Id : " + bookId);
                    
                    System.out.print("Enter Book Name : ");
                    
                    bookName = inputString.nextLine();
                    
                    
                    System.out.print("Enter Quantity :");
                    bookBalance = Validation.getIntegerInput();
                    
                    System.out.println("Select Book Type :");
                    System.out.println("==================");
                    System.out.println("1. Romantic");
                    System.out.println("2. Horror");
                    System.out.println("3. Historical");
                    System.out.println("4. Fiction");
                    System.out.println("5. Education");
                    System.out.println("==================");
                    
                    System.out.print("Enter your choice [1-5] > ");
                    
                    int choice = Validation.getIntegerInput();
                    
                    switch(choice){
                        case 1 -> {
                            bookType = 'R';
                        }
                        
                        case 2 ->{
                            bookType = 'H';
                        }
                        
                        case 3 ->{
                            bookType = 'T';
                        }
                        
                        case 4-> {
                            bookType = 'F';
                        }
                        
                        case 5 ->{
                            bookType = 'E';
                        }
                    }
                    
                    System.out.print("Enter Book price : ");
                    unitPrice = input.nextDouble();
                    
                    System.out.print("Enter Book Sold price : ");
                    soldPrice = input.nextDouble();
                    
                    Assignment.clearScreen();
                    
                    System.out.println("Book Author");
                    System.out.println("===========");
                    
                    System.out.print("Enter Author name > ");
                    author.setName(inputString.nextLine());
                    
                    System.out.print("Enter Author age > ");
                    author.setAge(Validation.getIntegerInput());
                    
                    
                    System.out.print("Author Arrive ? [Y/N] > ");
                    confirmChoice = toUpperCase(inputString.next().charAt(0));
                    
                    
                    if (confirmChoice == 'Y' && Validation.checkYesNo(confirmChoice)) {
                        author.setArrive(true);
                    }else if(confirmChoice == 'N' && Validation.checkYesNo(confirmChoice)) {
                        author.setArrive(false);
                    }
                    
                    displayBookDetails();
                    
                    System.out.print("Comfirm [Y/N] > "); 
                    confirmChoice = inputString.next().charAt(0);
                    
                    if (Validation.checkYesNo(confirmChoice)) {
                       
                        switch (toUpperCase(confirmChoice)) {
                            case 'Y' -> {
                                addBook(bookArray,new Book(new Author(author.getName(),author.getAge(),author.checkArrive()),bookId,bookName,bookType,bookBalance
                                        ,unitPrice,soldPrice,true));
                                try {
                                    writeBookToFile(bookArray);
                                } catch (IOException ex) {
                                    System.out.println("Failed To Write into File");
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
        boolean notFound = false;
        int currentIndex = 0;
    
        ArrayList<Book> bookArray = new ArrayList<>();
        try {      
            readBookFromFile(bookArray);
        }

        catch (FileNotFoundException ex) {
            System.out.println("Failed to read book record");

        }
        do {

            Assignment.clearScreen();
            System.out.println("Remove book  System");
            System.out.println("====================");


            System.out.print("Enter BookID [Q to exit]> ");
            IdSearch = inputString.nextLine();

            for (int i = 0; i < bookArray.size(); i++) {

                if (IdSearch.equals(bookArray.get(i).getBookId())) {
                    notFound = false;
                    displayBookDetails(bookArray.get(i));
                    currentIndex = i; 
                    break;
                }
            }

            if (notFound && toUpperCase(IdSearch.charAt(0))!='Q') {

                System.out.println("The BookId Entered Does Not Exist.");
                Assignment.systemPause();

            }else if(!notFound && toUpperCase(IdSearch.charAt(0))!='Q'){

                System.out.print("Confirm To Remove Book  ? [Y/N] >");
                confirm = inputString.next();

                if (toUpperCase(confirm.charAt(0)) == 'Y') {
                    removeBook(bookArray,bookArray.get(currentIndex));
                    try {
                        writeBookToFile(bookArray);
                    } catch (IOException ex) {
                        Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
                    }


                    System.out.println("Succcesful Removed");
                    Assignment.systemPause();
                }
            }

        } while (toUpperCase(IdSearch.charAt(0)) != 'Q');
    }
    
    @Override
    public String toString(){
        
        return String.format("%s    %s  %s  %s  %d  RM%.2f  RM%.2f",author.toString() ,bookId,bookName,convertBookType(bookType),
                     bookBalance,unitPrice,soldPrice); 
    }
    
    //check stock balance 
    public static void checkAvailable(ArrayList<Book> bookArray){
        for (Book book:bookArray) {
            if (book.bookBalance <= 0) {
                book.bookStatus = false;
            }else if (book.bookBalance > 0) {
                book.bookStatus = true;
            }
        }
    }
}
