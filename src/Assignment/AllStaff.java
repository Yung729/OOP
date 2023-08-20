/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;
import java.util.Date;
/**
 *
 * @author Yung
 */
public class AllStaff {
        private String id;
        private String name;
        private String ic;
        private String position;
        private String phoneNumber;
        private String email;
        private String address;
        private Date hireDate;
        private static int noOfStaff = 0;
        
        //constructor
        public AllStaff(){
            this("","","","","","", "");
        }
        
        public AllStaff(String id, String name, String ic, String position, String phoneNumber, String email, String address){
            this.id = id;
            this.name = name;
            this.ic = ic;
            this.position = position;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.address = address;
            hireDate = new Date();
            noOfStaff++;
        }
        
        //getter
        public String getId(){
            return id;
        }
        
        public String getName(){
            return name;
        }
        
        public String getIc(){
            return ic;
        }
        
        public String getPosition(){
            return position;
        }
        
        public String getPhoneNumber(){
            return phoneNumber;
        }
        
        public String getEmail(){
            return email;
        }
        
        public String getAddress(){
            return address;
        }
        
        public Date getHireDate(){
            return hireDate;
        }
        
        //setter
        public void setId(String id){
            this.id = id;
        }
        
        public void setName(String name){
            this.name = name;
        }
        
        public void setIc(String ic){
            this.ic = ic;
        }
        
        public void setPosition(String position){
            this.position = position;
        }
        
        public void setPhoneNumber(String phoneNumber){
            this.phoneNumber = phoneNumber;
        }
        
        public void setEmail(String email){
            this.email = email;
        }
        
        public void setAddress(String address){
            this.address = address;
        }
        
        //toString
        public String toString(){
            return "\nID: " + id + 
                   "\nName: " + name + 
                   "\nIC: " + ic + 
                   "\nPosition: " + position + 
                   "\nPhone Number: " + phoneNumber + 
                   "\nEmail: " + email + 
                   "\nAddress: " + address;
        }
}