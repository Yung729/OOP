/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

import static java.lang.Character.toUpperCase;
import java.util.Scanner;

/**
 *
 * @author Yung
 */
public class Validation {
    
    static Scanner input = new Scanner(System.in);
    
    public static boolean checkYesNo(char input) {  

        boolean check = true;

        if (toUpperCase(input) != 'Y' && toUpperCase(input) != 'N') {
            check = false;
        }

        return check;
    }
    
    public static int getIntegerInput() { //exception handling for int input
        int value;
        try {
            value = input.nextInt();
            input.nextLine();
        } catch (Exception e) {
            input.nextLine();
            return -1; //invalid
        }
        return value;
    }
    
    public static double getDoubleInput() { //exception handling for int input
        double value;
        try {
            value = input.nextDouble();
            input.nextLine();
        } catch (Exception e) {
            input.nextLine();
            return -1; //invalid
        }
        return value;
    }
    
    
    
}
