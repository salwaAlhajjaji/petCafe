/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petcafe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author Rahaf
 */
public class Owner {

    protected String ownerID;
    protected String ownerName;
    protected String ownerMobile;
    protected Employee rEmplyeeId;
    Vector<Pet> myPets;

    /*public Employee employeeName;
    public Employee employeeID;
     */
    public Owner() {

    }

    public Owner(String name, String id, String mobile, Vector<Pet> pets) {
        this.ownerID = id;
        this.ownerName = name;
        this.ownerMobile = mobile;
        this.myPets = pets;
    }
    public Owner(String name,String mobile){
        this.ownerName=name;
        this.ownerMobile=mobile;
        
    }

    public void setOwnetId(String id){
        this.ownerID=id;
    }
    public void setOwnerName(String name) {
        this.ownerName = name;
    }

    public void setOwnerMobile(String mobile) {
        this.ownerMobile = mobile;
    }

    public void setMyPets(Vector<Pet> pets) {
        this.myPets = pets;
    }
    public void setREmolyeeId(Employee id){
        this.rEmplyeeId=id;
    }

    /*
    
    public void setEmployeeName(Employee name){
        this.employeeName = name;
    }
    public void setEmplyeeID(Employee id){
        this.employeeID = id;
    }
     */
    public String getOwnerName() {
        return this.ownerName;
    }

    public String getOwnerID() {
        return this.ownerID;
    }

    public String getOwnerMobile() {
        return this.ownerMobile;
    }
    public Employee getREmployeeId(){
        return this.rEmplyeeId;
    }

    public Vector<Pet> getMyPets() {
        return this.myPets;
    }

    public void addPet(Pet myPet) {// add to the list
        myPets.add(myPet);
    }
  
      public String setOwnerID(){
        Random randomNum = new Random();
        int maxId=500;
        int minId=1;
        int rangeOfId = maxId- minId + 1;
        int ownerId =  randomNum.nextInt(rangeOfId) + minId;
        this.ownerID = "CL0"+ownerId;
           return ownerID;     
    }

    public boolean isOwnerExist(String mobile) {
        boolean exist = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            con = DBManager.getConnection();
            String select = "SELECT `Owner_mobile` FROM `owner_info` WHERE `Owner_mobile` = ? ";
            ps = con.prepareStatement(select);
            ps.setString(1, mobile);
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

    public void saveOwnerInfo() {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBManager.getConnection();
            String insert = "INSERT INTO `owner_info` (`Owner_ID`,`Owner_name`,`Owner_mobile`)"
                    + "VALUES(?,?,?,?)";
            ps = con.prepareStatement(insert);
            ps.setString(1, this.setOwnerID());
            ps.setString(2, this.ownerName);
            ps.setString(3, this.ownerMobile);
           
            

            int result = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public void deletOwner(String name, String mobile) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            String delet = "DELET FROM `owner_info` WHERE `Owner_name`=? and `Owner_mobile`=?";
            ps = con.prepareStatement(delet);
            ps.setString(1, name);
            ps.setString(2, mobile);
            int result = ps.executeUpdate();

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

    }

    public void updateOwnerInfo() {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBManager.getConnection();
            String update = "UPDATE `owner_info` set `Owner_name`=? and `Owner_mobile`=?"
                    + "where `Owner_mobile`=?";
            ps = con.prepareStatement(update);
            ps.setString(1, this.ownerName);
            ps.setString(2, this.ownerMobile);
            ps.setString(3, this.ownerMobile);
            int result = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (con != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean isValidMobile(String mobile) {

        if (mobile.length() > 0 && mobile.length() < 13) {
            int firstIndex = 0;
            if (mobile.charAt(0) == '+' && mobile.charAt(1) == '9' && mobile.charAt(2) == '6' && mobile.charAt(3) == '6') {
                firstIndex = 4;
            }
            for (int i = firstIndex; i < mobile.length(); i++) {
                if (!Character.isDigit(mobile.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkClintName(String name) {//check tha name
        return this.ownerName.equals(name);
    }

}
