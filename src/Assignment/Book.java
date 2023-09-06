/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

import static Assignment.Assignment.RED;
import static Assignment.Assignment.RESET;
import static Assignment.Assignment.input;
import static Assignment.Stationary.inputString;
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
    private String bookId;
    private char bookType;


  
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
                                    bookFromArray.isStockStatus()+'|'+bookFromArray.getBookType() +'|'+bookFromArray.author.getName() +'|'+bookFromArray.author.getYearOfBirth() + 
                        '|'+ bookFromArray.author.checkArrive()+'\n');
            }
        }
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
    
        String specificBookType = null;
        switch(bookType){
                case 'R'->{
                    specificBookType = "Romantic";
                }
                case 'H'->{
                    specificBookType = "Horror";
                }
                case 'T'->{
                    specificBookType = "Historical";
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
            System.out.println("| Author Name: "+book.author.getName());
            System.out.println("| Author YOB: "+book.author.getYearOfBirth());
            System.out.println("| Author Status: "+book.author.checkArrive());
            System.out.println("| Book Price :  " + book.getUnitPrice());
            System.out.println("| Book Sold Price :  " + book.getSoldPrice());
            System.out.println("| Book Total Added :  " + book.getStockQuantity());
            System.out.println("| Book Total Added (RM) :  " + (book.getUnitPrice() * book.getStockQuantity()));
            
    }
    
    @Override
    public void display(){
        double totalUnitPrice=0.0 ,totalSoldPrice=0.0 ;  
        int quantity = 0,count=0;
        Assignment.clearScreen();
        ArrayList<Book> bookArray = new ArrayList<>();

        System.out.println("Display All Book");
        System.out.printf("%-11s %-28s    %-8s    %-6s    %-9s    %-10s    %-10s    %-10s    %-10s    %-10s\n","Book Id","BookName","Quantity","Unit Price","Sold Price","Book Status","Type",
                "Author Name","YearOfBirth","status");
        System.out.println("============================================================================================================================================================");
        
        try {
            readBookFromFile(bookArray);
        } catch (FileNotFoundException ex) {
            System.out.println("Failed to Read File.");
        }

        for (Book bookDisplay: bookArray) {
            System.out.println(bookDisplay);     
            quantity += bookDisplay.getStockQuantity();
            totalUnitPrice += (bookDisplay.getStockQuantity() * bookDisplay.getUnitPrice());
            totalSoldPrice +=  (bookDisplay.getStockQuantity() * bookDisplay.getSoldPrice());
            count++;
        }
        
        System.out.println("\nTotal Book :" + count +"\nTotal Quantity :" + quantity +"\nTotal UnitPrice :" +totalUnitPrice +"\nTotal SoldPrice:" + totalSoldPrice );
        Assignment.systemPause();
    }
    
    @Override
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

            if (notFound && Character.toUpperCase(idSearch.charAt(0)) != 'Q') {                          
                System.out.println("The BookId Entered Does Not Exist.");
                Assignment.systemPause();

            }else if(!notFound && Character.toUpperCase(idSearch.charAt(0)) != 'Q' ){

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

                System.out.print("Enter Field to Edit [1-7] >");
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

                        bookArray.get(currentIndex).author.setYearOfBirth(inputString.nextInt());

                        System.out.print("Confirm To Edit Author Birth ? [Y/N] >");
                        confirm = inputString.next();

                        if (toUpperCase(confirm.charAt(0)) == 'Y' && Validation.checkYesNo(confirm.charAt(0))) {
                            try {
                                Author.editAuthor(bookArray, idSearch, bookArray.get(currentIndex).author.getYearOfBirth());
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
        

        } while(toUpperCase(idSearch.charAt(0))!='Q');
    }
    
    @Override
    public void add(){
       
        Author authorInput = new Author();
        Book book = new Book();
        int choice;
        char confirmChoice;
        boolean valid;
        
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
                  
                    do {
                        System.out.print("Enter Book Name : ");                 
                        book.setName(Validation.getStringInput());
                        
                        if (!validName(bookArray,book.getName())) {
                            System.out.println(RED +"Book Name Repeated or Too Short" + RESET);
                        }
                        
                    } while (!validName(bookArray,book.getName()));

                    
                    do {
                        System.out.print("Enter Quantity :");
                        book.setStockQuantity(Validation.getIntegerInput());
                        
                        if (!validQuantity(book.getStockQuantity())) {
                            System.out.println(RED +"Book Quantity Must At Least one" + RESET);
                        }
                        
                    } while (!validQuantity(book.getStockQuantity()));
      
                    System.out.println("Select Book Type :");
                    System.out.println("==================");
                    System.out.println("1. Romantic");
                    System.out.println("2. Horror");
                    System.out.println("3. Historical");
                    System.out.println("4. Fiction");
                    System.out.println("5. Education");
                    System.out.println("==================");
                    
                    do {

                    System.out.print("Enter your choice [1-5] > ");            
                    choice = Validation.getIntegerInput();
                    
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
                        
                        default ->{
                            System.out.println(RED +"Book Tyoe Select Range [1-5] Only" + RESET);
                        }
                    }
                    
                    } while (choice == -1);
                    
                    
                    
                    do {
                        System.out.print("Enter Book price : ");
                        book.setUnitPrice(Validation.getDoubleInput());
                        
                        if (!validUnitPrice(book.getUnitPrice())) {
                            System.out.println(RED +"Book Price Must more than RM 0 " + RESET);
                        }
                        
                    } while (!validUnitPrice(book.getUnitPrice()));
                    
                    
                    do {
                        System.out.print("Enter Book Sold price : ");
                        book.setSoldPrice(Validation.getDoubleInput());
                        
                        if (!validSoldPrice(book.getSoldPrice(),book.getUnitPrice())) {
                            System.out.println(RED +"Book Price Less Than Unit Price " + RESET);
                        }
                        
                    } while (!validSoldPrice(book.getSoldPrice(),book.getUnitPrice()));
                 
                    Assignment.clearScreen();
                    
                    System.out.println("Book Author");
                    System.out.println("===========");
                    
                    do {
                        System.out.print("Enter Author name > ");
                        authorInput.setName(Validation.getStringInput());
                        
                        if (!authorInput.validAuthorName(authorInput.getName())) {
                            System.out.println(RED+"Author Name Invalid" + RESET);
                        }
                        
                    } while (!authorInput.validAuthorName(authorInput.getName()));
                    
                    
                    
                    do {
                        System.out.print("Enter Author YOB > ");
                        authorInput.setYearOfBirth(Validation.getIntegerInput());
                        if (!authorInput.validAuthorYearOfBirth(authorInput.getYearOfBirth())) {
                            System.out.println(RED+ "Invalid Year Of Birth" + RESET);
                        }
                    } while (!authorInput.validAuthorYearOfBirth(authorInput.getYearOfBirth()));
                    
                                        
                    do {
                        System.out.print("Author Arrive ? [Y/N] > ");
                        confirmChoice = toUpperCase(inputString.next().charAt(0));

                        valid = Validation.checkYesNo(confirmChoice);
                        if (!valid) {
                            System.out.println(RED +"Input Invalid Must 'Y'/'N'" + RESET);
                        }
                        
                    } while (!valid);
                    
                    
                    if (confirmChoice == 'Y' && Validation.checkYesNo(confirmChoice)) {
                        authorInput.setArrive(true);
                    }else if(confirmChoice == 'N' && Validation.checkYesNo(confirmChoice)) {
                        authorInput.setArrive(false);
                    }
                    Assignment.clearScreen();
                    displayBookDetails(book);
                    
                    do {
                         System.out.print("Comfirm [Y/N] > "); 
                         confirmChoice = toUpperCase(inputString.next().charAt(0));

                        valid = Validation.checkYesNo(confirmChoice);
                        if (!valid) {
                            System.out.println(RED +"Input Invalid Must 'Y'/'N'" + RESET);
                        }
                        
                    } while (!valid);
                    
                   
                    
                    if (Validation.checkYesNo(confirmChoice)) {
                       
                        switch (toUpperCase(confirmChoice)) {
                            case 'Y' -> {
                                addBook(bookArray,new Book(book.getBookId(),book.getName(),book.getStockQuantity()
                                        ,book.getUnitPrice(),book.getSoldPrice(),true,book.getBookType(),
                                        new Author(authorInput.getName(),authorInput.getYearOfBirth(),authorInput.checkArrive())));
                                try {
                                    writeBookToFile(bookArray);
                                } catch (IOException ex) {
                                    System.out.println("Failed To Write into File");
                                }
                                
                                try {
                                    StockFlowReport.writeStockToFile(book.getBookId(),book.getStockQuantity(),book.getStockAddDate());
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
        
        String IdSearch ,confirm;
        boolean notFound;
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
            
    
            for (int i = 0; i < bookArray.size(); i++) {

                if (IdSearch.equals(bookArray.get(i).getBookId())) {
                    notFound = false;
                    displayBookDetails(bookArray.get(i));
                    currentIndex = i; 
                    break;
                }
            }

            if (notFound && Character.toUpperCase(IdSearch.charAt(0)) != 'Q') {

                System.out.println("The BookId Entered Does Not Exist.");
                Assignment.systemPause();

            }else if(!notFound  && Character.toUpperCase(IdSearch.charAt(0)) != 'Q'){

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
         
         }else{
                Assignment.clearScreen();
                System.out.println("Book List Is Empty !");
                Assignment.systemPause();
                IdSearch = "Q";
            }
        
            
        } while (toUpperCase(IdSearch.charAt(0)) != 'Q');
        
    }
    
    @Override
    public void search(){
        
        String search;
        boolean notFound = true;
        int choice;
         
        ArrayList <Book> bookArray = new ArrayList<> ();
        try {
            readBookFromFile(bookArray);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        do {
            System.out.println("1. Search By Id");
            System.out.println("2. Search By Name");
            System.out.println("0. Exit");
            System.out.print("Enter your Choice > ");
            choice = Validation.getIntegerInput();
            
            switch(choice){
                case 1 ->{
                    System.out.println("Enter Stationary Id > ");
                    search = Validation.getStringInput();
                    
                    for (Book book : bookArray) {
                        if (book.getBookId().equals(search)) {
                            System.out.println(book);
                            notFound = false;
                        }
                    }
                    
                    if (notFound) {
                        System.out.println(RED +"Id Does Not Exist" + RESET);
                    }
                }
                
                case 2 ->{
                    System.out.println("Enter Stationary Name > ");
                    search = Validation.getStringInput();
                    
                    for (Book book : bookArray) {
                        if (book.getName().equals(search)) {
                            System.out.println(book);
                            notFound = false;
                        }
                    }
                    
                    if (notFound) {
                        System.out.println(RED +"Id Does Not Exist" + RESET);
                    }
                }
            }
            
        } while (choice != 0);
    }
    
    //validation
    public Boolean validName(ArrayList<Book> bookArray,String name){
            //no simbol
            //no digit
        if (name.length()<=10) {
            return false;
        }
        
        for (Book book: bookArray) {
            if (book.getName().equals(name)) {
                return false;
            }
        }  
        return true;
    }

    @Override
    public String toString(){     
        return String.format("%-10s  %-10s  %-10s %-10s",bookId,
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
