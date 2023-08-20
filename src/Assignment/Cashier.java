/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

/**
 *
 * @author Yung
 */
public class Cashier extends AllStaff{
    private double basicSalary;
    private double sales;
    private double commissionRate;
    private static int noOfCashier = 0;
    
    
    //constructor
    public Cashier(){
        super("", "", "", "", "", "", "");
    }
    
    public Cashier(String id, String name, String ic, String position, String phoneNumber, String email, String address, double basicSalary, double sales, double commissionRate){
        super(id, name, ic, position, phoneNumber, email, address);
        this.basicSalary = basicSalary;
        this.sales = sales;
        this.commissionRate = commissionRate;
        noOfCashier++;
    }
    
    //getter
    public double getBasicSalary(){
        return basicSalary;
    }
    
    public double getSales(){
        return sales;
    }
    
    public double getCommissionRate(){
        return commissionRate;
    }
    
    //setter
    public void setBasicSalary(double basicSalary){
        this.basicSalary = basicSalary;
    }
    
    public void setSales(double sales){
        this.sales = sales;
    }
    
    public void setCommissionRate(double commissionRate){
        this.commissionRate = commissionRate;
    }
    
    //other methods
    //public String toString()
    
    public double calculateSalary(){
        return basicSalary + (sales * commissionRate);
    }
    
    
}

