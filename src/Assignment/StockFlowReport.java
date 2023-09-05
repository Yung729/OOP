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
    
    StockFlowReport(){
       
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getStockFlowDate() {
        return stockFlowDate;
    }

    public void setStockFlowDate(String stockFlowDate) {
        this.stockFlowDate = stockFlowDate;
    }

    public StockFlowReport(String id, String quantity, String stockFlowDate) {
        this.id = id;
        this.quantity = quantity;
        this.stockFlowDate = stockFlowDate;
    }
    
    public void report(){
        ArrayList<StockFlowReport> flowArray = new ArrayList<>();
        try {
            readStockFromFile(flowArray);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StockFlowReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Assignment.clearScreen();
        System.out.println("Stock Flow Report");
        System.out.println("=================");
        for (StockFlowReport report:flowArray) {
            System.out.println(report.getId() +" "+ report.getQuantity() +" " + report.getStockFlowDate());
        }
        
        Assignment.systemPause();
    }
    
    public static void writeStockToFile(String id,int quantity,LocalDate date) throws IOException{
        try ( FileWriter writeBookFile = new FileWriter("StockFlow.txt",true)) {
            
                writeBookFile.write(id + '|' +quantity+ '|' + date +'\n');
            
            
        }
    }
    
    public static void readStockFromFile(ArrayList<StockFlowReport> obj ) throws FileNotFoundException{
        File readStockFile = new File("StockFlow.txt");
        
        if (readStockFile.exists()) {
             Scanner productRead = new Scanner(readStockFile);
            while (productRead.hasNextLine()) {
                String line = productRead.nextLine();
                String[] data = line.split("\\|");
                obj.add(new StockFlowReport(data[0],data[1],data[2]));
               
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
