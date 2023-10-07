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
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static Assignment.Assignment.systemPause;
import static Assignment.Assignment.input;
import static Assignment.Assignment.clearScreen;
import static Assignment.Assignment.logo;

/**
 *
 * @Jesmine
 */
public class Admin extends Employees {

    private double bonusRate;
    private static int noOfAdmin = 0;
    public double sales = 0;
    public double totalSalary = 0;

    //constructor
    public Admin() {
        super("", "", "", "", "", "", "", "", 0.0);
        this.bonusRate = bonusRate;
        this.sales = sales;
        this.totalSalary = totalSalary;

    }

    public Admin(String id, String password, String name, String ic, String position, String phoneNumber, String email, String address, double basicSalary, double bonusRate, double sales, double totalSalary) {
        super(id, password, name, ic, position, phoneNumber, email, address, basicSalary);
        this.bonusRate = bonusRate;
        this.sales = sales;
        this.totalSalary = totalSalary;
        noOfAdmin++;
    }

    //getter
    public double getBonusRate() {
        return bonusRate;
    }

    public double getSales() {
        return sales;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public static int getNoOfAdmin() {
        return noOfAdmin;
    }

    //setter
    public void setBonusRate(double bonusRate) {
        this.bonusRate = bonusRate;
    }

    public void setSales(double sales) {
        this.sales = sales;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }

    //other method
    public double calculateBonus() {
        double bonus = 0;

        if (bonusRate > 0.00) {
            bonus = bonusRate * sales;
        } else if (bonusRate == 0.00) {
            bonus = 0;
        }

        return bonus;
    }

    public double calculateTotalSalary() {
        return super.getBasicSalary() + calculateBonus();
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nBonus Rate: " + bonusRate
                + "\nSales: " + sales
                + "\nTotal Salary: " + calculateTotalSalary();
    }

    //Function for add and remove
    public void addAd(ArrayList<Admin> adminArray, Admin ad) {
        adminArray.add(ad);
    }

    public void deleteAd(ArrayList<Admin> adminArray, Admin ad) {
        adminArray.remove(ad);
    }

    public void updateAdminSales(ArrayList<Admin> adminArray, String id, double s) {
        double newSales = 0;

        for (Admin admin : adminArray) {
            if (admin.getId().equals(id)) {
                newSales = sales + s;
                sales = newSales;
            }
        }
    }

    public void updateAdminTotalSalary(ArrayList<Admin> adminArray, String id, double s, double salary) {
        double newSalary = 0;

        for (Admin admin : adminArray) {
            if (admin.getId().equals(id)) {
                newSalary = calculateTotalSalary();
            }
        }

    }

    //File
    public static void writeAdmin(ArrayList<Admin> adminArray) throws IOException {
        try (FileWriter writeAdminFile = new FileWriter("Admin.txt")) {
            for (Admin admin : adminArray) {
                writeAdminFile.write(admin.getId() + "|"
                        + admin.getPassword() + "|"
                        + admin.getName() + "|"
                        + admin.getIc() + "|"
                        + admin.getPosition() + "|"
                        + admin.getPhoneNumber() + "|"
                        + admin.getEmail() + "|"
                        + admin.getAddress() + "|"
                        + admin.getBasicSalary() + "|"
                        + admin.getBonusRate() + "|"
                        + admin.getSales() + "|"
                        + admin.getTotalSalary() + "|"
                        + "\n"
                );
            }
        }

    }

    public static void readAdminFromFile(ArrayList<Admin> adminArray) throws FileNotFoundException {
        File readAdminFile = new File("Admin.txt");

        if (readAdminFile.exists()) {
            Scanner adminRead = new Scanner(readAdminFile);
            while (adminRead.hasNextLine()) {
                String line = adminRead.nextLine();
                String[] info = line.split("\\|");
                adminArray.add(new Admin(info[0], info[1], info[2], info[3], info[4], info[5], info[6], info[7], Double.parseDouble(info[8]), Double.parseDouble(info[9]), Double.parseDouble(info[10]), Double.parseDouble(info[11])));
            }
        } else {
            File createAdminFile = new File("Admin.txt");
            try {
                createAdminFile.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Assignment.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("File created : " + createAdminFile.getName() + "\n");
        }
    }

    //Main Function
    @Override
    public void add() {

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
        double bRate;
        int choice;
        char confirm;
        boolean error = false;
        boolean duplicate = false;
        boolean check = true;

        try {
            readAdminFromFile(adminArray);
        } catch (FileNotFoundException ex) {
            System.out.println(RED + "Cannot read the file!" + RESET);
        }

        try {
            new Cashier().readCashierFromFile(cashierArray);
        } catch (FileNotFoundException ex) {
            System.out.println(RED + "Cannot read the file!" + RESET);
        }

        do {
            clearScreen();
            System.out.println("Current Login > " + CURRENTNAME);
            System.out.println("=======================================");
            System.out.println("=              Add Admin              =");
            System.out.println("=======================================");

            super.setId(generateAdminID(adminArray));
            System.out.println("ID: " + super.getId());
            input.nextLine();

            do {
                error = false;
                System.out.println("**Press x to exit**");
                System.out.print("Enter Password (8 to 12 characters and numbers) > ");
                validatedPassword = input.nextLine();
                validatedPassword = validatedPassword.replace(" ", "");

                if (validatedPassword.equalsIgnoreCase("x")) {
                    System.out.println("Thanks!");
                    return;
                }

                error = super.checkPasswordFormat(validatedPassword);

                if (error) {
                    System.out.println(RED + "Password must be 8 to 12 combination of characters and numbers!" + RESET);
                    System.out.println(RED + "Example: Abc12345" + RESET);
                }

                if (!error) {
                    super.setPassword(validatedPassword);
                }

            } while (error);

            do {
                error = false;

                System.out.print("Enter Name > ");
                validatedName = input.nextLine();

                if (validatedName.equalsIgnoreCase("x")) {
                    System.out.println("Thanks!");
                    return;
                }

                error = super.checkNameFormat(validatedName);

                if (error) {
                    System.out.println(RED + "Invalid Name Format!" + RESET);
                }

                if (!error) {
                    super.setName(validatedName);
                }

                if (validatedPassword.equalsIgnoreCase("x")) {
                    System.out.println("Thanks!");
                    return;
                }

            } while (error);

            do {
                error = false;
                duplicate = false;

                System.out.print("Enter IC (040420-01-0110) > ");

                validatedIC = input.nextLine();

                validatedIC = validatedIC.replace(" ", "");

                if (validatedIC.equalsIgnoreCase("x")) {
                    System.out.println("Thanks!");
                    return;
                }

                error = super.checkIcFormat(validatedIC);
                duplicate = checkAdminDuplicateIc(adminArray, cashierArray, validatedIC);

                if (error) {
                    System.out.println(RED + "Invalid IC Format!" + RESET);
                    System.out.println(RED + "Example: 040420-01-0110" + RESET);
                }

                if (duplicate) {
                    System.out.println(RED + "RECORD ALREADY EXIST!" + RESET);
                }

                if (!error && !duplicate) {
                    super.setIc(validatedIC);
                }

            } while (error || duplicate);

            System.out.println("============================================");
            System.out.println("=                 Position                 =");
            System.out.println("============================================");
            System.out.println("=         1. Manager                       =");
            System.out.println("=         2. Department Leader             =");
            System.out.println("============================================");
            do {
                error = false;
                System.out.print("Enter your choice > ");
                try {
                    choice = input.nextInt();
                } catch (Exception e) {
                    System.out.println("Invalid input!");
                    error = true;
                    choice = 0;
                }

                switch (choice) {
                    case 1 ->
                        super.setPosition("Manager");

                    case 2 ->
                        super.setPosition("Department Leader");

                    default -> {
                        error = true;
                        System.out.println(RED + "Invalid input. Please enter again!" + RESET);
                    }
                }

            } while (error);
            input.nextLine();

            do {
                error = false;
                duplicate = false;

                System.out.print("Enter Phone Number > ");
                validatedPhoneNumber = input.nextLine();
                validatedPhoneNumber = validatedPhoneNumber.trim();

                if (validatedPhoneNumber.equalsIgnoreCase("x")) {
                    System.out.println("Thanks!");
                    return;
                }

                error = super.checkPhoneNumberFormat(validatedPhoneNumber);
                duplicate = checkAdminPhoneNumberDuplicate(adminArray, cashierArray, validatedPhoneNumber);

                if (error) {
                    System.out.println(RED + "Invalid format. Please enter again!" + RESET);
                    System.out.println(RED + "Example: 012-3456789" + RESET);
                }

                if (duplicate) {
                    System.out.println(RED + "RECORD ALREADY EXIST!" + RESET);
                }

                if (!error) {
                    super.setPhoneNumber(validatedPhoneNumber);
                }

            } while (error || duplicate);

            do {
                error = false;
                duplicate = false;

                System.out.print("Enter Email > ");
                validatedEmail = input.nextLine();
                validatedEmail = validatedEmail.trim();

                if (validatedEmail.equalsIgnoreCase("x")) {
                    System.out.println("Thanks!");
                    return;
                }

                error = super.checkEmailFormat(validatedEmail);
                duplicate = checkAdminEmailDuplicate(adminArray, cashierArray, validatedEmail);

                if (error) {
                    System.out.println(RED + "Invalid format. Please enter again!" + RESET);
                    System.out.println(RED + "Example: abc123@gmail.com" + RESET);
                }

                if (!error) {
                    super.setEmail(validatedEmail);
                }

                if (duplicate) {
                    System.out.println(RED + "RECORD ALREADY EXIST!" + RESET);
                }

            } while (error || duplicate);

            do {
                error = false;

                System.out.print("Enter Address > ");
                validatedAddress = input.nextLine();
                validatedAddress = validatedAddress.trim();

                if (validatedAddress.equalsIgnoreCase("x")) {
                    System.out.println("Thanks!");
                    return;
                }

                error = checkAdminAddressFormat(validatedAddress);

                if (error) {
                    System.out.println(RED + "Invalid format. Please enter again!" + RESET);
                    System.out.println(RED + "Example: 12, Jalan PV15, Setapak" + RESET);
                }

                if (!error) {
                    super.setAddress(validatedAddress);
                }

            } while (error);

            do {
                error = false;

                System.out.print("Enter Basic Salary > ");
                try {
                    bSalary = input.nextDouble();
                } catch (Exception e) {
                    System.out.println("Invalid input!");
                    error = true;
                    bSalary = 0;
                }

                if (bSalary <= 1000 || bSalary >= 15000) {
                    error = true;
                    System.out.println(RED + "Invalid input. Please enter again!" + RESET);
                }

                if (!error) {
                    super.setBasicSalary(bSalary);
                }

            } while (error);

            do {
                error = false;

                System.out.print("Enter Bonus Rate > ");
                try {
                    bRate = input.nextDouble();
                } catch (Exception e) {
                    System.out.println("Invalid input!");
                    error = true;
                    bRate = 0;
                }

                if (bRate > 1.00 || bRate <= 0) {
                    error = true;
                    System.out.println(RED + "Invalid input. Please enter again!" + RESET);
                }

                if (!error) {
                    setBonusRate(bRate);
                }
            } while (error);

            setSales(0);
            setTotalSalary(super.getBasicSalary());

            displayAdminInformation();

            do {
                error = false;

                System.out.print("Confirm Add (Y/N) > ");
                confirm = input.next().charAt(0);
                confirm = Character.toUpperCase(confirm);
                input.nextLine();

                check = checkYesNo(confirm);

                if (!check) {
                    error = true;
                    System.out.println(RED + "Invalid input. Please enter again!" + RESET);
                }

                if (confirm == 'Y') {
                    addAd(adminArray, new Admin(super.getId(), super.getPassword(), super.getName(), super.getIc(), super.getPosition(), super.getPhoneNumber(), super.getEmail(), super.getAddress(), getBasicSalary(), getBonusRate(), getSales(), getTotalSalary()));

                    try {
                        writeAdmin(adminArray);
                    } catch (IOException ex) {
                        Logger.getLogger(Assignment.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            } while (error);

            input.nextLine();

            do {
                error = false;
                System.out.print("Add more admin? (Y/N) > ");
                confirm = input.next().charAt(0);
                confirm = Character.toUpperCase(confirm);
                input.nextLine();

                check = checkYesNo(confirm);

                if (!check) {
                    error = true;
                    System.out.println(RED + "Invalid input. Please enter again!" + RESET);
                }

            } while (error);

        } while (confirm == 'Y');

    }

    @Override
    public void delete() {
        clearScreen();

        Admin admin = new Admin();
        ArrayList<Admin> adminArray = new ArrayList();

        String deleteId;
        String deletePassword;
        char confirm;
        char confirmDelete;
        int currentIndex = 0;
        boolean exist = true;
        boolean error = false;
        boolean check = false;

        try {
            readAdminFromFile(adminArray);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Assignment.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Current Login > " + CURRENTNAME);

        input.nextLine();

        if (adminArray.isEmpty()) {
            System.out.println("========================================");
            System.out.println("=             No Admin Record!         =");
            System.out.println("========================================");
        } else {
            do {
                confirm = 'N';
                clearScreen();

                System.out.println("=======================================");
                System.out.println("=            DELETE ADMIN             =");
                System.out.println("=======================================");

                do {
                    exist = true;
                    System.out.print("Enter Admin ID to delete (Press X to exit) > ");
                    deleteId = input.nextLine();

                    deleteId = deleteId.trim(); // Trim whitespace

                    if ((deleteId.equalsIgnoreCase("X"))) {
                        return; // Exit the loop if 'X' or 'x' is entered
                    }

                    exist = checkAdmin(adminArray, deleteId);

                    if (!exist) {
                        System.out.println("======================================");
                        System.out.println("=         ADMIN DOES NOT FOUND       =");
                        System.out.println("======================================");
                    }

                } while (!exist);

                do {
                    exist = true;
                    System.out.print("Enter Admin Password (Press X to exit) > ");
                    deletePassword = input.nextLine();

                    exist = checkAdminPassword(adminArray, deletePassword);

                    if (!exist) {
                        System.out.println(RED + "Invalid password!" + RESET);
                    }

                    if ((deletePassword.equalsIgnoreCase("X"))) {
                        return;
                    }

                } while (!exist);

                displayAdminInformation(adminArray, deleteId, deletePassword);
                currentIndex = checkAdminIndexNumber(adminArray, deleteId, deletePassword);

                do {
                    error = false;
                    System.out.print("Confirm Delete? (Y/N) > ");
                    confirmDelete = input.next().charAt(0);
                    confirmDelete = Character.toUpperCase(confirmDelete);

                    check = checkYesNo(confirmDelete);

                    if (!check) {
                        error = true;
                        System.out.println(RED + "Invalid input. Please enter again!" + RESET);
                    }
                } while (error);

                if (confirmDelete == 'Y') {
                    deleteAd(adminArray, adminArray.get(currentIndex));
                    try {
                        writeAdmin(adminArray);
                    } catch (IOException ex) {
                        Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("Successful Delete");
                }

                do {

                    error = false;

                    System.out.print("Delete more admin? (Y/N) > ");
                    confirm = input.next().charAt(0);
                    confirm = Character.toUpperCase(confirm);

                    check = checkYesNo(confirm);

                    if (!check) {
                        error = true;
                        System.out.println(RED + "Invalid input. Please enter again!" + RESET);
                    }

                } while (error);

                input.nextLine();
            } while (confirm == 'Y');
        }

        systemPause();
    }

    @Override
    public void edit() {
        clearScreen();

        Admin admin = new Admin();
        ArrayList<Admin> adminArray = new ArrayList();
        Cashier cashier = new Cashier();
        ArrayList<Cashier> cashierArray = new ArrayList();

        String editID;
        String editPassword;
        String oldPassword;
        String confirmPassword;
        String newPosition;
        String latest;
        double latestNum;
        double latestNum2;
        String hidePassword1;
        String hidePassword2;
        int choice;
        char confirm;
        int currentIndex = 0;
        boolean error = false;
        boolean exist = true;
        boolean check = true;
        boolean duplicate = false;

        try {
            readAdminFromFile(adminArray);
        } catch (FileNotFoundException ex) {
            System.out.println(RED + "Cannot read the file!" + RESET);
        }

        try {
            new Cashier().readCashierFromFile(cashierArray);
        } catch (FileNotFoundException ex) {
            System.out.println(RED + "Cannot read the file!" + RESET);
        }

        input.nextLine();

        if (adminArray.isEmpty()) {
            System.out.println("========================================");
            System.out.println("=             No Admin Record!         =");
            System.out.println("========================================");
        } else {
            do {

                clearScreen();

                System.out.println("Current Login > " + CURRENTNAME);

                System.out.println("========================================");
                System.out.println("=              EDIT ADMIN              =");
                System.out.println("========================================");

                do {
                    exist = true;

                    System.out.print("Enter Admin ID to edit (Press X to exit) > ");
                    editID = input.nextLine();

                    editID = editID.trim(); // Trim whitespace

                    if ((editID.equalsIgnoreCase("X"))) {
                        return; // Exit the loop if 'X' or 'x' is entered
                    }

                    exist = checkAdmin(adminArray, editID);

                    if (!exist) {

                        System.out.println("========================================");
                        System.out.println("=            RECORD NOT FOUND!         =");
                        System.out.println("========================================");
                    }

                } while (!exist);

                do {
                    exist = true;
                    System.out.print("Enter Admin Password (Press X to exit) > ");
                    editPassword = input.nextLine();
                    exist = checkAdminPassword(adminArray, editPassword);
                    if (!exist) {
                        System.out.println(RED + "Invalid password!" + RESET);
                    }

                    if ((editPassword.equalsIgnoreCase("X"))) {
                        return;
                    }

                } while (!exist);

                currentIndex = checkAdminIndexNumber(adminArray, editID, editPassword);

                do {
                    clearScreen();
                    error = false;

                    editAdminInformation(adminArray, editID, editPassword);

                    System.out.print("\nEnter your choice > ");
                    try {
                        choice = input.nextInt();
                    } catch (Exception e) {
                        System.out.println("Invalid Input!");
                        error = true;
                        choice = 0;
                    }

                    if (choice < 1 || choice > 8) {
                        error = true;
                        System.out.println(RED + "Invalid input. Please enter again!" + RESET);
                    } else if (choice == 8) {
                        return;
                    }

                } while (error);

                switch (choice) {
                    case 1:
                        clearScreen();
                        input.nextLine();
                        System.out.println("======================================");
                        System.out.println("=             EDIT PASSWORD          =");
                        System.out.println("======================================");

                        input.nextLine();
                        do {
                            error = false;
                            System.out.print("Enter Old Password > ");
                            oldPassword = input.nextLine();

                            exist = checkAdminPassword(adminArray, oldPassword);

                            if (!exist) { // Use equality operator for comparison
                                System.out.println(RED + "Invalid Password!" + RESET);
                                error = true;
                            }

                        } while (error);

                        do {
                            error = false;
                            System.out.print("Enter New Password > ");
                            latest = input.nextLine();

                            error = super.checkPasswordFormat(latest);

                            if (error) {
                                System.out.println(RED + "Password must be 8 to 12 combination of characters and numbers!" + RESET);
                                System.out.println(RED + "Example: Abc12345" + RESET);
                            }

                        } while (error);

                        do {
                            error = false;
                            System.out.print("Confirm New Password > ");
                            confirmPassword = input.nextLine();

                            if (confirmPassword.equals(latest)) {
                                error = false;
                            } else {
                                error = true;
                                System.out.println(RED + "Passwords entered are different!" + RESET);
                            }
                        } while (error);

                        System.out.println("-------------------------------------------");
                        System.out.println("                RESET PASSWORD             ");
                        System.out.println("-------------------------------------------");
                        System.out.println("Original | " + adminArray.get(currentIndex).getPassword());
                        System.out.println("New      | " + latest);
                        System.out.println("-------------------------------------------");

                        do {
                            error = false;
                            check = true;
                            System.out.print("Confirm to edit? (Y/N) > ");
                            confirm = input.next().charAt(0);
                            confirm = Character.toUpperCase(confirm);
                            check = checkYesNo(confirm);

                            if (!check) {
                                error = true;
                                System.out.println(RED + "Invalid input. Please enter again!" + RESET);
                            }
                        } while (error);

                        if (confirm == 'Y') {
                            adminArray.get(currentIndex).setPassword(latest);
                            try {
                                writeAdmin(adminArray);
                            } catch (IOException ex) {
                                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            System.out.println("Successful edit");

                        } else {
                            break;
                        }
                        break;

                    case 2:
                        input.nextLine();
                        System.out.println("======================================");
                        System.out.println("=              EDIT POSITION         =");
                        System.out.println("======================================");
                        System.out.println("======================================");
                        System.out.println("=               Position             =");
                        System.out.println("======================================");
                        System.out.println("=        1. Manager                  =");
                        System.out.println("=        2. Department Leader        =");
                        System.out.println("======================================");

                        do {
                            error = false;
                            System.out.print("Enter your choice > ");
                            try {
                                choice = input.nextInt();
                            } catch (Exception e) {
                                System.out.println("Invalid Input!");
                                error = true;
                                choice = 0;
                            }
                            if (choice != 1 && choice != 2) {
                                error = true;
                                System.out.println(RED + "Invalid input. Please enter again!");
                            }

                        } while (error);

                        if (choice == 1) {
                            newPosition = "Manager";
                        } else {
                            newPosition = "Department Leader";
                        }

                        System.out.println("-------------------------------------------");
                        System.out.println("                RESET POSITION             ");
                        System.out.println("-------------------------------------------");
                        System.out.println("Original | " + adminArray.get(currentIndex).getPosition());
                        System.out.println("New      | " + newPosition);
                        System.out.println("-------------------------------------------");

                        do {
                            error = false;
                            System.out.print("Confirm to edit? (Y/N) > ");
                            confirm = input.next().charAt(0);
                            confirm = Character.toUpperCase(confirm);

                            check = checkYesNo(confirm);

                            if (!check) {
                                error = true;
                                System.out.println(RED + "Invalid input. Please enter again!" + RESET);
                            }
                        } while (error);

                        if (confirm == 'Y') {
                            adminArray.get(currentIndex).setPosition(newPosition);
                            try {
                                writeAdmin(adminArray);
                            } catch (IOException ex) {
                                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            System.out.println("Successful edit");

                        } else {
                            break;
                        }
                        break;

                    case 3:
                        input.nextLine();
                        System.out.println("======================================");
                        System.out.println("=          EDIT PHONE NUMBER         =");
                        System.out.println("======================================");

                        do {
                            error = false;
                            duplicate = false;

                            System.out.print("Enter New Phone Number > ");
                            latest = input.nextLine();

                            error = super.checkPhoneNumberFormat(latest);
                            duplicate = checkAdminPhoneNumberDuplicate(adminArray, cashierArray, latest);

                            if (error) {
                                System.out.println(RED + "Invalid format. Please enter again!" + RESET);
                                System.out.println(RED + "Example: 012-3456789" + RESET);
                            }

                            if (duplicate) {
                                System.out.println(RED + "RECORD ALREADY EXIST!" + RESET);
                            }

                        } while (error || duplicate);

                        System.out.println("-------------------------------------------");
                        System.out.println("            RESET PHONE NUMBER             ");
                        System.out.println("-------------------------------------------");
                        System.out.println("Original | " + adminArray.get(currentIndex).getPhoneNumber());
                        System.out.println("New      | " + latest);
                        System.out.println("-------------------------------------------");

                        do {
                            error = false;
                            System.out.print("Confirm to edit? (Y/N) > ");
                            confirm = input.next().charAt(0);
                            confirm = Character.toUpperCase(confirm);

                            check = checkYesNo(confirm);

                            if (!check) {
                                error = true;
                                System.out.println(RED + "Invalid input. Please enter again!" + RESET);
                            }
                        } while (error);

                        if (confirm == 'Y') {
                            adminArray.get(currentIndex).setPhoneNumber(latest);
                            try {
                                writeAdmin(adminArray);
                            } catch (IOException ex) {
                                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            System.out.println("Successful edit");

                        } else {
                            break;
                        }
                        break;

                    case 4:
                        input.nextLine();
                        System.out.println("======================================");
                        System.out.println("=             EDIT EMAIL             =");
                        System.out.println("======================================");

                        do {
                            error = false;
                            duplicate = false;
                            System.out.print("Enter New Email > ");
                            latest = input.nextLine();

                            error = super.checkEmailFormat(latest);
                            duplicate = checkAdminEmailDuplicate(adminArray, cashierArray, latest);

                            if (error) {
                                System.out.println(RED + "Invalid Format. Please enter again!" + RESET);
                                System.out.println(RED + "Example: abc123@gmail.com" + RESET);
                            }

                            if (duplicate) {
                                System.out.println(RED + "RECORD ALREADY EXIST!" + RESET);
                            }

                        } while (error || duplicate);

                        System.out.println("-------------------------------------------");
                        System.out.println("                RESET EMAIL                ");
                        System.out.println("-------------------------------------------");
                        System.out.println("Original | " + adminArray.get(currentIndex).getEmail());
                        System.out.println("New      | " + latest);
                        System.out.println("-------------------------------------------");

                        do {
                            error = false;
                            System.out.print("Confirm to edit? (Y/N) > ");
                            confirm = input.next().charAt(0);
                            confirm = Character.toUpperCase(confirm);

                            check = checkYesNo(confirm);

                            if (!check) {
                                error = true;
                                System.out.println(RED + "Invalid input. Please enter again!" + RESET);
                            }

                        } while (error);

                        if (confirm == 'Y') {
                            adminArray.get(currentIndex).setEmail(latest);
                            try {
                                writeAdmin(adminArray);
                            } catch (IOException ex) {
                                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            System.out.println("Successful edit");

                        } else {
                            break;
                        }
                        break;

                    case 5:
                        input.nextLine();
                        System.out.println("======================================");
                        System.out.println("=           EDIT ADDRESS             =");
                        System.out.println("======================================");

                        do {
                            error = false;
                            System.out.print("Enter New Address > ");
                            latest = input.nextLine();

                            error = checkAdminAddressFormat(latest);

                            if (error) {
                                System.out.println(RED + "Invalid Format. Please enter again!" + RESET);
                                System.out.println(RED + "Example: 12, Jalan PV15, Setapak" + RESET);
                            }

                        } while (error);

                        System.out.println("-------------------------------------------");
                        System.out.println("                RESET ADDRESS             ");
                        System.out.println("-------------------------------------------");
                        System.out.println("Original | " + adminArray.get(currentIndex).getAddress());
                        System.out.println("New      | " + latest);
                        System.out.println("-------------------------------------------");

                        do {
                            error = false;
                            System.out.print("Confirm to edit? (Y/N) > ");
                            confirm = input.next().charAt(0);
                            confirm = Character.toUpperCase(confirm);

                            check = checkYesNo(confirm);

                            if (!check) {
                                error = true;
                                System.out.println(RED + "Invalid input. Please enter again!" + RESET);
                            }
                        } while (error);

                        if (confirm == 'Y') {
                            adminArray.get(currentIndex).setAddress(latest);
                            try {
                                writeAdmin(adminArray);
                            } catch (IOException ex) {
                                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            System.out.println("Successful edit");

                        } else {
                            break;
                        }
                        break;

                    case 6:
                        input.nextLine();
                        System.out.println("======================================");
                        System.out.println("=          EDIT BASIC SALARY         =");
                        System.out.println("======================================");

                        do {
                            error = false;
                            System.out.print("Enter New Basic Salary > ");
                            try {
                                latestNum = input.nextDouble();
                            } catch (Exception e) {
                                System.out.println("Invalid Input!");
                                error = true;
                                latestNum = 0;
                            }

                            if (latestNum < 1000 || latestNum > 15000) {
                                error = true;
                            }

                            if (error) {
                                System.out.println(RED + "Invalid Input. Please enter again!" + RESET);
                            }

                        } while (error);

                        System.out.println("-------------------------------------------");
                        System.out.println("             RESET BASIC SALARY            ");
                        System.out.println("-------------------------------------------");
                        System.out.println("Original | " + adminArray.get(currentIndex).getBasicSalary());
                        System.out.println("New      | " + latestNum);
                        System.out.println("-------------------------------------------");

                        do {
                            error = false;
                            System.out.print("Confirm to edit? (Y/N) > ");
                            confirm = input.next().charAt(0);
                            confirm = Character.toUpperCase(confirm);

                            check = checkYesNo(confirm);

                            if (!check) {
                                error = true;
                                System.out.println(RED + "Invalid input. Please enter again!" + RESET);
                            }

                        } while (error);

                        if (confirm == 'Y') {
                            adminArray.get(currentIndex).setBasicSalary(latestNum);
                            try {
                                writeAdmin(adminArray);
                            } catch (IOException ex) {
                                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            System.out.println("Successful edit");

                        } else {
                            break;
                        }
                        break;
                    case 7:
                        input.nextLine();
                        System.out.println("======================================");
                        System.out.println("=           EDIT BONUS RATE          =");
                        System.out.println("======================================");

                        do {
                            error = false;
                            System.out.print("Enter New Basic Salary > ");
                            try {
                                latestNum = input.nextDouble();
                            } catch (Exception e) {
                                System.out.println("Invalid Input!");
                                error = true;
                                latestNum = 0;
                            }

                            if (latestNum < 1000 || latestNum > 15000) {
                                error = true;
                            }

                            if (error) {
                                System.out.println(RED + "Invalid Input. Please enter again!" + RESET);
                            }

                        } while (error);

                        do {
                            error = false;
                            System.out.print("Enter New Bonus Rate: ");
                            try {
                                latestNum2 = input.nextDouble();
                            } catch (Exception e) {
                                System.out.println("Invalid Input!");
                                error = true;
                                latestNum2 = 0;
                            }
                            if (latestNum2 > 1.00 || latestNum2 < 0.00) {
                                error = true;
                            }

                            if (error) {
                                System.out.println(RED + "Invalid Input. Please enter again!" + RESET);

                            }

                        } while (error);

                        System.out.println("-------------------------------------------");
                        System.out.println("            RESET BASIC SALARY             ");
                        System.out.println("-------------------------------------------");
                        System.out.println("Original | " + adminArray.get(currentIndex).getBasicSalary());
                        System.out.println("New      | " + latestNum);
                        System.out.println("-------------------------------------------");
                        System.out.println("            RESET BONUS RATE");
                        System.out.println("-------------------------------------------");
                        System.out.println("Original | " + adminArray.get(currentIndex).getBonusRate());
                        System.out.println("New      | " + latestNum2);
                        System.out.println("-------------------------------------------");

                        do {
                            error = false;
                            System.out.print("Confirm to edit? (Y/N) > ");
                            confirm = input.next().charAt(0);
                            confirm = Character.toUpperCase(confirm);

                            check = checkYesNo(confirm);

                            if (!check) {
                                error = true;
                                System.out.println(RED + "Invalid input. Please enter again!" + RESET);
                            }

                        } while (error);

                        if (confirm == 'Y') {
                            adminArray.get(currentIndex).setBasicSalary(latestNum);
                            adminArray.get(currentIndex).setBonusRate(latestNum2);
                            try {
                                writeAdmin(adminArray);
                            } catch (IOException ex) {
                                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            System.out.println("Successful edit");

                        } else {
                            break;
                        }
                        break;
                    case 8:
                        return;

                }

                do {
                    error = false;
                    System.out.print("Edit more? (Y/N) >  ");
                    confirm = input.next().charAt(0);
                    confirm = Character.toUpperCase(confirm);

                    check = checkYesNo(confirm);

                    if (!check) {
                        error = true;
                        System.out.println(RED + "Invalid input. Please enter again!" + RESET);
                    }
                } while (error);

                input.nextLine();

            } while (confirm == 'Y');

            systemPause();

        }

    }

    @Override
    public void search() {
        Admin admin = new Admin();
        ArrayList<Admin> adminArray = new ArrayList();

        Scanner sc = new Scanner(System.in);

        String searchId;
        String searchName;
        String searchPosition;
        int choice;
        boolean error = false;
        boolean exist = false;

        try {
            readAdminFromFile(adminArray);
        } catch (FileNotFoundException ex) {
            System.out.println(RED + "Cannot read the file!" + RESET);
        }

        if (adminArray.isEmpty()) {
            System.out.println("========================================");
            System.out.println("=             No Admin Record!         =");
            System.out.println("========================================");
        } else {

            do {
                clearScreen();

                System.out.println("Current Login > " + CURRENTNAME);

                error = false;
                System.out.println("=========================================");
                System.out.println("=              SEARCH ADMIN             =");
                System.out.println("=========================================");
                System.out.println("=          1. Search by ID              =");
                System.out.println("=          2. Search by Name            =");
                System.out.println("=          3. Search by Position        =");
                System.out.println("=          4. Exit                      =");
                System.out.println("=========================================");

                System.out.print("Enter your choice > ");
                try {
                    choice = input.nextInt();
                } catch (Exception e) {
                    System.out.println("Invalid input!");
                    error = true;
                    choice = 0;
                }
                input.nextLine();

                switch (choice) {
                    case 1:
                        clearScreen();
                        System.out.println("=====================================");
                        System.out.println("=             Search by ID          =");
                        System.out.println("=====================================");
                        System.out.print("Enter ID to search > ");
                        searchId = input.nextLine();

                        exist = checkAdmin(adminArray, searchId);

                        if (exist == true) {
                            searchAdminId(adminArray, searchId);
                            systemPause();
                        } else {
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

                        exist = checkAdminName(adminArray, searchName);

                        if (exist == true) {
                            searchAdminName(adminArray, searchName);
                            systemPause();
                        } else {
                            System.out.println("===================================");
                            System.out.println("=          No Record Found!       =");
                            System.out.println("===================================");
                            systemPause();
                        }
                        break;

                    case 3:
                        clearScreen();
                        do {
                            error = false;
                            System.out.println("=======================================");
                            System.out.println("=          Search by Position         =");
                            System.out.println("=======================================");
                            System.out.print("\n");
                            System.out.println("==================================");
                            System.out.println("=            Position            =");
                            System.out.println("==================================");
                            System.out.println("=      1. Manager                =");
                            System.out.println("=      2. Department Leader      =");
                            System.out.println("==================================");
                            System.out.print("Enter your choice > ");
                            try {
                                choice = input.nextInt();
                            } catch (Exception e) {
                                System.out.println("Invalid input!");
                                error = true;
                                choice = 0;
                            }

                            switch (choice) {
                                case 1:
                                    searchPosition = "Manager";
                                    searchAdminPosition(adminArray, searchPosition);
                                    break;
                                case 2:
                                    searchPosition = "Department Leader";
                                    searchAdminPosition(adminArray, searchPosition);
                                    break;
                                default:
                                    System.out.println("Invalid input. Please enter again!");
                                    error = true;
                            }

                        } while (error);

                        systemPause();

                    case 4:
                        return;

                    default:
                        error = true;
                        System.out.println("Invalid input. Please enter again!");

                }

            } while (error);

        }

    }

    public void editAdminInformation(ArrayList<Admin> adminArray, String id, String password) {
        Admin admin = new Admin();

        for (Admin ad : adminArray) {
            if (ad.getId().equals(id)) {
                System.out.println("======================================================================================================");
                System.out.println("=                                              ADMIN                                                 =");
                System.out.println("======================================================================================================");
                System.out.printf("   ID                : %-25s |                      Edit                    \n", ad.getId());
                System.out.printf("   Password          : %-25s | ===================================================\n", ad.getPassword());
                System.out.printf("   Name              : %-25s |                 1. Password                  \n", ad.getName());
                System.out.printf("   IC                : %-25s |                 2. Position                  \n", ad.getIc());
                System.out.printf("   Position          : %-25s |                 3. Phone Number              \n", ad.getPosition());
                System.out.printf("   Phone Number      : %-25s |                 4. Email                     \n", ad.getPhoneNumber());
                System.out.printf("   Email             : %-25s |                 5. Address                   \n", ad.getEmail());
                System.out.printf("   Address           : %-25s |                 6. Basic Salary              \n", ad.getAddress());
                System.out.printf("   Basic Salary      : %-25.2f |                 7. Bonus Rate                \n", ad.getBasicSalary());
                System.out.printf("   Bonus Rate        : %-25.2f |                 8. Exit                      \n", ad.getBonusRate());
                System.out.printf("   Sales             : %-25.2f |                                            \n", ad.getSales());
                System.out.printf("   Total Salary      : %-25.2f |                                            \n", ad.getTotalSalary());
                System.out.printf("=======================================================================================================");

            }
        }

    }

    public void searchAdminId(ArrayList<Admin> adminArray, String id) {

        Admin admin = new Admin();

        for (Admin ad : adminArray) {
            if (ad.getId().equals(id)) {
                System.out.println("======================================================");
                System.out.println("=                       ADMIN                        =");
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
                System.out.println("   Bonus Rate        : " + ad.getBonusRate());
                System.out.println("   Sales             : " + ad.getSales());
                System.out.println("   Total Salary      : " + ad.getTotalSalary());
                System.out.println("======================================================");
            }
        }
    }

    public void searchAdminName(ArrayList<Admin> adminArray, String name) {
        Admin admin = new Admin();

        for (Admin ad : adminArray) {
            if (ad.getName().equals(name)) {
                System.out.println("======================================================");
                System.out.println("=                       ADMIN                        =");
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
                System.out.println("   Bonus Rate        : " + ad.getBonusRate());
                System.out.println("   Sales             : " + ad.getSales());
                System.out.println("   Total Salary      : " + ad.getTotalSalary());
                System.out.println("======================================================");
            }
        }
    }

    public void searchAdminPosition(ArrayList<Admin> adminArray, String position) {
        Admin admin = new Admin();
        int count = 0;

        System.out.println("=====================================================================================================");
        System.out.printf("%-8s %-25s %-20s %-15s %-20s\n", "ID", "NAME", "POSITION", "PHONE NUMBER", "EMAIL");
        System.out.println("=====================================================================================================");

        for (Admin ad : adminArray) {
            if (ad.getPosition().equals(position)) {
                System.out.printf("%-8s %-25s %-20s %-15s %-20s\n", ad.getId(), ad.getName(), ad.getPosition(), ad.getPhoneNumber(), ad.getEmail());
                count++;
            }
        }

        if (count == 0) {
            System.out.println("                               NO RECORD FOUND!");
        }

        System.out.println("=====================================================================================================");

    }

    public void displayAdminInformation() {
        clearScreen();

        Admin admin = new Admin();
        ArrayList<Admin> adminArray = new ArrayList();

        System.out.println("======================================================");
        System.out.println("=                       ADMIN                        =");
        System.out.println("======================================================");
        System.out.println("   ID                : " + super.getId());
        System.out.println("   Password          : " + super.getPassword());
        System.out.println("   Name              : " + super.getName());
        System.out.println("   IC                : " + super.getIc());
        System.out.println("   Position          : " + super.getPosition());
        System.out.println("   Phone Number      : " + super.getPhoneNumber());
        System.out.println("   Email             : " + super.getEmail());
        System.out.println("   Address           : " + super.getAddress());
        System.out.println("   Basic Salary      : " + super.getBasicSalary());
        System.out.println("   Bonus Rate        : " + getBonusRate());
        System.out.println("   Sales             : " + getSales());
        System.out.println("   Total Salary      : " + getTotalSalary());
        System.out.println("======================================================");

    }

    public void displayAdminInformation(ArrayList<Admin> adminArray, String id, String password) {
        clearScreen();

        Admin admin = new Admin();

        for (Admin ad : adminArray) {
            if (ad.getId().equals(id) && ad.getPassword().equals(password)) {
                System.out.println("======================================================");
                System.out.println("=                       ADMIN                        =");
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
                System.out.println("   Bonus Rate        : " + ad.getBonusRate());
                System.out.println("   Sales             : " + ad.getSales());
                System.out.println("   Total Salary      : " + ad.getTotalSalary());
                System.out.println("======================================================");
            }
        }

    }

    @Override
    public void view() {
        clearScreen();
        Scanner sc = new Scanner(System.in);

        int count = 0;

        ArrayList<Admin> adminArray = new ArrayList<>();

        try {
            readAdminFromFile(adminArray);
        } catch (FileNotFoundException ex) {
            System.out.println(RED + "Cannot read the file!");
            return;
        }

        System.out.println("Current Login > " + CURRENTNAME);

        if (adminArray.isEmpty()) {
            System.out.println("========================================");
            System.out.println("=             No Admin Record!         =");
            System.out.println("========================================");
        } else {

            System.out.println("                              =============================================================================");
            System.out.println("                              =                            View Admin Information                         =");
            System.out.println("                              =============================================================================");

            System.out.printf("%-8s %-25s %-20s %-15s %-25s %-13s %-8s %-10s %-15s\n", "ID", "Name", "Position", "Phone Number", "Email", "Basic Salary", "Sales", "Bonus Rate", "Total Salary");
            System.out.println("===================================================================================================================================================");
            for (Admin admin : adminArray) {
                count++;
                System.out.printf("%-8s %-25s %-20s %-15s %-25s %-13.2f %-8.2f %-10.2f %-15.2f\n",
                        admin.getId(), admin.getName(), admin.getPosition(), admin.getPhoneNumber(), admin.getEmail(), admin.getBasicSalary(),
                        admin.getSales(), admin.getBonusRate(), admin.getTotalSalary());
            }

            System.out.println("\nTotal Number of Admin > " + count);
        }

        systemPause();

    }

    @Override
    public void salesPerformance() {

        clearScreen();

        ArrayList<Admin> adminArray = new ArrayList<>();

        try {
            readAdminFromFile(adminArray);
        } catch (FileNotFoundException ex) {
            System.out.println(RED + "Cannot read the file!");
            return;
        }

        System.out.println("Current Login > " + CURRENTNAME);

        if (adminArray.isEmpty()) {
            System.out.println("========================================");
            System.out.println("=             No Admin Record!         =");
            System.out.println("========================================");
        } else {

            System.out.println("                                   ===============================================");
            System.out.println("                                   =           ADMIN SALES PERFORMANCE           =");
            System.out.println("                                   ===============================================");

            System.out.println("=============================================================================================================================");

            System.out.printf("%-8s %-25s %-13s %-8s %-10s %-15s\n", "ID", "Name", "Basic Salary", "Sales", "Bonus Rate", "Total Salary");

            for (Admin admin : adminArray) {
                System.out.printf("%-8s %-25s %-13.2f %-8.2f %-10.2f %-15.2f\n",
                        admin.getId(), admin.getName(), admin.getBasicSalary(),
                        admin.getSales(), admin.getBonusRate(), admin.getTotalSalary());
            }

            /* get sales from Sales Order
                maybe this can directly update from sales order? just move this code to the sales order
                
                try {
                if(new Validation().checkAdmin(adminArray, searchID) == true){
                    updateSales(arrayAdmin, searchID, sales);
                    updateTotalSales(arrayAdmin, searchID, sales, salary);
                    writeAdmin(adminArray);
                    } catch(IOException ex){
                    System.out.println("Unable to edit the password!");
                    }
                           
             */
        }
        systemPause();
    }

    //Other methods
    public String generateAdminID(ArrayList<Admin> adminArray) {
        String generatedAdminID;

        if (adminArray.isEmpty()) {
            generatedAdminID = "AD0001";
        } else {
            generatedAdminID = adminArray.get(adminArray.size() - 1).getId();
            int bufferAdminIDNum = Integer.parseInt(generatedAdminID.replaceAll("\\D+", ""));
            generatedAdminID = String.format("AD%04d", bufferAdminIDNum + 1);
        }

        return generatedAdminID;
    }

    //Validation
    public boolean checkAdmin(ArrayList<Admin> adminArray, String searchId) {
        boolean exist = false;
        for (Admin ad : adminArray) {
            if (ad.getId().equals(searchId)) {
                exist = true;
            }
        }

        return exist;
    }

    public boolean checkAdminName(ArrayList<Admin> adminArray, String searchName) {
        boolean exist = false;
        for (Admin ad : adminArray) {
            if (ad.getName().equals(searchName)) {
                exist = true;
            }
        }

        return exist;
    }

    public boolean checkAdminPassword(ArrayList<Admin> adminArray, String searchPassword) {
        boolean exist = false;
        for (Admin ad : adminArray) {
            if (ad.getPassword().equals(searchPassword)) {
                exist = true;
                break;
            }
        }

        return exist;
    }

    public boolean checkAdminDuplicateIc(ArrayList<Admin> adminArray, ArrayList<Cashier> cashierArray, String checkIC) {
        boolean duplicate = false;

        for (Admin ad : adminArray) {
            if (ad.getIc().equals(checkIC)) {
                duplicate = true;
                break;
            }
        }

        for (Cashier ch : cashierArray) {
            if (ch.getIc().equals(checkIC)) {
                duplicate = true;
                break;
            }
        }

        return duplicate;

    }

    public boolean checkAdminPhoneNumberDuplicate(ArrayList<Admin> adminArray, ArrayList<Cashier> cashierArray, String checkPhoneNumber) {
        boolean duplicate = false;

        for (Admin admin : adminArray) {
            if (admin.getPhoneNumber().equals(checkPhoneNumber)) {
                duplicate = true;
                break;
            }
        }

        for (Cashier cashier : cashierArray) {
            if (cashier.getPhoneNumber().equals(checkPhoneNumber)) {
                duplicate = true;
                break;
            }
        }

        return duplicate;
    }

    public boolean checkAdminEmailDuplicate(ArrayList<Admin> adminArray, ArrayList<Cashier> cashierArray, String checkEmail) {
        boolean duplicate = false;

        for (Admin admin : adminArray) {
            if (admin.getPhoneNumber().equals(checkEmail)) {
                duplicate = true;
                break;
            }
        }

        for (Cashier cashier : cashierArray) {
            if (cashier.getPhoneNumber().equals(checkEmail)) {
                duplicate = true;
                break;
            }
        }

        return duplicate;
    }

    public boolean checkAdminAddressFormat(String checkAddress) {
        boolean error = false;
        int countDigit = 0;
        int countLetter = 0;

        for (int i = 0; i < checkAddress.length(); i++) {
            char check = checkAddress.charAt(i);
            if (Character.isLetter(check)) {
                countLetter++;
            } else if (Character.isDigit(check)) {
                countDigit++;
            }
        }

        if (countDigit == 0 || countLetter == 0) {
            error = true;
        }

        return error;
    }

    public static int checkAdminIndexNumber(ArrayList<Admin> adminArray, String searchID, String password) {
        int currentIndex = 0;
        for (int i = 0; i < adminArray.size(); i++) {
            if (searchID.equals(adminArray.get(i).getId())) {
                currentIndex = i;
            }
        }

        return currentIndex;
    }

    public boolean checkYesNo(char check) {
        boolean checkYN = true;

        if (check != 'Y' && check != 'N') {
            checkYN = false;
        }

        return checkYN;
    }

    public static void adminMenu() {
        //menu list of main program    

        int choice;
        boolean error;

        do {
            clearScreen();
            logo();
            error = false;
            System.out.println("Current Login > " + CURRENTNAME);

            System.out.println("===========================================");
            System.out.println("=                  MENU                   =");
            System.out.println("===========================================");
            System.out.println("=        1. Staff Manage                  =");
            System.out.println("=        2. Display Stock Available Menu  =");
            System.out.println("=        3. Sales Order                   =");
            System.out.println("=        4. Membership Register           =");
            System.out.println("=        5. Stock Management              =");
            System.out.println("=        6. Sales Report                  =");
            System.out.println("=        0. Exit                          =");
            System.out.println("===========================================");

            System.out.print("Enter your choice > ");
            choice = Validation.getIntegerInput();

            switch (choice) {
                case 1 -> {
                    staffMenu();
                }

                case 2 -> {
                    Stock.stockAvailableMenu();
                }

                case 3 -> {
                    Sales.salesMenu();
                }

                case 4 -> {
                    new Member().memberMenu();
                }

                case 5 -> {
                    Stock.stockMainMenu();
                }

                case 6 -> {
                    Report.salesReportMenu();
                }

                case 0 -> {
                }

                default -> {
                    System.out.println(RED + "Invalid input. Please enter again!" + RESET);
                    systemPause();
                }

            }
        } while (choice != 0 || error);

    }

    public static boolean checkAdminIDPW(ArrayList<Admin> adminArray, String searchID, String searchPassword) {
        boolean exist = false;
        for (Admin ad : adminArray) {
            if (ad.getId().equals(searchID) && ad.getPassword().equals(searchPassword)) {
                exist = true;
            }
        }

        return exist;
    }

    public static void printAdminLoginName(ArrayList<Admin> adminArray, String id, String password) {
        int currentIndex = 0;

        currentIndex = checkAdminIndexNumber(adminArray, id, password);
        System.out.println("\nWelcome, " + adminArray.get(currentIndex).getName() + "!");
    }

    public static String storeAdminLoginName(ArrayList<Admin> adminArray, String id, String password) {
        int currentIndex = 0;

        currentIndex = checkAdminIndexNumber(adminArray, id, password);
        return adminArray.get(currentIndex).getName();
    }

}
