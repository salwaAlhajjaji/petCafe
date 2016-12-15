/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petcafe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author TOSHIBA
 */
public class Manger {
    protected String mangerId;
    protected String mangerName;
    protected String mangerSSN;
    public Manger(){
        
    }
    public Manger(String id,String name,String SSN){
        this.mangerId=id;
        this.mangerName=name;
        this.mangerSSN=SSN;
    }
     public Manger(String id,String name){
        this.mangerId=id;
        this.mangerName=name;
        
    }
     public void setID(String id){
         this.mangerId=id;
     }
     public void setName(String name){
         this.mangerName=name;
     }
     public void setSSN(String SSN){
         this.mangerSSN=SSN;
     }
     public String getID(){
         return this.mangerId;
     }
     public String getName(){
         return mangerName;
     }
     public String getSSN(){
         return this.mangerSSN;
     }
    public boolean isMangerExit(String id){
         boolean mangerExist = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            con = DBManager.getConnection();
            String select = "SELECT `Manger_name` FROM `manger_info` WHERE `Manger_ID`=? ";
            ps = con.prepareStatement(select);
            ps.setString(1,id);
            result = ps.executeQuery();
            if (result.first()) {
              
                mangerExist = true;
            } 

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return mangerExist;
    }
    public boolean checkSSN(String name) {
        boolean exist = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            con = DBManager.getConnection();
            String select = "SELECT `Manger_SSN` FROM `manger_info` WHERE `Manger_name`=?";
            ps = con.prepareStatement(select);
            ps.setString(1,name);
            
            result = ps.executeQuery();
            if (result.first()) {
              
                exist = true;
          
            } 

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return exist;
    
    }
   

    public boolean passworedviled(String name,String pass) {
       
        boolean exite=false;
        if(checkSSN(name)){
            exite=true;
        pass.equals("MA"+this.mangerSSN);
    }
        else{
           exite=false; 
        }
        return exite;
    }
}
