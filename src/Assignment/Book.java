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
     
    Author author = new Author();
    private  String bookId;
    private  char bookType;

    
    //private String startSellDate ;
    // language - can do report

    Book(){
        super("",0,0.0,0.0,false);
        this.author = null;
        bookId = "";
    }

    Book(String bookId, String name, int stockQuantity, double unitPrice, double soldPrice, boolean stockStatus,char bookType,Author author) {
        super(name, stockQuantity, unitPrice, soldPrice, stockStatus);
        this.bookId = bookId;
        this.bookType = bookType;
        this.author = author;
    }

    //setter
    public void setBookId(String bookId){
        this.bookId = bookId;
    }
    
    public void setBookType(char bookType){
        this.bookType = bookType;
    }
    
 
    //getter
    public  char getBookType(){
        return bookType;
    }

    public String getBookId(){
        return bookId;
    }
    
    //File Method
    public static void writeBookToFile(ArrayList<Book> bookArray) throws IOException{
        try ( FileWriter writeBookFile = new FileWriter("Book.txt")) {
            for (Book bookFromArray : bookArray) {
               
                writeBookFile.write(bookFromArray.getBookId()+'|'+bookFromArray.getName()+'|'+
                                    bookFromArray.getStockQuantity() + '|' + bookFromArray.getUnitPrice() +'|' + bookFromArray.getSoldPrice()+'|'+
                                    bookFromArray.isStockStatus()+'|'+bookFromArray.getBookType() +'|'+bookFromArray.author.getName() +'|'+bookFromArray.author.getAge() + '|'+ bookFromArray.author.checkArrive()+'\n');
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
                bookArray.add(new Book(data[0],data[1],Integer.parseInt(data[2]),Double.parseDouble(data[3]),
                        Double.parseDouble(data[4]),Boolean.parseBoolean(data[5]),data[6].charAt(0),
                        new Author(data[7],Integer.parseInt(data[8]),Boolean.parseBoolean(data[9]))));
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
            
            System.out.printf("%s   %s  %s  %d  RM%.2f  RM%.2f\n",allBook.getBookId(),allBook.getName(),specificBookType,
                              allBook.getStockQuantity(),allBook.getUnitPrice(),allBook.getSoldPrice());
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
                 latestBook.setName(newBookName);
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
                     latestBook.addStockQuantity(newBookQuantity);
                 }else if (sub) {
                     
                     int check = latestBook.getStockQuantity() - newBookQuantity;
                     
                     if (latestBook.isStockStatus() && check >= 0) {
                         latestBook.subStockQuantity(newBookQuantity);
                     }else{
                         System.out.println(RED+"Book Quantity Is Zero"+RESET);
                         Assignment.systemPause();
                     }
                     
                 }else {
                     latestBook.setStockQuantity(newBookQuantity);
                 }
             }     
        }
    }
    

 
    public static void displayBookDetails(Book book){
            System.out.println("====================================");
            System.out.println("|            Book Detail            |");
            System.out.println("====================================");
            System.out.println("| Book ID : " + book.bookId);
            System.out.println("| Book Name :  " + book.getName());
            System.out.println("| Book Type :  " + book.bookType);
            System.out.println("| Book Price :  " + book.getUnitPrice());
            System.out.println("| Book Sold Price :  " + book.getSoldPrice());
            System.out.println("| Book Total Added :  " + book.getStockQuantity());
            System.out.println("| Book Total Added (RM) :  " + (book.getUnitPrice() * book.getStockQuantity()));
            
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
        
        String idSearch  ,confirm;
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
            idSearch = Validation.getStringInput();


        if (!idSearch.isEmpty() && Character.toUpperCase(idSearch.charAt(0)) == 'Q') {         
                break;    

        }else{
            // find book pick
            for (int i = 0; i < bookArray.size(); i++) {

                if (idSearch.equals(bookArray.get(i).getBookId())) {

                    notFound = false;
                    displayBookDetails(bookArray.get(i));
                    bookArray.get(i).author.displayAuthorDetail();
                    currentIndex = i;
                    break;
                }

            }

            if (notFound) {                          
                System.out.println("The BookId Entered Does Not Exist.");
                Assignment.systemPause();

            }else if(!notFound ){

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
                        bookArray.get(currentIndex).setName(Validation.getStringInput());

                        System.out.print("Confirm To Edit Book Name ? [Y/N] >");
                        confirm = inputString.next();

                        if (toUpperCase(confirm.charAt(0)) == 'Y' && Validation.checkYesNo(confirm.charAt(0))) {
                            try {
                                editBook(bookArray, idSearch, bookArray.get(currentIndex).getName());
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
                                editBook(bookArray, idSearch, bookArray.get(currentIndex).getBookType());
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
                                editBook(bookArray, idSearch, bookArray.get(currentIndex).getSoldPrice());
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
                                        editBook(bookArray, idSearch, newBookQuantity,true,false);
                                        writeBookToFile(bookArray);
                                    } catch (IOException ex) {
                                        System.out.println(RED + "Failed to Edit The Book Type" +RESET);
                                    }

                                }
                                case 2 -> {
                                    try {
                                        editBook(bookArray, idSearch, newBookQuantity,false,true);
                                        writeBookToFile(bookArray);
                                    } catch (IOException ex) {
                                        System.out.println(RED + "Failed to Edit The Book Type" + RESET);
                                    }

                                }
                                case 3 -> {
                                    try {
                                        editBook(bookArray, idSearch, newBookQuantity,false,false);
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
                        bookArray.get(currentIndex).author.setName(Validation.getStringInput());

                        System.out.print("Confirm To Edit Author Name ? [Y/N] >");
                        confirm = inputString.next();

                        if (toUpperCase(confirm.charAt(0)) == 'Y' && Validation.checkYesNo(confirm.charAt(0))) {
                            try {
                                Author.editAuthor(bookArray, idSearch, bookArray.get(currentIndex).author.getName());
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
                                Author.editAuthor(bookArray, idSearch, bookArray.get(currentIndex).author.getAge());
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
                                   Author.editAuthor(bookArray, idSearch, false); 
                                }else{
                                  Author.editAuthor(bookArray, idSearch, false); 
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
        } 

        } while(toUpperCase(idSearch.charAt(0))!='Q');
    }
    
    public void add(){
       
        Author authorInput = new Author();
        Book book = new Book();
        char confirmChoice;
        
                ArrayList<Book> bookArray = new ArrayList<>();
                try {
                    readBookFromFile(bookArray);
                } catch (FileNotFoundException ex) {
                    System.out.println("File Error");
                }
        
                
                do {
                    
                    Assignment.clearScreen();
                    book.setBookId(generateBookId(bookArray));
                    
                    //prompt input
                    System.out.println("Book Id : " + book.getBookId());
                    
                    System.out.print("Enter Book Name : ");
                    book.setName(Validation.getStringInput());
                    
                  
                    System.out.print("Enter Quantity :");
                    book.setStockQuantity(Validation.getIntegerInput());
                    
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
                            book.setBookType('R');
                        }
                        
                        case 2 ->{
                            book.setBookType('H');
                        }
                        
                        case 3 ->{
                            book.setBookType('T');
                         
                        }
                        
                        case 4-> {
                            book.setBookType('F');
                            
                        }
                        
                        case 5 ->{
                            book.setBookType('E');
                           
                        }
                    }
                    
                    System.out.print("Enter Book price : ");
                    book.setUnitPrice(input.nextDouble());
                    
                    
                    System.out.print("Enter Book Sold price : ");
                    book.setSoldPrice(input.nextDouble());
                    
                    
                    Assignment.clearScreen();
                    
                    System.out.println("Book Author");
                    System.out.println("===========");
                    
                    System.out.print("Enter Author name > ");
                    authorInput.setName(Validation.getStringInput());
                    
                    System.out.print("Enter Author age > ");
                    authorInput.setAge(Validation.getIntegerInput());
                    
                    
                    System.out.print("Author Arrive ? [Y/N] > ");
                    confirmChoice = toUpperCase(inputString.next().charAt(0));
                    
                    
                    if (confirmChoice == 'Y' && Validation.checkYesNo(confirmChoice)) {
                        authorInput.setArrive(true);
                    }else if(confirmChoice == 'N' && Validation.checkYesNo(confirmChoice)) {
                        authorInput.setArrive(false);
                    }
                    
                    displayBookDetails(book);
                    
                    System.out.print("Comfirm [Y/N] > "); 
                    confirmChoice = inputString.next().charAt(0);
                    
                    if (Validation.checkYesNo(confirmChoice)) {
                       
                        switch (toUpperCase(confirmChoice)) {
                            case 'Y' -> {
                                addBook(bookArray,new Book(book.getBookId(),book.getName(),book.getStockQuantity()
                                        ,book.getUnitPrice(),book.getSoldPrice(),true,book.getBookType(),
                                        new Author(authorInput.getName(),authorInput.getAge(),authorInput.checkArrive())));
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
                        Validation.getStringInput();
                        
                    }else{
                        System.out.println("Wrong Input");
                    }
                    
                } while (toUpperCase(confirmChoice) != 'N');
    }
    
    public void remove(){
        String IdSearch ,confirm;
        boolean notFound = true;
        int currentIndex = 0;
    
        ArrayList<Book> bookArray = new ArrayList<>();
        try {      
            readBookFromFile(bookArray);
        }
        catch (FileNotFoundException ex) {
            System.out.println("Failed to read book record");
        }
    
        do {

         if (!bookArray.isEmpty()) {
            
            notFound =true;
            Assignment.clearScreen();
            System.out.println("Remove book  System");
            System.out.println("====================");


            System.out.print("Enter BookID [Q to exit]> ");
            IdSearch = Validation.getStringInput();
            
        if (!IdSearch.isEmpty() && Character.toUpperCase(IdSearch.charAt(0)) == 'Q') {         
            break;    
            
        }else{
            for (int i = 0; i < bookArray.size(); i++) {

                if (IdSearch.equals(bookArray.get(i).getBookId())) {
                    notFound = false;
                    displayBookDetails(bookArray.get(i));
                    currentIndex = i; 
                    break;
                }
            }

            if (notFound ) {

                System.out.println("The BookId Entered Does Not Exist.");
                Assignment.systemPause();

            }else if(!notFound){

                System.out.print("Confirm To Remove Book  ? [Y/N] >");
                confirm = inputString.next();

                
                
                if (toUpperCase(confirm.charAt(0)) == 'Y') {
                    removeBook(bookArray,bookArray.get(currentIndex));
                    
                    try {
                        writeBookToFile(bookArray);
                    } catch (IOException ex) {
                        Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
                    }


                    System.out.println("Succesful Removed");
                    Assignment.systemPause();
                }
            }
        }
            }else{
                Assignment.clearScreen();
                System.out.println("Book List Is Empty !");
                Assignment.systemPause();
                IdSearch = "Q";
            }
        
            
        } while (toUpperCase(IdSearch.charAt(0)) != 'Q');
        
    }
    
    @Override
    public String toString(){
        
        return String.format("%s  %s  %s %s",bookId,
                     super.toString(),convertBookType(bookType),author.toString() ); 
    }
    
    //check stock balance 
    public static void checkAvailable(ArrayList<Book> bookArray){
        for (Book book:bookArray) {
            if (book.getStockQuantity() <= 0) {
                book.setStockStatus(false);
            }else if (book.getStockQuantity() > 0) {
                book.setStockStatus(true);
            }
        }
    }
}
