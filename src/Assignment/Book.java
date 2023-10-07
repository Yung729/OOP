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
public class Book extends Stock {

    Author author = new Author();
    private String bookId;
    private char bookType;

    static Scanner input = new Scanner(System.in);
    static Scanner inputString = new Scanner(System.in);

    Book() {
        super("", 0, 0.0, 0.0, false);
        this.author = null;
        bookId = "";
    }

    Book(String bookId, String name, int stockQuantity, double unitPrice, double soldPrice, boolean stockStatus, char bookType, Author author) {
        super(name, stockQuantity, unitPrice, soldPrice, stockStatus);
        this.bookId = bookId;
        this.bookType = bookType;
        this.author = author;
    }

    //setter
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setBookType(char bookType) {
        this.bookType = bookType;
    }

    //getter
    public char getBookType() {
        return bookType;
    }

    public String getBookId() {
        return bookId;
    }

    //File Method
    public static void writeToFile(ArrayList<Book> bookArray) throws IOException {
        try (FileWriter writeBookFile = new FileWriter("Book.txt")) {
            for (Book bookFromArray : bookArray) {

                writeBookFile.write(bookFromArray.getBookId() + '|' + bookFromArray.getName() + '|'
                        + bookFromArray.getStockQuantity() + '|' + bookFromArray.getUnitPrice() + '|' + bookFromArray.getSoldPrice() + '|'
                        + bookFromArray.isStockStatus() + '|' + bookFromArray.getBookType() + '|' + bookFromArray.author.getName() + '|' + bookFromArray.author.getYearOfBirth()
                        + '|' + bookFromArray.author.checkArrive() + '\n');
            }
        }
    }

