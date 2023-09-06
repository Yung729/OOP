/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

/**
 *
 * @Jesmine
 */
public class Employees {
        private String id;
        private String password;
        private String name;
        private String ic;
        private String position;
        private String phoneNumber;
        private String email;
        private String address;      
        private double basicSalary;
        
        //constructor
        public Employees(){
            this("","","","","","","", "", 0.0);
        }
        
        public Employees(String id, String password, String name, String ic, String position, String phoneNumber, String email, String address, double basicSalary){
            this.id = id;
            this.password = password;
            this.name = name;
            this.ic = ic;
            this.position = position;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.address = address;
            this.basicSalary = basicSalary;

        }
        
        //getter
        public String getId(){
            return id;
        }
        
        public String getPassword(){
            return password;
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
        
        public double getBasicSalary(){
            return basicSalary;
        }
        
        //setter
        public void setId(String id){
            this.id = id;
        }
        
        public void setPassword(String password){
            this.password = password;
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
        
        public void setBasicSalary(double basicSalary){
            this.basicSalary = basicSalary;
        }
      
        //toString
        @Override
        public String toString(){
            return "\nID: " + id + 
                   "\nPassword: " + password +
                   "\nName: " + name + 
                   "\nIC: " + ic + 
                   "\nPosition: " + position + 
                   "\nPhone Number: " + phoneNumber + 
                   "\nEmail: " + email + 
                   "\nAddress: " + address +
                   "\nBasic Salary: " + basicSalary;
        }
        
        //Validation
        public boolean checkPasswordFormat(String checkPassword){
        boolean error = false;
        int countDigit = 0;
        int countLetter = 0;
        
        for(int i = 0; i < checkPassword.length() ; i++){
            char same = checkPassword.charAt(i);
            if(Character.isLetter(same)){
                countLetter++;
            } else if(Character.isDigit(same)){
                countDigit++;
            }
        }
        
        if(countLetter == 0 || countDigit == 0){
            error = true;
        }
        
        if(checkPassword.length() < 8 || checkPassword.length() > 12){
            error = true;
        }
        
        return error;
    }
        
        public boolean checkIcFormat(String checkIC){
        boolean error = false;
        int countDigit = 0;
        int countLetter = 0;
        int countDash = 0;
        char dash = '-';
        
        for(int i = 0; i < checkIC.length(); i++){
            char check = checkIC.charAt(i);
            if(Character.isLetter(check)){
                countLetter++;
            } else if(Character.isDigit(check)){
                countDigit++;
            } else if(check == dash){
                countDash++;
            }
        }
        
        if(checkIC.length() == 14){

            if(checkIC.charAt(2) == '0'){
                if(checkIC.charAt(3) == '0'){
                    error = true;
                }
            } else if(checkIC. charAt(2) == '1'){
                if(checkIC.charAt(3) != '0' && checkIC.charAt(3) != '1' && checkIC.charAt(3) != '2'){
                    error = true;
                    }
            }
        
            if(checkIC.charAt(4) != '0' && checkIC.charAt(4) != '1' && checkIC.charAt(4) != '2' && checkIC. charAt(4) != '3'){
                error = true;
            } 
        
            if((checkIC.charAt(2) == '0' && checkIC.charAt(3) == '1') || (checkIC.charAt(3) == '3') || (checkIC.charAt(3) == '5') || (checkIC.charAt(3) == '7') || (checkIC.charAt(3) == '8') || (checkIC.charAt(3) == '0') || (checkIC.charAt(2) == '1' && checkIC.charAt(3) == '2')){
                if(checkIC.charAt(4) == '3'){
                    if(checkIC.charAt(5) != '1' && checkIC.charAt(5) != '0'){
                        error = true;
                    }
            }
            } else if((checkIC.charAt(2) == '1' && checkIC.charAt(2) == '3') || checkIC.charAt(3) == '4' || checkIC.charAt(3) == '6' && checkIC.charAt(3) == '9'){
                if(checkIC.charAt(4) == '3'){
                    if(checkIC.charAt(5) != '0'){
                        error = true;
                    }
                }
            }

            if(checkIC.charAt(7) != '0' && ((checkIC.charAt(7) == '1') && (checkIC.charAt(8) != '0')) && ((checkIC.charAt(7) == '1') && (checkIC.charAt(8) != '1')) && ((checkIC.charAt(7) == '1') && (checkIC.charAt(8) != '2')) && ((checkIC.charAt(7) == '1') && (checkIC.charAt(8) != '3')) && ((checkIC.charAt(7) == '1') && (checkIC.charAt(8) != '4'))){
                error = true;
            }

            if(countLetter > 0 || checkIC.charAt(6)!= dash || checkIC.charAt(9) != dash || countDash > 2 || countDash < 2){
                error = true;
            }
        
            } else {
                error = true;
            }
        
        return error;
    }
        
        public boolean checkNameFormat(String checkName){
        boolean error = false;
        int countDigit = 0;
        
        for(int i = 0; i < checkName.length(); i++){
            char check = checkName.charAt(i);
            if(Character.isDigit(check)){
                countDigit++;
            }
        }
        
        if(countDigit > 0){
            error = true;
        }
        
        return error;
    }
    
        public boolean checkPhoneNumberFormat(String checkPhoneNumber){
        boolean error = false;
        int countDigit = 0;
        int countLetter = 0;
        int countDash = 0;
        char dash = '-';
        
        for(int i = 0; i < checkPhoneNumber.length(); i++){
            char check = checkPhoneNumber.charAt(i);
            if(Character.isLetter(check)){
                countLetter++;
            } else if(Character.isDigit(check)){
                countDigit++;
            } else if(check == dash){
                countDash++;
            }
        }
        
        if(checkPhoneNumber.length() < 11 || checkPhoneNumber.length() > 12 || countLetter > 0 || countDash > 1 || countDash < 0 || checkPhoneNumber.charAt(3) != dash){
            error = true;
        }
        
        return error;
    }
    
        public boolean checkEmailFormat(String checkEmail){
        boolean error = false;
        int countAlian = 0;
        int countDot = 0;
        char alian = '@';
        char dot = '.';
        
        if(Character.isDigit(checkEmail.charAt(0))){
            error = true;
        }
        
        if(checkEmail.charAt(0) == alian || checkEmail.charAt(0) == dot){
            error = true;
        }
        
        for(int i = 0; i < checkEmail.length(); i++){
            char check = checkEmail.charAt(i);
            
            if(check == alian){
                countAlian++;
            }else if(check == dot){
                countDot++;
            }
        }     
        
        if(countAlian < 1 || countAlian > 1 || countDot < 1 || countDot > 1){
            error = true;
        }
        
        return error;
    }
    
        public boolean checkAddressFormat(String checkAddress){
        boolean error = false;
        int countDigit = 0;
        int countLetter = 0;
        
        for(int i = 0; i < checkAddress.length(); i++){
            char check = checkAddress.charAt(i);
            if(Character.isLetter(check)){
                countLetter++;
            } else if(Character.isDigit(check)){
                countDigit++;
            }
        }
        
        if(countDigit == 0 || countLetter == 0){
            error = true;
        }
        
        return error;
        
    }
        
        
    
}

