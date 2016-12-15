/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petcafe;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Rahaf
 */
public class DBManager {

   public static final String USER_NAME = "root"; // complete here
         public static final String PSSWORD = "raghad123"; // complete  here
         public static final String url = "jdbc:mysql://localhost:3306/pet_cafe";
    
    public static Connection getConnection(){
        Connection con = null;
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception ex){
            System.out.println("Exception :"+ex.getMessage());
        }
        
        try{
                // add missing parts here 
                
                con =  DriverManager.getConnection(url, USER_NAME, PSSWORD);
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return con;
    }
}