    public static void readFromFile(ArrayList<Book> bookArray) throws FileNotFoundException {
        File readBookFile = new File("Book.txt");

        if (readBookFile.exists()) {
            Scanner productRead = new Scanner(readBookFile);
            while (productRead.hasNextLine()) {
                String line = productRead.nextLine();
                String[] data = line.split("\\|");
                bookArray.add(new Book(data[0], data[1], Integer.parseInt(data[2]), Double.parseDouble(data[3]),
                        Double.parseDouble(data[4]), Boolean.parseBoolean(data[5]), data[6].charAt(0),
                        new Author(data[7], Integer.parseInt(data[8]), Boolean.parseBoolean(data[9]))));
            }
        } else {
            File createProductFile = new File("Book.txt");
            try {
                createProductFile.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Stationary.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    //other method
    public String generateId(ArrayList<Book> bookArray) {
        String bookIdGenerated;

        if (bookArray.isEmpty()) {
            bookIdGenerated = "B0001";
        } else {
            bookIdGenerated = bookArray.get(bookArray.size() - 1).getBookId();

            int bufferBookIdNum = Integer.parseInt(bookIdGenerated.replaceAll("\\D+", ""));

            bookIdGenerated = String.format("B%04d", bufferBookIdNum + 1);
        }

        return bookIdGenerated;
    }

    public String convertBookType(char bookType) {

        String specificBookType = null;
        switch (bookType) {
            case 'R' -> {
                specificBookType = "Romantic";
            }
            case 'H' -> {
                specificBookType = "Horror";
            }
            case 'T' -> {
                specificBookType = "Historical";
            }
            case 'F' -> {
                specificBookType = "Fiction";
            }
            case 'E' -> {
                specificBookType = "Education";
            }
        }

        return specificBookType;
    }

    public static void addBook(ArrayList<Book> bookArray, Book Book) {
        bookArray.add(Book);
    }

    public void removeBook(ArrayList<Book> bookArray, Book Book) {
        bookArray.remove(Book);
    }

    // overloading method of editBook 
    public static void editBook(ArrayList<Book> bookArray, String searchBookId, String newBookName) {
        for (Book latestBook : bookArray) {
            if (latestBook.getBookId().equals(searchBookId)) {
                latestBook.setName(newBookName);
            }

        }
    }

    public static void editBook(ArrayList<Book> bookArray, String searchBookId, char newBookType) {
        for (Book latestBook : bookArray) {
            if (latestBook.getBookId().equals(searchBookId)) {
                latestBook.setBookType(newBookType);
            }
        }
    }

    public static void editBook(ArrayList<Book> bookArray, String searchBookId, double newBookPrice) {
        for (Book latestBook : bookArray) {
            if (latestBook.getBookId().equals(searchBookId)) {
                latestBook.setSoldPrice(newBookPrice);
            }
        }
    }

    public static void editBook(ArrayList<Book> bookArray, String searchBookId, int newBookQuantity, boolean add, boolean sub) {
        for (Book latestBook : bookArray) {
            if (latestBook.getBookId().equals(searchBookId)) {
                if (add) {
                    latestBook.addStockQuantity(newBookQuantity);
                } else if (sub) {

                    int check = latestBook.getStockQuantity() - newBookQuantity;

                    if (latestBook.isStockStatus() && check >= 0) {
                        latestBook.subStockQuantity(newBookQuantity);
                    } else {
                        System.out.println(RED + "Book Quantity Is Zero" + RESET);
                        Assignment.systemPause();
                    }

                } else {
                    latestBook.setStockQuantity(newBookQuantity);
                }
            }
        }
    }

    public void displayBookDetails(Book book) {
        System.out.println("====================================");
        System.out.println("|            Book Detail            |");
        System.out.println("====================================");
        System.out.println("  Book ID               : " + book.bookId);
        System.out.println("  Book Name             : " + book.getName());
        System.out.println("  Book Type             : " + convertBookType(book.bookType));
        System.out.println("  Book Price            : RM " + book.getUnitPrice());
        System.out.println("  Book Sold Price       : RM " + book.getSoldPrice());
        System.out.println("  Book Total Added      : " + book.getStockQuantity());
        System.out.println("  Book Total Added (RM) : RM " + (book.getUnitPrice() * book.getStockQuantity()));
        book.author.displayAuthorDetail(book.author);
    }

    public void displayBookDetails(Book book, Author authorDisplay) {
        System.out.println("====================================");
        System.out.println("|            Book Detail            |");
        System.out.println("====================================");
        System.out.println("  Book ID               : " + book.bookId);
        System.out.println("  Book Name             : " + book.getName());
        System.out.println("  Book Type             : " + convertBookType(book.bookType));
        System.out.println("  Book Price            : RM " + book.getUnitPrice());
        System.out.println("  Book Sold Price       : RM " + book.getSoldPrice());
        System.out.println("  Book Total Added      : " + book.getStockQuantity());
        System.out.println("  Book Total Added (RM) : RM " + (book.getUnitPrice() * book.getStockQuantity()));
        authorDisplay.displayAuthorDetail(authorDisplay);
    }

    @Override
    public void display() {
        Assignment.clearScreen();
        Assignment.logo();
        ArrayList<Book> bookArray = new ArrayList<>();

        System.out.printf("%-11s %-28s    %-8s    %-6s    %-9s    %-17s    %-13s    %-10s    %-10s    %-10s\n", "Book Id", "BookName", "Quantity", "Unit Price", "Sold Price", "Book Status", "Type",
                "Author Name", "YearOfBirth", "status");
        System.out.println("================================================================================================================================================================");

        try {
            readFromFile(bookArray);
        } catch (FileNotFoundException ex) {
            System.out.println("Failed to Read File.");
        }

        for (Book bookDisplay : bookArray) {
            System.out.println(bookDisplay);

        }

        Assignment.systemPause();
    }

    @Override
    public void adjust() {

        String idSearch, authorSearch = null, confirm;
        int choice, newBookQuantity, currentIndex = 0;
        boolean notFound, valid;
        boolean error;

        ArrayList<Book> bookArray = new ArrayList<>();
        ArrayList<Book> checkArray = new ArrayList<>();

        do {
            bookArray.clear();
            checkArray.clear();
            try {
                readFromFile(bookArray);
                readFromFile(checkArray);
            } catch (FileNotFoundException ex) {
                System.out.println("Failed to read book record");
            }
            notFound = true;
            Assignment.clearScreen();
            Assignment.logo();

            System.out.println("===========================================");
            System.out.println("               Adjust Stock                 ");
            System.out.println("===========================================");

            do {
                System.out.print("Enter BookID [Q to exit]> ");
                idSearch = Validation.getStringInput();

                if ("".equals(idSearch)) {
                    continue;
                }

                if (toUpperCase(idSearch.charAt(0)) == 'Q') {
                    break;
                }

                if (!validID(idSearch)) {
                    System.out.println(RED + "Book ID Must in B0001 format" + RESET);
                }

            } while (!validID(idSearch));

            // find book pick
            for (int i = 0; i < bookArray.size(); i++) {

                if (idSearch.equals(bookArray.get(i).getBookId())) {

                    notFound = false;
                    displayBookDetails(bookArray.get(i));

                    currentIndex = i;
                    break;
                }

            }

            if (notFound && Character.toUpperCase(idSearch.charAt(0)) != 'Q') {
                System.out.println("The BookId Entered Does Not Exist.");
                Assignment.systemPause();

            } else if (!notFound && Character.toUpperCase(idSearch.charAt(0)) != 'Q') {

                System.out.println(Assignment.GREENBG + "Option" + Assignment.RESET);
                System.out.println("1. Book Name \t 2. Book Type \t 3. Book Price \t 4. Book Quantity");
                System.out.println("5. Author Name \t 6. Author Year Of Birth \t 7.Author Status");
                System.out.println("=========================");

                System.out.print("Enter Field to Edit [0 to cancel] >");
                choice = Validation.getIntegerInput();

                switch (choice) {

                    case 1 -> {

                        do {
                            System.out.print("Enter Book Name :");
                            bookArray.get(currentIndex).setName(Validation.getStringInput());

                            if (!validName(checkArray, bookArray.get(currentIndex).getName())) {
                                System.out.println(RED + "Book Name Repeated or Too Short" + RESET);
                            }

                        } while (!validName(checkArray, bookArray.get(currentIndex).getName()));

                        System.out.print("Confirm To Edit Book Name ? [Y/N] >");
                        confirm = inputString.next();

                        if (toUpperCase(confirm.charAt(0)) == 'Y' && Validation.checkYesNo(confirm.charAt(0))) {
                            try {
                                editBook(bookArray, idSearch, bookArray.get(currentIndex).getName());
                                writeToFile(bookArray);
                            } catch (IOException ex) {
                                System.out.println("Failed to Edit The Book Name");
                            }
                        } else {
                            System.out.println(RED + "Edit is denied");
                        }
                    }

                    case 2 -> {

                        do {
                            System.out.print("Enter Book Type [(R)omantic,(H)orror,His(T)orical,(F)iction,(E)ducation] :");
                            bookArray.get(currentIndex).setBookType(inputString.next().charAt(0));

                            switch (Character.toUpperCase(bookArray.get(currentIndex).getBookType())) {
                                case 'R' -> {
                                    bookArray.get(currentIndex).setBookType('R');
                                    error = false;
                                }

                                case 'H' -> {
                                    bookArray.get(currentIndex).setBookType('H');
                                    error = false;
                                }

                                case 'T' -> {
                                    bookArray.get(currentIndex).setBookType('T');
                                    error = false;

                                }

                                case 'F' -> {
                                    bookArray.get(currentIndex).setBookType('F');
                                    error = false;
                                }

                                case 'E' -> {
                                    bookArray.get(currentIndex).setBookType('E');
                                    error = false;
                                }

                                default -> {
                                    System.out.println(RED + "Book Tyoe Select Range [1-5] Only" + RESET);
                                    Assignment.systemPause();
                                    error = true;
                                }
                            }

                        } while (error);

                        System.out.print("Confirm To Edit Book Type ? [Y/N] >");
                        confirm = inputString.next();

                        if (toUpperCase(confirm.charAt(0)) == 'Y' && Validation.checkYesNo(confirm.charAt(0))) {
                            try {
                                editBook(bookArray, idSearch, bookArray.get(currentIndex).getBookType());
                                writeToFile(bookArray);
                            } catch (IOException ex) {
                                System.out.println("Failed to Edit The Book Type");
                            }
                        } else {
                            System.out.println(RED + "Edit is denied");
                        }
                    }

                    case 3 -> {

                        do {
                            System.out.print("Enter Book Price :");
                            bookArray.get(currentIndex).setSoldPrice(Validation.getDoubleInput());

                            if (!validSoldPrice(bookArray.get(currentIndex).getSoldPrice(), bookArray.get(currentIndex).getUnitPrice())) {
                                System.out.println(RED + "Book Price Less Than Unit Price " + RESET);
                            }

                        } while (!validSoldPrice(bookArray.get(currentIndex).getSoldPrice(), bookArray.get(currentIndex).getUnitPrice()));

                        System.out.print("Confirm To Edit Book Price ? [Y/N] >");
                        confirm = inputString.next();

                        if (toUpperCase(confirm.charAt(0)) == 'Y' && Validation.checkYesNo(confirm.charAt(0))) {
                            try {
                                editBook(bookArray, idSearch, bookArray.get(currentIndex).getSoldPrice());
                                writeToFile(bookArray);
                            } catch (IOException ex) {
                                System.out.println("Failed to Edit The Book Type");
                            }
                        } else {
                            System.out.println(RED + "Edit is denied" + RESET);
                        }
                    }

                    case 4 -> {
                        System.out.println("Edit Book Quantity");
                        System.out.println("========================");
                        System.out.println("1. Add Book Quantity");
                        System.out.println("2. Sub Book Quantity");
                        System.out.println("3. Set Book Quantity");
                        System.out.println("========================");

                        System.out.print("Enter your Choice > ");
                        choice = Validation.getIntegerInput();

                        if (choice > 0 && choice <= 3) {

                            do {
                                valid = true;
                                System.out.print("Enter Book Quantiy >");
                                newBookQuantity = Validation.getIntegerInput();
                                if (choice == 2) {

                                    if (newBookQuantity > bookArray.get(currentIndex).getStockQuantity()) {
                                        valid = false;
                                    }

                                }
                            } while (!valid);

                            System.out.print("Confirm To Edit Book Quantity ? [Y/N] >");
                            confirm = inputString.next();

                            if (toUpperCase(confirm.charAt(0)) == 'Y' && Validation.checkYesNo(confirm.charAt(0))) {
                                switch (choice) {
                                    case 1 -> {
                                        try {
                                            editBook(bookArray, idSearch, newBookQuantity, true, false);
                                            checkAvailable(bookArray);
                                            writeToFile(bookArray);
                                        } catch (IOException ex) {
                                            System.out.println(RED + "Failed to Edit The Book Type" + RESET);
                                        }

                                        try {
                                            StockFlowReport.writeStockToFile(bookArray.get(currentIndex).getBookId(), newBookQuantity,
                                                    bookArray.get(currentIndex).getStockAddDate());
                                        } catch (IOException ex) {
                                            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                    }
                                    case 2 -> {
                                        try {
                                            editBook(bookArray, idSearch, newBookQuantity, false, true);
                                            checkAvailable(bookArray);
                                            writeToFile(bookArray);
                                        } catch (IOException ex) {
                                            System.out.println(RED + "Failed to Edit The Book Type" + RESET);
                                        }

                                        try {
                                            StockFlowReport.writeStockToFile(bookArray.get(currentIndex).getBookId(), -(newBookQuantity),
                                                    bookArray.get(currentIndex).getStockAddDate());
                                        } catch (IOException ex) {
                                            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                    }
                                    case 3 -> {
                                        int previousQuantity = bookArray.get(currentIndex).getStockQuantity();
                                        try {
                                            editBook(bookArray, idSearch, newBookQuantity, false, false);
                                            checkAvailable(bookArray);
                                            writeToFile(bookArray);
                                        } catch (IOException ex) {
                                            System.out.println(RED + "Failed to Edit The Book Type" + RESET);
                                        }

                                        try {
                                            StockFlowReport.writeStockToFile(bookArray.get(currentIndex).getBookId(),
                                                    -(previousQuantity - newBookQuantity),
                                                    bookArray.get(currentIndex).getStockAddDate());
                                        } catch (IOException ex) {
                                            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                    default -> {
                                    }
                                }

                            } else {
                                System.out.println(RED + "Edit is denied" + RESET);
                            }
                        } else {
                            System.out.println(RED + "Invalid Input" + RESET);
                        }
                    }

                    case 5 -> {

                        boolean check = false;

                        do {

                            System.out.print("Enter Author Name :");

                            if (!check) {
                                authorSearch = bookArray.get(currentIndex).author.getName();
                            }

                            bookArray.get(currentIndex).author.setName(Validation.getStringInput());

                            if (!bookArray.get(currentIndex).author.validAuthorName(bookArray.get(currentIndex).author.getName()) || bookArray.get(currentIndex).author.getName().equals(authorSearch)) {
                                System.out.println(RED + "Author Name Invalid" + RESET);
                                check = true;
                            }

                        } while (!bookArray.get(currentIndex).author.validAuthorName(bookArray.get(currentIndex).author.getName()) || bookArray.get(currentIndex).author.getName().equals(authorSearch));

                        System.out.print("Confirm To Edit Author Name ? [Y/N] >");
                        confirm = inputString.next();

                        if (toUpperCase(confirm.charAt(0)) == 'Y' && Validation.checkYesNo(confirm.charAt(0))) {
                            try {
                                Author.editAuthor(bookArray, authorSearch, bookArray.get(currentIndex).author.getName());
                                writeToFile(bookArray);
                            } catch (IOException ex) {
                                System.out.println("Failed to Edit The Author Name");
                            }
                        } else {
                            System.out.println(RED + "Edit is denied");
                        }
                    }

                    case 6 -> {

                        do {
                            System.out.print("Enter Year Of Birth :");

                            authorSearch = bookArray.get(currentIndex).author.getName();

                            bookArray.get(currentIndex).author.setYearOfBirth(Validation.getIntegerInput());

                            if (!bookArray.get(currentIndex).author.validAuthorYearOfBirth(bookArray.get(currentIndex).author.getYearOfBirth())) {
                                System.out.println(RED + "Invalid Year Of Birth, YOB accept 1800 to current only !" + RESET);
                            }

                        } while (!bookArray.get(currentIndex).author.validAuthorYearOfBirth(bookArray.get(currentIndex).author.getYearOfBirth()));

                        System.out.print("Confirm To Edit Author Birth ? [Y/N] >");
                        confirm = inputString.next();

                        if (toUpperCase(confirm.charAt(0)) == 'Y' && Validation.checkYesNo(confirm.charAt(0))) {
                            try {
                                Author.editAuthor(bookArray, authorSearch, bookArray.get(currentIndex).author.getYearOfBirth());
                                writeToFile(bookArray);
                            } catch (IOException ex) {
                                System.out.println("Failed to Edit The Book Name");
                            }
                        } else {
                            System.out.println(RED + "Edit is denied");
                        }
                    }
                    case 7 -> {

                        System.out.println("Current status : " + bookArray.get(currentIndex).author.checkArrive());

                        authorSearch = bookArray.get(currentIndex).author.getName();

                        System.out.print("Confirm To Edit Status ? [Y/N] >");
                        confirm = Validation.getStringInput();

                        if (toUpperCase(confirm.charAt(0)) == 'Y' && Validation.checkYesNo(confirm.charAt(0))) {
                            try {
                                if (bookArray.get(currentIndex).author.checkArrive()) {
                                    Author.editAuthor(bookArray, authorSearch, false);
                                    Author.updateDiscount(1, authorSearch, bookArray);
                                } else {
                                    Author.editAuthor(bookArray, authorSearch, true);
                                    Author.updateDiscount(2, authorSearch, bookArray);
                                }

                                writeToFile(bookArray);
                            } catch (IOException ex) {
                                System.out.println("Failed to Edit The Book Name");
                            }
                        } else {
                            System.out.println(RED + "Edit is denied");
                        }
                    }

                    case 0 -> {
                    }

                    default -> {
                        System.out.println(RED + "INVALID INPUT" + RESET);
                        Assignment.systemPause();
                    }
                }
            }

        } while (toUpperCase(idSearch.charAt(0)) != 'Q');

    }

    @Override
    public void add() {

        Author authorInput = new Author();
        Book book = new Book();
        int choice;
        char confirmChoice;
        boolean valid;

        ArrayList<Book> bookArray = new ArrayList<>();
        try {
            readFromFile(bookArray);
        } catch (FileNotFoundException ex) {
            System.out.println("File Error");
        }

        do {
            Assignment.clearScreen();
            Assignment.logo();
            System.out.println("===========================================");
            System.out.println("                 Add Stock                 ");
            System.out.println("===========================================");

            book.setBookId(generateId(bookArray));

            //prompt input
            System.out.println("Book Id : " + book.getBookId());

            do {
                System.out.print("Enter Book Name : ");
                book.setName(Validation.getStringInput());

                if (!validName(bookArray, book.getName())) {
                    System.out.println(RED + "Book Name Repeated or Too Short" + RESET);
                }

            } while (!validName(bookArray, book.getName()));

            do {
                System.out.print("Enter Quantity :");
                book.setStockQuantity(Validation.getIntegerInput());

                if (!validQuantity(book.getStockQuantity())) {
                    System.out.println(RED + "Book Quantity Must At Least one" + RESET);
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

                switch (choice) {
                    case 1 -> {
                        book.setBookType('R');
                    }

                    case 2 -> {
                        book.setBookType('H');
                    }

                    case 3 -> {
                        book.setBookType('T');

                    }

                    case 4 -> {
                        book.setBookType('F');

                    }

                    case 5 -> {
                        book.setBookType('E');

                    }

                    default -> {
                        System.out.println(RED + "Book Tyoe Select Range [1-5] Only" + RESET);
                    }
                }

            } while (choice == -1 || (choice < 1 || choice > 5));

            do {
                System.out.print("Enter Book price : ");
                book.setUnitPrice(Validation.getDoubleInput());

                if (!super.validUnitPrice(book.getUnitPrice())) {
                    System.out.println(RED + "Book Price Must more than RM 0 " + RESET);
                }

            } while (!super.validUnitPrice(book.getUnitPrice()));

            do {
                System.out.print("Enter Book Sold price : ");
                book.setSoldPrice(Validation.getDoubleInput());

                if (!validSoldPrice(book.getSoldPrice(), book.getUnitPrice())) {
                    System.out.println(RED + "Book Price Less Than Unit Price " + RESET);
                }

            } while (!validSoldPrice(book.getSoldPrice(), book.getUnitPrice()));

            Assignment.clearScreen();
            Assignment.logo();

            System.out.println("Book Author");
            System.out.println("===========");

            do {
                System.out.print("Enter Author name > ");
                authorInput.setName(Validation.getStringInput());

                if (!authorInput.validAuthorName(authorInput.getName())) {
                    System.out.println(RED + "Author Name Invalid" + RESET);
                }

            } while (!authorInput.validAuthorName(authorInput.getName()));

            ArrayList<Book> bookAuthor = new ArrayList<>();
            boolean authorOccur = false;
            try {
                readFromFile(bookAuthor);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (Book authorCheck : bookAuthor) {
                if (authorInput.equals(authorCheck.author)) {
                    authorOccur = true;
                    authorInput.setYearOfBirth(authorCheck.author.getYearOfBirth());
                    authorInput.setArrive(authorCheck.author.checkArrive());
                }
            }

            if (!authorOccur) {
                do {
                    System.out.print("Enter Author YOB > ");
                    authorInput.setYearOfBirth(Validation.getIntegerInput());
                    if (!authorInput.validAuthorYearOfBirth(authorInput.getYearOfBirth())) {
                        System.out.println(RED + "Invalid Year Of Birth, YOB accept 1800 to current only !" + RESET);
                    }
                } while (!authorInput.validAuthorYearOfBirth(authorInput.getYearOfBirth()));

                do {
                    System.out.print("Author Alive ? [Y/N] > ");
                    confirmChoice = toUpperCase(inputString.next().charAt(0));

                    valid = Validation.checkYesNo(confirmChoice);
                    if (!valid) {
                        System.out.println(RED + "Invalid Input Must 'Y'/'N'" + RESET);
                    }

                } while (!valid);

                if (confirmChoice == 'Y' && Validation.checkYesNo(confirmChoice)) {
                    authorInput.setArrive(true);
                } else if (confirmChoice == 'N' && Validation.checkYesNo(confirmChoice)) {
                    authorInput.setArrive(false);
                    Author.updateDiscount(book, 1);
                }
            } else if (authorOccur) {
                System.out.println(Assignment.GREEN + "Author Already exist in system the data will be copy" + Assignment.RESET);
                Assignment.systemPause();
            }

            Assignment.clearScreen();
            Assignment.logo();
            displayBookDetails(book, authorInput);

            do {
                System.out.print("Comfirm [Y/N] > ");
                confirmChoice = toUpperCase(inputString.next().charAt(0));

                valid = Validation.checkYesNo(confirmChoice);
                if (!valid) {
                    System.out.println(RED + "Invalid Input Must 'Y'/'N'" + RESET);
                }

            } while (!valid);

            if (Validation.checkYesNo(confirmChoice)) {

                switch (toUpperCase(confirmChoice)) {
                    case 'Y' -> {
                        addBook(bookArray, new Book(book.getBookId(), book.getName(), book.getStockQuantity(),
                                 book.getUnitPrice(), book.getSoldPrice(), true, book.getBookType(),
                                new Author(authorInput.getName(), authorInput.getYearOfBirth(), authorInput.checkArrive())));
                        try {
                            writeToFile(bookArray);
                        } catch (IOException ex) {
                            System.out.println("Failed To Write into File");
                        }

                        try {
                            StockFlowReport.writeStockToFile(book.getBookId(), book.getStockQuantity(), book.getStockAddDate());
                        } catch (IOException ex) {
                            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    case 'N' ->
                        System.out.println("Thank you...");
                    default ->
                        System.out.println("Wrong input");
                }

                do {
                    System.out.print("Any More Book ? [Y/N] > ");
                    confirmChoice = inputString.next().charAt(0);

                    valid = Validation.checkYesNo(confirmChoice);
                    if (!valid) {
                        System.out.println(RED + "Invalid Input Must 'Y'/'N'" + RESET);
                    }

                } while (!valid);

            } else {
                System.out.println("Wrong Input");
            }

        } while (toUpperCase(confirmChoice) != 'N');
    }

    @Override
    public void remove() {

        String IdSearch, confirm;
        boolean notFound;
        int currentIndex = 0;

        ArrayList<Book> bookArray = new ArrayList<>();
        try {
            readFromFile(bookArray);
        } catch (FileNotFoundException ex) {
            System.out.println("Failed to read book record");
        }

        do {

            if (!bookArray.isEmpty()) {

                notFound = true;
                Assignment.clearScreen();
                Assignment.logo();
                System.out.println("===========================================");
                System.out.println("              Remove Stock                 ");
                System.out.println("===========================================");

                do {
                    System.out.print("Enter BookID [Q to exit]> ");
                    IdSearch = Validation.getStringInput();

                    if ("".equals(IdSearch)) {
                        continue;
                    }

                    if (toUpperCase(IdSearch.charAt(0)) == 'Q') {
                        break;
                    }

                    if (!validID(IdSearch)) {
                        System.out.println(RED + "Book ID Must in B0001 format" + RESET);
                    }

                } while (!validID(IdSearch));

                for (int i = 0; i < bookArray.size(); i++) {

                    if (IdSearch.equals(bookArray.get(i).getBookId())) {
                        notFound = false;
                        displayBookDetails(bookArray.get(i));
                        currentIndex = i;
                        break;
                    }
                }

                if (notFound && Character.toUpperCase(IdSearch.charAt(0)) != 'Q') {

                    System.out.println(RED + "The BookId Entered Does Not Exist." + RESET);
                    Assignment.systemPause();

                } else if (!notFound && Character.toUpperCase(IdSearch.charAt(0)) != 'Q') {

                    System.out.print("Confirm To Remove Book  ? [Y/N] >");
                    confirm = inputString.next();

                    if (toUpperCase(confirm.charAt(0)) == 'Y') {

                        try {
                            StockFlowReport.writeStockToFile(bookArray.get(currentIndex).getBookId(), -(bookArray.get(currentIndex).getStockQuantity()),
                                    bookArray.get(currentIndex).getStockAddDate());
                        } catch (IOException ex) {
                            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        removeBook(bookArray, bookArray.get(currentIndex));

                        try {
                            writeToFile(bookArray);
                        } catch (IOException ex) {
                            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        System.out.println("Succesful Removed");
                        Assignment.systemPause();
                    }
                }

            } else {
                Assignment.clearScreen();
                System.out.println("Book List Is Empty !");
                Assignment.systemPause();
                IdSearch = "Q";
            }

        } while (toUpperCase(IdSearch.charAt(0)) != 'Q');

    }

    @Override
    public void search() {

        String search = null;
        boolean notFound = true;
        int choice;

        ArrayList<Book> bookArray = new ArrayList<>();
        try {
            readFromFile(bookArray);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        }

        do {
            Assignment.clearScreen();
            Assignment.logo();
            System.out.println("===========================================");
            System.out.println("              Search Stock                 ");
            System.out.println("===========================================");
            System.out.println("1. Search By Id");
            System.out.println("2. Search By Name");
            System.out.println("3. Search By Book Type");
            System.out.println("0. Exit");
            System.out.print("Enter your Choice > ");
            choice = Validation.getIntegerInput();

            switch (choice) {
                case 1 -> {
                    Assignment.clearScreen();
                    Assignment.logo();
                    do {

                        System.out.print("Enter Book Id > ");
                        search = Validation.getStringInput();

                        if (!validID(search)) {
                            System.out.println(RED + "Book ID Must in B0001 format" + RESET);
                        }

                    } while (!validID(search));

                    for (Book book : bookArray) {
                        if (book.getBookId().equals(search)) {
                            Assignment.clearScreen();
                            Assignment.logo();
                            displayBookDetails(book);
                            notFound = false;
                        }
                    }

                    if (notFound) {
                        System.out.println(RED + "Id Does Not Exist" + RESET);
                    }
                }

                case 2 -> {

                    Assignment.clearScreen();
                    Assignment.logo();
                    System.out.print("Enter Book Name > ");
                    search = Validation.getStringInput();

                    for (Book book : bookArray) {
                        if (book.getName().equals(search)) {
                            Assignment.clearScreen();
                            Assignment.logo();
                            displayBookDetails(book);
                            notFound = false;
                        }
                    }

                    if (notFound) {
                        System.out.println(RED + "Book Name Does Not Exist" + RESET);
                    }
                }

                case 3 -> {

                    Assignment.clearScreen();
                    Assignment.logo();
                    System.out.println("1.Romantic 2.Horror 3.Historical 4.Fiction  5.Eduaction :");
                    System.out.print("Choose Book Type > ");

                    do {
                        choice = Validation.getIntegerInput();

                        if (choice < 1 && choice > 5) {
                            System.out.println(RED + "Invalid Input" + RESET);
                        }
                    } while (choice < 1 && choice > 5);

                    switch (choice) {
                        case 1 ->
                            search = "R";
                        case 2 ->
                            search = "H";
                        case 3 ->
                            search = "T";
                        case 4 ->
                            search = "F";
                        case 5 ->
                            search = "E";
                    }
                    Assignment.clearScreen();
                    Assignment.logo();
                    System.out.printf("%-11s %-28s    %-8s    %-6s    %-9s    %-17s    %-13s    %-10s    %-10s    %-10s\n", "Book Id", "BookName", "Quantity", "Unit Price", "Sold Price", "Book Status", "Type",
                            "Author Name", "YearOfBirth", "status");
                    System.out.println("================================================================================================================================================================");

                    for (Book book : bookArray) {
                        if (book.getBookType() == search.charAt(0)) {

                            System.out.println(book);
                            notFound = false;
                        }
                    }

                    if (notFound) {
                        System.out.println(RED + "Book Name Does Not Exist" + RESET);
                    }
                }

                case 0 -> {
                }
                default -> {
                    System.out.println(RED + "Invalid Input" + RESET);
                }
            }
            Assignment.systemPause();
        } while (choice != 0);
    }

    //validation
    public Boolean validID(String id) {

        if (id.length() < 5) {
            return false;
        } else if (id.charAt(0) != 'B') {
            return false;
        }

        for (int i = 1; i < 5; i++) {
            if (!Character.isDigit(id.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public Boolean validName(ArrayList<Book> bookArray, String name) {

        if (name.length() < 5) {
            return false;
        }

        for (Book book : bookArray) {
            if (book.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%-10s  %-10s  %-17s %-10s", bookId,
                super.toString(), convertBookType(bookType), author.toString());
    }

    //check stock balance 
    public static void checkAvailable(ArrayList<Book> bookArray) {
        for (Book book : bookArray) {
            if (book.getStockQuantity() <= 0) {
                book.setStockStatus(false);
            } else if (book.getStockQuantity() > 0) {
                book.setStockStatus(true);
            }
        }
    }

}
