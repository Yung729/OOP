/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Scanner;

/**
 *
 * @author Yung
 */
public class Assignment {

    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    
    public static final String REDBG = "\u001B[41m";
    public static final String GREENBG = "\u001B[42m";
    public static final String YELLOWBG = "\u001B[43m";
    public static final String BLUEBG = "\u001B[44m";
    public static final String PURPLEBG = "\u001B[45m";
    public static final String CYANBG = "\u001B[46m";
    public static final String RESET = "\u001B[0m";

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Employees.login();
    }
    
    public static void logo(){
        System.out.printf(PURPLE+"%s\n", "                      _"+RESET);
	System.out.printf(BLUE+"%s\n", "      _   _ _ __ ___ | |_"+RESET);
	System.out.printf(PURPLE+"%s\n", "     | | | | '_ ` _ \\| __|"+RESET);
	System.out.printf(BLUE+"%s\n", "     | |_| | | | | | | |_"+RESET);
	System.out.printf(PURPLE+"%s\n\n", "      \\__,_|_| |_| |_|\\__|"+RESET);

    }

    public static void systemPause(){
        Scanner buffer = new Scanner(System.in);
        System.out.print(GREEN+"Enter any key to continue...."+RESET);
        buffer.nextLine(); 
    }
    
    public static void systemPause(String sentence){
        Scanner buffer = new Scanner(System.in);
        System.out.print(sentence);
        buffer.nextLine(); 
    }
    
    public static void clearScreen() {  
         try {
            Robot robot = new Robot();
            robot.setAutoDelay(10);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_L);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_L);
        } catch (AWTException ex) {
            
        }
        
    }  
    
}
