/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

/**
 *
 * @author Yung
 */
public class Author {
    private String name;
    private int age;
    private boolean arrive;
    
    Author(String name,int age,boolean arrive){
        this.name = name;
        this.age = age;
        this.arrive = arrive;
    }
    

    //setter
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setAge(int age){
        this.age = age;
    }
    
    public void setArrive(boolean arrive){
        this.arrive = arrive;
    }
    
    //getter
    public String getName(){
        return name;
    }
    
    public int getAge(){
        return age;
    }
    
    public boolean checkArrive(){
        return arrive;
    }
        
    //method
    public double commemorativeOffer(boolean arrive){
        double offerRate = 0.0;
        if (arrive) {
            offerRate =  0.2;
        }
        return offerRate;
    }
    
    public  void displayAuthorDetail(){
            System.out.println("| Author Name :  " + name);
            System.out.println("| Author Age :  " + age);
            System.out.println("| Author status :  " + arrive);
            System.out.println("|                                  |");
            System.out.println("====================================");
    }
    
    public static void displayAuthorDetail(Book book){
            System.out.println("| Author Name :  " + book.author.name);
            System.out.println("| Author Age :  " + book.author.age);
            System.out.println("| Author status :  " + book.author.arrive);
            System.out.println("|                                  |");
            System.out.println("====================================");
    }
    
    @Override
    public String toString(){
        return String.format("%-15s    %d  %s",name,age,String.valueOf(arrive));
    }
  
    
}
