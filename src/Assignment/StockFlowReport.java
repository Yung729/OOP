/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yung
 */
public class StockFlowReport extends Report {
    private String id;
    private String quantity;
    private String stockFlowDate;
    private String staffID;
    
    StockFlowReport(){
       
    }


    public StockFlowReport(String id, String quantity, String stockFlowDate,String staffID) {
        this.id = id;
        this.quantity = quantity;
        this.stockFlowDate = stockFlowDate;
        this.staffID = staffID;
    }
    
    @Override
    public void printReport(){
        String buffer = "c";
        ArrayList<StockFlowReport> flowArray = new ArrayList<>();
        try {
            readStockFromFile(flowArray);
        } catch (FileNotFoundException ex) {
            System.out.println("Unable Read The File");
        }
        
        Assignment.clearScreen();
        Assignment.logo();
        System.out.println("                                        Stock Flow Report");
        System.out.println("============================================================================================");
        System.out.println("Date \t\t\t Stock ID \t\tQuantity \t\tIncharge staff");
        System.out.println("============================================================================================");
        for (StockFlowReport report:flowArray) {
            
            if (report.stockFlowDate.equals(buffer) || buffer.equals("")) {
                report.stockFlowDate = "";
            }
            System.out.printf("%-21s    %-20s   %-20s   %-20s\n",report.stockFlowDate,report.id , report.quantity ,report.staffID);
            buffer = report.stockFlowDate;
        }
        
        Assignment.systemPause();
    }
    
    public static void writeStockToFile(String id,int quantity,LocalDate date) throws IOException{
        try ( FileWriter writeBookFile = new FileWriter("StockFlow.txt",true)) {
            
                writeBookFile.write(id + '|' +quantity+ '|' + date +'|' + Employees.CURRENTID +'\n');
            
            
        }
    }
    
    public static void readStockFromFile(ArrayList<StockFlowReport> obj ) throws FileNotFoundException{
        File readStockFile = new File("StockFlow.txt");
        
        if (readStockFile.exists()) {
             Scanner productRead = new Scanner(readStockFile);
            while (productRead.hasNextLine()) {
                String line = productRead.nextLine();
                String[] data = line.split("\\|");
                obj.add(new StockFlowReport(data[0],data[1],data[2],data[3]));
               
            }
        }else {
            File createStockFile = new File("StockFlow.txt");
            try {
                createStockFile.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Stationary.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
           
            
        
    }
}
