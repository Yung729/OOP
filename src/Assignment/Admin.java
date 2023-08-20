/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

/**
 *
 * @author Yung
 */
public class Admin extends AllStaff{
    private double basicSalary;
    private double bonusRate;
    private double sales;
    private double totalSalary;
    private static int noOfAdmin = 0;
    
    
    //constructor
    public Admin(){
        super("", "", "", "", "", "", "");
    }
    
    public Admin(String id, String name, String ic, String position, String phoneNumber, String email, String address, double basicSalary, double bonusRate, double sales, double totalSalary){
        super(id, name, ic, position, phoneNumber, email, address);
        this.basicSalary = basicSalary;
        this.bonusRate = bonusRate;
        this.sales = sales;
        this.totalSalary = totalSalary;
        noOfAdmin++;
    }
    
    //getter
    public double getBasicSalary(){
        return basicSalary;
    }
    
    public double getBonusRate(){
        return bonusRate;
    }
    
    public double getSales(){
        return sales;
    }
    
    public double getTotalSalary(){
        return totalSalary;
    }
    
    //setter
    public void setBasicSalary(double basicSalary){
        this.basicSalary = basicSalary;
    }
    
    public void setBonusRate(double bonusRate){
        this.bonusRate = bonusRate;
    }
    
    public void setSales(double sales){
        this.sales = sales;
    }
    
    public void setTotalSalary(double totalSalary){
        this.totalSalary = totalSalary;
    }
    
    //other method
    public double calculateBonus(){
        return sales * bonusRate;
    }
    
    public double calculateTotalSalary(){
        return basicSalary + calculateBonus();
    }
    
}
