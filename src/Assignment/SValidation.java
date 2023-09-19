/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

/**
 *
 * @author xinru
 */
import java.util.Scanner;
import static java.lang.Character.toUpperCase;

public class SValidation {
    static Scanner input = new Scanner(System.in);

    public static boolean checkYesNo(char input) {
        return toUpperCase(input) == 'Y' || toUpperCase(input) == 'N';
}

    public static int getIntegerInput(){
        //exception handling for int input
        int value;
        try{
            value = input.nextInt();
            input.nextLine();

        }catch(Exception e){
            input.nextLine();
            return -1; //invalid
        }
        return value;
    }

    public static int getIntegerInput(String question) {
        String res = getStringInput(question);
        if (res == null)  return -1;
        if (!res.matches("\\d+"))  return -1;  //if not number

        return Integer.parseInt(res);
    }

    public static double getDoubleInput(){
        double value;
        try{
            value = input.nextDouble();
            input.nextLine();

        }catch(Exception e){
            input.nextLine();
            return -1;
        }
        return value;
    }

    public static String getStringInput(){
        String value;
        try{
            value = input.nextLine();
        }catch(Exception e){
            input.nextLine();
            return null;
        }
        return value;
    }

    public static String getStringInput(String question) {
        String value;
        System.out.print(question);
        try {
            value = input.next();
        } catch (Exception e) {
            input.nextLine();
            return null;
        }
        return value;
    }

    public static int getIsContinue(String ans) {
        if(toUpperCase(ans.charAt(0)) != 'Y' && toUpperCase(ans.charAt(0)) != 'N'){
            return -1;
        }

        if (toUpperCase(ans.charAt(0)) == 'Y') {
            return 1;
        } else {
            return 0;
        }
    }

    public static boolean checkIsContinue(String question) {
        String ans;
        do {
            ans = getStringInput(question);
            if (ans == null) {
                System.out.println("Invalid input!");
                continue;
            }
            if (toUpperCase(ans.charAt(0)) != 'Y' && toUpperCase(ans.charAt(0)) != 'N') {
                System.out.println("Invalid input!");
                continue;
            }
            break;
        } while (true);

        return toUpperCase(ans.charAt(0)) == 'Y';
    }
}
