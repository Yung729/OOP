/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sales;

/**
 *
 * @author user
 */
import java.util.ArrayList;

public class Report {
    private final ArrayList<Transaction> transactionList;

    public Report(){
        transactionList = new ArrayList<>();
        initTransactionList();
    }

    private void initTransactionList(){
        ArrayList<String> transactionIDList = FileHandler.getColumnByType(FileHandler.TRANSACTION_DB, "transactionID");
        for (String transactionID : transactionIDList) {
            transactionList.add(new Transaction(transactionID));
        }
    }

    public void printReport(){
        System.out.println("Display Report");
    }

    protected ArrayList<Transaction> getTransactionList(){
        return transactionList;
    }
}

