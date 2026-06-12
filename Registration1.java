/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.registration1;
import java.util.Scanner;
/**
 *
 * @author St10464247 Tumelo MAHAPE
 */
public class Registration1 {
    //Declarations 
 private static String firstName;
    private static String lastName;
    private static String userName;
    private static String cellPhoneNumber;
    private static String password;

    public static void setFirstName(String fname) {
        firstName = fname;
        
    }
    public static String getFirstName()           {
        return firstName;
    }
    
    public static void setLastname( String lname)              {
     lastName = lname;
     
    }
    
    public static String getLastname()           {
        return lastName;
    }
    
public static void setUserName(String username)                {
    userName = username;
    
}

public static String getUserName()                  {
    return userName;
}

public static void setCallNumber(String cellphonenumber)        {
    cellPhoneNumber = cellphonenumber;
    
}

public static String getCellNumber()                            {
    return cellPhoneNumber;
}

public static void setPassword(String passw0rd)                 {
    password = passw0rd;
}

public static String getPassword()                          {
    return password;
}

public static void main(String[] args)                          {
    
        try (Scanner input = new Scanner(System.in)) {
            Login login = new Login(firstName, lastName);
            
            System.out.println("*Registration*");
            
            System.out.print("please enter your first name: ");
            firstName = input.nextLine();
            
            System.out.print("please enter your last name: ");
            lastName = input.nextLine();
            
            System.out.print("please enter your username(less than 5 characters and must contain an underscore): ");
            userName = input.nextLine();
            
            System.out.print("please enter cellphone number with international country code (+27xxxxxxxxx):");
            cellPhoneNumber = input.nextLine();
            
            System.out.print("please create a password( at least 8 char, 1 Capital letter, 1 number, 1 special char):");
            password = input.nextLine();
            
            String regStatus = login.registerUser(userName, password, cellPhoneNumber, firstName, lastName);
            System.out.println(regStatus);
            
            if (regStatus.equals("Registration successful."))         {
                System.out.println("*Login*");
                
                System.out.print("Enter the UserName you created: ");
                String loginUser = input.nextLine();
                
                System.out.print("Enter the password you created: ");
                String loginPass = input.nextLine();
                
                String loginStatus = login.returnLoginStatus(loginUser, loginPass);
                System.out.println(loginStatus);
                
            }   }
}
}
    
    
